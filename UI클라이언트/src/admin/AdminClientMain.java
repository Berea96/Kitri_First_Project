package admin;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import adminUi.AdminLogin;

//키트리 192.168.15.28
//집 192.168.0.22

public class AdminClientMain {
	public static void main(String[] args) throws IOException {
		Socket s = new Socket("192.168.15.28", 50001);
//		Socket s = new Socket("192.168.0.22", 50001);
		
		PrintWriter pw = new PrintWriter(s.getOutputStream());
		Scanner sis = new Scanner(s.getInputStream());
		
//		new AdminMenu(s, pw, sis).start();
		new AdminLogin(s, pw, sis).start();
	}
}
