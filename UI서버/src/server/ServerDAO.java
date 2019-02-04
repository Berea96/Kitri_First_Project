package server;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import vo.FoodVO;
import vo.MemberVO;

//DB접속에 관한 메소드 총괄
public class ServerDAO {

	private Connection con = null;
	private PreparedStatement ps = null;
	private PreparedStatement ps2 = null;
	private PreparedStatement ps3 = null;
	private ResultSet rs = null;
	private ResultSet rs2 = null;
	private Scanner sc = new Scanner(System.in);
	private String sql = null;
	
	private int rownum = 0;
	private int rowcount = 0;
	private int time = 0;
	private int use = 0;
	private String result = "";
	
	public String checkId(String id) {
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//System.out.println("드라이버 로딩 성공");
			
			con = DriverManager.getConnection
			("jdbc:oracle:thin:@127.0.0.1:1521:xe", "project", "1234");
			//System.out.println("연결 성공");
			
			ps = con.prepareStatement
			("select * from member where id = ?");
			ps.setString(1, id);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				return "중복된 아이디";
			}
			else {
				return "사용가능한 아이디";
			}
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		} catch (SQLException e) {
			System.out.println("연결 실패!!!");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("알수 없는 오류");
		} finally {
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {}
		}
		return "";
	}
	
	public String checkPhone(String phone) {
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//System.out.println("드라이버 로딩 성공");
			
			con = DriverManager.getConnection
			("jdbc:oracle:thin:@127.0.0.1:1521:xe", "project", "1234");
			//System.out.println("연결 성공");
			
			ps = con.prepareStatement
			("select * from member where phone = ?");
			ps.setString(1, phone);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				return "중복된 번호";
			}
			else {
				return "사용가능";
			}
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		} catch (SQLException e) {
			System.out.println("연결 실패!!!");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("알수 없는 오류");
		} finally {
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {}
		}
		return "";
	}

	
	//회원가입 메소드
	public String insertMember(MemberVO vo) {
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//System.out.println("드라이버 로딩 성공");
			
			con = DriverManager.getConnection
			("jdbc:oracle:thin:@127.0.0.1:1521:xe", "project", "1234");
			//System.out.println("연결 성공");
			
			ps = con.prepareStatement
			("select * from member where id = ?");
			ps.setString(1, vo.getId());
			rs = ps.executeQuery();
			
			if(rs.next()) {
				return "존재하는 아이디입니다.";
			}
			else {
				if(vo.getPhone().equals("null")) {
					String sql = "insert into member values(?, ?, ?, ?, null, 0, 0)";
					ps = con.prepareStatement(sql);
					ps.setString(1, vo.getId());
					ps.setString(2, vo.getPw());
					ps.setString(3, vo.getName());
					ps.setInt(4, vo.getAge());
					
					rowcount = ps.executeUpdate();
				}
				else {
					String sql = "insert into member values(?, ?, ?, ?, ?, 0, 0)";
					ps = con.prepareStatement(sql);
					ps.setString(1, vo.getId());
					ps.setString(2, vo.getPw());
					ps.setString(3, vo.getName());
					ps.setInt(4, vo.getAge());
					ps.setString(5, vo.getPhone());
					
					rowcount = ps.executeUpdate();
				}
			
				if(rowcount == 1)
					return "가입 성공 시간 충전 후 로그인 해주세요.";
				else 
					return "가입 실패 입력값을 확인해보세요.";
			
			}
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		} catch (SQLException e) {
			System.out.println("연결 실패!!!");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("알수 없는 오류");
		} finally {
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {}
		}
		return "";
	}
	
	//로그인 시 컴퓨터의 공석을 보여주는 메소드
	public String selectComputer() {
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//System.out.println("드라이버 로딩 성공");
			
			con = DriverManager.getConnection
			("jdbc:oracle:thin:@127.0.0.1:1521:xe", "project", "1234");
			//System.out.println("연결 성공");
			
			ps = con.prepareStatement
			("select * from computer");
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int no = rs.getInt("no");
				String use = rs.getString("use");
				
				if(use.equals("T")) use = "사용 중";
				else use = "사용 가능";
				
				result += no + "번 : " + use + ", ";
			}
			
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		} catch (SQLException e) {
			System.out.println("연결 실패!!!");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("알수 없는 오류");
		} finally {
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {}
		}
		return result;
	}
	
	//로그인을 담당하는 메소드
	//각각의 상황에 따라 return값이 다르다
	public int selectMember(MemberVO vo) {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//System.out.println("드라이버 로딩 성공");
			
			con = DriverManager.getConnection
			("jdbc:oracle:thin:@127.0.0.1:1521:xe", "project", "1234");
			//System.out.println("연결 성공");
			
			ps = con.prepareStatement
			("select * from member where id = ? and pw = ?");
			ps.setString(1, vo.getId());
			ps.setString(2, vo.getPw());
			rs = ps.executeQuery();

			if(rs.next()) {
				String id = rs.getString("id");
				String pw = rs.getString("pw");
				time = rs.getInt("time");
				use = rs.getInt("use");
				if(id.equals("admin") && pw.equals("1234")) {
					return 5;
				}
			}
			else {
				return 0;
			}
			
			if(time == 0) {
				return 2;
			}
			else if(use != 0) {
				return 3;
			}
			else {
				ps = con.prepareStatement
				("update member set use = (select no from computer " +
										  "where no = ?) " + 
				 "where id = ?");
				ps.setInt(1, vo.getUse());
				ps.setString(2, vo.getId());
				rowcount = ps.executeUpdate();
				
				if(rowcount == 1) {
					ps = con.prepareStatement
					("update computer set use = 'T' " +
					 "where no = ?");
					ps.setInt(1, vo.getUse());
					ps.executeUpdate();
				}
				else {
					ps = con.prepareStatement
					("update computer set use = 'F' " +
					 "where no = ?");
					ps.setInt(1, vo.getUse());
					ps.executeUpdate();
				}
				return 1;
			}
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		} catch (SQLException e) {
			System.out.println("연결 실패!!!");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("알수 없는 오류");
		} finally {
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {}
		}
		return 4;
	}
	//강제종료시 사용종료메시지/실행
	public int selectShutDownInfo(String id) {
		int useNo = 1;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
	
			con = DriverManager.getConnection
			("jdbc:oracle:thin:@127.0.0.1:1521:xe", "project", "1234");
			
			ps = con.prepareStatement
						("select use from member where id = ?");
			ps.setString(1, id);
			rs = ps.executeQuery();
			rs.next();
			
			useNo = rs.getInt("use");
				
								
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		} catch (SQLException e) {
			System.out.println("연결 실패!!!");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("알수 없는 오류111");
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				rs.close();
				con.close();
			} catch (SQLException e) {}
		} 
		return useNo;
	}
	//회원 강제종료 메소드
	public String shutDown(String id) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
	
			con = DriverManager.getConnection
			("jdbc:oracle:thin:@127.0.0.1:1521:xe", "project", "1234");
			
			ps = con.prepareStatement
					("select * from member where id = ?");
			ps.setString(1, id);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				int use = rs.getInt("use");
				if(use == 0) {
					return "0";
				}
				else {
					ps = con.prepareStatement
							("update computer set use = 'F' " + 
									"where no = (select use from member " +
												"where id = ?)");
					ps.setString(1, id);
					rowcount = ps.executeUpdate();
					
					ps = con.prepareStatement
							("update member set use = 0 " + 
									"where id = ?");
					ps.setString(1, id);
					ps.executeUpdate();
						
					result = "강제 종료 시켰습니다.";
					}
			}
			else {
				return result;
			}
								
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		} catch (SQLException e) {
			System.out.println("연결 실패!!!");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("알수 없는 오류111");
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {}
		} 
		return result;
	}
	//회원삭제 메소드
	public String memberDel(String id) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");	
			
			con = DriverManager.getConnection
			("jdbc:oracle:thin:@127.0.0.1:1521:xe", "project", "1234");
			
			ps2 = con.prepareStatement("select use from member where id = ?");
			ps2.setString(1, id);
			rs2= ps2.executeQuery();
			
			if(rs2.next()) { 
				int use = rs2.getInt("use");
				
				if(use==0) {
					ps = con.prepareStatement
							("delete member where id = ?");
					ps.setString(1, id);
					ps.executeUpdate();	
					
					result = "회원 삭제 완료";
				}
				else if(use!=0 && use<=50){
					result = "사용중인 회원입니다.";				
				}
			}
			else return result;
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		} catch (SQLException e) {
			System.out.println("존재하지 않는 ID입니다");
//			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("알수 없는 오류");
			e.printStackTrace();
		} finally {
			try {
				ps2.close();
				con.close();
			} catch (SQLException e) {}
		} 
		return result;
	}
	//회원조회 메소드
	public String memberInfo(String s) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");	
			con = DriverManager.getConnection
			("jdbc:oracle:thin:@127.0.0.1:1521:xe", "project", "1234");
							
			ps = con.prepareStatement
			("select * from member where id = ?");
			ps.setString(1, s);
			rs = ps.executeQuery();
			
				while(rs.next()) {
					String id = rs.getString("id");
					String pw = rs.getString("pw");
					String name = rs.getString("name");
					int age = rs.getInt("age");
					String phone = rs.getString("phone");
					int time = rs.getInt("time");
					int use = rs.getInt("use");
					
					result = id+"-"+pw+"-"+name+"-"+age+"-"+phone+"-"+time+"-"+use;
				}
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		} catch (SQLException e) {
			System.out.println("연결 실패!!!");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("알수 없는 오류");
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {}
		} 
		return result;
	}
	
	public String updateTime(String id, int time) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			con = DriverManager.getConnection
			("jdbc:oracle:thin:@127.0.0.1:1521:xe", "project", "1234");
			
			ps = con.prepareStatement
			("select * from member where id = ?");
			ps.setString(1, id);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				ps = con.prepareStatement
				("update member set time = ? where id = ?");
				ps.setInt(1, time);
				ps.setString(2, id);
				rowcount = ps.executeUpdate();
				
				if(rowcount == 1) {
					result = "저장 완료";
				}
				else {
					result = "실패!!";
				}
			}
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		} catch (SQLException e) {
			System.out.println("연결 실패!!!");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("알수 없는 오류");
		} finally {
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {}
		}
		return result;
	}
	
	//로그아웃시에 사용할 메소드
	public String updateMember(MemberVO vo) {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection
					("jdbc:oracle:thin:@127.0.0.1:1521:xe", "project", "1234");
			
			ps = con.prepareStatement
					("select * from member where id = ? and pw = ?");
			ps.setString(1, vo.getId());
			ps.setString(2, vo.getPw());
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				ps = con.prepareStatement
						("update computer set use = 'F' where no = ?");
				ps.setInt(1, vo.getUse());
				ps.executeUpdate();
				
				ps = con.prepareStatement
						("update member set use = 0 where id = ?");
				ps.setString(1, vo.getId());
				ps.executeUpdate();
				
				return "로그아웃 성공";
			}
			else return "로그아웃 실패";
			
		} catch (ClassNotFoundException e) {
			System.out.println("=============");
			System.out.println("드라이버 로딩 실패");
			System.out.println("=============");
		} catch (SQLException e) {
			System.out.println("=============");
			System.out.println("연결 실패!!!");
			System.out.println("=============");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("=============");
			System.out.println("알수 없는 오류");
			System.out.println("=============");
		} finally {
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {}
		}
		return "?";
		}
	//시간 추가를 했을 때 db에 저장하는 부분 
	//금액이 부족, 동일, 많을 시 각각에 return값이 다름
	public String updateTime(String id, int time, int money) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//System.out.println("드라이버 로딩 성공");
			
			con = DriverManager.getConnection
			("jdbc:oracle:thin:@127.0.0.1:1521:xe", "project", "1234");
			//System.out.println("연결 성공");
			//System.out.println(id + " " + time + " " + money);
			
			ps = con.prepareStatement
			("select * from member where id = ?");
			ps.setString(1, id);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				ps = con.prepareStatement
				("select money from time " +
				 "where time = ? ");
				ps.setInt(1, time);
				rs = ps.executeQuery();
				if(rs.next()) {
					int selectmoney = rs.getInt("money");
					
					if(selectmoney > money) {
						result = "돈이 부족합니다.";
					}
					
					else if(selectmoney == money) {
						ps = con.prepareStatement
								("update member " +
										"set time = time + ? " +
										"where id = ?");
						ps.setInt(1, time);
						ps.setString(2, id);
						rowcount = ps.executeUpdate();
						
						ps2 = con.prepareStatement("insert into sales values(?, sysdate, ?, (select money from time where time = ?), 0,0,0 )");
						ps2.setString(1, id);
						ps2.setInt(2, time);
						ps2.setInt(3, time);
						ps2.executeUpdate();
						
						ps3 = con.prepareStatement("update sales set sales_sum = (nvl(food_price,0) * nvl(food_pcs,0) + nvl(charge_price,0)) where id = ?");
						ps3.setString(1, id);
						ps3.executeUpdate();
						if(rowcount == 1) {
							result = time / 60 + "시간 충전완료";
						}
					}
					
					else if(selectmoney < money) {
						ps = con.prepareStatement
								("update member " +
										"set time = time + ? " +
										"where id = ?");
						ps.setInt(1, time);
						ps.setString(2, id);
						rowcount = ps.executeUpdate();
						
						ps2 = con.prepareStatement("insert into sales values(?, sysdate, ?, (select money from time where time = ?), 0,0,0 )");
						ps2.setString(1, id);
						ps2.setInt(2, time);
						ps2.setInt(3, time);
						ps2.executeUpdate();
						
						ps3 = con.prepareStatement("update sales set sales_sum = (nvl(food_price,0) * nvl(food_pcs,0) + nvl(charge_price,0)) where id = ?");
						ps3.setString(1, id);
						ps3.executeUpdate();
						
						int backmoney = money - selectmoney;
						
						if(rowcount == 1) {
							return time / 60 + "시간 충전완료, 거스름 돈 : " + backmoney;
						}
						else {
							result = "오류";
						}
					}
				}
				else result = "잘못된 시간 입력";
			}
			else {
				result = "존재하지 않는 아이디입니다.";
			}
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		} catch (SQLException e) {
			System.out.println("연결 실패!!!");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("알수 없는 오류");
		} finally {
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {}
		}
		return result;
	}
	
	// 음식 종류별 매출 조회
	public String foodTypeSalesDAO() {
		try {			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection
					("jdbc:oracle:thin:@127.0.0.1:1521:xe", "project", "1234");
			//검색 sql
			ps = con.prepareStatement
			("select type, sum(f_sales) from food_sales group by type");
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String type = rs.getString("type");
				int typeSales = rs.getInt("sum(f_sales)");
				
				result += type + " 총 매출 : " + typeSales + "원,";				
			}
			
			System.out.println(result);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Sql오류!");
			System.out.println(e.getMessage());
			System.exit(0);
		} finally {
			//5.연결해제
			try {
				rs.close();
				ps.close();	
				con.close();
			} catch (SQLException e) {
			}
		}
		return result;
	}
	
	 //기간별 매출 조회
	 public String salesSearchDAO(String start, String end) {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con = DriverManager.getConnection
				("jdbc:oracle:thin:@127.0.0.1:1521:xe", "project", "1234");
				//검색 sql
				
				System.out.println(start + " : " + end);
				
				ps = con.prepareStatement
				("select sum(sales_sum) as total " + 
				 "from sales " + 
				 "where sales_date between to_date(?,'YYMMDD') " + 
				 "and to_date(? || ' 23:59:59', 'YYMMDD HH24:MI:SS')");
				ps.setString(1, start);
				ps.setString(2, end);
				rs = ps.executeQuery();
				
				if(rs.next()) {
					result = rs.getInt("total") + "원";
				}
				else {
					return result;
				}
				
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					System.out.println("Sql오류!");
					return "0";
				}
