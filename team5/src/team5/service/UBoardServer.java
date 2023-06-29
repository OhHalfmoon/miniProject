package team5.service;

import team5.vo.Member;
import team5.vo.UBoard;

/**
 * @author DG
 * @since 02/07
 * 
 * UBoardService생성
 * ubsRegister()
 * ubsModifty()
 * ubsRemove()
 * usbList()
 * 생성
 * 
 * 02/08
 * 메소드 명 변경
 * 
 * 02/12
 * 파일의 영속화를 위한 saveIo메소드 추가
 * 영속화한 파일의 글번호 값 가져오는 cntAdd()메소드 추가
 */
public interface UBoardServer {

	void ubsRegister();			//글 쓰기
	void ubsModify();			//글 수정	
	void ubsRemove();			//글 삭제
	void ubsList();				//글 목록
//	void saveIo();				//파일의 영속화를 위한 IOStream 구현부에서 private으로 변경
	void cntBno();				//영속화 시킨 파일에서 글번호를 가져오는 메소드
	
	
	
	String toStrDistanceFormat(double d);			//km, 와 m를 변환해주는 메소드
	double getDistance(Member m1, UBoard m2);		//m1의 경도, 위도 와 m2의 경도 위도를 계산해서 거리를 반환 한다.
//	UBoard findBy(String userId);

}
