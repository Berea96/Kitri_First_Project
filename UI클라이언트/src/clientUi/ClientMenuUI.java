package clientUi;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Chat.MultiChattingClient;
import client.ClientLogin;
import vo.MemberVO;

public class ClientMenuUI extends JFrame{
	
	private JPanel pboss;
	private JPanel background;
	private ImageIcon is;
	private JPanel p1, p2, p3, p4, p5, p6, p7;
	private JButton b1, b2, b3, b4, b5, b6;
	private JLabel l1;
	
	private Socket s;
	private PrintWriter pw;
	private Scanner sis;
	
	private String id;
	private String pw1;
	private int useNo;
	private MemberVO vo;
	
	private String result;

	public ClientMenuUI(MemberVO vo, Socket s){
		this.s = s;
		this.vo = vo;
		this.id = vo.getId();
		this.pw1 = vo.getPw();
		this.useNo = vo.getUse();
		
		try {
			pw = new PrintWriter(s.getOutputStream());
			sis = new Scanner(s.getInputStream());
		} catch (IOException e) {
		}
		
		pboss = new JPanel();
		
		is = new ImageIcon("c:\\use5.png");
		background = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(is.getImage(), 0, 0, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		
		p1 = new JPanel(new FlowLayout());
		p2 = new JPanel(new FlowLayout());
		p3 = new JPanel(new FlowLayout());
		p4 = new JPanel(new FlowLayout());
		p5 = new JPanel(new FlowLayout());
		p6 = new JPanel(new FlowLayout());
		p7 = new JPanel(new FlowLayout());
		
		b1 = new JButton("게임하기");
		b2 = new JButton("음식주문");
		b3 = new JButton("시간추가");
		b4 = new JButton("카운터문의");
		b5 = new JButton("쪽지");
		b6 = new JButton("로그아웃");
		
		l1 = new JLabel("결과");
		
		setTitle("메뉴");
		setLayout(null);
	}

	public void start() throws IOException {
		
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				l1.setText("게임중...");
			}
		});
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				pw.println(0 + "-food");
//				pw.flush();
//				
//				String[] foodList = sis.nextLine().split(", ");
				
				new Thread(new Runnable() {
					public void run() {
//						new OrderFoodUI(s, foodList, id);
						new OrderFoodUI(s, id);
					}
					}).start();
				
//				String result2 = new ClientLogin().orderFood(foodList);
				
				
//				new Thread(new Runnable() {
//					public void run() {
//						
//						pw.println(result2 + "-" + vo.getId());
//						pw.flush();
//						
//						String result = sis.nextLine();
//						if(result.contains("부족")) {
////							System.out.println(result);
//							new ReceiveMessage(result);
//						}
//						else {
//							
//							new ReceiveMessage(result);
//						}
//					}
//				}).start();
			}
		});
		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				String result2 = new Client().timePlus(vo);
				
				
//				new Thread(new Runnable() {
//					
//					public void run() {
//						
//						pw.println(6 + "-" + result2);
//						pw.flush();
//						
//						String result = sis.nextLine();
//						if(result.contains("부족")) {
//							System.out.println(result);
//						}
//						else {
//							new ReceiveMessage(result);
//						}
//					}
//				}).start();
				
				new AddTimeUI(vo, s);
			}
		});
		b4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Thread(new Runnable() {
					public void run() {
						new MultiChattingClient(useNo + "번 pc").start();
					}
				}).start();
			}
		});
		b5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				result = new ClientLogin().sendMessage();
//				
//				pw.println(8 + "-" + vo.getUse() + "-" + result);
//				pw.flush();
				
				new SendMessageUI(vo, s).start();
			}
		});
		b6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				result = new ClientLogin().logout(vo);
				
				pw.println(9 + "-" + id + "-" + pw1 + "-" + useNo);
				pw.flush();
				System.out.println("===============");
				System.out.println(sis.nextLine());
				System.out.println("컴퓨터를 종료합니다.");
				System.out.println("===============");
				
				try {
					pw.close();
					sis.close();
					s.close();
				} catch (IOException e) {
				}
				System.exit(0);
			}
		});
//		b1.setPreferredSize(new Dimension(160, 100));
		b1.setPreferredSize(new Dimension(200, 40));
		b2.setPreferredSize(new Dimension(200, 40));
		b3.setPreferredSize(new Dimension(200, 40));
		b4.setPreferredSize(new Dimension(200, 40));
		b5.setPreferredSize(new Dimension(200, 40));
		b6.setPreferredSize(new Dimension(200, 40));
		l1.setPreferredSize(new Dimension(200, 40));
		
		p1.add(b1);
		p2.add(b2);
		p3.add(b3);
		p4.add(b4);
		p5.add(b5);
		p6.add(b6);
		p7.add(l1);
		
//		p1.setBounds(400, 600, 160, 110);
		p1.setBounds(16, 1, 200, 50);
		p2.setBounds(16, 51, 200, 50);
		p3.setBounds(16, 101, 200, 50);
		p4.setBounds(16, 151, 200, 50);
		p5.setBounds(16, 201, 200, 50);
		p6.setBounds(16, 251, 200, 50);
		p7.setBounds(16, 301, 200, 50);
		
		add(p1);
//		add(p2);
//		add(p3);
//		add(p4);
//		add(p5);
//		add(p6);
//		add(p7);
		
		pboss.setSize(300, 500);
		pboss.setLocation(1200, 90);
		
		pboss.add(p1);
		pboss.add(p2);
		pboss.add(p3);
		pboss.add(p4);
		pboss.add(p5);
		pboss.add(p6);
		pboss.add(p7);
		background.setBounds(10, 10, 1200, 580);
		
		add(background);
		add(pboss);
		
		setSize(1500, 800);
		
		setLocation(300, 200);
		
//		setAlwaysOnTop(true);
		setVisible(true);
	}
}