//			finally {
//					//5.연결해제
//					try {
//						rs.close();
//						ps.close();	
//						con.close();
//					} catch (SQLException e) {
//						// database 프로그램 자체의 문제가 발생했을때
//					}
//					//System.out.println("con연결해제성공");
//				}
			return result;
	 }
	//현재 총 매출
	public String currentSalesDAO() {
		
		String str = "";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection
			("jdbc:oracle:thin:@127.0.0.1:1521:xe", "project", "1234");
			
			//검색 sql
			String sql = "select sum(nvl(sales_sum,0)) as total from sales";
			PreparedStatement ps = con.prepareStatement(sql);
			//검색문 실행
			rs = ps.executeQuery();
			
			if(rs.next()) {
				int totalSales = rs.getInt("total");
				str = String.valueOf(totalSales) + "원";
				System.out.println("현재 총 매출 = "+str);
				}
			else {
				return result;
			}
			
			//System.out.println("\n"+">> (DB: 현재 매출 계산 완료) <<");
			rs.close();
			ps.close();	
			con.close();
			} catch (SQLException e) {
				System.out.println("Sql오류!");
				System.out.println(e.getMessage());
				System.exit(0);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
//		finally {
				//5.연결해제
//				try {
//					con.close();
//				} catch (SQLException e) {
//					// database 프로그램 자체의 문제가 발생했을때
//				}
				//System.out.println("con연결해제성공");
//			}					
		return str;						
 }
	
	//음식목록을 보여주는 메소드
