package adminUi;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Chat.ReceiveMessage;

public class SalesViewUI extends JFrame {
	
	private JPanel p1, p2;
	private JButton b1, b2, b3, b4;
	
	private Socket s;
	private PrintWriter pw;
	private Scanner sis;
	
	private String result = "";
	
	public SalesViewUI(Socket s, PrintWriter pw, Scanner sis) {
		this.s = s;
		this.pw = pw;
		this.sis = sis;
		
		setTitle("매출조회 메뉴");
		setLayout(new GridLayout(1, 0));
		
		p1 = new JPanel(new GridLayout(4, 0));
		p2 = new JPanel();
		
		b1 = new JButton("현재 누적 매출");
		b2 = new JButton("조회 기간 별 매출");
		b3 = new JButton("타입별매출");
		b4 = new JButton("돌아가기");
		
	}
	
	public void start() {
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SalesViewUI(s, pw, sis).currentSales();
			}
		});
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SalesViewUI(s, pw, sis).salesSearch();
			}
		});
		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SalesViewUI(s, pw, sis).foodTypeSales();
			}
		});
		b4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new AdminMenuUI(s, pw, sis).start();
			}
		});
		
		p1.add(b1);
		p1.add(b2);
		p1.add(b3);
		p1.add(b4);
		
		add(p1);
		
		setBounds(500, 400, 400, 300);
		setVisible(true);
		
	}
	
	public void currentSales() {
		pw.println(7);
		pw.flush();
		
		result = sis.nextLine();
		if(result.equals("")) {
//			System.out.println("ㅠㅠ 매출이 없습니다...");
			new ReceiveMessage("ㅠㅠ 매출이 없습니다...");
		}
		else {
			new ReceiveMessage("현재까지 누적 매출 : " + result);
			
//			System.out.println(">>" + result + "<<");
		}
	}
	
	public void salesSearch() {
		
		JFrame f1;
		JPanel p1, p2, p3;
		JTextField tf1, tf2;
		JLabel l1, l2;
		JButton b1, b2;
		
		f1 = new JFrame("조회 기간별 매출 조회");
		f1.setLayout(new GridLayout(3, 0));
		
		p1 = new JPanel(new FlowLayout());
		p2 = new JPanel(new FlowLayout());
		p3 = new JPanel(new FlowLayout());
		
		tf1 = new JTextField(10);
		tf2 = new JTextField(10);
		
		tf2.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				char keyCode = e.getKeyChar();
				if(keyCode == KeyEvent.VK_ENTER) {
					String start = tf1.getText();
					String end = tf2.getText();
					
					pw.println(8 + "-" + start + "-" + end);
					pw.flush();
					
					result = sis.nextLine();
					
					if(result.equals("")) {
						f1.dispose();
//						System.out.println("ㅠㅠ매출이 없습니다...");
						new ReceiveMessage("ㅠㅠ 매출이 없습니다...");
					}
					else if(result.equals("0")) {
//						System.out.println("날짜를 잘못 입력하셨습니다.");
						tf1.setText("");
						tf2.setText("");
						new ReceiveMessage("날짜를 잘못 입력하셨습니다.");
					}
					else {
						f1.dispose();
//						System.out.println(start + " 부터 " + end + "까지의 매출");
//						System.out.println(">>" + result + "<<");
						new ReceiveMessage("조건 매출," + start + "," + end + "," + result);
					}
				}
			}
		});
		
		l1 = new JLabel("시작일(yymmdd) : ");
		l2 = new JLabel("최종일(yymmdd) : ");
		
		b1 = new JButton("조회");
		b2 = new JButton("닫기");
		
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String start = tf1.getText();
				String end = tf2.getText();
				
				pw.println(8 + "-" + start + "-" + end);
				pw.flush();
				
				result = sis.nextLine();
				
				if(result.equals("")) {
					f1.dispose();
//					System.out.println("ㅠㅠ매출이 없습니다...");
					new ReceiveMessage("ㅠㅠ 매출이 없습니다...");
				}
				else if(result.equals("0")) {
//					System.out.println("날짜를 잘못 입력하셨습니다.");
					tf1.setText("");
					tf2.setText("");
					new ReceiveMessage("날짜를 잘못 입력하셨습니다.");
				}
				else {
					f1.dispose();
//					System.out.println(start + " 부터 " + end + "까지의 매출");
//					System.out.println(">>" + result + "<<");
					new ReceiveMessage("조건 매출," + start + "," + end + "," + result);
				}
			}
			
		});
		
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				f1.dispose();
			}
		});
		
		p1.add(l1);
		p1.add(tf1);
		p2.add(l2);
		p2.add(tf2);
		p3.add(b1);
		p3.add(b2);
		
		f1.add(p1);
		f1.add(p2);
		f1.add(p3);
		
		f1.setBounds(700, 400, 200, 200);
		
		f1.setVisible(true);
	}
	
	public void foodTypeSales() {
		pw.println(9);
		pw.flush();
		
		result = sis.nextLine();
		if(result.equals("")) {
//			System.out.println("ㅠㅠ 매출이 없습니다...");
			new ReceiveMessage("ㅠㅠ 매출이 없습니다...");
		}
		else {
			System.out.println("타입별 매출");
			new ReceiveMessage("타입," + result);
			
//			System.out.println(">>" + result + "<<");
		}
	}
}
