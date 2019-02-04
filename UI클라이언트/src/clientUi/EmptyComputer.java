package clientUi;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Chat.ReceiveMessage;

public class EmptyComputer extends JFrame{

	private JFrame f1;
//	private JButton b1, b2, b3, b4, b5;
//	private JPanel p1, p2, p3, p4, p5, p6, p7, p8, p9, p10;
	private JPanel[] p;
	private JButton[] b;
	
//	private String[] empty;
	
	private int useNo;
	
//	private JLabel l1, l2, l3, l4, l5;
	
	EmptyComputer(String[] empty){
	
	f1 = new JFrame("공석");
	f1.addWindowListener(new WindowAdapter() {
		public void windowClosing(WindowEvent e) {
			System.exit(0);
		}
	});
	f1.setLayout(new GridLayout(10, 0));
//	b1 = new JButton(empty[0]);
//	b2 = new JButton(empty[1]);
//	b3 = new JButton(empty[2]);
//	b4 = new JButton(empty[3]);
//	b5 = new JButton(empty[4]);
//	l1 = new JLabel(empty[5]);
//	l2 = new JLabel(empty[6]);
//	l3 = new JLabel(empty[7]);
//	l4 = new JLabel(empty[8]);
//	l5 = new JLabel(empty[9]);
	
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
				if(empty[n].contains("사용 중")) {
					new ReceiveMessage("사용중인 컴퓨터입니다");
//					start();
//					break;
				}
				else {
					useNo = n + 1;
					f1.dispose();
				}
			}
		});
	}
	
//	p1 = new JPanel(new GridLayout(0, 5));
//	p2 = new JPanel(new GridLayout(0, 5));
	}
	
	public int start() {
		while(useNo == 0) {
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
			
			f1.setBounds(500, 200, 800, 500);
			f1.setAlwaysOnTop(true);
			f1.setVisible(true);
			dispose();
		}
		return useNo;
	}
}
