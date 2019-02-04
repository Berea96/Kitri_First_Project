package client;


import java.util.Scanner;

import Chat.ReceiveMessage;
import vo.MemberVO;

//클라이언트 main에서 선택한 메소드 실행
public class Client {
	
	private Scanner sc = new Scanner(System.in);
	private MemberVO vo = null;
	private String result = "";
	private int result2 = 0;
	//클라이언트측 pc에서 동작하는 회원가입 창
	//저장된 값을 server측 pc로 전달 
	public String signUp(String id, String pw, String name, int age, String phone) {
//		System.out.println("======================");
//		System.out.println("회원가입 입니다.");
//		System.out.println("======================");
		if(phone.equals("")) 
			vo = new MemberVO(id, pw, name, age);
		else
			vo = new MemberVO(id, pw, name, age, phone);
	
		result = vo.toString();
		
//		System.out.println("======================");
//		System.out.println(result);
//		System.out.println("======================");
		return result;
	}
	
	//일반 로그인
	public String logIn(String[] empty) {
		
		System.out.println("=========컴퓨터 목록=========");
		for(int i = 0; i < empty.length - 1; i++) {
			System.out.print(empty[i] + "	");
			if((i + 1) % 5 == 0) {
				System.out.println();
			}
		}
		System.out.println("=====================");
		
		System.out.println("======================");
		System.out.println("로그인 입니다.");
		while(true) {
			
			System.out.print("자리 번호 : ");
			int useNo = sc.nextInt();
			
			if(useNo > 50) {
				System.out.println("잘못된 번호");
				continue;
			}
			
			if(empty[useNo - 1].contains("사용 중")) {
				System.out.println("사용중인 컴퓨터입니다");
			}else {
				System.out.print("아이디 : ");
				String id = sc.next();
				System.out.print("암호 : ");
				String pw = sc.next();
				System.out.println("======================");
				
				vo = new MemberVO(id, pw, useNo);
				return vo.getId() + "-" + vo.getPw() + "-" + vo.getUse();
			}
		}
	}
		
		public String loginSuccess(int logIn, MemberVO vo) {
		if(logIn == 1) {
//			System.out.println("==========================");
//			System.out.println("로그인 성공");
//			System.out.println("==========================");
//			new ReceiveMessage("로그인 성공");
			
			return logIn + "-" + vo.getId() + "-" + vo.getPw() + "-" + vo.getUse();
			//그 후 프로그램
		}
		
		else if(logIn == 2) {
//			System.out.println("==========================");
//			System.out.println("충전이 필요합니다.");
//			System.out.println("==========================");
			new ReceiveMessage("충전이 필요합니다.");
			
			return 3 + "-" + vo.getId() + "-" + vo.getPw() + "-" + vo.getUse();
		}
		
		else if(logIn == 3) {
//			System.out.println("==========================");
//			System.out.println("사용중인 회원입니다.");
//			System.out.println("==========================");
			new ReceiveMessage("사용중인 회원입니다.");
			
			return 0 + "-" + vo.getId() + "-" + vo.getPw() + "-" + vo.getUse();
		}
		
		else if(logIn == 4) {
//			System.out.println("==========================");
//			System.out.println("오류가 발생했습니다. 다시 시도하세요.");
//			System.out.println("==========================");
			new ReceiveMessage("오류가 발생했습니다. 다시 시도하세요.");
			
			return 0 + "-" + vo.getId() + "-" + vo.getPw() + "-" + vo.getUse();
		}
		
		else if(logIn == 0) {
//			System.out.println("==========================");
//			System.out.println("아이디, 패스워드를 잘못 입력하셨습니다.");
//			System.out.println("==========================");
			new ReceiveMessage("아이디, 패스워드를 잘못 입력하셨습니다.");
			
			return 0 + "-" + vo.getId() + "-" + vo.getPw() + "-" + vo.getUse();
		}
		return "";
	}	
	//로그인 전 시간추가
	public String timePlus() {
		System.out.println("=========요금표=========");
		System.out.println("1시간 : 1000원");
		System.out.println("2시간 : 2000원");
		System.out.println("3시간 : 3000원");
		System.out.println("5시간 : 5000원");
		System.out.println("13시간 : 10000원");
		System.out.println("27시간 : 20000원");
		System.out.println("41시간 : 30000원");
		System.out.println("======================");
		
		while(true) {
		System.out.println("======================");
		System.out.print("아이디 : ");
		String id = sc.next();
		System.out.print("추가할 시간 : ");
		int time = sc.nextInt();
		System.out.print("투입할 금액 : ");
		int money = sc.nextInt();
		System.out.println("======================");
		
		result = id + "-" + time * 60 + "-" + money;
		//break;
		return result;
		}
	}
	
	//로그인 후 시간추가
	public String timePlus(MemberVO mvo) {
		
		System.out.println("=========요금표=========");
		System.out.println("1시간 : 1000원");
		System.out.println("2시간 : 2000원");
		System.out.println("3시간 : 3000원");
		System.out.println("5시간 : 5000원");
		System.out.println("13시간 : 10000원");
		System.out.println("27시간 : 20000원");
		System.out.println("41시간 : 30000원");
		System.out.println("======================");
		
		System.out.println("======================");
		System.out.print("추가할 시간 : ");
		int time = sc.nextInt();
		System.out.print("투입할 금액 : ");
		int money = sc.nextInt();
		System.out.println("======================");
		
		result = mvo.getId() + "-" + time * 60 + "-" + money;
		return result;
	}
	
}
