package com.boram.shopping.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.boram.manager.vo.Product;
import com.boram.manager.vo.ProductDao;

public class ContentPanel{
	private JPanel content;
	
	public JPanel getContent() {
		return this.content;
	}
	/**
	 *  상품 상세페이지
	 */
	public ContentPanel(Product product) {
		content = new JPanel();
		content.setBackground(new Color(255, 255, 255));
		content.setBounds(0, 259, 718, 890);
		content.setLayout(null);
		content.setVisible(true);
		
		//List<Product> ProductList = new ProductDao().fileRead();
		
		Image image = null;
		try {
			File file = new File(MainView.PATH + product.getImgFilePath());
			image = ImageIO.read(file).getScaledInstance(718 , 630, Image.SCALE_SMOOTH);
		} catch (IOException e) {
			System.out.println("이미지 불러오기 에러 : " + e.getMessage());
		}
		
		JLabel contentImage = new JLabel(new ImageIcon(image));
		contentImage.setBounds(0, 0, 700, 630);
		content.add(contentImage);
		
		
	/*	
		private int pNo;				// 게시물번호
		private int category;			// 카테고리
		private String productName;		// 상품이름
		private int price;				// 상품가격
		private String size;			// 상품사이즈
		private String explain;			// 상품설명
		private String imgFilePath;		// 이미지경로
		private int stock;				// 상품재고
		private int count; 				// 조회수
		
	*/	
		DecimalFormat df = new DecimalFormat("###,###");
		JLabel productName = new JLabel(product.getProductName());
		productName.setFont(new Font("굴림", Font.BOLD, 25));
		productName.setBounds(14, 637, 631, 44);
		content.add(productName);
		
		JLabel price = new JLabel("판매가");
		price.setForeground(Color.GRAY);
		price.setFont(new Font("굴림", Font.BOLD, 20));
		price.setBounds(14, 696, 133, 44);
		content.add(price);
		
		JLabel label_1 = new JLabel(df.format(product.getPrice())+"원");
		label_1.setForeground(Color.GRAY);
		label_1.setFont(new Font("굴림", Font.BOLD, 20));
		label_1.setBounds(141, 696, 133, 44);
		content.add(label_1);
	}

}