public String selectFood() {
		
		try {
			//System.out.println("======================");
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//System.out.println("드라이버 로딩 성공");
			
			con = DriverManager.getConnection
			("jdbc:oracle:thin:@127.0.0.1:1521:xe", "project", "1234");
//			System.out.println("연결 성공");
//			System.out.println("======================");
			
			ps = con.prepareStatement
					("select * from food");
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int no = rs.getInt("no");
				String name = rs.getString("name");
				int time = rs.getInt("time");
				int price = rs.getInt("price");
				String type = "";
				
				if(time == 3) {
					type = "[면류]";
				}
				else if(time == 10) {
					type = "[밥류]";
				}
				else if(time == 7) {
					type = "[분식류]";
				}
				else if(time == 2) {
					type = "[음료류]";
				}
				
				result += type + " : " + no + " : " + name + " : " + 
						  time + "분 : " + price + "원, ";
			}
			
		} catch (ClassNotFoundException e) {
			System.out.println("=============");
			System.out.println("드라이버 로딩 실패");
			System.out.println("=============");
		} catch (SQLException e) {
			System.out.println("=============");
			System.out.println("연결 실패!!!");
			System.out.println("=============");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("=============");
			System.out.println("알수 없는 오류");
			System.out.println("=============");
		} finally {
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {}
		}
		return result;
	}

