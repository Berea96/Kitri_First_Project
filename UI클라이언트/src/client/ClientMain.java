package client;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
//클라이언트부분 메인 선택창 제공
public class ClientMain {
	public static void main(String[] args) throws IOException {
		
		Socket s = new Socket("127.0.0.1", 50001);
		
		PrintWriter pw = new PrintWriter(s.getOutputStream());
		Scanner sis = new Scanner(s.getInputStream());
		Scanner output = new Scanner(System.in);
		String result = "";
		
		new ClientMenu(s, pw, sis).start();
	}
}