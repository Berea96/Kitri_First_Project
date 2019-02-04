package client;
import java.util.Scanner;

import vo.MemberVO;

public class ClientLogin {
	
	private Scanner sc = new Scanner(System.in);
	
	private String title = "";
	private String result = "";
	
	public String orderFood(String[] foodList) {
		System.out.println("=========메뉴판=========");
		for(int i = 0; i < foodList.length; i++) {
			System.out.println(foodList[i]);
		}
		System.out.println("======================");
		
		System.out.print("음식 번호 : ");
		int no = sc.nextInt();
		System.out.print("갯수 : ");
		int pcs = sc.nextInt();
//		System.out.print("투입할 돈 : ");
//		int money = sc.nextInt();
//		
//		System.out.println("주문 완료");
//		result = no + "-" + money + "-" + pcs;
		
		System.out.println("주문완료");
		result = no + "-" + pcs;
		
		return result;
	}
	
	public String logout(MemberVO vo) {
		System.out.println("===============");
		System.out.println("로그아웃합니다.");
		System.out.println("===============");
		
		return vo.getId() + "-" + vo.getPw() + "-" + vo.getUse();
	}
	
	public String sendMessage() {
		
		System.out.println("관리자에게 보낼 쪽지의 내용을 입력하세요.");
		System.out.print(">>");
		String msg = sc.nextLine();
		return msg;
	}
}

