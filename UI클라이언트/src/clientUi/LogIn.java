package clientUi;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Chat.ReceiveMessage;
import client.Client;
import vo.MemberVO;

public class LogIn extends JFrame implements ActionListener {

	private int useNo;
	private JFrame frame;
	private String id;
	private String pw1;
	private JPanel pboss;
	private JPanel pis;
	private JPanel bottom;
	private JLabel l1, l2, l3, l4;
	private JTextField tf1, tf2, tf3;
	private JPanel p1, p2, p3, p4, p5, p6;
	private JButton b1, b2, b3;
	private ImageIcon is, is2;
	
//	private MemberVO vo;
	private int buttonNo = 0;
	private int result2;
	private String[] empty;
	
	EmptyComputer emc;
	
	Socket s = null;
	PrintWriter pw2 = null;
	Scanner sis = null;
	Scanner output = null;
	String result = "";
	
	public LogIn() throws HeadlessException, IOException {
		
		s = new Socket("127.0.0.1", 50001);
		pw2 = new PrintWriter(s.getOutputStream());
		sis = new Scanner(s.getInputStream());
		output = new Scanner(System.in);
		result = "";
		
		frame = new JFrame("kitri PC방");
		frame.setBounds(300, 200, 1500, 800);
		
		pboss = new JPanel(null);
		
		l1 = new JLabel("자리 번호");
		l2 = new JLabel("아이디 ");
		l3 = new JLabel("비밀번호");
		l4 = new JLabel("결과");
		tf1 = new JTextField(10);
		tf2 = new JTextField(11);
		tf3 = new JPasswordField(10);
		p1 = new JPanel(new FlowLayout());
		p2 = new JPanel(new FlowLayout());
		p3 = new JPanel(new FlowLayout());
		p4 = new JPanel(new FlowLayout());
		p5 = new JPanel();
		p6 = new JPanel();
		b1 = new JButton("로그인\n");
		b2 = new JButton("회원가입");
		b3 = new JButton("시간추가");
		
		pw2.println(2 + "-");
		pw2.flush();
		
		empty = sis.nextLine().split(", ");
		emc = new EmptyComputer(empty);
		
		try {
			int buttonNo = emc.start();
			tf1.setText(String.valueOf(buttonNo));
			
		} catch (HeadlessException e1) {
		}
		
		is = new ImageIcon("c:\\use2.jpg");
		is2 = new ImageIcon("c:\\use3.png");
		
		pis = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(is.getImage(), 0, 0, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		bottom = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(is2.getImage(), 0, 0, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				while(true) {
					try {
					useNo = Integer.parseInt(tf1.getText());
					if(useNo > 50) {
						new ReceiveMessage("잘못된 번호입니다.");
						l4.setText("잘못된 번호입니다.");
						tf1.setText("");
						tf2.setText("");
						tf3.setText("");
//						dispose();
//						start();
//						break;
					}
					else if(empty[useNo - 1].contains("사용 중")) {
						new ReceiveMessage("사용중인 컴퓨터입니다");
						l4.setText("사용중인 컴퓨터입니다");
						tf1.setText("");
						tf2.setText("");
						tf3.setText("");
//						dispose();
//						start();
//						break;
					}
					else {
						id = tf2.getText();
						pw1 = tf3.getText();
						l4.setText(useNo + " : " + id + " : " + pw1);
						tf1.setText("");
						tf2.setText("");
						tf3.setText("");
						
						pw2.println(2 + "-" + id + "-" + pw1 + "-" + useNo);
						pw2.flush();
						
						l4.setText(sis.nextLine());
						emc.dispose();
						int logIn = Integer.parseInt(l4.getText());
						
						MemberVO vo = new MemberVO(id, pw1, useNo);
						
						result = new Client().loginSuccess(logIn, vo);
						
						String[] loginInfo = result.split("-");
						if(loginInfo[0].equals("1")) {
							
							dispose();
							frame.dispose();
							
//							pw2.println(0 + "-" + vo.getId());
//							pw2.flush();
//							
//							int time = Integer.parseInt(sis.nextLine());
							lostTime lt = new lostTime(vo);
							new Thread(new Runnable() {
								public void run() {
									lt.start();
								}
							}).start();
							
							try {
								new ClientMenuUI(vo, s).start();
							} catch (IOException e1) {
							}
							
						}
						else if(loginInfo[0].equals("3")) {
//							result = new Client().timePlus(vo);
//							pw2.println(loginInfo[0] + "-" + result);
//							pw2.flush();
//							System.out.println(sis.nextLine());
							tf2.setText("");
							tf3.setText("");
							new AddTimeUI(vo, s);
						}
//						break;
					}
					} catch(NumberFormatException ne) {
						l4.setText("자리번호는 숫자만 입력가능합니다.");
						tf2.setText("");
						tf3.setText("");
//						dispose();
//						start();
//						break;
					}
				}
//				vo = new MemberVO(id, pw1, Integer.parseInt(useNo));
				
//			}
		});
		
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();

				try {
					new SignUp(s).start();
				} catch (HeadlessException e1) {
				} catch (IOException e1) {
				}
				
//				vo = new MemberVO(id, pw, Integer.parseInt(useNo));
			}
		});
		
		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				result = new Client().timePlus();
