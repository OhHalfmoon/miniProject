package team5.vo;

import java.io.Serializable;

public class Order implements Serializable {
	
	//주문번호
	private int orderNum;
	private String userId;
	private String proName;
	private int proId;
    private int proCnt;
	private int proPrice;
	
	public Order() {
		
	}

	public Order(int orderNum, String userId, String proName, int proId, int proCnt, int proPrice) {
		super();
		this.orderNum = orderNum;
		this.userId = userId;			
		this.proName = proName;
		this.proId = proId;
		this.proCnt = proCnt;
		this.proPrice = proPrice;
	}

	public int getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}
	
	public int getProId() {
		return proId;
	}

	public void setProId(int proId) {
		this.proId = proId;
	}
	
	public int getProCnt() {
		return proCnt;
	}

	public void setProCnt(int proCnt) {
		this.proCnt = proCnt;
	}

	public int getProPrice() {
		return proPrice;
	}

	public void setProPrice(int proPrice) {
		this.proPrice = proPrice;
	}

	@Override
	public String toString() {
		return "결제번호"+ getOrderNum()+" "+ getUserId()+" "+ getProName()+" "+getProCnt()+"개: "+getProPrice();
	}
			
}		