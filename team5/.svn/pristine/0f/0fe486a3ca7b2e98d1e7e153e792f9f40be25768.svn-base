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

import team5.util.AdminCheckException;
import team5.vo.Product;

public class ProductServiceImpl implements ProductService {
	/**
	 * @Author 우성준
	 * 
	 * @Since 23/02/08
	 * 
	 *        상품기능담당클래스
	 * 
	 *        IOStream 추가, 싱글톤 추가, 메소드 지역변수추가(String writer) 멤버서비스 필요
	 */
	private List<Product> products = new ArrayList<Product>();
	private int proId = 0;
	ReviewService rs = ReviewServiceImpl.getInstance();
	MemberService ms = MemberServiceImpl.getInstance();
	CartService cs = CartServiceImpl.getInstance();

	private static ProductService productService = new ProductServiceImpl();

	private ProductServiceImpl() {
	}

	public static ProductService getInstance() {
		return productService;
	}

	{
		try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("product.ser"))) {
			products = (List<Product>) inputStream.readObject();
		} catch (FileNotFoundException e) {
			products.add(new Product(1001, "청바지", "하의", 1, 30000, new Date(), "admin", 11L));
			products.add(new Product(2002, "티셔츠", "상의", 2, 20000, new Date(), "admin", 6L));
			products.add(new Product(3003, "귀걸이", "악세", 3, 15000, new Date(), "admin", 3L));
			save();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	private List<Product> ware = new ArrayList<Product>();
	{
		try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("ware.ser"))) {
			ware = (List<Product>) inputStream.readObject();
		} catch (FileNotFoundException e) {
			ware.add(new Product(1001, "청바지", "하의", 1, 30000, new Date(), "admin", 11L));
			ware.add(new Product(2002, "티셔츠", "상의", 2, 20000, new Date(), "admin", 6L));
			ware.add(new Product(3003, "귀걸이", "악세", 3, 15000, new Date(), "admin", 3L));
			saveWare();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}

	public List<Product> getWare() {
		return ware;
	}

	public void setWare(List<Product> ware) {
		this.ware = ware;
	}

//	상품삭제(전체상품에서)
	@Override
	public void remove(String writer) {
		adminCheck(writer);
		boolean run = true;
		remove: while (run) {
			int proId = nextInt("상품코드를 입력하세요 >");
			if (findBy(proId) == null) {
				System.err.println("없는 상품입니다");
				continue remove;
			}
			products.remove(findBy(proId));
			ware.remove(findByWare(proId));
			save();
			saveWare();
			run = false;
			return;
		}
	}

//	상품등록(관리자 일 때만 실행가능)
	@Override
	public void register(String writer) {
		adminCheck(writer);
		boolean run = true;
		register: while (run) {
			String proName = nextLine("상품명을 입력하세요 >");
			if (proName.equals("")) {
				System.err.println("빈칸은 안됩니다");
				continue register;
			}
			String category = nextLine("카테고리 설정하세요 >");
			if (category.equals("상의") || category.equals("하의") || category.equals("악세")) {
				try {
					int proCnt = checkRange(nextInt("수량을 입력하세요 >"), 0, 10000000);
					int proPrice = checkRange(nextInt("가격을 입력하세요 >"), 0, 100000000);

					if (category.equals("하의"))
						proId += 1000;
					else if (category.equals("상의"))
						proId += 2000;
					else
						proId += 3000;
					Product product = new Product(proId++, proName, category, proCnt, proPrice, new Date(), "admin",
							0L);
					products.add(product);
					ware.add(product);
					save();
					saveWare();
					run = false;
					return;
				} catch (NumberFormatException e) {
					System.err.println("숫자를 입력해주세요");
					continue register;
				}
			}
			System.err.println("상의, 하의, 악세만 입력받을 수 있습니다");
		}
	}

