package com.boram.manager.run;

import java.util.ArrayList;
import java.util.List;

import com.boram.manager.vo.Order;
import com.boram.manager.vo.OrderDao;
import com.boram.manager.vo.Product;
import com.boram.manager.vo.ProductDao;

public class Test {
	public static void main(String[] args) {
		ArrayList<Order> orderList = new ArrayList<Order>();
		
		
			
			ProductDao pDao = new ProductDao();
			List<Product> pArr= pDao.fileRead();
			ArrayList<Integer> nArr = new ArrayList<Integer>();
			ArrayList<Integer> amount = new ArrayList<Integer>();
			int date = 20190001;
			
			for (int i = 0; i < 5; i++) {
				nArr.add((int)(Math.random()*455+1));
				amount.add((int)(Math.random()*10+1));
				date += 100;
				
				int price =0;
				for (int j = 0; j < nArr.size(); j++) {
					
					for (int j2 = 0; j2 < pArr.size(); j2++) {
						if(nArr.get(j)== pArr.get(j2).getpNo()) {
							price += (pArr.get(j2).getPrice())*(amount.get(j));
							break;
						}
					}
				}
				orderList.add(new Order("1", "Seoul", nArr, amount, 2, price, date));
				
			}
			date = 20190601;
			
			for (int i = 0; i < 5; i++) {
				nArr.add((int)(Math.random()*455+1));
				amount.add((int)(Math.random()*10+1));
				date -= 100;
				
				int price =0;
				for (int j = 0; j < nArr.size(); j++) {
					
					for (int j2 = 0; j2 < pArr.size(); j2++) {
						if(nArr.get(j)== pArr.get(j2).getpNo()) {
							price += (pArr.get(j2).getPrice())*(amount.get(j));
							break;
						}
					}
				}
				orderList.add(new Order("1", "Seoul", nArr, amount, 2, price, date));
				
			}
			
		System.out.println(orderList);	
		OrderDao oDa= new OrderDao();
		oDa.fileSave(orderList);
	}
}
