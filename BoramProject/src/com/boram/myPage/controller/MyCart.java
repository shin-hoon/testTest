package com.boram.myPage.controller;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import com.boram.manager.vo.Order;
import com.boram.manager.vo.Product;
import com.boram.member.controller.MemberController;
import com.boram.member.vo.Member;

public class MyCart extends Product implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4623389045564207219L;
	ArrayList<Product> CList = new ArrayList<>();
	ArrayList<Order> OList= new ArrayList<>();
	ArrayList<Integer> pNo =new ArrayList<>();
	ArrayList<Integer> amount = new ArrayList<>();
	MemberController mc= new MemberController();
	Member m = mc.nugu(); 
	// product 에서 몇개만 뽑아서 장바구니리스트 만들어야함.
	// -> 그냥다 추가해서 몇개만뽑음.
	// 장바구니리스트에 추가하는건 상품쪽에서.

	{
		// 1,1,"hat",35000,one,1,1.
		CList.add(new Product(1, 1, "hat", 35001, "one1", 1, 1));
		CList.add(new Product(2, 2, "hat", 35002, "one2", 1, 1));
		CList.add(new Product(3, 3, "hat", 35003, "one3", 1, 1));
		CList.add(new Product(4, 1, "hat", 35004, "one4", 1, 1));
		CList.add(new Product(5, 2, "hat", 35005, "one5", 1, 1));
		CList.add(new Product(6, 3, "hat", 35006, "one6", 1, 1));
		CList.add(new Product(7, 4, "hat", 35007, "one7", 1, 1));
	}

	public MyCart() {
	}

	/**
	 * @param product
	 *            장바구니 추가될 product객체리스트.
	 */
	public void addCart(Product product) {
		CList.add(product);
	}

	/**
	 * 전체조회
	 * 
	 * @return 전체리스트 리턴 후 View에서 출력.
	 */
	public ArrayList<Product> cartPrint() {
		return CList;
	}

	/**
	 * 삭제품목번호 받아서 삭제..
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
	 * 장바구니 전체삭제.
	 */
	public void cartDeleteAll() {
		CList.clear();
	}

	
	public int cartOrder() {
		/*int result = 0;
		if (CList.isEmpty()) {
			result = 0;
		} else {// 임시변수!
			try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(result + "MyOrder.txt"))) {
				for (Product i : CList) {
					oos.writeObject(i);
					result = 1;
				}
				// for(int i=0;i<CList.size();i++) {
				// oos.writeObject(CList);
				// return 1;
				// }
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (result == 1) {
			CList.clear();// 임시변수!!
			File file = new File("0MyCart.txt");
			// System.out.println(file.getName() + "파일");
			if (file.exists()) {
				file.delete();
				result = 2;//
			}
		}
		return result;*/
		int result =0;
		
		if (CList.isEmpty()) {
			result = 0;
		}else {
			int oNo =OList.size()+1;
			String oId = m.getId();
			String oAdd=m.getAddress();
			//CList의 pNo리스트
			for(Product i:CList) {
				pNo.add(i.getpNo());
			}
			for(Product i : CList) {
				amount.add(i.getpNo());
			}
			int state =0;
			int payment=0;
			for(Product i:CList) {
				payment+=i.getPrice();
			}
			OList.add(new Order(oNo,oId,oAdd,pNo,amount,state,payment));
			
		}
			
		
		return result;
	}

	/**
	 * 임시변수 확인할것!!!!!!!!! 실행시 회원번호 + MyCart.txt만들어서 CList저장. 로드먼저 하지않고 save시 덮을껀지
	 * 추가할것인지 결정후 손볼것. 장바구니 비어있거나 파일생성실패시 0 성공시 1
	 */
	public int saveCart() {// notSerializable exc
		int result = 0;
		if (CList.isEmpty()) {
			// System.out.println("Empty");
			result = 0;
		} else {// 임시변수확인!!!!!!
			try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(result + "MyCart.txt"))) {
				for (Product i : CList) {
					oos.writeObject(i);
					result = 1;
					// return result;
				}
				// for(int i=0;i<CList.size();i++) {
				// oos.writeObject(CList.get(i));
				// //return 1;
				// }
				oos.writeObject(CList);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				// result=0;
				e.printStackTrace();
			}
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public void loadCart() {// 잘 작동하는지 확인할것.
		int result = 0;// 임시변수확인!!
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(result + "MyCart.txt"))) {
			while (true) {
//				CList = (ArrayList<Product>) ois.readObject();
				CList.clear();
				CList.add((Product)ois.readObject());
				result = 1;
			
			}

		} catch (EOFException e) {
			// e.printStackTrace();
			System.out.println("불러오기 완료.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
