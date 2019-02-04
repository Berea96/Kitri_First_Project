package clientUi;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Chat.ReceiveMessage;
import vo.MemberVO;

public class AddTimeUI extends JFrame {

	private JPanel p1, p2, p3, p4;
	private JLabel l1, l2;
	private JButton b1, b2, b3, b4, b5, b6, b7, b8;
	private JTextField tf1, tf2;
	private JButton a, c;
	
	private MemberVO vo;
	private Socket s;
	private PrintWriter pw;
	private Scanner sis;
	
	private int time;
	
	public AddTimeUI(Socket s) {
		this.s = s;
		try {
			pw = new PrintWriter(s.getOutputStream());
			sis =new Scanner(s.getInputStream());
		} catch (IOException e1) {
		}
		
		setTitle("시간 충전");
		setLayout(null);
		p1 = new JPanel(new GridLayout(8, 0));
		p2 = new JPanel(new GridLayout(2, 0));
		p3 = new JPanel(new FlowLayout());
		p4 = new JPanel(new FlowLayout());
		l1 = new JLabel("시간을 선택하세요.");
		l2 = new JLabel("아이디 : ");
		b1 = new JButton("1시간 : 1000원");
		b2 = new JButton("2시간 : 2000원");
		b3 = new JButton("3시간 : 3000원");
		b4 = new JButton("4시간 : 4000원");
		b5 = new JButton("5시간 : 5000원");
		b6 = new JButton("13시간 : 10000원");
		b7 = new JButton("27시간 : 20000원");
		b8 = new JButton("41시간 : 30000원");
		tf1 = new JTextField(20);
		tf2 = new JTextField(15);
		
		tf1.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				char keyCode = e.getKeyChar();
				if(keyCode == KeyEvent.VK_ENTER) {
					new Thread(new Runnable() {
						
						public void run() {
							
							pw.println(6 + "-" + tf2.getText() + "-" + time + "-" + tf1.getText());
							pw.flush();
							
							String result = sis.nextLine();
							if(result.contains("부족")) {
								new ReceiveMessage(result);
							}
							else {
								new ReceiveMessage(result);
							}
						}
					}).start();
					dispose();
				}
			}
		});
		
		a = new JButton("충전");
		c = new JButton("뒤로가기");
		
		p1.setBounds(10, 10, 250, 200);
		p4.setBounds(5, 220, 250, 50);
		p2.setBounds(10, 271, 250, 50);
		p3.setBounds(-8, 341, 300, 40);
		
		
		p1.add(b1);
		p1.add(b2);
		p1.add(b3);
		p1.add(b4);
		p1.add(b5);
		p1.add(b6);
		p1.add(b7);
		p1.add(b8);
		
		p2.add(l1);
		p2.add(tf1);
		
		p4.add(l2);
		p4.add(tf2);
		p3.add(a);
		p3.add(c);
		
		
		add(p1);
		add(p2);
		add(p4);
		add(p3);
		
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				time = 60;
				l1.setText("입력시간 : " + time / 60 + "시간, 돈을 입력해주세요.");
			}
		});
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				time = 120;
				l1.setText("입력시간 : " + time / 60 + "시간, 돈을 입력해주세요.");
			}
		});
		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				time = 180;
				l1.setText("입력시간 : " + time / 60 + "시간, 돈을 입력해주세요.");
			}
		});
		b4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				time = 240;
				l1.setText("입력시간 : " + time / 60 + "시간, 돈을 입력해주세요.");
			}
		});
		b5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				time = 300;
				l1.setText("입력시간 : " + time / 60 + "시간, 돈을 입력해주세요.");
			}
		});
		b6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				time = 780;
				l1.setText("입력시간 : " + time / 60 + ", 돈을 입력해주세요.");
			}
		});
		b7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				time = 1620;
				l1.setText("입력시간 : " + time / 60 + "시간, 돈을 입력해주세요.");
			}
		});
		b8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				time = 2460;
				l1.setText("입력시간 : " + time / 60 + "시간, 돈을 입력해주세요.");
			}
		});
		a.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Thread(new Runnable() {
					
					public void run() {
						
						pw.println(6 + "-" + tf2.getText() + "-" + time + "-" + tf1.getText());
						pw.flush();
						
						String result = sis.nextLine();
						if(result.contains("부족")) {
							new ReceiveMessage(result);
						}
						else {
							new ReceiveMessage(result);
						}
					}
				}).start();
				dispose();
			}
		});
		c.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		setBounds(700, 400, 290, 440);
		setVisible(true);
	}
	
	public AddTimeUI(MemberVO vo, Socket s) {
		this.s = s;
		this.vo = vo;
		
		try {
			pw = new PrintWriter(s.getOutputStream());
			sis =new Scanner(s.getInputStream());
		} catch (IOException e1) {
		}
		
		setTitle("시간 충전");
		setLayout(null);
		p1 = new JPanel(new GridLayout(8, 0));
		p2 = new JPanel(new GridLayout(2, 0));
		p3 = new JPanel(new FlowLayout());
		p4 = new JPanel(new FlowLayout());
		l1 = new JLabel("시간을 선택하세요.");
		b1 = new JButton("1시간 : 1000원");
		b2 = new JButton("2시간 : 2000원");
		b3 = new JButton("3시간 : 3000원");
		b4 = new JButton("4시간 : 4000원");
		b5 = new JButton("5시간 : 5000원");
		b6 = new JButton("13시간 : 10000원");
		b7 = new JButton("27시간 : 20000원");
		b8 = new JButton("41시간 : 30000원");
		tf1 = new JTextField(20);
		
		tf1.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				char keyCode = e.getKeyChar();
				if(keyCode == KeyEvent.VK_ENTER) {
					actionPerformed(null);
				}
			}
		});
		
		a = new JButton("충전");
		c = new JButton("뒤로가기");
		
		p1.setBounds(10, 10, 250, 200);
		p2.setBounds(10, 210, 250, 50);
		p3.setBounds(-8, 301, 300, 50);
		
		p1.add(b1);
		p1.add(b2);
		p1.add(b3);
		p1.add(b4);
		p1.add(b5);
		p1.add(b6);
		p1.add(b7);
		p1.add(b8);
		
		tf1.setSize(90, 10);
		
		p2.add(l1);
		p2.add(tf1);
		
		p3.add(a);
		p3.add(c);
		
		add(p1);
		add(p2);
		add(p3);
		
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				time = 60;
				l1.setText("입력시간 : " + time / 60 + "시간, 돈을 입력해주세요.");
			}
		});
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				time = 120;
				l1.setText("입력시간 : " + time / 60 + "시간, 돈을 입력해주세요.");
			}
		});
		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				time = 180;
				l1.setText("입력시간 : " + time / 60 + "시간, 돈을 입력해주세요.");
			}
		});
		b4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				time = 240;
				l1.setText("입력시간 : " + time / 60 + "시간, 돈을 입력해주세요.");
			}
		});
		b5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				time = 300;
				l1.setText("입력시간 : " + time / 60 + "시간, 돈을 입력해주세요.");
			}
		});
		b6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				time = 780;
				l1.setText("입력시간 : " + time / 60 + ", 돈을 입력해주세요.");
			}
		});
		b7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				time = 1620;
				l1.setText("입력시간 : " + time / 60 + "시간, 돈을 입력해주세요.");
			}
		});
		b8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				time = 2460;
				l1.setText("입력시간 : " + time / 60 + "시간, 돈을 입력해주세요.");
			}
		});
		a.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Thread(new Runnable() {
					
					public void run() {
//						System.out.println("6" + vo.getId() +  time + tf1.getText());
						
						System.out.println(vo.getId());
						System.out.println(time);
//						System.out.println(tf1.getText());
						int money = Integer.parseInt(tf1.getText());
						
						pw.println(6 + "-" + vo.getId() + "-" + time + "-" + money);
						pw.flush();
						
						String result = sis.nextLine();
						if(result.contains("부족")) {
//							System.out.println(result);
							new ReceiveMessage(result);
						}
						else {
							new ReceiveMessage(result);
						}
					}
				}).start();
				dispose();
			}
		});
		c.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		setBounds(700, 400, 290, 400);
		setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		new Thread(new Runnable() {
			
			public void run() {
//				System.out.println("6" + vo.getId() +  time + tf1.getText());
				
//				System.out.println(vo.getId());
//				System.out.println(time);
//				System.out.println(tf1.getText());
				int money = Integer.parseInt(tf1.getText());
				
				pw.println(6 + "-" + vo.getId() + "-" + time + "-" + money);
				pw.flush();
				
				String result = sis.nextLine();
				if(result.contains("부족")) {
//					System.out.println(result);
					new ReceiveMessage(result);
				}
				else {
					new ReceiveMessage(result);
				}
			}
		}).start();
		dispose();
	}
}
