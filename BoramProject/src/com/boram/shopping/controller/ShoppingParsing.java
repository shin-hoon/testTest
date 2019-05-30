package com.boram.shopping.controller;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.boram.shopping.vo.ShoopingVO;
public class ShoppingParsing {
	/*public static void main(String[] args) {
		new ShoppingParsing().testParsing();
	}*/
	
	
	public List<ShoopingVO> testParsing() {
		List<ShoopingVO> dataList = new ArrayList<>();
		
		try {
			Document doc = Jsoup.connect("https://m.mutnam.com/product/list.html?cate_no=50").get();
			Elements img = doc.select("div.thumbnail a img");
			Elements title = doc.select("div.description strong.name a");
			Elements price = doc.select("div.description div.spec p.price");
			
			int cnt = 0;
			for(int i = 0; i < img.size(); i++) {
				Element imgTmp = img.get(i);
				Element titleTmp = title.get(i);
				Element priceTmp = price.get(cnt);
			/*	
				System.out.println("https:"+imgTmp.attr("src"));
				System.out.println(titleTmp.text());
				System.out.println(priceTmp.text());
				System.out.println();
			*/	
				ShoopingVO vo = new ShoopingVO();
				vo.setImage("https:"+imgTmp.attr("src"));
				vo.setTitle(titleTmp.text());
				vo.setPrice(priceTmp.text());
				dataList.add(vo);
				cnt += 2;
			}
		} catch(Exception e) {
			System.out.println("ÆÄ½Ì¿¡·¯ : " + e.getMessage());
		}
		
		return dataList;
	}
	
	/*public List<String> testImage() {
		List<String> dataList = testParsing();
		
		for(int i = 0; i < dataList.size(); i++) {
			
		}
		
		return dataList;
	}
	*/
}