//				
//				pw2.println(3 + "-" + result);
//				pw2.flush();				
//				System.out.println(sis.nextLine());
				new AddTimeUI(s);
			}
		});
	}
	
	public void start() {
		
		setTitle("로그인");
		setLayout(new GridLayout(0, 1));
		frame.setLayout(null);
		pboss.setLayout(new GridLayout(0, 1));
		
		tf3.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				char keyCode = e.getKeyChar();
				if(keyCode == KeyEvent.VK_ENTER) {
					actionPerformed(null);
				}
			}
		});
		
		
//		System.out.println("=========컴퓨터 목록=========");
//		for(int i = 0; i < empty.length - 1; i++) {
//			System.out.print(empty[i] + "	");
//			if((i + 1) % 5 == 0) {
//				System.out.println();
//			}
//		}
//		System.out.println("=====================");
		
		
//		new EmptyComputer(empty).start();
		
		b1.setPreferredSize(new Dimension(140, 150));
		
		l1.setBackground(Color.GRAY);
		l2.setBackground(Color.GRAY);
		l3.setBackground(Color.GRAY);
		
		p1.add(l1);
		p1.add(tf1);
		p2.add(l2);
		p2.add(tf2);
		p3.add(l3);
		p3.add(tf3);
//		p4.add(b1);
		p4.add(b2);
		p4.add(b3);
		p5.add(b1);
		
		p6.add(l4);
		
		add(p1);
		add(p2);
		add(p3);
		add(p4);
//		add(p6);
//		add(p5);
		
		pis.setBackground(Color.gray);
		bottom.setBackground(Color.LIGHT_GRAY);
		
		pboss.add(p1);
		pboss.add(p2);
		pboss.add(p3);
		pboss.add(p4);
		pboss.add(p5);
		pboss.setSize(250, 150);
		p5.setSize(300, 300);
		pis.setSize(1460, 565);
		bottom.setSize(600, 170);
//		p6.setSize(100, 100);
		
		
		pboss.setLocation(1030, 580);
		p5.setLocation(1240, 580);
		pis.setLocation(10, 10);
		bottom.setLocation(10, 580);
//		p6.setLocation(950, 581);
		
		p5.setVisible(true);
		frame.setBackground(Color.blue);
		frame.add(pboss);
		frame.add(pis);
		frame.add(p5);
		frame.add(bottom);
//		frame.add(p6);
	
		frame.setVisible(true);
		
		
		setLocation(700, 400);
		
		setAlwaysOnTop(true);
