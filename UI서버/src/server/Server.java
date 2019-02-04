package server;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

import Chat.ReceiveMessage;
import vo.FoodVO;
import vo.MemberVO;

//서버측 thread담당 부분
public class Server extends Thread{
	
	private Socket s = null;
	private Scanner sis = null;
	private PrintWriter pw = null;
	private InetSocketAddress clientIp = null;
	private ServerDAO dao = null;
	private String result = "";
	
	public Server(Socket s) {
		this.s = s;
	}

	public void run() {
		try {
			clientIp = (InetSocketAddress)s.getRemoteSocketAddress();
			System.out.println(clientIp.getHostName() + "연결 중");
		
			sis = new Scanner(s.getInputStream());
			pw = new PrintWriter(s.getOutputStream());
			
			if(clientIp.getHostName().equalsIgnoreCase("KJpc")) {
				while(true) {
					String[] input = sis.nextLine().split("-");
					
					if(input[0].equals("0")) {
						MemberVO vo = new MemberVO(input[1], input[2]);
						pw.println(new ServerDAO().selectMember(vo));
						pw.flush();
					}
					else if(input[0].equals("1")) {
						pw.println(new ServerDAO().memberInfo(input[1]));
						pw.flush();
					}
					else if(input[0].equals("2")) {
						pw.println(new ServerDAO().memberDel(input[1]));
						pw.flush();
					}
					else if(input[0].equals("3")) {
						pw.println(new ServerDAO().shutDown(input[1]));
						pw.flush();
					}
//					else if(input[0].equals("4")) {
//						
//					}
//					else if(input[0].equals("5")) {
//						
//					}
//					else if(input[0].equals("6")) {
//						
//					}
					else if(input[0].equals("7")) {
						pw.println(new ServerDAO().currentSalesDAO());
						pw.flush();
					}
					else if(input[0].equals("8")) {
						pw.println(new ServerDAO().salesSearchDAO(input[1], input[2]));
						pw.flush();
					}
					else if(input[0].equals("9")) {
						pw.println(new ServerDAO().foodTypeSalesDAO());
						pw.flush();
					}
				}
			}
			
			else {
				while(true) {
					String[] input = sis.nextLine().split("-");
					
					if(input[0].equals("0")) {
						if(input[1].contains("food")) {
							pw.println(new ServerDAO().selectFood());
							pw.flush();
					
//							String[] foodData = sis.nextLine().split("-");
						}
						else {
							String id = input[1];
							
							result = new ServerDAO().selectTime(id);
							
							pw.println(result);
							pw.flush();
						}
						continue;
					}
					else if(input[0].equals("1")) {
						String id = input[1];
						String pw = input[2];
						String name = input[3];
						int age =Integer.parseInt(input[4]);
						String phone = input[5];
						MemberVO vo = new MemberVO(id, pw, name, age, phone);
						
						result = new ServerDAO().insertMember(vo);
						
						this.pw.println(result);
						this.pw.flush();
					}
					//로그인부(미구현)
					else if(input[0].equals("2") && input.length < 2) {
						result = new ServerDAO().selectComputer();
						pw.println(result);
						pw.flush();
					}
					else if(input[0].equals("2") && input.length > 2) {	
						String id = input[1];
						String pw = input[2];
						int no = Integer.parseInt(input[3]);
						
						MemberVO vo = new MemberVO(id, pw, no);
						
						System.out.println(id + " " + pw + " " + no);
						
						this.pw.println(new ServerDAO().selectMember(vo));
						this.pw.flush();
					}
					else if(input[0].equals("3")) {
						String id = input[1];
						int time = Integer.parseInt(input[2]);
						int money = Integer.parseInt(input[3]);
						
						result = new ServerDAO().updateTime(id, time, money);
						
						pw.println(result);
						pw.flush();
					}
//					else if(input[0].equals("4")) {
//						PrintWriter pw2 = new PrintWriter(s.getOutputStream());
//						
//						new Thread(new Runnable() {
//							public void run() {
//								System.out.println(input[1]);
//								
//								pw2.println("게임해");
//								pw2.flush();
//							}
//						}).start();
//					}
					else if(input[0].equals("5")) {
//						pw.println(new ServerDAO().selectFood());
//						pw.flush();
//						
//						String[] foodData = sis.nextLine().split("-");
						
						int no = Integer.parseInt(input[1]);
//						int money = Integer.parseInt(foodData[1]);
						int pcs = Integer.parseInt(input[2]);
						String id = input[3];
//						FoodVO vo = new FoodVO(no, money, pcs);
						FoodVO vo = new FoodVO(no, pcs);
						String[] food = new ServerDAO().cookFood(id, vo).split("-");
						
						PrintWriter pw2 = new PrintWriter(s.getOutputStream());
						
						new Thread(new Runnable() {
							public void run() {
								try {
									if(Integer.parseInt(food[0]) > 1) {
										
										sleep(Integer.parseInt(food[0]) * 1000);
										pw2.println(food[1]);
										pw2.flush();
									}
									else {
										pw2.println(food[1]);
										pw2.flush();
									}
								} catch (NumberFormatException e) {
									e.printStackTrace();
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						}).start();
					}
					else if(input[0].equals("6")) {
						String id = input[1];
						int time = Integer.parseInt(input[2]);
						int money = Integer.parseInt(input[3]);
						
						PrintWriter pw2 = new PrintWriter(s.getOutputStream());
						
						new Thread(new Runnable() {
							
							public void run() {
								result = new ServerDAO().updateTime(id, time, money);
								
								pw2.println(result);
								pw2.flush();
							}
						}).start();
					}
					else if(input[0].equals("8")) {
						int no = Integer.parseInt(input[1]);
						String msg = input[2];
						
						new Thread(new Runnable() {
							public void run() {
								new ReceiveMessage(msg, no);
							}
						}).start();
					}
					else if(input[0].equals("9")) {
						String id = input[1];
						String pw = input[2];
						int no = Integer.parseInt(input[3]);
						
						MemberVO vo = new MemberVO(id, pw, no);
						
						result = new ServerDAO().updateMember(vo);
						
						this.pw.println(result);
						this.pw.flush();
					}
					else if(input[0].equals("10")) {
						String id = input[1];
						
						result = new ServerDAO().checkId(id);
						
						pw.println(result);
						pw.flush();
					}
					else if(input[0].equals("11")) {
						String phone = input[1];
						
						result = new ServerDAO().checkPhone(phone);
						
						pw.println(result);
						pw.flush();
					}
				}
			}
		} catch (IOException e) {
			System.out.println("======================");
			System.out.println("접속오류!!!");
			System.out.println("======================");
			
		} catch (NoSuchElementException e) {
			
		} finally {
			sis.close();
			pw.close();
			try {
				s.close();
				System.out.println("연결해체");
			} catch (IOException e) {}
		}
	}
}
