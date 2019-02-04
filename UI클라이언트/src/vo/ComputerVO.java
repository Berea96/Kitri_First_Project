package vo;

//컴퓨터번호, 사용여부 저장 클래스
public class ComputerVO {
	
	private int no;
	private String use;
	
	//기본 생성자
	public ComputerVO() {
	}
	
	//컴퓨터 번호, 사용여부 생성자
	public ComputerVO(int no, String use) {
		this.no = no;
		this.use = use;
	}

	//컴퓨터 번호 getter, setter
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}

	//컴퓨터 사용여부 getter, setter
	public String getUse() {
		return use;
	}
	public void setUse(String use) {
		this.use = use;
	}
}
