package team5;

import java.io.Console;

import team5.service.CartService;
import team5.service.CartServiceImpl;
import team5.service.MemberService;
import team5.service.MemberServiceImpl;
import team5.service.OrderService;
import team5.service.OrderServiceImpl;
import team5.service.ProductService;
import team5.service.ProductServiceImpl;
import team5.service.ReviewService;
import team5.service.ReviewServiceImpl;
import team5.service.UBoardServer;
import team5.service.UBoardServerImpl;
import team5.util.RangeException;
import team5.util.Utils;

/**
 * @author DG
 * @since 02/11 vinteageMain 생성 로그인 및 회원가입 (Member 연결) 게시글 등록 (UBoard 연결)
 *
 *
 *        추가사항 각 service별 연결 switch문 정리
 *
 */
public class vintageMain {

	static boolean run = true;
	static MemberService ms = MemberServiceImpl.getInstance();
	static ProductService ps = ProductServiceImpl.getInstance();
	static ReviewService rs = ReviewServiceImpl.getInstance();
	static CartService cs = CartServiceImpl.getInstance();
	static OrderService os = OrderServiceImpl.getInstance();
	static UBoardServerImpl ubs = new UBoardServerImpl();
	static RangeException re = new RangeException();

	public static void main(String[] args) throws Exception {
		cs.getCarts().clear();
		
		while (run) {
			try {
				mainRun();
			} catch (NumberFormatException e) {
				System.err.println("잘못된 입력입니다.");
			} catch (RangeException e) {
				System.err.println(e.getMessage());
			}
		}
	}

	static void mainRun() throws Exception {

		int input = Utils.checkRange(Utils.nextInt("1.로그인 2. 회원가입 3.종료"), 1, 3);
		switch (input) {
		case 1:
			ms.login();
			if (ms.getLoginUser() != null) {
				loginRun();
			}
			break;
		case 2:
			ms.register();
			break;
		case 3:
			run = false;
			break;
		default:
			break;
		}
	}

	static void productBuy() {
		if (ms.getLoginUser().getUserId().equals(("admin"))) {
			int adminInput = Utils.checkRange(Utils.nextInt("1. 제품등록 2. 제품수정 3. 제품삭제 4. 종료"), 1, 4);

			pro: switch (adminInput) {
			case 1:
				ps.register(ms.getLoginUser().getUserId());
				break pro;
			case 2:
				ps.modify(ms.getLoginUser().getUserId());
				break;
			case 3:
				ps.remove(ms.getLoginUser().getUserId());
			default:
				break;
			}
		} else {
			boolean psRun = true;
			while (psRun) {
				try {
					int psInput = Utils.checkRange(Utils.nextInt("구매하실 상품 코드를 입력하세요"), 1001, 3999);
					ps.info(psInput);
					
//					rs.reviewList(psInput);
//					if (psInput != 0) {
//						int rsInput = Utils.checkRange(Utils.nextInt("1. 장바구니 담기 2. 후기등록 3. 후기삭제 4. 종료"), 1, 4);
//
//						switch (rsInput) {
//						case 1:
//							cs.addCart(psInput);
//							break;
//						case 2:
//							rs.register(ms.getLoginUser().getUserId(), psInput);
//							break;
//						case 3:
//							rs.remove(ms.getLoginUser().getUserId(), psInput);
//							break;
//						case 4:
//							psRun = false;
//							break;
//						default:
//							break;
//						}
//					}
					psRun = false;
				} catch (NullPointerException | NumberFormatException e) {
					System.err.println("잘못된 입력 값 입니다");
				} catch (RangeException e) {
					System.err.println(e.getMessage());
				}
			}
		}
	}

	static void ubsRun() {
		boolean ubsCheck = true;
		while (ubsCheck) {
			try {
				switch (Utils.checkRange(Utils.nextInt("1. 중고거래 구매목록 2. 중고거래 판매등록 3. 중고거래 수정 4. 중고거래 삭제 5. 종료"), 1,
						5)) {
				case 1:
//					ubs.ubsList();
					ubs.usedBuy();
					break;
				case 2:
					ubs.ubsRegister();
					break;
				case 3:
					ubs.ubsModify();
					break;
				case 4:
					ubs.ubsRemove();
					break;
				case 5:
					ubsCheck = false;
					break;
				}
			} catch (RangeException e) {
				System.err.println(e.getMessage());
			}catch(NullPointerException | NumberFormatException e) {
				System.err.println("잘못된 입력 값 입니다");
			}
		}
	}

	static void loginRun() {

		loginWhile: while (true) {
			try {
				int input = Utils.checkRange(
						Utils.nextInt("1. 상품보기 2. 장바구니 3. 장바구니 삭제 4. 구매 5. 구매내역조회 6. 중고거래 7. 회원정보수정 8. 회원탈퇴 9. 로그아웃 "), 1, 9);
				switch (input) {
				case 1:
					ps.list();
					productBuy();
					break;
				case 2:
					cs.displayCart();
					break;
				case 3:
					cs.removeCart();
					break;
				case 4: 
					os.buy();
					break;
				case 5:
					os.displayOrder();
					break;
				case 6:
					ubsRun();
					break;
				case 7:
					ms.modify();
					break;
				case 8:
					ms.remove();
					break loginWhile;
				case 9:
					
					break loginWhile;
				}
			} catch (RangeException e) {
				System.err.println(e.getMessage());
			} catch(NullPointerException | NumberFormatException e) {
				System.err.println("잘못된 입력 값 입니다.");
			}
		}
	}

}