//		setVisible(true);
//		pack();
	}
	
	public void actionPerformed(ActionEvent e) {
//		while(true) {
			try {
			useNo = Integer.parseInt(tf1.getText());
			if(useNo > 50) {
				new ReceiveMessage("잘못된 번호입니다.");
				l4.setText("잘못된 번호입니다.");
				tf1.setText("");
				tf2.setText("");
				tf3.setText("");
//				dispose();
//				start();
//				break;
			}
			else if(empty[useNo - 1].contains("사용 중")) {
				new ReceiveMessage("사용중인 컴퓨터입니다");
				l4.setText("사용중인 컴퓨터입니다");
				tf1.setText("");
				tf2.setText("");
				tf3.setText("");
//				dispose();
//				start();
//				break;
			}
			else {
				id = tf2.getText();
				pw1 = tf3.getText();
				l4.setText(useNo + " : " + id + " : " + pw1);
				tf1.setText("");
				tf2.setText("");
				tf3.setText("");
				
				pw2.println(2 + "-" + id + "-" + pw1 + "-" + useNo);
				pw2.flush();
				
				l4.setText(sis.nextLine());
				int logIn = Integer.parseInt(l4.getText());
				
				MemberVO vo = new MemberVO(id, pw1, useNo);
				
				result = new Client().loginSuccess(logIn, vo);
				
				String[] loginInfo = result.split("-");
				if(loginInfo[0].equals("1")) {
					emc.dispose();
					
					dispose();
					frame.dispose();
					
//					pw2.println(0 + "-" + vo.getId());
//					pw2.flush();
//					
//					int time = Integer.parseInt(sis.nextLine());
					lostTime lt = new lostTime(vo);
					new Thread(new Runnable() {
						public void run() {
							lt.start();
						}
					}).start();
					
					try {
						new ClientMenuUI(vo, s).start();
					} catch (IOException e1) {
					}
					
				}
				else if(loginInfo[0].equals("3")) {
					tf2.setText("");
					tf3.setText("");
					new AddTimeUI(vo, s);
				}
//				break;
			}
			} catch(NumberFormatException ne) {
				l4.setText("자리번호는 숫자만 입력가능합니다.");
				tf2.setText("");
				tf3.setText("");
//				dispose();
//				start();
//				break;
			}
		}
//	}
	
	public void setText(String no) {
		tf1.setText(no);
	}
	
	class empty {
		
		JFrame f1;
		JButton b1, b2, b3, b4, b5;
		JPanel p1, p2, p3, p4, p5, p6, p7, p8, p9, p10;
		JPanel[] p;
		JButton[] b;
		
		String[] empty;
		
		JLabel l1, l2, l3, l4, l5;
		
		empty(String[] empty){
		
		f1 = new JFrame("공석");
		f1.setLayout(new GridLayout(10, 0));
		b1 = new JButton(empty[0]);
		b2 = new JButton(empty[1]);
		b3 = new JButton(empty[2]);
		b4 = new JButton(empty[3]);
		b5 = new JButton(empty[4]);
		l1 = new JLabel(empty[5]);
		l2 = new JLabel(empty[6]);
		l3 = new JLabel(empty[7]);
		l4 = new JLabel(empty[8]);
		l5 = new JLabel(empty[9]);
		
		p = new JPanel[10];
		b = new JButton[50];
		
		
		for(int i = 0; i < p.length; i++) {
			p[i] = new JPanel(new GridLayout(0, 5));
		}
		
		for(int i = 0; i < b.length; i++) {
			b[i] = new JButton(empty[i]);
			
			int n = i;
			
			b[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					f1.dispose();
					tf1.setText(String.valueOf(n));
					dispose();
				}
			});
		}
		
		b[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				f1.dispose();
				dispose();
			}
		});
		
		p1 = new JPanel(new GridLayout(0, 5));
		p2 = new JPanel(new GridLayout(0, 5));
		}
		
		public void start() {
			
			int cnt = 0;
			for(int i = 0; i < p.length; i++) {
				for(int j = 0; j < 5; j++) {
					p[i].add(b[j + cnt]);
				}
				cnt += 5;
			}
			
			f1.add(p[0]);
			f1.add(p[1]);
			f1.add(p[2]);
			f1.add(p[3]);
			f1.add(p[3]);
			f1.add(p[4]);
			f1.add(p[5]);
			f1.add(p[6]);
			f1.add(p[7]);
			f1.add(p[8]);
			f1.add(p[9]);
			
			f1.setBounds(100, 100, 800, 500);
			f1.setAlwaysOnTop(true);
			f1.setVisible(true);
		}
	}
	
	public void print() {
		System.out.println(useNo + " : " + id + " : " + pw1);
	}

//	public static void main(String[] args) {
//		try {
//			new LogIn().start();
//		} catch (HeadlessException e) {
//		} catch (IOException e) {
//		}
//	}
}
