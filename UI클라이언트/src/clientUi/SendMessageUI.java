package clientUi;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import vo.MemberVO;

public class SendMessageUI extends JFrame {

	private JPanel p1, p2;
	private JLabel l1;
	private JTextField tf1;
	private JButton b1;
	
	private MemberVO vo;
	
	private Socket s;
	private PrintWriter pw;
	
	public SendMessageUI(MemberVO vo, Socket s) {
		this.s = s;
		this.vo = vo;
		try {
			pw = new PrintWriter(s.getOutputStream());
		} catch (IOException e) {
		}
		
		setTitle("쪽지");
		setLayout(new FlowLayout());
		
		p1 = new JPanel();
		p2 = new JPanel(new FlowLayout());
		l1 = new JLabel("관리자에게 보낼 쪽지의 내용을 입력하세요.(20자 내)");
		tf1 = new JTextField(20);
		b1 = new JButton("보내기");
		
		setLocation(800, 600);
	}
	
	public void start() {
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pw.println(8 + "-" + vo.getUse() + "-" + tf1.getText());
				pw.flush();
				dispose();
			}
		});
		
		tf1.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				char keyCode = e.getKeyChar();
				if(keyCode == KeyEvent.VK_ENTER) {
					actionPerformed(null);
				}
			}
		});
		
		p1.add(l1);
		p2.add(tf1);
		p2.add(b1);
		
		add(p1);
		add(p2);
		
		setSize(400, 130);
		
		setAlwaysOnTop(true);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		pw.println(8 + "-" + vo.getUse() + "-" + tf1.getText());
		pw.flush();
		dispose();
	}
}
