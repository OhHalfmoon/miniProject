package team5.vo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author DG
 * @since 23/02/06 19:00 UBoard VO 생성
 * 
 * 
 *        23/02/07 17:00 필드 선언 및 getter setter, constructor, toString
 * 
 *        23/02/11 00:14 필드 명 변경 및 ToString메서드 선언
 * 
 *        남은 것 toString 가공 할 것 implements Serializable 추가
 * 
 */
public class UBoard implements Serializable {

	private int boardNo; // 글번호
	private String useId; // getInstance로 가져온 현재 로그인한 useId
	private String useAddress; // getInstance로 가져온 현재 로그인한 useAddress
	private String boardContent; // 중고거래 상품
	private int boardPrice; // 중고거래 상품의 가격
	private Date boardDate; // 글 작성 시간
	private boolean boardCommit; // 판매 여부
	private double x; // 글 작성한 회원의 경도
	private double y; // 글 작성한 회원의 위도

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

	public int getboardPrice() {
		return boardPrice;
	}

	public void setboardPrice(int boardPrice) {
		this.boardPrice = boardPrice;
	}

	public UBoard() {
		// TODO Auto-generated constructor stub
	}

	public UBoard(String useId, String useAddress, int boardNo, String boardContent, int boardPrice, Date boardDate,
			boolean boardCommit, double x, double y) {
		super();
		this.useId = useId;
		this.useAddress = useAddress;
		this.boardNo = boardNo;
		this.boardContent = boardContent;
		this.boardPrice = boardPrice;
		this.boardDate = boardDate;
		this.boardCommit = boardCommit;
		this.x = x;
		this.y = y;
	}

	public String getUseId() {
		return useId;
	}

	public void setUseId(String useId) {
		this.useId = useId;
	}

	public String getUseAddress() {
		return useAddress;
	}

	public void setUseAddress(String useAddress) {
		this.useAddress = useAddress;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getboardContent() {
		return boardContent;
	}

	public void setboardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public Date getBoardDate() {
		return boardDate;
	}

	public void setBoardDate(Date boardDate) {
		this.boardDate = boardDate;
	}

	public boolean isBoardCommit() {
		return boardCommit;
	}

	public void setBoardCommit(boolean boardCommit) {
		this.boardCommit = boardCommit;
	}

	@Override
	public String toString() {
		String print;
		print = String.format("%3d %10s %5s %7d %25s %10s", getBoardNo(), getUseId(), getboardContent(),
				getboardPrice(), new SimpleDateFormat("yy/MM/dd HH:mm:ss").format(getBoardDate()),
				isBoardCommit() ? "판매완료" : "판매중");
		return print;
	}

}
