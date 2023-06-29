package team5.service;

import static team5.util.Utils.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

//import team5.util.MyRangeException;
import team5.service.MemberService;
//import team5.util.MyRangeException;
import team5.util.Utils;
import team5.vo.Member;

/**
 * @author 고은채
 * 
 * @Since 23/02/08 회원가입(register) , 로그인(login) 메서드 완성
 * 
 * @Since 23/02/09 고객 더미 데이터 생성 회원가입, 로그인 (중복 아이디, 중복 전화번호) 조건 추가 경도 위도 주소 받아오는
 *        메소드(미완성)
 * 
 * @Since 23/02/11 싱글톤 처리 회원탈퇴(remove), 회원수정(modify) 메서드 추가 고객 주소의 경도 위도를 받아오는
 *        메서드 (kakao api)
 * 
 * @Since 23/02/12 회원탈퇴메소드 수정 파일 영속화 작업 (파일 저장하는 save() 추가)
 * 
 * @Since 23/02/13 더미데이터 변경, findBy메서드 수정
 * 
 * @Since 23/02/14  login메서드 수정 
 * 					modify 메서드 수정(스캐너로 아이디 받아오는 대신 비밀번호 받아오기)
 * 					remove 메서드 수정
 * 					(현재 로그인한 아이디를 member객체에 넣어놓은 후 
 * 					조건식으로 받아온 아이디의 비밀번호와 동일하면 삭제 처리) 
 * 
 * @Since 23/02/15  
	
 * 		
 * 
 * 
 */

public class MemberServiceImpl implements MemberService {

	public List<Member> members = new ArrayList<Member>();
	double x;
	double y;
	String addr;
	String name;
	String userID;
	String password;
	static boolean run = true;

	/**
	 * 싱글톤 생성 & 더미데이터 파일 영속화 
	 * 
	 * <param>
	 *
	 */
	private static MemberService memberService = new MemberServiceImpl(); // 인스턴스 생성, 클래스가 로드될 때 1번 실행

	public MemberServiceImpl() {
	} // 생성자 생성

	public static MemberService getInstance() {
		return memberService;
	} // 메소드 생성

	private Member loginUser;
	{
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("member.ser"))) {
			members = (List<Member>) ois.readObject();
		} catch (FileNotFoundException e) {
			// 인스턴스 초기화 블록을 이용해서 더미 고객 데이터 사용
			 members.add(new Member("구경찰", "police", "112", "서울특별시 구로구 가마산로 235(구로동)", "01001120112", 126.887014988407, 37.4945320625106, false));
	         members.add(new Member("복지관", "center", "93", "서울특별시 구로구 디지털로31길 93(구로동)", "028520525", 126.8908157, 37.4867825, false));
	         members.add(new Member("더조은", "tj", "306", "서울특별시 구로구 디지털로 306(구로동)", "028381680", 126.897336395657, 37.4859231683292, false));
	         members.add(new Member("관리자", "admin", "team5", "서울특별시 종로구 청와대로 1(세종로)", "01012345678", 126.9767701, 37.586652, true)); 
	         save();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}

	public Member getLoginUser() { // getter
		return loginUser;
	}

//
//	/**
//	 * 회원가입 시 비밀번호 입력값 범위 검사
//	 */
//	String checkRange(String password) {
//		return checkRange(password, "4", "10"); // 4자리 ~ 10자리 입력
//	}
//	
//	String checkRange(String password, String start, String end) {
//		if (password.length() <= 4 || password.length() <= 11 ) {
//			throw new RangeException1(String start, String end);
//		} 
//		return password;
//	}

	
	
//	/**
//	 * 회원가입 시 아이디 입력값 범위 검사 (한글 입력 x)
//	 */
//	String checkRange2 (String userId) {
//		return checkRange2(userId);
//	}
//	
//	String checkRange2(String userId) {
//		char userId =
//		if (!(userId >= 'A' && userId <= 'Z') && !(userId >= 'a' && userId <= 'z')) {
//            return false; {
//			throw new MyRangeException2 (limit);
//		} 
//		return userId;
//	}
	
