package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import Chat.MultiChattingServer;

//서버를 담당하는 메인 
//관리자 pc
public class ServerMain {
	
	public static void main(String[] args) throws IOException {
		
		System.out.println("======================");
		ServerSocket ss = new ServerSocket(50001);
		System.out.println("관리자 pc 작동 중");
		System.out.println("======================");
		
		new Thread(new Runnable() {
			public void run() {
				new MultiChattingServer().start();
			}
		}).start();
		
		new Thread(new Runnable() {
			public void run() {
				new LostTimeServer().start();
			}
		}).start();
	
		while(true) {
			Socket s = ss.accept();
			Thread t = new Server(s);
			t.start();
		}
		//ss.close();
	}
}
