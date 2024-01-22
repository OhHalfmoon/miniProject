package team5.service;

import static team5.util.Utils.*;

import java.util.ArrayList;
import java.util.List;

import team5.util.RangeException;
//import team5.util.MyRangeException;
import team5.vo.Product;

/**
 * @author 오상현
 * 
 * @since 23/02/07
 * 장바구니 특성상 자체 클래스(객체)생성 및 보유 보다는 기능위주 구현이 필요하므로
 * cart클래스 없이 바로 서비스 인터페이스 및 클래스 생성
 * 등록 조회기능 추가
 * 
 * @since 23/02/08
 * 더미데이터 추가 
 * - 카트 더미데이터 3개
 * - 물품재고 더미데이터 3개
 * 등록 조회기능 수정
 * 삭제기능 추가 : 호출시 인덱스를 출력하고, 입력받은 인덱스를 삭제
 * 메서드명 수정
 * 
 * @since 23/02/09
 * ProductServiceImpl 클래스와 동기화 작업 진행중
 * 
 * @since 23/02/12
 * 메서드명 수정, 싱글톤 작업 진행
 * 
 * @since 23/02/13
 * 싱글톤 수정,  add 및 remove에서 수량을 받을 수 있도록 수정
 * 영속화 작업 완료
 * 
 * @since 23/02/13
 * 장바구니에서 수량 및 총액이 나올 수 있게 수정
 * 유저ID를 받아와 주문 추가
 * 장바구니 내역을 order에서 주문
 * 주문내역서 생성
 * 주문내역조회에서 전체 유저의 총액이 나오는 문제 해결
 * cnt값이 추가되면서 장바구니에서 삭제할 시 cnt값과 가격 변경 수정
 * 수량값 변경이 물품 구매 후 진행되자, 장바구니에 여러번 무한히 담고 결제가 가능한 오류 발견
 * 장바구니에 담으면서 물류 차감 및 장바구니 내역 초기화시 다시 물량을 올리는 기능 구현
 * 반복문으로 인해 proCnt가 누적해서 차감되는 오류 수정
 */

public class CartServiceImpl implements CartService{
	
	
	
	//싱글톤
	private static CartService cartService = new CartServiceImpl();  
	
	private CartServiceImpl() {
		
	}
	
    public static CartService getInstance() {
        return cartService;
    }    

    private List<Product> carts = new ArrayList<Product>(); 
    
    public List<Product> getCarts() {
		return carts;
	}
	public void setCarts(List<Product> carts) {
		this.carts = carts;
	}

	private Product product;
    ProductService prod = ProductServiceImpl.getInstance();
    MemberService mem = MemberServiceImpl.getInstance();
    
    {
// 더미데이터
//    carts.add(new Product(1001,"하의", "청바지",1,3000));
    } 
	
	@Override
	//카트에 물건을 추가하는 메서드 (productserviceimple에서 호출받는 용도)
	// 추가한 만큼 product 물량 차감
	public void addCart(int proId) {
		mem = MemberServiceImpl.getInstance();
		prod = ProductServiceImpl.getInstance();		
		product = prod.findBy(proId);
		int proCnt = nextInt("담을 수량을 입력하세요");
		if ((proCnt <= product.getProCnt())&&(proCnt>0) ) {	
			int cntPrice = proCnt*product.getProPrice();
			carts.add(new Product(product.getProId(), product.getCategory(), product.getProName(), proCnt,cntPrice, mem.getLoginUser().getUserId()));
			System.out.println("장바구니에 물건이 추가되었습니다.");
			prod.decreaseProCnt(proId);			
		} else if (proCnt <= 0) {
			System.err.println("1 이상의 값을 입력바랍니다");
		} else
			System.err.println("재고를 초과하는 수량입니다");
	}

