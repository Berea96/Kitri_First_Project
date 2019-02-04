package clientUi;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
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
import client.Client;

public class SignUp extends JFrame{
	
	private String id;
	private String pw;
	private String name;
	private int age;
	private String phone;
	
	private JLabel l1, l2, l3, l4, l5;
	private JLabel l6, l7;
	private JButton b1, b2, b3, b4;
	
	private JTextField tf1, tf2, tf3, tf4, tf5;
	
	private JPanel p1, p2, p3, p4, p5, p6;
	
	private Socket s = null;
	private PrintWriter pw2;
	private Scanner sis;
	
	private String result = "";
	
	public SignUp(Socket s) throws HeadlessException, IOException {
		
		this.s = s;
		this.pw2 = new PrintWriter(s.getOutputStream());
		this.sis = new Scanner(s.getInputStream());
		
		l1 = new JLabel("아이디");
		l2 = new JLabel("비밀번호");
		l3 = new JLabel("이름");
		l4 = new JLabel("나이");
		l5 = new JLabel("핸드폰 번호(선택)");
		l6 = new JLabel("중복 결과");
		l7 = new JLabel("인증 결과");
		
		b1 = new JButton("중복확인");
		b2 = new JButton("인증");
		b3 = new JButton("가입");
		b4 = new JButton("뒤로가기");
		
		tf1 = new JTextField(10);
		tf2 = new JPasswordField(10);
		tf3 = new JTextField(10);
		tf4 = new JTextField(10);
		tf5 = new JTextField(10);
		
		tf5.setText("");
		
		p1 = new JPanel(new FlowLayout());
		p2 = new JPanel(new FlowLayout());
		p3 = new JPanel(new FlowLayout());
		p4 = new JPanel(new FlowLayout());
		p5 = new JPanel(new FlowLayout());
		p6 = new JPanel(new FlowLayout());
		
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = tf1.getText();
				
				pw2.println(10 + "-" + id);
				pw2.flush();
				
				String check = sis.nextLine();
				
				if(check.contains("중복")) {
					l6.setText("중복된 아이디");
					p1.setBounds(44, 20, 410, 40);
				}
				else if(check.contains("사용")) {
					l6.setText("사용가능");
					p1.setBounds(44, 20, 380, 40);
				}
			}
		});
		
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String phone = tf5.getText();
				
				pw2.println(11 + "-" + phone);
				pw2.flush();
				
				String check = sis.nextLine();
				if(check.contains("중복")) {
					l7.setText("중복된 번호");
					p5.setBounds(-3, 180, 400, 40);
				}
				else if(check.contains("사용")) {
					l7.setText("인증되었습니다.");
					p5.setBounds(10, 180, 400, 40);
				}
			}
		});
		
		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				id = tf1.getText();
				pw = tf2.getText();
				name = tf3.getText();
				age = Integer.parseInt(tf4.getText());
				phone = tf5.getText();
				
				if(phone.equals(null)) {
					phone = "";
				}
				
				String result = new Client().signUp(id, pw, name, age, phone);
				
				pw2.println(1 + "-" + result);
				pw2.flush();
				result = sis.nextLine();
//				System.out.println(result);
				
				if(result.contains("실패")) {
//					System.out.println("가입 실패");
					new ReceiveMessage(result);
					
					tf1.setText("");
					tf2.setText("");
					tf3.setText("");
					tf4.setText("");
					tf5.setText("");
				}
				else if(result.contains("존재")) {
//					System.out.println("가입 실패");
					new ReceiveMessage(result);
					
					tf1.setText("");
					tf2.setText("");
					tf3.setText("");
					tf4.setText("");
					tf5.setText("");
				}
				else if(result.contains("성공")) {
					new ReceiveMessage(result);
					dispose();
//					try {
//						new LogIn().start();
//					} catch (HeadlessException e1) {
//					} catch (IOException e1) {
//					}
				}
			}
		});
		
		b4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
//				try {
////					new LogIn().start();
//				} catch (HeadlessException e1) {
//				} catch (IOException e1) {
//				}
			}
		});
	}

	public void start() {
		
		setTitle("회원가입창");
		setLayout(null);
		setBounds(300, 300, 450, 350);
		
		p1.add(l1);
		p1.add(tf1);
		p1.add(b1);
		p1.add(l6);
		p2.add(l2);
		p2.add(tf2);
		p3.add(l3);
		p3.add(tf3);
		p4.add(l4);
		p4.add(tf4);
		p5.add(l5);
		p5.add(tf5);
		p5.add(b2);
		p5.add(l7);
		p6.add(b3);
		p6.add(b4);
		
		p1.setBounds(31, 20, 410, 40);
		p2.setBounds(-47, 60, 400, 40);
		p3.setBounds(-34, 100, 400, 40);
		p4.setBounds(-34, 140, 400, 40);
		p5.setBounds(-10, 180, 400, 40);
		p6.setBounds(11, 240, 400, 40);
		
		add(p1);
		add(p2);
		add(p3);
		add(p4);
		add(p5);
		add(p6);
		
		setAlwaysOnTop(true);
		setVisible(true);
	}
	
//	public static void main(String[] args) {
//		new SignUp().start();
//	}
	
}
