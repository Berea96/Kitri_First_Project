package Chat;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class MultiMessageClient  implements ActionListener{

	private Frame f, f2;
	private Panel p;
	private Button b;
	private TextArea ta;
	private TextField tf;
	
	private String name;
	private Sender sender;
	
	public MultiMessageClient(String name) {

		this.name = name;
		f = new Frame(name);
		
		p = new Panel();
		b = new Button("send");
		ta = new TextArea(20, 50);
		tf = new TextField();
	}

	public void start() {
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				f.dispose();
				sender.send("exit");
			}
		});
		
		f.add(BorderLayout.SOUTH, p);
		
		b.addActionListener(this);
		b.setBackground(Color.gray);
		
		tf.setColumns(40);
		tf.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				char keyCode = e.getKeyChar();
				if(keyCode == KeyEvent.VK_ENTER) {
					actionPerformed(null);
				}
			}
		});
		
		p.setBackground(Color.WHITE);
		p.add(tf);
		p.add(b);
		
		f.add(BorderLayout.CENTER, ta);
		
		f.setVisible(true);
		f.pack();
		
		Socket s = null;
		
		try {
			s = new Socket("127.0.0.1", 50002);
			System.out.println("서버에 연결되었습니다.");
			
			sender = new Sender(s);
			
			Thread receiver = new Thread(new receiver(s));
			receiver.start();
			
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		String text = tf.getText();
		if(text.equals("")) {
			text = "...";
		}
		sender.send(text);
		
		tf.setText("");
		tf.requestFocus();
	}
	
	public void appendMessage(String message) {
		ta.append(message);
		ta.append("\n");
	}
	
	class Sender {
		Socket s;
		DataOutputStream out;
		
		Sender(Socket s){
			this.s = s;
			
			try {
				out = new DataOutputStream(s.getOutputStream());
				
				if(out != null) {
					out.writeUTF(name);
				}
			} catch (IOException e) {}
		}
		
		public void send(String message) {
			if(out != null) {
				try {
					out.writeUTF("[" + name + "] : " + message);
				} catch (IOException e) {}
			}
		}
	}
	
	class receiver implements Runnable {

		Socket s;
		DataInputStream in;
		
		receiver(Socket s) {
			this.s = s;
			
			try {
				in = new DataInputStream(s.getInputStream());
			} catch (IOException e) {}
		}
		
		public void run() {
			while(in != null) {
				try {
//					MultiMessageClient.this.appendMessage(in.readUTF());
					new ReceiveMessage(in.readUTF(), 1);
				} catch (IOException e) {}
			}
		}
	}
}
