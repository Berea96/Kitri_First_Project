package admin;
import java.util.Scanner;

import vo.MemberVO;

public class Admin {

	private MemberVO vo;
	private String result;
	private int result2;
	private int cnt = 3;
	
	Scanner sc = new Scanner(System.in);
	
	//관리자 계정 로그인
	public String admin() {
		
		System.out.println("관리자 로그인");
		System.out.print("ID : ");
		String id = sc.next();
		System.out.print("암호 : ");
		String pw = sc.next();
		vo = new MemberVO(id, pw);
		
		result = vo.getId() + "-" + vo.getPw();
		return result;
	}
}
