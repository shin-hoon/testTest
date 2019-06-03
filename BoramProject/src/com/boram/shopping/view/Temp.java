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
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Temp extends JFrame {
	private JPanel content;
	/**
	 * 
	 */
	private static final long serialVersionUID = -8447375608770781274L;
	private JPanel contentPane;
	private JTextField textField;

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
		
		
		
		
		
		setContentPane(content);
		
		textField = new JTextField();
		textField.setBounds(56, 64, 212, 24);
		content.add(textField);
		textField.setColumns(10);
		
	}
}