	@Override
	//카트에 담긴 물건을 삭제하는 메서드, 삭제한 만큼 product 물량 증가
	public void removeCart() {
		prod = ProductServiceImpl.getInstance();
	
				Product cartMem = findByCartMemberId(mem.getLoginUser().getUserId());
				boolean containMem = carts.contains(cartMem);
				if (!containMem) {
					 System.err.println("장바구니에 상품이 없습니다");
					 return;
				}
		
				for(int i = 0; i < carts.size(); i++) {
					if (mem.getLoginUser().getUserId().equals(carts.get(i).getWriter())) {
						System.out.println("상품코드 : "+carts.get(i).getProId()+"  "+ carts.get(i).getProName()+" : "+carts.get(i).getProCnt()+"개, "+ carts.get(i).getProPrice()+"원");
					}
//					boolean removeRun = true;
//					while(removeRun) {
//						try {
//							for (Product p : carts) {
//								System.out.println("상품코드 : "+p.getProId()+"  "+ p.getProName()+" : "+p.getProCnt()+"개, "+ p.getProPrice()+"원");
//								
//							} 					
//					else if (!mem.getLoginUser().getUserId().equals(carts.get(i).getWriter())){
//						System.err.println("장바구니에 상품이 없습니다");
//						return;
//					}
				}
				int proId = nextInt("상품 코드를 입력하세요");
				int productIdx = findByCartIdx(proId);
				product = prod.findBy(proId);
				Product caProduct = findByCart(proId);
				boolean contain = carts.contains(caProduct);
						if (!caProduct.getWriter().equals(mem.getLoginUser().getUserId())) {
							 System.err.println("장바구니에 해당 상품이 없습니다");
							 return;
						}
						if (!contain) {				 
							 System.err.println("장바구니에 상품이 없습니다");
							 return;
						} 
						int proCnt = nextInt("삭제할 수량을 입력하세요");		
						if ((carts.get(productIdx).getProCnt() >= proCnt)&&(proCnt>0)) {							
							Product minProd = carts.get(productIdx);
							minProd.setProCnt(minProd.getProCnt()-proCnt);
							minProd.setProPrice(minProd.getProPrice()-(proCnt*product.getProPrice()));
							System.out.println("삭제가 완료되었습니다.");			
							if (carts.get(productIdx).getProCnt()==0) {
								carts.remove(carts.get(productIdx));
							}
							product.setProCnt(product.getProCnt()+proCnt);
							prod.save();
							return;
						} else if (proCnt<=0) {
							System.err.println("1 이상의 값을 입력바랍니다");
						} else	{
							System.err.println("장바구니에 담긴 수량보다 초과하여 입력하였습니다.");						
						}
//			} catch (RangeException e) {
//				System.err.println("장바구니에 없는 상품입니다");
//			} catch (NumberFormatException e) {
//				System.err.println("잘못된 입력입니다.");
//			} 
//		}						
	}

	@Override
	//현재 장바구니 내용조회 메서드
	public void displayCart() {
		Product cartMem = findByCartMemberId(mem.getLoginUser().getUserId());
		boolean containMem = carts.contains(cartMem);
		if (!containMem) {
			 System.err.println("장바구니에 상품이 없습니다");
			 return;
		}
		for(int i = 0; i < carts.size(); i++) {
			if (mem.getLoginUser().getUserId().equals(carts.get(i).getWriter())) {
				System.out.println("상품코드 : "+carts.get(i).getProId()+"  "+ carts.get(i).getProName()+" : "+carts.get(i).getProCnt()+"개, "+ carts.get(i).getProPrice()+"원");
			} 
		}
//		for (Product p : carts) {
//			System.out.println("상품코드 : "+p.getProId()+"  "+ p.getProName()+" : "+p.getProCnt()+"개, "+ p.getProPrice()+"원");
//		} 
		int sum = 0;
		for(int i = 0; i < carts.size(); i++) {
			if (mem.getLoginUser().getUserId().equals(carts.get(i).getWriter())) {
				sum = sum + carts.get(i).getProPrice();
			}
		}
//		for (Product p : carts) {
//			sum = sum + p.getProPrice();
//		}
		System.out.println("총 합계 : "+ sum+"원");		
	}
	//물품번호를 입력하여 장바구니에 담긴 해당 물품번호의 물건 찾기
	@Override
	public Product findByCart(int proId) {
		Product product = null;
		for (int i = 0; i < carts.size(); i++) {
			if (carts.get(i).getProId() == proId) {
				product = carts.get(i);
				break;
			}
		}
		return product;
	}
	
	public Product findByCartMemberId(String mem) {
		Product product = null;
		for (int i = 0; i < carts.size(); i++) {
			if (carts.get(i).getWriter() == mem) {
				product = carts.get(i);
				break;
			}
		}
		return product;
	}
	//물품번호를 입력하여 장바구니에 담긴 해당 물품번호의 인덱스번호 찾기
	public int findByCartIdx(int proId) {
		int cartsIdx = 0;
		for (int i = 0; i < carts.size(); i++) {
			if (carts.get(i).getProId() == proId) {
				cartsIdx = i;
				break;
			}
		}
		return cartsIdx;
	}
}
