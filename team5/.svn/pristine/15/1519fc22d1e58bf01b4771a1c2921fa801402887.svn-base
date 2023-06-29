package team5.vo;

import java.util.Scanner;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/*
 * @Author 고은채
 * 
 * @Since 23/02/06
 * 
 * 23/02/08
 * Member VO 담당
 * (userId, name, password, addr, phoneNum, admin)
 * 
 * kakao api를 이용하여 경도와 위도를 받아올 변수 (x, y) 추가
 * 
 */

public class Member implements Comparable<Member>, Serializable{
	
	private String userId ; //pk
	private String name;
	private String password;
	private String addr;
	private String phoneNum; 
	boolean admin;
//	private Date regDate; //가입일

	
	// 경도, 위도 받아오기
	private double x;
	private double y;
	//private String roadAddress;
	
	
	public Member() {
		//Member 기본 생성자
	}

	
	public Member(String name, String userId, String password, String addr, String phoneNum, double x, double y, boolean admin ) {
			super();
			this.userId = userId;
			this.name = name;
			this.password = password;
			this.addr = addr;
			this.phoneNum = phoneNum;
			this.x = x;
			this.y = y;
			this.admin = admin;
		}

	
	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getAddr() {
		return addr;
	}


	public void setAddr(String addr) {
		this.addr = addr;
	}


	public String getPhoneNum() {
		return phoneNum;
	}


	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}


	public boolean isAdmin() {
		return admin;
	}


	public void setAdmin(boolean admin) {
		this.admin = admin;
	}


	public double getX() {
		return x;
	}


	public void setX(double x) {
		this.x = x;
	}


	public double getY() {
		return y;
	}


	public void setY(double y) {
		this.y = y;
	}


	@Override
	public int compareTo(Member o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	@Override
	public String toString() {
		return "[이름=" + name + ", 아이디=" + userId + ", 비밀번호=" + password + ", 주소=" + addr
				+ ", 전화번호=" + phoneNum + ", 경도=" + x + ", 위도=" + y + ", 관리자여부=" + admin + "]";
	}
	
	
}