//	
//	
//	/**
//	 * 회원가입 시 전화번호 입력값 범위 검사 (11자 미만 ,11자 초과 안됨)
//	 */
//	String checkRange3(String PhoneNum) {
//		return checkRange3(PhoneNum, "10"); // 4자리 ~ 10자리 입력
//	}
//	
//	String checkRange3 (String phoneNum, String num) {
//		if (phoneNum.length() <= 11) {
//			throw new MyRangeException();
//		} 
//		return phoneNum;
//	}
	
	


	/**
	 * 회원 등록 메서드 <param>
	 */
	public void register() throws UnsupportedEncodingException, Exception {

		System.out.println("=====================회원가입=====================");
		String name = Utils.nextLine("귀하의 이름을 입력하세요 :");
		String userID = Utils.nextLine("사용할 아이디를 입력하세요 :"); // 영어 + 숫자
		// 중복된 아이디 등록 방지
		if (findBy(userID) != null) {
			System.out.println("중복 아이디가 존재합니다.");
			return;
		}

		String password = (Utils.nextLine("사용할 비밀번호를 입력하세요 : "));
		// 4자리 ~ 10자리까지의 비밀번호 입력
		if( password.length() == 0 || password.length() < 4 || password.length() > 10) {
			System.err.println("4자리~10자리 사이에 값을 입력하세요.");
			return;
		}
		String addr = Utils.nextLine("거주하시는 주소를 입력하세요(도로명 주소 포함) : ");
		findAddress(addr);
		
		String phoneNum = Utils.nextLine("전화번호를 입력하세요 : ");
		// 중복된 전화번호 등록 방지
		if (findByPhone(phoneNum) != null) {
			System.err.println("현재 전화번호는 사용 중입니다");
			return;
		} else if ( phoneNum.length() < 11) {
			System.err.println("적게 입력하셨습니다. 11자리의 휴대폰 번호의 형식을 입력해주세요.");
			return;
		
		} else if ( phoneNum.length() > 11) {
			System.err.println("더 입력하셨습니다. 11자리의 휴대폰 번호의 형식을 입력해주세요.");
			return;
		
		} else { 
			Member member = new Member(name, userID, password, addr, phoneNum, x, y, false);
			members.add(member);
			save();
			System.out.println(name + "님 가입을 축하합니다.");
			System.out.println(members.size() + "번째 회원이 되셨습니다.");
		}
	}
