/**
 * @Author:sj
 * 
 * @Since 23/02/11
 * 
 * 리뷰VO
 * 중복 확인 추가
 */

package team5.vo;

import java.io.Serializable;
import java.util.Date;

public class Review implements Serializable {
	private Long rno;
	private int proId;
	private String content;
	private Date regDate;
	private String writer;
	private boolean duplication = false;

	public Review() {
		// TODO Auto-generated constructor stub
	}

	public Review(Long rno, int proId, String content, Date regDate, String writer, boolean duplication) {
		super();
		this.rno = rno;
		this.proId = proId;
		this.content = content;
		this.regDate = regDate;
		this.writer = writer;
		this.duplication = duplication;
	}

	public Long getRno() {
		return rno;
	}

	public void setRno(Long rno) {
		this.rno = rno;
	}

	public int getProId() {
		return proId;
	}

	public void setProId(int proId) {
		this.proId = proId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public boolean isDuplication() {
		return duplication;
	}

	public void setDuplication(boolean duplication) {
		this.duplication = duplication;
	}

	@Override
	public String toString() {
		return rno + "      " + content + "        " + regDate + "        " + writer;
	}

}
