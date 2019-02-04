package Chat;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class ReceiveMessage {

	private JFrame f;
	private JLabel  lbl;
	private String msg;
	private int no;
	
	public ReceiveMessage(String msg, int no) {
		this.msg = msg;
		
		f = new JFrame(no + "번 pc에서 쪽지");
		lbl = new JLabel(msg, JLabel.CENTER);
		
		f.add(lbl, BorderLayout.CENTER);
		f.setBackground(Color.LIGHT_GRAY);
		
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		f.setLocation(1, 200);
		f.setAlwaysOnTop(true);
		f.setVisible(true);
		f.setSize(350, 100);
	}
}
