package admin;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.InputMismatchException;
import java.util.Scanner;

import Chat.MultiChattingClient;

public class AdminMenu {
	private Socket s = null;
	
	private PrintWriter pw = null;
	private Scanner sis = null;
	private Scanner output = new Scanner(System.in);
	private String result = "";
	private int cnt = 3;
	
	public AdminMenu(Socket s, PrintWriter pw, Scanner sis) {
		this.s = s;
		this.pw = pw;
		this.sis = sis;
	}

	public void start() throws IOException {
		
		while(true) {
			try {
				String[] admin = new Admin().admin().split("-");			
				
				if(!admin[0].equals("0")) {
					
					pw.println(0 + "-" + admin[0] + "-" + admin[1]);
					pw.flush();
					
					result = sis.nextLine();
					
					if(result.equals("5")) {
						System.out.println("로그인 성공");
						while(true) {
							System.out.println("=================");						
							System.out.println("1. 회원 조회	|");
							System.out.println("2. 회원 삭제	|");
							System.out.println("3. 강제 종료	|");
							System.out.println("4. 채팅 시작	|");
							System.out.println("5. 매출              |");
							System.out.println("6. 로그아웃	|");
//							System.out.println("6. 현재 총 매출	|");
//							System.out.println("7. 기간별 매출	|");
//							System.out.println("8. 음식 종류별 매출	|");
							System.out.println("=================");
							int n = output.nextInt();
//							MemberVO vo = new MemberVO();
							if(n==1) {
								System.out.print("id : ");
								pw.println(n + "-" + output.next());
								pw.flush();
								
								String[] info = sis.nextLine().split("-");
								if(info[0].equals("")) {
									System.out.println("없는 회원 아이디입니다.");
									continue;
								}
								
								System.out.println("조회한 회원의 정보 ");
								System.out.println("아이디 : " + info[0]);
								System.out.println("비밀번호 : " + info[1]);
								System.out.println("이름 : " + info[2]);
								System.out.println("나이 : " + info[3]);
								System.out.println("핸드폰 번호 : " + info[4]);
								System.out.println("남은 시간 : " + info[5]);
								System.out.println("사용중인 컴퓨터 : " + info[6]);
							}
							else if(n==2) {
								System.out.print("id : ");
								pw.println(n + "-" + output.next());
								pw.flush();
								
								result = sis.nextLine();
								if(result.equals("")) {
									System.out.println("없는 회원 아이디입니다.");
									continue;
								}
								else {
									System.out.println(result);
								}
							}
							else if(n==3) {
								System.out.print("id : ");
								pw.println(n + "-" + output.next());
								pw.flush();
								
								result = sis.nextLine();
								
								if(result.equals("")) {
									System.out.println("없는 회원 아이디입니다.");
									continue;
								}
								else if(result.equals("0")) {
									System.out.println("로그인 되어있지 않은 아이디입니다.");
								}
								else {
									System.out.println(result);
								}
							}
							else if(n==4) {
								new Thread(new Runnable() {
									public void run() {
										new MultiChattingClient("관리자 pc").start();
									}
								}).start();
							}
							else if(n == 5) {
								while(true) {
									System.out.println("================");
									System.out.println("|7. 현재 누적 매출     |");
									System.out.println("|8. 조회 기간 별 매출 |");
									System.out.println("|9. 음식별 매출         |");
									System.out.println("|10. 돌아가기           |");
									System.out.println("================");
									int n2 = output.nextInt();
									
									if(n2 == 7) {
										pw.println(n2);
										pw.flush();
										
										result = sis.nextLine();
										if(result.equals("")) {
											System.out.println("ㅠㅠ매출이 없습니다...");
										}
										else {
											System.out.println("현재까지의 누적 매출");
											System.out.println(">>" + result + "<<");
										}
									}
									else if(n2 == 8) {
										while(true) {
											System.out.print("시작일(yymmdd) : ");
											String start = output.next();
											System.out.print("최종일 : ");
											String end = output.next();
											
											pw.println(n2 + "-" + start + "-" + end);
											pw.flush();
											
											result = sis.nextLine();
											
											if(result.equals("")) {
												System.out.println("ㅠㅠ매출이 없습니다...");
												break;
											}
											else if(result.equals("0")) {
												System.out.println("날짜를 잘못 입력하셨습니다.");
											}
											else {
												System.out.println(start + " 부터 " + end + "까지의 매출");
												System.out.println(">>" + result + "<<");
												break;
											}
										}
									}
									else if(n2 == 9) {
										pw.println(n2);
										pw.flush();
										
										String [] result2 = sis.nextLine().split(",");
										
										System.out.println(result2[0]);
										System.out.println(result2[1]);
										System.out.println(result2[2]);
										System.out.println(result2[3]);
									}
									else if(n2 == 10) {
										break;
									}
									else {
										System.out.println("다시 입력해 주세요.");
									}
								}
							}
							else if(n==6) {
								System.out.println("===============");
								System.out.println("컴퓨터를 종료합니다.");
								System.out.println("===============");
								
								pw.close();
								sis.close();
								output.close();
								s.close();
								System.exit(0);
							}
						}
					}
					else {
						System.out.println("아이디, 비밀번호를 잘못 입력 하셨습니다.");
						System.out.println(--cnt + "번 더 잘못입력시 강제 종료 됩니다.");
						
						if(cnt == 0) {
						pw.close();
						sis.close();
						output.close();
						s.close();
						System.exit(0);
						}
						else {
							continue;
						}
					}
				}
				else {
					System.out.println("잘못 된 정보!!");
					continue;
				}
			} catch (InputMismatchException e) {
				System.out.println("올바른 값을 입력해주세요.");
				continue;
			}
		}
	}
}
