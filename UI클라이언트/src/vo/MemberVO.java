package vo;


//회원정보를 저장하는 클래스
public class MemberVO {
	
	private String id;
	private String pw;
	private String name;
	private int age;
	private String phone;
	private int use;
	private int time;
	//기본생성자
	public MemberVO() {
	}
	public MemberVO(String id, String pw) {
		this.id = id;
		this.pw = pw;
	}
	//로그인시 생성자
	public MemberVO(String id, String pw, int use) {
		this.id = id;
		this.pw = pw;
		this.use = use;
	}

	//폰번호 미기입시 생성자
	public MemberVO(String id, String pw, String name, 
				    int age) {
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.age = age;
	}

	//모든 정보 생성자
	public MemberVO(String id, String pw, String name,
				    int age, String phone) {
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.age = age;
		this.phone = phone;
	}
	
	public String toString() {
		return this.id + "-" + this.pw + "-" + this.name + "-" + this.age + "-" +
			   this.phone + "-" + this.use + "-" + this.time;
	}
	
	//아이디용 getter, setter
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	//암호용 getter, setter
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}

	//이름용 getter, setter
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	//나이용 getter, setter
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

	//폰번호용 getter, setter
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	//사용여부용 getter, setter
	public int getUse() {
		return use;
	}
	public void setUse(int use) {
		this.use = use;
	}
	//남은 이용시간에 관한 getter, setter
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
}
