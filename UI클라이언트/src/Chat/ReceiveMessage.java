package Chat;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class ReceiveMessage {

	private JFrame f;
	private JLabel  lbl;
	private JLabel l1, l2, l3, l4;
	private String msg;
	private int no;
	
	public ReceiveMessage(String msg, int no) {
		this.msg = msg;
		
		f = new JFrame(no + "번 pc에서 쪽지");
		lbl = new JLabel(msg, JLabel.CENTER);
		l1 = new JLabel(msg, JLabel.CENTER);
		l2 = new JLabel(msg, JLabel.CENTER);
		l3 = new JLabel(msg, JLabel.CENTER);
		l4 = new JLabel(msg, JLabel.CENTER);
		
		f.add(lbl, BorderLayout.CENTER);
		f.setBackground(Color.LIGHT_GRAY);
		
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		f.setAlwaysOnTop(true);
		f.setVisible(true);
		f.setSize(350, 100);
	}
	
	public ReceiveMessage(String msg) {
		this.msg = msg;
		
//		if(msg.contains("0")) {
//			f = new JFrame("오류!!");
//			lbl = new JLabel("오류! 해당 아이디는 사용하고 있지 않습니다.", JLabel.CENTER);
//			
//			f.add(lbl, BorderLayout.CENTER);
//			f.setBackground(Color.LIGHT_GRAY);
//			f.setLocation(700, 500);
//			
//			f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//			
//			f.setAlwaysOnTop(true);
//			f.setVisible(true);
//			f.setSize(350, 100);
//		}
		if(msg.contains("배달")) {
			f = new JFrame("주문하신 음식");
			lbl = new JLabel(msg, JLabel.CENTER);
			
			f.add(lbl, BorderLayout.CENTER);
			f.setBackground(Color.LIGHT_GRAY);
			f.setLocation(1100, 400);
			
			f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
			f.setAlwaysOnTop(true);
			f.setVisible(true);
			f.setSize(350, 100);
		}
		else if(msg.contains("cnt")) {
			String[] msgarr = msg.split(",");
			f = new JFrame("");
//			lbl = new JLabel(msg, JLabel.CENTER);
			l1 = new JLabel(msgarr[1] + ", " + msgarr[2], JLabel.CENTER);
			l2 = new JLabel(msgarr[3], JLabel.CENTER);
			
			f.add(l1, BorderLayout.NORTH);
			f.add(l2, BorderLayout.CENTER);
			f.setBackground(Color.LIGHT_GRAY);
			f.setLocation(700, 400);
			
			f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
			f.setAlwaysOnTop(true);
			f.setVisible(true);
			f.setSize(350, 100);
		}
		else if(msg.contains("충전이 필요")){
			f = new JFrame("충전 !");
			lbl = new JLabel(msg, JLabel.CENTER);
			
			f.add(lbl, BorderLayout.CENTER);
			f.setBackground(Color.LIGHT_GRAY);
			f.setLocation(1100, 400);
			
			f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
			f.setAlwaysOnTop(true);
			f.setVisible(true);
			f.setSize(350, 100);
		}
		else if(msg.contains("충전")){
			f = new JFrame("충전 완료");
			lbl = new JLabel(msg, JLabel.CENTER);
			
			f.add(lbl, BorderLayout.CENTER);
			f.setBackground(Color.LIGHT_GRAY);
			f.setLocation(1100, 400);
			
			f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
			f.setAlwaysOnTop(true);
			f.setVisible(true);
			f.setSize(350, 100);
		}
		else if(msg.contains("남은 시간")){
			f = new JFrame("알림");
			lbl = new JLabel(msg, JLabel.CENTER);
			
			f.add(lbl, BorderLayout.CENTER);
			f.setBackground(Color.LIGHT_GRAY);
			f.setLocation(1100, 400);
			
			f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
			f.setAlwaysOnTop(true);
			f.setVisible(true);
			f.setSize(350, 100);
		}
		else if(msg.contains("곧")){
			f = new JFrame("알림");
			lbl = new JLabel(msg, JLabel.CENTER);
			
			f.add(lbl, BorderLayout.CENTER);
			f.setBackground(Color.LIGHT_GRAY);
			f.setLocation(1100, 400);
			
			f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
			f.setAlwaysOnTop(true);
			f.setVisible(true);
			f.setSize(350, 100);
		}
		else if(msg.contains("부족")){
			f = new JFrame("error!");
			lbl = new JLabel(msg, JLabel.CENTER);
			
			f.add(lbl, BorderLayout.CENTER);
			f.setBackground(Color.LIGHT_GRAY);
			f.setLocation(700, 500);
			
			f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
			f.setAlwaysOnTop(true);
			f.setVisible(true);
			f.setSize(350, 100);
		}
		else if(msg.contains("가입 성공")) {
			f = new JFrame("가입 성공");
			lbl = new JLabel(msg, JLabel.CENTER);
			
			f.add(lbl, BorderLayout.CENTER);
			f.setBackground(Color.LIGHT_GRAY);
			f.setLocation(700, 500);
			
			f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
			f.setAlwaysOnTop(true);
			f.setVisible(true);
			f.setSize(350, 100);
		}
		else if(msg.contains("가입 실패")) {
			f = new JFrame("error!");
			lbl = new JLabel(msg, JLabel.CENTER);
			
			f.add(lbl, BorderLayout.CENTER);
			f.setBackground(Color.LIGHT_GRAY);
			f.setLocation(700, 500);
			
			f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
			f.setAlwaysOnTop(true);
			f.setVisible(true);
			f.setSize(350, 100);
		}
		else if(msg.contains("잘못 입")) {
			f = new JFrame("error!");
			lbl = new JLabel(msg, JLabel.CENTER);
			
			f.add(lbl, BorderLayout.CENTER);
			f.setBackground(Color.LIGHT_GRAY);
			f.setLocation(700, 500);
			
			f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
			f.setAlwaysOnTop(true);
			f.setVisible(true);
			f.setSize(350, 100);
		}
		else if(msg.contains("충전이 필")) {
			f = new JFrame("충전");
			lbl = new JLabel(msg, JLabel.CENTER);
			
			f.add(lbl, BorderLayout.CENTER);
			f.setBackground(Color.LIGHT_GRAY);
			f.setLocation(700, 500);
			
			f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
			f.setAlwaysOnTop(true);
			f.setVisible(true);
			f.setSize(350, 100);
		}
		else if(msg.contains("존재")) {
			f = new JFrame("error!");
			lbl = new JLabel(msg, JLabel.CENTER);
			
			f.add(lbl, BorderLayout.CENTER);
			f.setBackground(Color.LIGHT_GRAY);
			f.setLocation(700, 500);
			
			f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
			f.setAlwaysOnTop(true);
			f.setVisible(true);
			f.setSize(350, 100);
		}
		else if(msg.contains("사용 중")) {
			f = new JFrame("사용 중");
			lbl = new JLabel(msg, JLabel.CENTER);
			
			f.add(lbl, BorderLayout.CENTER);
			f.setBackground(Color.LIGHT_GRAY);
			f.setLocation(700, 500);
			
			f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
			f.setAlwaysOnTop(true);
			f.setVisible(true);
			f.setSize(350, 100);
		}
		else if(msg.contains("오류")) {
			f = new JFrame("error!");
			lbl = new JLabel(msg, JLabel.CENTER);
			
			f.add(lbl, BorderLayout.CENTER);
			f.setBackground(Color.LIGHT_GRAY);
			f.setLocation(700, 500);
			
			f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
			f.setAlwaysOnTop(true);
			f.setVisible(true);
			f.setSize(350, 100);
		}
		else if(msg.contains("잘못된")) {
			f = new JFrame("error");
			lbl = new JLabel(msg, JLabel.CENTER);
			
			f.add(lbl, BorderLayout.CENTER);
			f.setBackground(Color.LIGHT_GRAY);
			f.setLocation(700, 500);
			
			f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
			f.setAlwaysOnTop(true);
			f.setVisible(true);
			f.setSize(350, 100);
		}
		else if(msg.contains("사용가능")) {
			f = new JFrame("사용 가능");
			lbl = new JLabel(msg, JLabel.CENTER);
			
			f.add(lbl, BorderLayout.CENTER);
			f.setBackground(Color.LIGHT_GRAY);
			f.setLocation(900, 500);
			
			f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
			f.setAlwaysOnTop(true);
			f.setVisible(true);
			f.setSize(350, 100);
		}
		else if(msg.contains("종료")) {
			if(msg.equals("5초")) {
				f = new JFrame("컴퓨터 종료");
				lbl = new JLabel(msg, JLabel.CENTER);
				
				f.add(lbl, BorderLayout.CENTER);
				f.setBackground(Color.LIGHT_GRAY);
				f.setLocation(900, 400);
				
				f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				
				f.setAlwaysOnTop(true);
				f.setVisible(true);
				f.setSize(350, 100);
			}
			else {
				f = new JFrame("컴퓨터 종료");
				lbl = new JLabel(msg, JLabel.CENTER);
				
				f.add(lbl, BorderLayout.CENTER);
				f.setBackground(Color.LIGHT_GRAY);
				f.setLocation(700, 400);
				
				f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				
				f.setAlwaysOnTop(true);
				f.setVisible(true);
				f.setSize(350, 100);
			}
		}
		else if(msg.contains("삭제")) {
			f = new JFrame("회원 삭제");
			lbl = new JLabel(msg, JLabel.CENTER);
			
			f.add(lbl, BorderLayout.CENTER);
			f.setBackground(Color.LIGHT_GRAY);
			f.setLocation(700, 400);
			
			f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
			f.setAlwaysOnTop(true);
			f.setVisible(true);
			f.setSize(350, 100);
		}
		else if(msg.contains("조회")) {
			String[] msgarr = msg.split("-");
			
			msg = "아이디 : " + msgarr[1] + ", 비밀번호 : " + msgarr[2] + ", 이름 : " + msgarr[3] +
				  ", 나이 : " + msgarr[4] +", 폰번호 : " + msgarr[5] + ", 남은시간: " + 
					Integer.parseInt(msgarr[6]) / 60 + "시간, 사용 중인 좌석 : " + msgarr[7];
			f = new JFrame("회원 조회 결과");
			lbl = new JLabel(msg, JLabel.CENTER);
			
			f.add(lbl, BorderLayout.CENTER);
			f.setBackground(Color.LIGHT_GRAY);
			f.setLocation(700, 400);
			
			f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
			f.setAlwaysOnTop(true);
			f.setVisible(true);
			f.setSize(600, 100);
		}
		else if(msg.contains("매출")) {
			if(msg.contains("ㅠㅠ")){
				f = new JFrame("ㅠㅠ매출없음");
				lbl = new JLabel(msg, JLabel.CENTER);
				
				f.add(lbl, BorderLayout.CENTER);
				f.setBackground(Color.LIGHT_GRAY);
				f.setLocation(700, 500);
				
				f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				
				f.setAlwaysOnTop(true);
				f.setVisible(true);
				f.setSize(350, 100);
			}
			else if(msg.contains("누적")) {
				f = new JFrame("현재까지 누적 매출");
				lbl = new JLabel(msg, JLabel.CENTER);
				
				f.add(lbl, BorderLayout.CENTER);
				f.setBackground(Color.LIGHT_GRAY);
				f.setLocation(700, 500);
				
				f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				
				f.setAlwaysOnTop(true);
				f.setVisible(true);
				f.setSize(350, 100);
			}
			else if(msg.contains("조건")) {
				String[] result = msg.split(",");
				f = new JFrame("조건별 매출조회");
				f.setLayout(new GridLayout(2, 0));
//				lbl = new JLabel(msg, JLabel.CENTER);
				
				l1 = new JLabel(result[1] + " 부터 ~ " + result[2] + " 까지의 매출 조회", JLabel.CENTER);
				l2 = new JLabel(result[3], JLabel.CENTER);
				
				l1.setLocation(10, 1);
				l2.setLocation(10, 50);
				
				f.add(l1);
				f.add(l2);
//				f.add(lbl, BorderLayout.CENTER);
				f.setBackground(Color.LIGHT_GRAY);
				f.setLocation(700, 500);
				
				f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				
				f.setAlwaysOnTop(true);
				f.setVisible(true);
				f.setSize(350, 100);
			}
			else if(msg.contains("타입")) {
				String [] result2 = msg.split(",");
				f = new JFrame("물건 타입별 매출 조회");
				f.setLayout(new GridLayout(4, 0));
//				lbl = new JLabel(msg, JLabel.CENTER);
				l1 = new JLabel(result2[1], JLabel.CENTER);
				l2 = new JLabel(result2[2], JLabel.CENTER);
				l3 = new JLabel(result2[3], JLabel.CENTER);
				l4 = new JLabel(result2[4], JLabel.CENTER);
				
				f.add(l1);
				f.add(l2);
				f.add(l3);
				f.add(l4);
				f.setBackground(Color.LIGHT_GRAY);
				f.setLocation(700, 500);
				
				f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				
				f.setAlwaysOnTop(true);
				f.setVisible(true);
				f.setSize(350, 300);
			}
		}
		else {
			f = new JFrame("????");
			lbl = new JLabel(msg, JLabel.CENTER);
			
			f.add(lbl, BorderLayout.CENTER);
			f.setBackground(Color.LIGHT_GRAY);
			f.setLocation(1100, 400);
			
			f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
			f.setAlwaysOnTop(true);
			f.setVisible(true);
			f.setSize(350, 100);
		}
	}
}
