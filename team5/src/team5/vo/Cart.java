package team5.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 오상현
 *@since 23/02/07
 *장바구니(카트)VO에서 필요한 변수 정의
 *유저id, 물품id, 물품가격, 물품수량
 */
public class Cart {
	//아이디, 물건번호, 물건 가격, 물건 수량
	private static Cart cart = new Cart();
	private Cart() {
		
	}

    public static Cart getCartInstance() {
        return cart;
    }
    private List<Product> carts = new ArrayList<Product>();
    private Product product;
    {
    carts.add(new Product(1001,"하의", "청바지",1,(carts.get(0).getProPrice()*carts.get(0).getProCnt())));
//	                carts.add(new Product("티셔츠", 1));
    }

    //장바구니에 담기
    public void addCart() {
        //담을 상품을 선택하세요 >> prodId >> Product
        // Product product = new Product("자켓, 3);
        {

        }

    }

    // 제거
    public void removeCart() {
        //제거할 상품을 선택하세요 >> proId >> Product
        // cart.remove(product);
    }
    // 조회
    public void display() {
        int sum = 0;
        carts.forEach(a -> {
            System.out.println(a);
        });
    }
//	    public void main(String[] args) {
//	        Cart cart = new Cart();
//	        cart.display();
//	    }

//	    public product findby() {

//	    }
    public static void main(String[] args) {
    Cart ca = new Cart();
//	    System.out.println(getCartInstance().carts);
    ca.display();

    } 
}


