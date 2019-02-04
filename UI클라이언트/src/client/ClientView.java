package client;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import vo.ComputerVO;
import vo.FoodVO;

public class ClientView {

	private Socket s = null;
	private PrintWriter pw = null;
	private Scanner sis = null;
	public ClientView(Socket s) {
		this.s = s;
	}
	
	public ArrayList<FoodVO> foodView() {
		
		ArrayList<FoodVO> list = null;
		
		return list;
	}
	
	public ArrayList<ComputerVO> computerView() {
		
		ArrayList<ComputerVO> list = null;
		
		return list;
	}
}
