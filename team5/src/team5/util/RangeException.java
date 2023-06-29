package team5.util;

@SuppressWarnings("serial")
public class RangeException extends RuntimeException{
	
	public RangeException() {	}

	RangeException(int start, int end) {
		super(start + " 와 " + end + " 사이의 값을 입력하세요.");
	}
	
	
	
//	//비밀번호
//public class RangeException1 extends RuntimeException{
//	
//	public RangeException1() {	}
//
//	RangeException1(String start, String end) {
//		super(start + " 와 " + end + " 사이의 값을 입력하세요.");
//	}
//	

	
//	//아이디
//public class RangeException2 extends RuntimeException {
//	
//	public RangeException2() { } //생성자 생성
//	
//	MyRangeException2(String limit) {
//		super("영문자로 입력해주세요 (한글X)");
//		
//	}
//	
//	//전화번호
//public class RangeException3 extends RuntimeException {
//	
//	public RangeException3() { } //생성자 생성
//	
//	MyRangeException3(String limit) {
//		super(start + " 와 " + end + " 사이의 값을 입력하세요.");
//		
//			}
//		}
//	}	
}
