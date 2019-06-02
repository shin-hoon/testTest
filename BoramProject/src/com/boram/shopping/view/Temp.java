package com.boram.shopping.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.boram.manager.vo.Product;
import com.boram.manager.vo.ProductDao;
import java.awt.Font;

public class Temp extends JFrame {
	private JPanel content;
	/**
	 * 
	 */
	private static final long serialVersionUID = -8447375608770781274L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Temp frame = new Temp();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Temp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 0, 718, 1890);
		content = new JPanel();
		content.setBackground(new Color(255, 255, 255));
		content.setBounds(0, 259, 718, 1890);
		content.setLayout(null);
		content.setVisible(true);
		
		
		List<Product> ProductList = new ProductDao().fileRead();
		
		Image image = null;
		try {
			File file = new File(MainView.PATH + ProductList.get(2).getImgFilePath());
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
		JLabel productName = new JLabel("[MUTNAM] 하이퀄리티 라이더 자켓");
		productName.setFont(new Font("굴림", Font.BOLD, 25));
		productName.setBounds(14, 637, 631, 44);
		content.add(productName);
		
		JLabel price = new JLabel("판매가");
		price.setForeground(Color.GRAY);
		price.setFont(new Font("굴림", Font.BOLD, 20));
		price.setBounds(14, 696, 133, 44);
		content.add(price);
		
		JLabel label_1 = new JLabel("55,300\uC6D0");
		label_1.setForeground(Color.GRAY);
		label_1.setFont(new Font("굴림", Font.BOLD, 20));
		label_1.setBounds(141, 696, 133, 44);
		content.add(label_1);
		
		
		
		setContentPane(content);
	}
}