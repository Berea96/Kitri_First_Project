package vo;

//요금표를 저장하는 클래스
public class TimeVO {

	private int money;
	private int time;
	
	
	public TimeVO() {
	}
	
	public TimeVO(int money, int time) {
		this.money = money;
		this.time = time;
	}
	
	//입력된 돈 getter, setter
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}

	//돈에 따른 시간 geeter, setter
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
}
