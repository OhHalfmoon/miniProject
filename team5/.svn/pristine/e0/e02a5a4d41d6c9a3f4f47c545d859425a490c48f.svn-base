package team5.service;

import static team5.util.Utils.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import team5.vo.Product;
import team5.vo.Review;

public class ReviewServiceImpl implements ReviewService {
	/**
	 * @Author:우성준
	 * 
	 * @Since 23/02/08 후기기능담당클래스
	 * 
	 *        IOstream추가, 싱글톤추가, 메소드 지역변수추가(String writer) 멤버서비스 및 오더서비스 필요
	 * 
	 *        상품별 글번호 추가
	 *
	 */

	private List<Review> reviewList = new ArrayList<Review>();
	ProductService ps;
	OrderService os;
	private static Long rno = 1L;

	private static ReviewService reviewService = new ReviewServiceImpl();

	private ReviewServiceImpl() {
	}

	public static ReviewService getInstance() {
		return reviewService;
	}

	{
		try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("Review.ser"))) {
			reviewList = (List<Review>) inputStream.readObject();
		} catch (FileNotFoundException e) {
			reviewList.add(new Review(rno++, 1001, "너무 멋있어요!", new Date(), "sj", true));
			reviewList.add(new Review(rno++, 1001, "입을만 해요", new Date(), "pp", true));
			reviewList.add(new Review(rno++, 1001, "그럭저럭...", new Date(), "ap", true));
			reviewList.add(new Review(rno++, 1001, "JAVAMAN", new Date(), "sh", true));
			reviewList.add(new Review(rno++, 1001, "힘들어......", new Date(), "ec", true));
			reviewList.add(new Review(rno++, 1001, "살려줘;;", new Date(), "dg", true));
			reviewList.add(new Review(rno++, 1001, "다음에도 또 살 것 같아요^^", new Date(), "yl", true));
			reviewList.add(new Review(rno++, 1001, "hahahahaha!!!!!", new Date(), "jm", true));
			reviewList.add(new Review(rno++, 1001, "오졌죠 화이팅!!", new Date(), "sm", true));
			reviewList.add(new Review(rno++, 1001, "기장이 생각보다 짧아요..", new Date(), "rm", true));
			reviewList.add(new Review(rno++, 1001, "good!", new Date(), "aa", true));
			reviewList.add(new Review(1L, 2002, "많이 커요ㅜ", new Date(), "JAVAMAN", true));	
			reviewList.add(new Review(2L, 2002, "마실 나갈용 정도입니다", new Date(), "wsj", true));	
			reviewList.add(new Review(3L, 2002, "가성비 괜찮은듯요", new Date(), "rigo", true));
			reviewList.add(new Review(4L, 2002, "지인한테도 추천할만 해요", new Date(), "golovkin", true));	
			reviewList.add(new Review(5L, 2002, "좀 애매하네요", new Date(), "mayweather", true));	
			reviewList.add(new Review(6L, 2002, "믿고 입는 당근사!!", new Date(), "pacquiao", true));
			reviewList.add(new Review(1L, 3003, "예뻐요!!", new Date(), "bivol", true));	
			reviewList.add(new Review(2L, 3003, "쪼금 아쉬워요...", new Date(), "canelo", true));	
			reviewList.add(new Review(3L, 3003, "선물하기 좋아요!", new Date(), "biden", true));	
			save();

		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}

//	후기등록
	@Override
	public void register(String writer, int proId) {
		boolean run = true;
		os = OrderServiceImpl.getInstance();
		ps = ProductServiceImpl.getInstance();
		if (os.findByOrder(writer, proId) == null || checkDuplicatian(writer, proId) == true) {
			System.err.println("접근권한이 없습니다");
			return;
		}
		register: while (run) {
			String s = nextLine("후기를 입력하세요 >");
			if (s.equals("")) {
				System.err.println("빈칸은 안됩니다");
				continue register;
			}
			rno = ps.findBy(proId).getRnoCnt() + 1L;
			Review r = new Review(rno, proId, s, new Date(), writer, true);
			ps.findBy(proId).setRnoCnt(rno);
			reviewList.add(r);
			save();
			ps.save();
			run = false;
		}
	}

//	후기삭제
	@Override
	public void remove(String writer, int proId) {
		os = OrderServiceImpl.getInstance();
		ps = ProductServiceImpl.getInstance();
		if (os.findByOrder(writer, proId) == null || checkDuplicatian(writer, proId) == false) {
			System.err.println("접근권한이 없습니다");
			return;
		}
		rno = ps.findBy(proId).getRnoCnt();
		findBy(writer, proId).setDuplication(false);
		reviewList.remove(findBy(writer, proId));
		ps.findBy(proId).setRnoCnt(--rno);
		save();
		ps.save();
	}

//	상품별 후기리스트
	@Override
	public void reviewList(int proId) {
		System.out.println();
		System.out.println(" 후기         내용                            등록날짜                 쟉성자              ");
		System.out.println(
				"======================================================================================================");
		for (int i = 0; i < reviewList.size(); i++) {
			if (reviewList.get(i).getProId() == proId)
				System.out.println(reviewList.get(i));
		}
	}

	public Review findBy(String writer, int proId) {
		Review review = null;
		for (int i = 0; i < reviewList.size(); i++) {
			if (reviewList.get(i).getWriter().equals(writer) && reviewList.get(i).getProId() == proId) {
				review = reviewList.get(i);
				break;
			}
		}
		return review;
	}

	public Review findByProId(int proId) {
		Review review = null;
		for (int i = 0; i < reviewList.size(); i++) {
			if (reviewList.get(i).getProId() == proId) {
				review = reviewList.get(i);
				break;
			}
		}
		return review;
	}

	public boolean checkDuplicatian(String writer, int proId) {
		if (findBy(writer, proId) == null)
			return false;
		else
			return true;
	}

	public void save() {
		try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("review.ser"))) {
			objectOutputStream.writeObject(reviewList);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
