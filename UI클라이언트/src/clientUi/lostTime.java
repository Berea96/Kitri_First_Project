package clientUi;
import java.awt.Color;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;

import Chat.ReceiveMessage;
import vo.MemberVO;

//남은시간을 조회하는 클래스
public class lostTime extends JFrame implements Runnable{
	
	//프레임을 사용하였다.
	private Socket s = null;
	private boolean isStop = true;
	private PrintWriter pw = null;
	private Scanner sis = null;
	private MemberVO vo = null;
	private JLabel lbl;
	private int time;
	private int use;
	
	//생성자로 로그인한 회원의 아이디, 남은시간을 받아오고
	//위의 변수에 값을 전달
	public lostTime(MemberVO vo) {
		this.vo = vo;
	}

	public int getTime() {
		
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}

	//쓰레드로 작동 시킬 것을 안에 작성한다.
	public void run() {
		while (true) {
			shutdownInfo();
			time();
			
			if(use == 0) {
				dispose();
				isStop = true;
				if(isStop) {
					try {
//						System.out.println("5초 후 컴퓨터가 종료됩니다.");
						new ReceiveMessage("5초 후 컴퓨터가 종료됩니다.");
						Thread.sleep(5000);
						pw.println(2 + "-" + vo.getId() + "-" + vo.getPw() + "-" + vo.getUse());
						pw.flush();
						System.out.println("===============");
						System.out.println(sis.nextLine());
						System.out.println("컴퓨터를 강제 종료합니다.");
						System.out.println("===============");
						
						try {
							pw.close();
							sis.close();
							s.close();
						} catch (IOException e) {
						}
						System.exit(0);
					} catch (InterruptedException e) {
					}
				}
				
				break;
			}
			if (time == 0) {
				dispose();
				isStop = true;
				if(isStop) {
					pw.println(2 + "-" + vo.getId() + "-" + vo.getPw() + "-" + vo.getUse());
					pw.flush();
					System.out.println("===============");
					System.out.println(sis.nextLine());
					System.out.println("컴퓨터를 강제 종료합니다.");
					System.out.println("===============");
					
					try {
						pw.close();
						sis.close();
						s.close();
					} catch (IOException e) {
					}
					System.exit(0);
				}
				break;
			}
			try {
				Thread.sleep(1000); 
			} catch (Exception e) {}
		}
	}
	
	//dao에서 시간감소 메소드를 실행해 id값을 전달하고
	//또 리턴값으로 남은 시간을 받아와
	//위의 쓰레드로 1초마다 감소시키게 함
	private void time() {
		
		pw.println(1 + "-" + vo.getId());
		pw.flush();
		
		time = Integer.parseInt(sis.nextLine());
		
		if(time >= 60) {
			lbl.setText("남은 시간 : " + time / 60 + "시간" + time % 60 + "분");
		}
		else if(time == 10) {
//			System.out.println("남은 시간 10분 남았습니다.");
			new ReceiveMessage("남은 시간 10분 남았습니다.");
			lbl.setText("남은 시간 : " + time + "분");
		}
		else if(time == 5) {
//			System.out.println("곧 컴퓨터가 종료됩니다.");
			new ReceiveMessage("곧 컴퓨터가 종료됩니다.");
			lbl.setText("남은 시간 : " + time + "분");
		}
		else if(time > 0) {
			lbl.setText("남은 시간 : " + time + "분");
		}
	}
	
	public void start() {
		lbl = new JLabel("남은 시간", JLabel.CENTER); 
		add("Center", lbl); 
		
		setTitle(vo.getId() + "님의 남은시간");
		setBounds(1577, 200, 350, 100); 
//		getContentPane().setBackground(Color.GRAY);
		
		setAlwaysOnTop(true);
//		setUndecorated(true); //타이틀 바 없애기
		setVisible(true); 
		
		try {
			s = new Socket("localhost", 50003);
			pw = new PrintWriter(s.getOutputStream());
			sis = new Scanner(s.getInputStream());
		} catch (IOException e) {
		}
		
		new Thread(this).start();
	}
	
	public void shutdownInfo() {
		pw.println(3 + "-" + vo.getId());
		pw.flush();
		
		use = Integer.parseInt(sis.nextLine());
	}
//	public static void main(String[] args) {
//		new lostTime("3").start();
//	}
}