//	상품수정(관리자 일때만 가능)
	@Override
	public void modify(String writer) {
		adminCheck(writer);
		boolean run = true;
		modify: while (run) {
			try {
				int num = nextInt("상품코드를 입력하세요");
				Product p = findBy(num);
				if (p.equals(null)) {
					System.err.println("없는 상품 번호입니다");
					continue modify;
				}
				p.setProName(nextLine("바꾸고 싶은 상품명으로 입력하세요 >"));
				if (p.getProName().equals("")) {
					System.err.println("빈칸은 안됩니다");
					continue modify;
				}
				p.setCategory(nextLine("바꾸고 싶은 카테고리로 입력하세요 >"));
				if (p.getCategory().equals("상의") || p.getCategory().equals("하의") || p.getCategory().equals("악세")) {
					p.setProCnt(checkRange(nextInt("들어온 수량과 합쳐서 입력하세요 >"), 0, 1000000000));
					p.setProPrice(checkRange(nextInt("변동될 가격을 입력하세요 >"), 0, 1000000000));
					p.setRegDate(new Date());
					p.setRnoCnt(0L);
					save();
					Product w = findByWare(num);
					w.setProCnt(p.getProCnt());
					w.setProPrice(p.getProPrice());
					w.setRegDate(new Date());
					w.setRnoCnt(0L);
					saveWare();
					run = false;
				} else {
					System.err.println("상의, 하의, 악세만 입력받을 수 있습니다");
					continue modify;
				}
			} catch (NumberFormatException e) {
				System.err.println("숫자를 입력해주세요");
				continue modify;
			} catch (NullPointerException e) {
				System.err.println("없는 상품입니다");
				continue modify;
			}

		}
	}

//  상세정보
	@Override
	public void info(int proId) {
		rs = ReviewServiceImpl.getInstance();
		cs = CartServiceImpl.getInstance();
		boolean run = true;
		while (run) {
			if (findBy(proId) == null) {
				System.err.println("없는 상품입니다");
				break;
			}
			Product p = findBy(proId);
			rs.reviewList(proId);
			System.out.println("상품명 :" + p.getProName());
			System.out.println("가격 :" + p.getProPrice());

			int rsInput = checkRange(nextInt("1. 장바구니 담기 2. 후기등록 3. 후기삭제 4. 종료"), 1, 4);

			switch (rsInput) {
			case 1:
				cs.addCart(proId);
				break;
			case 2:
				rs.register(ms.getLoginUser().getUserId(), proId);
				break;
			case 3:
				rs.remove(ms.getLoginUser().getUserId(), proId);
				break;
			case 4:
				run = false;
				break;
			default:
				break;
			}

			run = false;
		}
	}

	// 상품전체 리스트
	@Override
	public void list() {
		System.out.println("상품코드   상품명    카테고리   재고   가격             올린날짜             작성자");
		System.out.println("=================================================================================");
		Collection<Product> cup = products;
		for (Product product : cup) {
			System.out.println(product);
		}
		System.out.println();
	}

	public Product findBy(int proId) {
		Product product = null;
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).getProId() == proId) {
				product = products.get(i);
				break;
			}
		}
		return product;
	}

	public Product findByWare(int proId) {
		Product product = null;
		for (int i = 0; i < ware.size(); i++) {
			if (ware.get(i).getProId() == proId) {
				product = ware.get(i);
				break;
			}
		}
		return product;
	}

	public void save() {
		try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("product.ser"))) {
			objectOutputStream.writeObject(products);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void saveWare() {
		try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("ware.ser"))) {
			objectOutputStream.writeObject(ware);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void decreaseProCnt(int proId) {
		cs = CartServiceImpl.getInstance();
		findBy(proId).setProCnt(findBy(proId).getProCnt() - cs.findByCart(proId).getProCnt());
		save();
	}

	public void increaseProCnt() {
		for (int i = 0; i < products.size(); i++) {
			products.get(i).setProCnt(ware.get(i).getProCnt());
		}
		save();
	}

	public String adminCheck(String writer) {
		if (!ms.findBy(writer).getUserId().equals("admin")) {
			throw new AdminCheckException(writer);
		}
		return writer;
	}
}
