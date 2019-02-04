package adminUi;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Chat.MultiChattingClient;

public class AdminMenuUI extends JFrame {

	private JPanel p1, p2;
	private JButton b1, b2, b3, b4, b5, b6;
	
	private Socket s;
	private PrintWriter pw;
	private Scanner sis;
	
	private String result;
	private int cnt = 3;
	
	public AdminMenuUI(Socket s, PrintWriter pw, Scanner sis) {
		this.s = s;
		this.pw = pw;
		this.sis = sis;
		
		setTitle("관리자 메뉴");
		setLayout(new GridLayout(1, 0));
		
		p1 = new JPanel(new GridLayout(6, 0));
		p2 = new JPanel();
		
		b1 = new JButton("회원조회");
		b2 = new JButton("회원 삭제");
		b3 = new JButton("강제 종료");
		b4 = new JButton("채팅 시작");
		b5 = new JButton("매출 조회");
		b6 = new JButton("로그아웃");
		
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AdminMenu(s, pw, sis).memberInfo();
			}
		});
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AdminMenu(s, pw, sis).memberDel();
			}
		});
		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AdminMenu(s, pw, sis).shutDown();
			}
		});
		b4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Thread(new Runnable() {
					public void run() {
						new MultiChattingClient("관리자 pc").start();
					}
				}).start();
			}
		});
		b5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new SalesViewUI(s, pw, sis).start();
			}
		});
		b6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}
	
	public void start() {
		
		p1.add(b1);
		p1.add(b2);
		p1.add(b3);
		p1.add(b4);
		p1.add(b5);
		p1.add(b6);
		
		add(p1);
		
		setBounds(500, 400, 300, 300);
		
		setAlwaysOnTop(true);
		setVisible(true);
		
	}
}


