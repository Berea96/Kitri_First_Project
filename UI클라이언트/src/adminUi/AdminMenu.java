package adminUi;

import java.awt.FlowLayout;
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

public class AdminMenu {
	
	private Socket s;
	private PrintWriter pw;
	private Scanner sis;
	
	public AdminMenu(Socket s, PrintWriter pw, Scanner sis) {
		this.s = s;
		this.pw = pw;
		this.sis = sis;
	}

	public void memberInfo() {
		
		JFrame f1;
		JPanel p1;
		JLabel l1;
		JButton b1;
		JTextField tf1;
		
		f1 = new JFrame("회원 조회");
		p1 = new JPanel(new FlowLayout());
		l1 = new JLabel("조회할 아이디 : ");
		b1 = new JButton("조회");
		tf1 = new JTextField(10);
		
		tf1.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				char keyCode = e.getKeyChar();
				if(keyCode == KeyEvent.VK_ENTER) {
					pw.println(1 + "-" + tf1.getText());
					pw.flush();
					
					String msg = sis.nextLine();
					if(!msg.equals("")) {
					f1.dispose();
					new ReceiveMessage("조회" + "-" + msg);
					}
					else {
						new ReceiveMessage("오류! 조회할 데이터가 없습니다.");
					}
				}
			}
		});
		
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pw.println(1 + "-" + tf1.getText());
				pw.flush();
				
				String msg = sis.nextLine();
				if(!msg.equals("")) {
				f1.dispose();
				new ReceiveMessage("조회" + "-" + msg);
				}
				else {
					new ReceiveMessage("오류! 조회할 데이터가 없습니다.");
				}
			}
		});
		
		p1.add(l1);
		p1.add(tf1);
		p1.add(b1);
		
		f1.add(p1);
		
		f1.setLocation(700, 400);
		f1.setAlwaysOnTop(true);
		f1.setVisible(true);
		f1.pack();
	}
	
	public void memberDel() {
		JFrame f1;
		JPanel p1;
		JLabel l1;
		JButton b1;
		JTextField tf1;
		
		f1 = new JFrame("회원 삭제");
		p1 = new JPanel(new FlowLayout());
		l1 = new JLabel("삭제할 아이디 : ");
		b1 = new JButton("삭제");
		tf1 = new JTextField(10);
		
		tf1.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				char keyCode = e.getKeyChar();
				if(keyCode == KeyEvent.VK_ENTER) {
					String id = tf1.getText();
					pw.println(2 + "-" + id);
					pw.flush();
					
					String msg = sis.nextLine();
					if(!msg.equals("")) {
					f1.dispose();
					new ReceiveMessage(id + msg);
					}
					else {
						new ReceiveMessage("오류! 삭제할 데이터가 없습니다.");
					}
				}
			}
		});
		
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = tf1.getText();
				pw.println(2 + "-" + id);
				pw.flush();
				
				String msg = sis.nextLine();
				if(!msg.equals("")) {
				f1.dispose();
				new ReceiveMessage(id + msg);
				}
				else {
					new ReceiveMessage("오류! 삭제할 데이터가 없습니다.");
				}
			}
		});
		
		p1.add(l1);
		p1.add(tf1);
		p1.add(b1);
		
		f1.add(p1);
		
		f1.setLocation(700, 400);
		f1.setAlwaysOnTop(true);
		f1.setVisible(true);
		f1.pack();
	}
	
	public void shutDown() {
		JFrame f1;
		JPanel p1;
		JLabel l1;
		JButton b1;
		JTextField tf1;
		
		f1 = new JFrame("강제 종료");
		p1 = new JPanel(new FlowLayout());
		l1 = new JLabel("종료할 아이디 : ");
		b1 = new JButton("종료");
		tf1 = new JTextField(10);
		
		tf1.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				char keyCode = e.getKeyChar();
				if(keyCode == KeyEvent.VK_ENTER) {
					pw.println(3 + "-" + tf1.getText());
					pw.flush();
					
					String msg = sis.nextLine();
					if(msg.equals("0")){
						new ReceiveMessage("오류! 해당  아이디는 사용하고 있지 않습니다.");
					}
					else if(!msg.equals("")) {
						f1.dispose();
						new ReceiveMessage(msg);
					}
					else {
						new ReceiveMessage("오류! 종료할 수 없습니다.");
					}
				}
			}
		});
		
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pw.println(3 + "-" + tf1.getText());
				pw.flush();
				
				String msg = sis.nextLine();
				if(msg.equals("0")){
					f1.dispose();
					new ReceiveMessage("오류! 해당  아이디는 사용하고 있지 않습니다.");
				}
				else if(!msg.equals("")) {
					f1.dispose();
					new ReceiveMessage(msg);
				}
				else {
					new ReceiveMessage("오류! 종료할 수 없습니다.");
				}
			}
		});
		
		p1.add(l1);
		p1.add(tf1);
		p1.add(b1);
		
		f1.add(p1);
		
		f1.setLocation(700, 400);
		f1.setAlwaysOnTop(true);
		f1.setVisible(true);
		f1.pack();
	}
	
//	public static void main(String[] args) {
//		new AdminMenu().memberInfo();
//	}
}
