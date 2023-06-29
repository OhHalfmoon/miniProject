package team5.service;

import java.util.List;

import team5.vo.Order;

//import team5.Order;
public interface OrderService {
//구입한다
	void buy();
//취소한다
//	void removeOrder();
	
	Order findByOrder(String userID, int proID);
	Order findByReceipt(String userId);
	
	void displayOrder();
	
	List<Order>getReceipts();
}
