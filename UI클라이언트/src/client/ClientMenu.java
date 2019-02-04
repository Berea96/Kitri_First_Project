package client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.InputMismatchException;
import java.util.Scanner;

import Chat.MultiChattingClient;
import Chat.ReceiveMessage;
import clientUi.lostTime;
import vo.MemberVO;

public class ClientMenu {
	
	private Socket s = null;
	
	private PrintWriter pw = null;
	private Scanner sis = null;
	private Scanner output = new Scanner(System.in);
	private String result = "";
	
	public ClientMenu(Socket s, PrintWriter pw, Scanner sis) {
		this.s = s;
		this.pw = pw;
		this.sis = sis;
	}
	
	public void start() throws IOException {
		while(true) {
			try {
				System.out.println("=============");
				System.out.println("| 서비스를 선택     |");
				System.out.println("|1. 회원가입       |");
				System.out.println("|2. 로그인          |");
				System.out.println("|3. 시간추가       |");
				System.out.println("|0. 종료하기       |");
				System.out.println("=============");
				int n = output.nextInt();
				
				
				if(n == 1) {
//					result = new Client().signUp();
					
					pw.println(n + "-" + result);
					pw.flush();
					System.out.println(sis.nextLine());
					continue; 
				}  
				else if(n == 2) {
					pw.println(n + "-");
					pw.flush();
					
					String[] empty = sis.nextLine().split(", ");
					
					String[] loginInfo = new Client().logIn(empty).split("-");
					MemberVO vo = new MemberVO(loginInfo[0], loginInfo[1], Integer.parseInt(loginInfo[2]));				
					
					pw.println(vo.getId() + "-" + vo.getPw() + "-" + vo.getUse());
					pw.flush();
					
					String[] loginInfo2 = new Client().loginSuccess(Integer.parseInt(sis.nextLine()), vo).split("-");
					
					if(loginInfo2[0].equals("1")) {
						
//						pw.println(0 + "-" + vo.getId());
//						pw.flush();
//						
//						int time = Integer.parseInt(sis.nextLine());
						lostTime lt = new lostTime(vo);
						new Thread(new Runnable() {
							public void run() {
								lt.start();
							}
						}).start();
						
						while(true) {
							
//							new Thread(new Runnable() {
//								public void run() {
//									while(true) {
//										int useNo = 1;
//										try {
//											useNo = new ServerDAO().selectShutDownInfo(vo.getId());
//											Thread.sleep(5000);
//											
//											if(useNo == 0) {
//												System.out.println("10초 후 강제종료됩니다.");
//												Thread.sleep(10000);
//												pw.println(9 + "-" + vo.getId() + "-" + vo.getPw() + "-" + vo.getUse());
//												pw.flush();
//												System.out.println("===============");
//												System.out.println(sis.nextLine());
//												System.out.println("컴퓨터를 강제 종료합니다.");
//												System.out.println("===============");
//												
//												System.exit(0);
//											}
//											else continue;
//										} catch (InterruptedException e) {}
//									}
//								}
//							}).start();
							try {	
								System.out.println("=============");
								System.out.println("| 서비스를 선택     |");
								System.out.println("|4. 게임하기       |");
								System.out.println("|5. 요리주문       |");
								System.out.println("|6. 시간추가       |");
								System.out.println("|7. 카운터 문의   |");
								System.out.println("|8. 쪽지             |");
								System.out.println("|9. 로그아웃       |");
								System.out.println("=============");
								int n2 = output.nextInt();
								
								if(n2 == 4) {
									PrintWriter pw2 = new PrintWriter(s.getOutputStream());
									Scanner sis2 = new Scanner(s.getInputStream());
									
									new Thread(new Runnable() {
										public void run() {
											
											pw2.println(n2 + "-게임하기");
											pw2.flush();
											
											System.out.println(sis2.nextLine());
										}
									}).start();
									
									System.out.println("게임 중...");
									continue;
								}
								
								if(n2 == 5) {
									pw.println(n2 + "-");
									pw.flush();
									
									String[] foodList = sis.nextLine().split(", ");
									
									PrintWriter pw2 = new PrintWriter(s.getOutputStream());
									Scanner sis2 = new Scanner(s.getInputStream());
									
									String result2 = new ClientLogin().orderFood(foodList);
									
									new Thread(new Runnable() {
										public void run() {
											
											pw2.println(result2 + "-" + vo.getId());
											pw2.flush();
											
											String result = sis2.nextLine();
											if(result.contains("부족")) {
												System.out.println(result);
											}
											else {
												new ReceiveMessage(result);
											}
										}
									}).start();
									continue;
								}
								else if(n2 == 6) {
									
									PrintWriter pw2 = new PrintWriter(s.getOutputStream());
									Scanner sis2 = new Scanner(s.getInputStream());
									
									String result2 = new Client().timePlus(vo);
									
									new Thread(new Runnable() {
										public void run() {
											
											pw2.println(n2 + "-" + result2);
											pw2.flush();
											
											String result = sis2.nextLine();
											if(result.contains("부족")) {
												System.out.println(result);
											}
											else {
												new ReceiveMessage(result);
											}
										}
									}).start();
									continue;
								}
								else if(n2 == 7) {
									new Thread(new Runnable() {
										public void run() {
											new MultiChattingClient(vo.getUse() + "번 pc").start();
										}
									}).start();
								}
								else if(n2 == 8) {
									result = new ClientLogin().sendMessage();
									
									pw.println(n2 + "-" + vo.getUse() + "-" + result);
									pw.flush();
								}
								else if(n2 == 9) {
									result = new ClientLogin().logout(vo);
									
									pw.println(n2 + "-" + vo.getId() + "-" + vo.getPw() + "-" + vo.getUse());
									pw.flush();
									System.out.println("===============");
									System.out.println(sis.nextLine());
									System.out.println("컴퓨터를 종료합니다.");
									System.out.println("===============");
									
									System.exit(0);
								}
								else {
									System.out.println("잘못된 번호 다시 선택하세요.");
							}
							}catch(InputMismatchException e){
								System.out.println("올바른 값을 입력해주세요");
								continue;
							}
						}
					}
					else if(loginInfo2[0].equals("3")) {
						result = new Client().timePlus(vo);
						pw.println(loginInfo2[0] + "-" + result);
						pw.flush();
						System.out.println(sis.nextLine());
						continue;
					}
					else continue;
				}
				else if(n == 3) {
					result = new Client().timePlus();
					
					pw.println(n + "-" + result);
					pw.flush();				
					System.out.println(sis.nextLine());
					continue;
				}
				else if(n == 0) {
					break;
				}
				else {
					System.out.println("잘못된 번호 다시 선택하세요.");
				}	
			}catch (InputMismatchException e) {
				System.out.println("올바른 값을 입력해주세요.");
				continue;
			} catch (IndexOutOfBoundsException e) {
				System.out.println("동시에 여러작업을 실시할 수 없습니다.");
				e.printStackTrace();
				continue;
			} 
		}
	}
}