//public String cookFood(String id, FoodVO fvo) {
//	try {
//		System.out.println("======================");
//		Class.forName("oracle.jdbc.driver.OracleDriver");
//		System.out.println("드라이버 로딩 성공");
//		
//		con = DriverManager.getConnection
//		("jdbc:oracle:thin:@127.0.0.1:1521:xe", "project", "1234");
//		System.out.println("연결 성공");
//		System.out.println("======================");
//		
//		ps = con.prepareStatement
//		("select * from food " +
//		 "where no = ? ");
//		ps.setInt(1, fvo.getNo());
//		rs = ps.executeQuery();
//		int selectPrice = 0;
//		int time = 0;
//		String name = "";
//		String type = "";
//		
//		
//		if(rs.next()) {
//			selectPrice = rs.getInt("price") * fvo.getPcs();
//			time = rs.getInt("time") * fvo.getPcs();
//			name = rs.getString("name");
//			
//			System.out.println("주문 : " + name + " " + fvo.getPcs() + "개 " + 
//							   selectPrice + "원" + time + "분");
//			
//			if(time / fvo.getPcs() == 2) 
//				type = "음료류";
//			else if(time / fvo.getPcs() == 3)
//				type = "면류";
//			else if(time / fvo.getPcs() == 7)
//				type = "분식류";
//			else if(time / fvo.getPcs() == 10)
//				type = "밥류";
//			
//			if(selectPrice > fvo.getPrice()) {
//				return 1 + "-돈이 부족합니다.";
//			}
//			else if(selectPrice == fvo.getPrice()) {
//				ps = con.prepareStatement
//				("select * from food where no = ?");
//				ps.setInt(1, fvo.getNo());
//				rs = ps.executeQuery();
//				
//				if(rs.next()) {
//					ps = con.prepareStatement
//					("insert into sales values(?, sysdate, 0, 0, ?, ?, 0)");
//					ps.setString(1, id);
//					ps.setInt(2, selectPrice);
//					ps.setInt(3, fvo.getPcs());
//					ps.executeUpdate();
//					
//					ps = con.prepareStatement
//					("update sales set sales_sum = (nvl(food_price,0) * nvl(food_pcs,0)" + 
//												 "+ nvl(charge_price,0)) where id = ?");
//					ps.setString(1, id);
//					ps.executeUpdate();
//					
//					ps = con.prepareStatement
//					("insert into food_sales values(?, ?, sysdate, ?, ?, ?, ?)");
//					ps.setString(1, name);
//					ps.setInt(2, fvo.getNo());
//					ps.setInt(3, selectPrice / fvo.getPcs());
//					ps.setInt(4, fvo.getPcs());
//					ps.setInt(5, selectPrice);
//					ps.setString(6, type);
//					ps.executeUpdate();
//							
//					return time + "-" + name + " " + fvo.getPcs() + "개 배달";
//				}
//			}
//			else if(selectPrice < fvo.getPrice()) {
//				ps = con.prepareStatement
//				("select * from food where no = ?");
//				ps.setInt(1, fvo.getNo());
//				rs = ps.executeQuery();
//				
//				int backmoney = fvo.getPrice() - selectPrice;
//				
//				if(rs.next()) {
//					ps = con.prepareStatement
//					("insert into sales values(?, sysdate, 0, 0, ?, ?, 0)");
//					ps.setString(1, id);
//					ps.setInt(2, selectPrice);
//					ps.setInt(3, fvo.getPcs());
//					ps.executeUpdate();
//							
//					ps = con.prepareStatement
//					("update sales set sales_sum = (nvl(food_price,0) * nvl(food_pcs,0)" + 
//												 "+ nvl(charge_price,0)) where id = ?");
//					ps.setString(1, id);
//					ps.executeUpdate();
//					
//					ps = con.prepareStatement
//					("insert into food_sales values(?, ?, sysdate, ?, ?, ?, ?)");
//					ps.setString(1, name);
//					ps.setInt(2, fvo.getNo());
//					ps.setInt(3, selectPrice / fvo.getPcs());
//					ps.setInt(4, fvo.getPcs());
//					ps.setInt(5, selectPrice);
//					ps.setString(6, type);
//					ps.executeUpdate();
//					
//					return time + "-" + name + " " + fvo.getPcs() + "개 배달, 거스름돈 : " + backmoney;
//				}
//			}
//			else 
//				return 1 + "-알수없음...";
//		}
//		else 
//			return 1 + "-" + "이름을 다시 입력해주세요.";
//		
//	} catch (ClassNotFoundException e) {
//		System.out.println("=============");
//		System.out.println("드라이버 로딩 실패");
//		System.out.println("=============");
//	} catch (SQLException e) {
//		System.out.println("=============");
//		System.out.println("연결 실패!!!");
//		System.out.println("=============");
//		e.printStackTrace();
//	} catch (Exception e) {
//		System.out.println("=============");
//		System.out.println("알수 없는 오류");
//		System.out.println("=============");
//	} finally {
//		try {
//			rs.close();
//			ps.close();
//			con.close();
//		} catch (SQLException e) {}
//	}
//	return 1 + "-";
//}

