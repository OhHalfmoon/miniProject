package team5.service;

import java.util.List;

import team5.vo.Product;

public interface CartService {
	
	List<Product>getCarts();
//	List<Product>setCarts();	
	
	//물건을 담는다
	void addCart(int proId);
	
	//물건을 비운다
	void removeCart();
	
	//물품 내용을 조회한다
	void displayCart();
	
	//물품번호 입력하여 해당물품번호 물건 찾기
	Product findByCart(int proId);
	
	//물품번호 입력하여 해당물품번호 물건 인덱스 찾기
	int findByCartIdx(int proId);

}
