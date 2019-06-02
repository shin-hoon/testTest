package com.boram.manager.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.boram.manager.vo.Order;
import com.boram.manager.vo.OrderDao;
import com.boram.manager.vo.Product;
import com.boram.manager.vo.ProductDao;
import com.boram.member.vo.Member;
import com.boram.member.vo.MemberDao;

public class ManagerController2 {

	private OrderDao od = new OrderDao();
	private ProductDao pd = new ProductDao();
	private MemberDao md = new MemberDao();

	private ArrayList<Order> oArr = od.fileRead();
	private List<Product> pArr = pd.fileRead();
	private ArrayList<Member> mArr = md.fileRead();

	public ArrayList<Member> searchMember() {
		return mArr;
	}

	public List<Product> searchProduct() {
		return pArr;
	}

	public void insertProduct(int category, String productName, int price, String size, String explain, int stock) {

		int pNo = 1;
		try {
			if (pArr != null) {
				pNo = pArr.get(pArr.size() - 1).getpNo() + 1;
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			pNo = 1;
		}

		pArr.add(new Product(pNo, category, productName, price, size, stock, 0));
	}

	public int searchpNo(int pNo) {
		// pNo일치하는 인덱스 반환
		for (int i = 0; i < pArr.size(); i++) {
			if (pArr.get(i).getpNo() == pNo) {
				return i;
			}
		}
		return -1;

	}

	public void updateProduct(int index, int menu, String update) {

		int result = Integer.parseInt(update);

		switch (menu) {
		case 1:

			pArr.get(index).setCategory(result);
			break;
		case 2:
			pArr.get(index).setProductName(update);
			break;
		case 3:
			pArr.get(index).setPrice(result);
			break;
		case 4:
			pArr.get(index).setSize(update);
			break;
		case 5:
			pArr.get(index).setExplain(update);
			break;

		default:
			break;
		}

	}

	public void deleteProduct(int result) {
		pArr.remove(result);
	}

	public void deleteMember(int result) {
		mArr.remove(result);
	}

	public void updateProduct(int result, int stock) {
		pArr.get(result).setStock(stock);
	}

	public HashMap<Integer, Double> analysis() {
		HashMap<Integer, Double> anl = new HashMap<Integer, Double>();

		ArrayList<Integer> pNo = new ArrayList<>();

		int count[] = new int[pArr.size()];
		for (int i = 0; i < oArr.size(); i++) {
			pNo = oArr.get(i).getpNo();
			for (int j = 0; j < pArr.size(); j++) {
				for (int k = 0; k < pNo.size(); k++) {
					if (pNo.get(k) == pArr.get(j).getpNo()) {
						count[j] += oArr.get(i).getAmount().get(k);
					}
				}
				try {
					anl.put(pArr.get(j).getpNo(), (double) (count[j] / pArr.get(j).getCount()));
				} catch (ArithmeticException e) {
					anl.put(pArr.get(j).getpNo(), 0.0);
				}
			}
		}
		return anl;

	}

	public ArrayList<Integer> salesState(int month, int term) {

		ArrayList<Integer> sumArr = new ArrayList<Integer>();
		int count;

		int a = (month / 100) % 100;
		// month의 형태 'yyyyMMdd'
		// 월을 나타냄

		for (int i = 0; i < term; i++) {
			count = 0;
			if (a == 0) {
				for (int j = 0; j < oArr.size(); j++) {
					if ((month / 100 - i - 100) == (oArr.get(j).getOrderDate() / 100)) {
						count += oArr.get(j).getPayment();
					}
				}

				sumArr.add(count);
			} else {
				for (int j = 0; j < oArr.size(); j++) {
					if ((month / 100 - i) == (oArr.get(j).getOrderDate() / 100)) {
						count += oArr.get(j).getPayment();
					}
				}
				a--;
				sumArr.add(count);
			}

		}

		return sumArr;

	}

}
