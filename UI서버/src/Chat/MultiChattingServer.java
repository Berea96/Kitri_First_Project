package Chat;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MultiChattingServer extends Thread{
	
	private Map<String, DataOutputStream> clients;

	public MultiChattingServer() {
		this.clients = Collections.synchronizedMap(new HashMap<>());
	}

	public void start() {
		ServerSocket ss = null;
		Socket s = null;
		
		try {
			ss = new ServerSocket(50002);
			System.out.println("채팅 서버 대기중");
			
			while(true) {
				s = ss.accept();
				System.out.println("[" + s.getInetAddress() + " : " + s.getPort() + "]" + 
								   " 님이 접속했습니다.");
				
				receiver r = new receiver(s);
				r.start();
				
			}
		} catch (IOException e) {}
	}
	
	public void sendAll(String message) {
		Iterator<String> it = clients.keySet().iterator();
		
		while(it.hasNext()) {
			try {
				String name = it.next();
				DataOutputStream out = clients.get(name);
				String[] msg = message.split(" : ");
				if(msg[0].equals("[관리자 pc]")) {
					String[] msg2 = null;
					if(msg[1].contains(" /w ")) {
						msg2 = msg[1].split(" /w ");
						if(msg2[0].equals(name) || name.equals("관리자 pc")) {
							out.writeUTF(msg[0] + " : " + msg2[1]);
						}
					}
					else {
						out.writeUTF("<전체공지> : " + message);
					}
				}
				else {
					if(msg[0].equals("[" + name + "]") || name.equals("관리자 pc")) {
						out.writeUTF(message);
					}
					else {
						
					}
				}
			} catch (IOException e) {}
		}
	}
	
	class receiver extends Thread {
		Socket s;
		DataOutputStream out;
		DataInputStream in;
		
		receiver(Socket s) {
			this.s = s;
			try {
				out = new DataOutputStream(s.getOutputStream());
				
				in = new DataInputStream(s.getInputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		public void run() {
			String name = "";
			String msg = "";
			
			try {
				name = in.readUTF();
				
				sendAll(name + "님이 입장하셨습니다.");
				
				clients.put(name, out);
				System.out.println("현재 접속자 수는 " + clients.size() + "명 입니다.");
				
				while(in != null) {
					msg = in.readUTF();
					
					if(msg.contains("exit")) {
						break;
					}
					sendAll(msg);
				}
			} catch (IOException e) {
			} finally {
				sendAll(name + "님이 나가셨습니다.");
				
				clients.remove(name);
				System.out.println("[" + s.getInetAddress() + " : " + s.getPort() + "]" + 
								   " 님이 접속을 종료하셨습니다.");
				System.out.println("현재 접속자 수는" + clients.size() + "명 입니다..");
			}
		}
	}
	
//	public static void main(String[] args) {
//		new MultiChattingServer().start();
//	}
}
