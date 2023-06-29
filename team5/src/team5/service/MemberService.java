package team5.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import team5.vo.Member;

/**
 * @author 고은채
 * @since 0208
 * 회원가입, 회원 로그인, 회원정보 수정, 회원정보 저장, 회원 탈퇴
 * 
 * @since 0209
 * 회원정보를 상세조회 (단일조회) 
 * 
 */
public interface MemberService  {
	//회원을 등록 (가입)
	void register() throws UnsupportedEncodingException, Exception;
	
	//회원정보를 상세조회 (단일조회)
	void Memberlist();
	
	//회원 로그인
	void login();
	
	//회원정보를 수정한다
	void modify();
	
	//회원정보를 저장한다
	void save();
	
	//회원정보를 삭제한다 (탈퇴)
	void remove();
	
	//Member getLoginUser()
	Member findBy(String userID);
	Member getLoginUser();
	
	
	
//	//주소에 대한 경도 , 위도를 받아온다
//	void getJSONData(String apiUrl) throws Exception;
//	void findAddress(String addr) throws UnsupportedEncodingException, Exception;
	
	
}
		