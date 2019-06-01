package com.boram.myPage.controller;

import java.io.*;
import java.util.*;

import com.boram.manager.vo.Order;
import com.boram.manager.vo.OrderDao;
import com.boram.manager.vo.Product;
import com.boram.member.controller.MemberController;
import com.boram.member.vo.Member;

public class MyCart extends Product implements Serializable {
	private static final long serialVersionUID = 4623389045564207219L;
	static ArrayList<Product> CList = new ArrayList<>();// 장바구니리스트
	ArrayList<Order> OList = new ArrayList<>();// 주문리스트

	// 로그인하면서 누가 로그인했는지 회원정보 가져옴.
	MemberController mc = new MemberController();

	// 초기 임시데이터
//	 {
//	 // pNo, category, productName, price, size, stock, count(조회수)
//	 // 1,1,"hat",35000,one,1,1.
//	 CList.add(new Product(1, 1, "hat", 35001, "one1", 1, 1));
//	 CList.add(new Product(2, 2, "hat", 35002, "one2", 1, 1));
//	 CList.add(new Product(3, 3, "hat", 35003, "one3", 1, 1));
//	 CList.add(new Product(4, 1, "hat", 35004, "one4", 1, 1));
//	 CList.add(new Product(5, 2, "hat", 35005, "one5", 1, 1));
//	 CList.add(new Product(6, 3, "hat", 35006, "one6", 1, 1));
//	 CList.add(new Product(7, 4, "hat", 35007, "one7", 1, 1));
//	 }

	public MyCart() {
	}

	/**
	 * @param product
	 *            장바구니 추가될 product객체리스트.
	 */
	// 중복시 수량증가 해야되나?
	public void addCart(Product product) {
		CList.add(product);
	}

	/**
	 * 전체조회
	 * 
	 * @return 전체리스트 리턴 후 View에서 출력.
	 */
	public ArrayList<Product> cartList() {
		return CList;
	}

	/**
	 * 안쓰는코드!!!!!!!!!!!!! 삭제품목번호 받아서 삭제..
	 * 
	 * @param delete
	 *            삭제할 품목번호
	 * @return 삭제성공1/실패0
	 */
	public int cartDelete(int delete) {
		int result = 0;
		for (Product i : CList) {
			if (i.getpNo() == delete) {
				// System.out.println(i);
				CList.remove(i);
				result = 1;
				return result;
			} else {
				result = 0;
			}
		}
		return result;
	}

	/**
	 * 안쓰는코드!!!!!!!!!!!!! 장바구니 전체삭제.
	 */
	public void cartDeleteAll() {
		CList.clear();
	}

	/**
	 * @return 실패0, 성공1
	 */
	public int cartOrder() {
		OrderDao od = new OrderDao();// 주문정보 Output
		ArrayList<Integer> pNo = new ArrayList<>();// 주문리스트속 상품번호들
		ArrayList<Integer> amount = new ArrayList<>();// 주문리스트 속 주문수량
		int result = 0;
		// 장바구니 비어있으면 0리턴.
		if (CList.isEmpty()) {
			result = 0;
		} else {
			// CList+Member => OList만들기.
			int oNo =0; // 마지막order번호 +1
			if (OList.size() != 0) {
				oNo = OList.size()+1;
			}
			String oId = MemberController.m.getId();// 주문자 id
			String oAdd = MemberController.m.getAddress();// 주문자 주소.
			// CList안 상품의 pNo목록을 ArrayList<Integer> pNo로 넣음.
			for (Product i : CList) {
				pNo.add(i.getpNo());
			}
			// pNo별 수량체크 손볼것!!
			for (Product i : CList) {
				amount.add(i.getpNo());
			}
			int state = 0;
			int payment = 0;
			for (Product i : CList) {
				payment += i.getPrice();
			}
			OList.add(new Order(oNo, oId, oAdd, pNo, amount, state, payment));
			od.fileSave(OList);
			result = 1;
		}
		return result;
	}

	/**
	 * 임시변수확인!!!!!!!!!!! 실행시 회원번호 +MyCart.txt만들어서 CList저장. MyCart폴더로 저장경로 설정.
	 * 
	 * @param CList
	 *            MyCartView에서 CList받아서 저장.
	 * @return
	 * @return 실패0, 성공1
	 */
	public void saveCart(ArrayList<Product> CList) {// notSerializable exc
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(MemberController.m.getmNo() + "MyCart.txt"))) {
			// oos.writeObject(null);
			for (Product i : CList) {
				oos.writeObject(i);
			}
			// for(int i=0;i<CList.size();i++) {
			// oos.writeObject(CList.get(i));
			// //return 1;
			// }
			// oos.writeObject(CList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			// result=0;
			e.printStackTrace();
		}
	}

	public void loadCart() {// 잘 작동하는지 확인할것.
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(MemberController.m.getmNo() + "MyCart.txt"))) {
			CList.clear();
			while (true) {
				// CList = (ArrayList<Product>) ois.readObject();
				CList.add((Product) ois.readObject());
			}
		
		}
		catch (EOFException e) {
			// e.printStackTrace();
			System.out.println("불러오기 완료.");
		} catch (FileNotFoundException e) {
			// 장바구니 파일이 없을때 새로만들어주어야함.
			CList.clear();
			saveCart(CList);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
