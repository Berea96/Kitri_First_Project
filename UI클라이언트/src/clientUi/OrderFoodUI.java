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

public class OrderFoodUI extends JFrame {
	
	private JPanel p1, p2, p3, p4;
	private JLabel l1, l2;
	private JButton b1, b2, b3, b4, b5, b6, b7, b8;
	private JTextField tf1, tf2;
	private JButton a, c;
	
	private Socket s;
	private PrintWriter pw;
	private Scanner sis;
	
	private String[] foodList;
	private String id;
	private int no;
	private int pcs;
	
	public OrderFoodUI(Socket s, String[] foodList, String id) {
		this.s = s;
		this.id = id;
		this.foodList = foodList;
		try {
			pw = new PrintWriter(s.getOutputStream());
			sis =new Scanner(s.getInputStream());
		} catch (IOException e1) {
		}
		
		setTitle("음식 주문");
		setLayout(null);
		p1 = new JPanel(new GridLayout(8, 0));
		p2 = new JPanel(new GridLayout(2, 0));
		p3 = new JPanel(new FlowLayout());
		p4 = new JPanel(new FlowLayout());
		l1 = new JLabel("음식을 선택하세요.");
		l2 = new JLabel("개수 : ");
		b1 = new JButton(foodList[0]);
		b2 = new JButton(foodList[1]);
		b3 = new JButton(foodList[2]);
		b4 = new JButton(foodList[3]);
		b5 = new JButton(foodList[4]);
		b6 = new JButton(foodList[5]);
		b7 = new JButton(foodList[6]);
		b8 = new JButton(foodList[7]);
		tf1 = new JTextField(10);
		tf2 = new JTextField(10);
		
		tf2.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				char keyCode = e.getKeyChar();
				if(keyCode == KeyEvent.VK_ENTER) {
					actionPerformed(null);
				}
			}
		});
		
		a = new JButton("주문");
		c = new JButton("뒤로가기");
		
		p1.setBounds(23, 10, 300, 200);
		p2.setBounds(10, 221, 340, 30);
		p4.setBounds(10, 250, 300, 30);
		
		p3.setBounds(5, 301, 340, 40);
		
		p1.add(b1);
		p1.add(b2);
		p1.add(b3);
		p1.add(b4);
		p1.add(b5);
		p1.add(b6);
		p1.add(b7);
		p1.add(b8);
		
		p2.add(l1);