public String cookFood(String id, FoodVO fvo) {
	try {
		System.out.println("======================");
		Class.forName("oracle.jdbc.driver.OracleDriver");
		System.out.println("드라이버 로딩 성공");
		
		con = DriverManager.getConnection
		("jdbc:oracle:thin:@127.0.0.1:1521:xe", "project", "1234");
		System.out.println("연결 성공");
		System.out.println("======================");
		
		ps = con.prepareStatement
		("select * from food " +
		 "where no = ? ");
		ps.setInt(1, fvo.getNo());
		rs = ps.executeQuery();
		int selectPrice = 0;
		int time = 0;
		String name = "";
		String type = "";
		
		
		if(rs.next()) {
			selectPrice = rs.getInt("price") * fvo.getPcs();
			time = rs.getInt("time") * fvo.getPcs();
			name = rs.getString("name");
			
			System.out.println(id + "님 주문 : " + name + " " + fvo.getPcs() + "개 " + 
							   selectPrice + "원" + time + "분");
			
			if(time / fvo.getPcs() == 2) 
				type = "음료류";
			else if(time / fvo.getPcs() == 3)
				type = "면류";
			else if(time / fvo.getPcs() == 7)
				type = "분식류";
			else if(time / fvo.getPcs() == 10)
				type = "밥류";
			
			ps = con.prepareStatement
					("select * from food where no = ?");
			ps.setInt(1, fvo.getNo());
			rs = ps.executeQuery();
			
			if(rs.next()) {
				ps = con.prepareStatement
						("insert into sales values(?, sysdate, 0, 0, ?, ?, 0)");
				ps.setString(1, id);
				ps.setInt(2, selectPrice);
				ps.setInt(3, fvo.getPcs());
				ps.executeUpdate();
				
				ps = con.prepareStatement
						("update sales set sales_sum = (nvl(food_price,0) * nvl(food_pcs,0)" + 
								"+ nvl(charge_price,0)) where id = ?");
				ps.setString(1, id);
				ps.executeUpdate();
				
				ps = con.prepareStatement
						("insert into food_sales values(?, ?, sysdate, ?, ?, ?, ?)");
				ps.setString(1, name);
				ps.setInt(2, fvo.getNo());
				ps.setInt(3, selectPrice / fvo.getPcs());
				ps.setInt(4, fvo.getPcs());
				ps.setInt(5, selectPrice);
				ps.setString(6, type);
				ps.executeUpdate();
				
				return time + "-" + name + " " + fvo.getPcs() + "개 배달";
			}
			else 
				return 1 + "-알수없음...";
		}
		else 
			return 1 + "-" + "이름을 다시 입력해주세요.";
		
	} catch (ClassNotFoundException e) {
		System.out.println("=============");
		System.out.println("드라이버 로딩 실패");
		System.out.println("=============");
	} catch (SQLException e) {
		System.out.println("=============");
		System.out.println("연결 실패!!!");
		System.out.println("=============");
		e.printStackTrace();
	} catch (Exception e) {
		System.out.println("=============");
		System.out.println("알수 없는 오류");
		System.out.println("=============");
	} finally {
		try {
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {}
	}
	return 1 + "-";
}

public String selectTime(String id) {
	try {

		Class.forName("oracle.jdbc.driver.OracleDriver");
		con = DriverManager.getConnection
		("jdbc:oracle:thin:@127.0.0.1:1521:xe", "project", "1234");
			
		ps = con.prepareStatement
		("select time from member " +
		 "where id = ?");
		ps.setString(1, id);
		rs = ps.executeQuery();
		rs.next();
		
		time = rs.getInt("time");
		
	} catch (ClassNotFoundException e) {
		System.out.println("=============");
		System.out.println("드라이버 로딩 실패");
		System.out.println("=============");
	} catch (SQLException e) {
		System.out.println("=============");
		System.out.println("연결 실패!!!");
		System.out.println("=============");
		e.printStackTrace();
	} catch (Exception e) {
		System.out.println("=============");
		System.out.println("알수 없는 오류");
		System.out.println("=============");
	} finally {
		try {
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {}
	}
	return String.valueOf(time);
}
	//로그인 시 시간을 감소시키는 부분(미구현)
	public int decreaseTime(String id) {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection
			("jdbc:oracle:thin:@127.0.0.1:1521:xe", "project", "1234");
		
			ps = con.prepareStatement
			("select time, use from member " +
			 "where id = ?");
			ps.setString(1, id);
			rs = ps.executeQuery();
			
			rs.next();
			use = rs.getInt("use");
			
			if(use != 0) {
				ps = con.prepareStatement
				("update member set time = time - 1 where id = ?");
				ps.setString(1, id);
				ps.executeUpdate();
				
				ps = con.prepareStatement("select time from member where id = ?");
				ps.setString(1, id);
				rs = ps.executeQuery();
				rs.next();
				time = rs.getInt("time");
				
				if(time == 0) {
					return 0;
				}
			}
			
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		} catch (SQLException e) {
			System.out.println("연결 실패!!!");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("알수 없는 오류");
		} finally {
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {}
		}
		return time;
	}
}
