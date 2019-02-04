package server;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import vo.MemberVO;



public class LostTimeServer {
	
	public void start(){
		ServerSocket ss = null;
		Socket s = null;
		Scanner sis = null;
		PrintWriter pw = null;
		
		try {
			ss = new ServerSocket(50003);
			System.out.println("시간 서버 대기중");
		
			while(true){
				s = ss.accept();
				pw = new PrintWriter(s.getOutputStream());
				sis = new Scanner(s.getInputStream());
				
				receiver r = new receiver(s);
				r.start();
//				while(true) {
//					int result = new ServerDAO().decreaseTime(sis.nextLine());
//					pw.println(result);
//					pw.flush();
//				}
			}
		} catch (IOException e) {
			System.out.println("sdafasd");
			System.exit(0);
		} catch (Exception e) {
			System.out.println("sadfasfd");
			System.exit(0);
		}
	}
	
	class receiver extends Thread {
		
		Socket s;
		PrintWriter pw;
		Scanner sis;
		
		receiver(Socket s) {
			this.s = s;
			try {
				this.pw = new PrintWriter(s.getOutputStream());
				this.sis = new Scanner(s.getInputStream());
			} catch (IOException e) {
			}
		}

		public void run() {
			try {
				while(true) {
					String[] input = sis.nextLine().split("-");
					if(input[0].equals("1")) {
						int result = new ServerDAO().decreaseTime(input[1]);
						
						pw.println(result);
						pw.flush();
					}
					else if(input[0].equals("2")) {
						String id = input[1];
						String pw = input[2];
						int use = Integer.parseInt(input[3]);
						
						MemberVO vo = new MemberVO(id, pw, use);
						
						String result = new ServerDAO().updateMember(vo);
						
						this.pw.println(result);
						this.pw.flush();
					}
					else if(input[0].equals("3")) {
						String id = input[1];
						
						int use = new ServerDAO().selectShutDownInfo(id);
						pw.println(use);
						pw.flush();
					}
				}
			} catch(Exception e) {
				System.out.println("연결해제");
			}
		}
	}
	
//	public static void main(String[] args) {
//		new LostTimeServer().start();
//	}
}
