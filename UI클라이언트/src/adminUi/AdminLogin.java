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
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Chat.ReceiveMessage;

public class AdminLogin extends JFrame{
	
	private JPanel p1, p2, p3;
	private JLabel l1, l2;
	private JButton b1;
	private JTextField tf1, tf2;
	
	private Socket s;
	private PrintWriter pw;
	private Scanner sis;
	
	private String id;
	private String pw2;
	private String result;
	private int cnt = 3;
	
	public AdminLogin(Socket s, PrintWriter pw, Scanner sis) {
		
		this.s = s;
		this.pw = pw;
		this.sis = sis;
		
		setTitle("관리자 용");
		setLayout(new GridLayout(3, 0));
		
		p1 = new JPanel(new FlowLayout());
		p2 = new JPanel(new FlowLayout());
		p3 = new JPanel(new FlowLayout());
		
		l1 = new JLabel("아이디");
		l2 = new JLabel("비밀번호");
		
		b1 = new JButton("로그인");
		
		tf1 = new JPasswordField(11);
		tf2 = new JPasswordField(10);
		
		tf2.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				char keyCode = e.getKeyChar();
				if(keyCode == KeyEvent.VK_ENTER) {
					actionPerformed(null);
				}
			}
		});
		
	}
	
	public void start() {
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				id = tf1.getText();
				pw2 = tf2.getText();
				
				pw.println(0 + "-" + id + "-" + pw2);
				pw.flush();
				
				result = sis.nextLine();
				
				if(result.equals("5")) {
					new AdminMenuUI(s, pw, sis).start();
				}
				else {
					if(cnt == 1) {
						new ReceiveMessage("강제 종료됩니다.");
						System.exit(0);
					}
//					System.out.println("아이디, 비밀번호를 잘못 입력 하셨습니다.");
//					System.out.println(--cnt + "번 더 잘못입력시 강제 종료 됩니다.");
					new ReceiveMessage("cnt,아이디, 비밀번호를 잘못 입력 하셨습니다.," + --cnt + "번 더 잘못입력시 강제 종료 됩니다.");

					tf1.setText("");
					tf2.setText("");
				}
			}
		});
		
		p1.add(l1);
		p1.add(tf1);
		p2.add(l2);
		p2.add(tf2);
		p3.add(b1);
		
		add(p1);
		add(p2);
		add(p3);
		
		setBounds(500, 400, 300, 200);
		
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		id = tf1.getText();
		pw2 = tf2.getText();
		
		pw.println(0 + "-" + id + "-" + pw2);
		pw.flush();
		
		result = sis.nextLine();
		
		if(result.equals("5")) {
			dispose();
			new AdminMenuUI(s, pw, sis).start();
		}
		else {
			if(cnt == 1) {
//				System.out.println("강제 종료됩니다.");
				new ReceiveMessage("강제 종료됩니다.");
				System.exit(0);
			}
			
			new ReceiveMessage("cnt,아이디, 비밀번호를 잘못 입력 하셨습니다.," + --cnt + "번 더 잘못입력시 강제 종료 됩니다.");
//			System.out.println("아이디, 비밀번호를 잘못 입력 하셨습니다.");
//			System.out.println(--cnt + "번 더 잘못입력시 강제 종료 됩니다.");
			tf1.setText("");
			tf2.setText("");
		}
	}

//	public static void main(String[] args) {
//		new AdminLogin().start();
//	}
}