//		p2.add(tf1);
		
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
				no = 1;
				l1.setText("입력번호 : " + no + "번 너구리, 개수를 입력해주세요.");
			}
		});
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				no = 2;
				l1.setText("입력번호 : " + no + "번 신라면, 개수를 입력해주세요.");
			}
		});
		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				no = 3;
				l1.setText("입력번호 : " + no + "번 치킨마요 덮밥, 개수를 입력해주세요.");
			}
		});
		b4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				no = 4;
				l1.setText("입력번호 : " + no + "번 김치 볶음밥, 개수를 입력해주세요.");
			}
		});
		b5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				no = 5;
				l1.setText("입력번호 : " + no + "번 떢볶이, 개수를 입력해주세요.");
			}
		});
		b6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				no = 6;
				l1.setText("입력번호 : " + no + "번 찐만두, 개수를 입력해주세요.");
			}
		});
		b7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				no = 7;
				l1.setText("입력번호 : " + no + "번 딸기 생과일 주스, 개수를 입력해주세요.");
			}
		});
		b8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				no = 8;
				l1.setText("입력번호 : " + no + "번 바나나 생과일 주스, 개수를 입력해주세요.");
			} 
		});
		a.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Thread(new Runnable() {
					public void run() {
						String pcs = tf2.getText();
						if(pcs.equals("")) {
							dispose();
						}
						pw.println(5 + "-" + no + "-" + pcs + "-" + id);
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
		setBounds(700, 400, 365, 400);
		setVisible(true);
	}
	
	public OrderFoodUI(Socket s, String id) {
		this.s = s;
		this.id = id;
		try {
			pw = new PrintWriter(s.getOutputStream());
			sis =new Scanner(s.getInputStream());
		} catch (IOException e1) {
		}
		
		setTitle("음식 주문");
		setLayout(null);
		p1 = new JPanel(new GridLayout(8, 0));
		p2 = new JPanel(new GridLayout(2, 0));
		p3 = new JPanel(new FlowLayout());
		p4 = new JPanel(new FlowLayout());
		l1 = new JLabel("음식을 선택하세요.");
		l2 = new JLabel("개수 : ");
		b1 = new JButton("[면류] : 1 : 너구리 : 3분 : 2000원");
		b2 = new JButton("[면류] : 2 : 신라면 : 3분 : 2000원");
		b3 = new JButton("[밥류] : 3 : 치킨마요 덮밥 : 10분 : 4000원");
		b4 = new JButton("[밥류] : 4 : 김치 볶음밥 : 10분 : 4000원");
		b5 = new JButton("[분식류] : 5 : 떢볶이 : 7분 : 4000원");
		b6 = new JButton("[분식류] : 6 : 찐만두 : 7분 : 4000원");
		b7 = new JButton("[음료류] : 7 : 딸기 생과일 주스 : 2분 : 4000원");
		b8 = new JButton("[음료류] : 8 : 바나나 생과일 주스 : 2분 : 4000원");
		tf1 = new JTextField(10);
		tf2 = new JTextField(10);
		
		tf2.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				char keyCode = e.getKeyChar();
				if(keyCode == KeyEvent.VK_ENTER) {
					actionPerformed(null);
				}
			}
		});
		
		a = new JButton("주문");
		c = new JButton("뒤로가기");
		
		p1.setBounds(23, 10, 300, 200);
		p2.setBounds(10, 221, 340, 30);
		p4.setBounds(10, 250, 300, 30);
		
		p3.setBounds(5, 301, 340, 40);
		
		p1.add(b1);
		p1.add(b2);
		p1.add(b3);
		p1.add(b4);
		p1.add(b5);
		p1.add(b6);
		p1.add(b7);
		p1.add(b8);
		
		p2.add(l1);
//		p2.add(tf1);
		
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
				no = 1;
				l1.setText("입력번호 : " + no + "번 너구리, 개수를 입력해주세요.");
			}
		});
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				no = 2;
				l1.setText("입력번호 : " + no + "번 신라면, 개수를 입력해주세요.");
			}
		});
		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				no = 3;
				l1.setText("입력번호 : " + no + "번 치킨마요 덮밥, 개수를 입력해주세요.");
			}
		});
		b4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				no = 4;
				l1.setText("입력번호 : " + no + "번 김치 볶음밥, 개수를 입력해주세요.");
			}
		});
		b5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				no = 5;
				l1.setText("입력번호 : " + no + "번 떢볶이, 개수를 입력해주세요.");
			}
		});
		b6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				no = 6;
				l1.setText("입력번호 : " + no + "번 찐만두, 개수를 입력해주세요.");
			}
		});
		b7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				no = 7;
				l1.setText("입력번호 : " + no + "번 딸기 생과일 주스, 개수를 입력해주세요.");
			}
		});
		b8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				no = 8;
				l1.setText("입력번호 : " + no + "번 바나나 생과일 주스, 개수를 입력해주세요.");
			} 
		});
		a.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Thread(new Runnable() {
					public void run() {
						String pcs = tf2.getText();
						if(pcs.equals("")) {
							dispose();
						}
						pw.println(5 + "-" + no + "-" + pcs + "-" + id);
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
		setBounds(700, 400, 365, 400);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		new Thread(new Runnable() {
			public void run() {
				String pcs = tf2.getText();
				if(pcs.equals("")) {
					dispose();
				}
				pw.println(5 + "-" + no + "-" + pcs + "-" + id);
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
}
