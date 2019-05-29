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
			Elements price = doc.select("div.spec p.price");
			
			/*System.out.println("https:"+img);
			System.out.println(title);
			System.out.println(price);*/
			for(int i = 0; i < img.size(); i++) {
				Element imgTmp = img.get(i);
				Element titleTmp = title.get(i);
				Element priceTmp = price.get(i);
				System.out.println(imgTmp.attr("src"));
				System.out.println(titleTmp.text());
				System.out.println(priceTmp.text());
				
				dataList.get(i).setImage(imgTmp.attr("src"));
				dataList.get(i).setTitle(titleTmp.text());
				dataList.get(i).setTitle(priceTmp.text());
			}
		} catch(Exception e) {
			System.out.println("파싱에러 : " + e.getMessage());
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
	
	
	
	
	/*
	
		Image image = null;
		Image image2 = null;
		
        try {
            URL url = new URL("https://m.mutnam.com/web/product/medium/201905/fc7e31fe79e7108dfc8882cfba71da4d.gif");
            URL ur2 = new URL("https://m.mutnam.com/web/product/medium/201905/0c774c7b53fa9f91cd60ccb96502a97e.gif");
            image = ImageIO.read(url).getScaledInstance(450, 430, Image.SCALE_SMOOTH);
            image2 = ImageIO.read(ur2).getScaledInstance(450, 430, Image.SCALE_SMOOTH);
        } catch (IOException e) {
        	System.out.println("이미지 불러오기 에러 : " + e.getMessage());
        }
        
        JLabel label = new JLabel(new ImageIcon(image));
        label.setBounds(15, 0, 325, 432);
        JLabel label2 = new JLabel(new ImageIcon(image2));
        label2.setBounds(359, 0, 325, 432);
        
	
	
	
	*/
}
