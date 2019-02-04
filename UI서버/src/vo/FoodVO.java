package vo;

//음식목록을 저장하는 클래스
public class FoodVO {

	private int no;
	private String name;
	private int time;
	private int price;
	private int pcs;

	//기본생성자
	public FoodVO() {
	}
	
	public FoodVO(int no, int price, int pcs) {
		this.no = no;
		this.pcs = pcs;
		this.price = price;
	}
	
	public FoodVO(int no, int pcs) {
		this.no = no;
		this.pcs = pcs;
	}

	//음식목록 생성자
	public FoodVO(int no, String name, int time, int price) {
		this.no = no;
		this.name = name;
		this.time = time;
		this.price = price;
	}
	
	//음식 번호 getter, setter
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}

	//음식이름 getter, setter
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	//요리시간 getter, setter
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	
	//가격 getter, setter
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}

	public int getPcs() {
		return pcs;
	}
	public void setPcs(int pcs) {
		this.pcs = pcs;
	}
}