//		
//	String checkRange() {
//		return checkRange(num, 0, 100);
//	}
//	int checkRange(String num, int start, int end) {
//		if (num <  start || num > end) {
//			throw new MyRangeException(start, end);
//		}
//		return num;
//	}
//		
		


	/**
	 * findBy - ID userID 조회
	 */
	public Member findBy(String userId) {
		Member member = null;
		for (int i = 0; i < members.size(); i++) {
			if (members.get(i).getUserId().equals(userId)) {
				member = members.get(i);
				break;
			}
		}
		return member;
	}

	
	/**
	 * checkPhoneNum - PhoneNum members PhoneNum 조회 <param> phoneNum
	 * 
	 */
	public Member findByPhone(String phoneNum) {
		Member member = null;
		for (int i = 0; i < members.size(); i++) {
			if (members.get(i).getPhoneNum().equals(phoneNum)) {
				member = members.get(i);
				break;
			}
		}
		return member;
	}

	
	/**
	 * checkPassword - password members password 조회 
	 * <param> password
	 *
	 */
	public Member findByPw(String password) {
		Member member = null;
		for (int i = 0; i < members.size(); i++) {
			if (members.get(i).getPassword().equals(password)) {
				member = members.get(i);
				break;
			}
		}
		return member;
	}

	
	
	/**
	 * 회원정보 저장 파일 저장 메소드 
	 * <param>
	 * 
	 */
	public void save() {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("member.ser"))) {
			oos.writeObject(members); // students list의 내용을 파일화
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	
	/**
	 * 회원정보 수정 <param>
	 * 
	 */
	public void modify() {
		System.out.println("=================회원정보 수정================");
//		Member member = findBy(userID);
//		if (findBy(userID) == null) {
//			System.out.println("등록되지 않은 id입니다.");
//			return;
//		MemberService mem = MemberServiceImpl.getInstance();
		
		
		Member member = findByPw(Utils.nextLine("현재 비밀번호를 입력하세요"));
		
		if (member != findByPw(loginUser.getPassword())) {
			System.err.println("비밀번호가 틀렸습니다.");
		} else {
			// 입력한 아이디가 존재할 때
			switch (Utils.checkRange(Utils.nextInt("1. 비밀번호 수정  2. 주소 수정 3. 회원리스트 "), 1, 3)) {
			case 1:
				String input;
				input = (Utils.nextLine("비밀번호를 입력해주세요."));
				if(input.length() >= 4 && input.length() <= 10) {
					member.setPassword(input);
					System.out.println("비밀번호 변경이 완료되었습니다");
					break;
				} else {
					System.err.println("4자리 ~ 10자리 사이에 비밀번호를 입력해주세요");
					break;
				}
		
			case 2:
				member.setAddr(Utils.nextLine("변경할 주소를 입력하세요 ★ 도로명 주소를 포함 "));
				System.out.println("주소가 " + loginUser.getAddr() + "로 변경되었습니다");
				break;
				
			case 3:
				Member memberAdmin = findBy(loginUser.getUserId());
				if(memberAdmin.getUserId().equals("admin")) {
					Memberlist();
				} else {
					System.err.println("권한이 없습니다");
				}
				break;
				
			}
		}
	}
	
	/**
	 * 회원 탈퇴 <param>
	 * 
	 */
	public void remove() {
		boolean removewhile = true;
		
		System.out.println("==============회원 탈퇴===============");
		Member member = findBy(loginUser.getUserId());
		if (member.getPassword().equals(Utils.nextLine("비밀번호를 입력해주세요."))) {
			members.remove(member);
			System.out.println("삭제되었습니다");
			removewhile = false;
			} else {
				System.out.println("비밀번호가 다릅니다");
		}
	}
	
	
	
	/**
	 * 회원정보 상세조회 단일조회
	 * 
	 */
	public void Memberlist() {
		System.out.println("회원가입한 고객의 정보");
		System.out.println("=================================================");
		for (int i = 0; i < members.size(); i++) {
			System.out.println(members.get(i));
		}
	}

	
	/**
	 * 회원 로그인 <param>
	 * 아이디를 입력하면 그 아이디의 비밀번호와 일치하는지 수정 
	 */
	public void login() {
		boolean loginwhile = true;
		
		while (loginwhile) {
		System.out.println("==================로그인을 해주세요==================");
		String userID = Utils.nextLine("아이디를 입력하세요 : ");
		String pw = Utils.nextLine("비밀번호를 입력하세요 : ");

		Member member = findBy(userID);
		if (findBy(userID) == null) {
			System.err.println("등록되지 않은 id입니다.");
			return;
		} else if (findBy(userID) != null) {
			if (!(member.getPassword().equals(pw))) {
				System.err.println("비밀번호가 틀렸습니다");
			} else {
				loginUser = member;
				System.out.println("[" + loginUser.getName() + "]님께서 로그인하셨습니다.");
				loginwhile = false;
				}
			}
		}
	}

	
	
	
	/**
	 * REST API로 통신하여 받은 JSON형태의 데이터를 String으로 받아오는 메소드 주소 받아오는 메서드 (경도, 위도)
	 * 
	 */
	private static String getJSONData(String apiUrl) throws Exception {
		HttpURLConnection conn = null;
		StringBuffer response = new StringBuffer();

		// 인증키 - KakaoAK하고 한 칸 띄워주셔야해요!
		//사용할 경우 "" 사이에 카카오 api 키 입력
		String auth = "KakaoAK " + "";

		// URL 설정
		URL url = new URL(apiUrl);

		conn = (HttpURLConnection) url.openConnection();

		// Request 형식 설정
		conn.setRequestMethod("GET");
		conn.setRequestProperty("X-Requested-With", "curl");
		conn.setRequestProperty("Authorization", auth);

		// request에 JSON data 준비
		conn.setDoOutput(true);

		// 보내고 결과값 받기
		int responseCode = conn.getResponseCode();
		if (responseCode == 400) {
			System.out.println("400:: 해당 명령을 실행할 수 없음");
		} else if (responseCode == 401) {
			System.out.println("401:: Authorization가 잘못됨");
		} else if (responseCode == 500) {
			System.out.println("500:: 서버 에러, 문의 필요");
		} else { // 성공 후 응답 JSON 데이터받기

			Charset charset = Charset.forName("UTF-8");
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), charset));

			String inputLine;
			while ((inputLine = br.readLine()) != null) {
				response.append(inputLine);

			}
		}
		return response.toString();
	}

	
	/**
	 * 주소 받아오는 메서드 (경도, 위도)
	 * 
	 */
	public void findAddress(String addr) throws UnsupportedEncodingException, Exception {
		// 검색 갯수
		int size = 30;

		// 검색 키워드
		String keyword = addr;

		String result = getJSONData(
				String.format("https://dapi.kakao.com/v2/local/search/address.json?query=%s&size=%d",
						URLEncoder.encode(keyword, "utf-8"), size));
		Gson gson = new Gson();
		Map<String, ?> map = gson.fromJson(result, Map.class);

		((List<?>) (map.get("documents"))).forEach(o -> {
			if (o instanceof Map) {
				Map<String, ?> m = (Map<String, ?>) o;
				m = (Map<String, ?>) m.get("road_address");
				x = Double.valueOf(String.valueOf(m.get("x")));
				y = Double.valueOf(String.valueOf(m.get("y")));
			}
		});
	}

}
