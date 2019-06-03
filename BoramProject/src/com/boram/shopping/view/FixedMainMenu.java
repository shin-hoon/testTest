package com.boram.shopping.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import com.boram.manager.vo.Product;
import com.boram.manager.vo.ProductDao;
import com.boram.member.controller.MemberController;
import com.boram.shopping.controller.MainMouseEvent;

public class FixedMainMenu {
	private JPanel mainMenu;
	// 고정 페이지 JPanel에 담기는 JLabel(맨 위쪽 => 메인 베너, 검색, 로그인, 마이 페이지, 관리자 페이지)
	private JLabel kategorie, logo, search, login, myPage, adminPage;	
	private JTextField textField;
	public static List<Product> searchList;
	public static boolean searchChk = false; 
	
	public JPanel getMainMenu() {
		return this.mainMenu;
	}
	
	/**
	 *   고정 페이지 JPanel(맨 위쪽, 서브메뉴, 메인 베너, 검색, 로그인, 마이 페이지, 관리자 페이지)
	 *   고정 페이지 JPanel에 담기는 JLabel(맨 위쪽 => 메인 베너, 검색, 로그인, 마이 페이지, 관리자 페이지)
	 */
	public FixedMainMenu() {
		mainMenu = new JPanel();
		mainMenu.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		mainMenu.setBackground(new Color(255, 255, 255));
		mainMenu.setBounds(-16, 0, 729, 79);
		mainMenu.setVisible(true);
		mainMenu.setLayout(null);
		
		
		kategorie = new JLabel(new ImageIcon(MainView.PATH + "image\\MainImage\\kategorie.jpg"));
		kategorie.setBackground(new Color(255, 204, 51));
		kategorie.setBounds(30, 12, 55, 57);
		mainMenu.add(kategorie);
		
		logo = new JLabel("Boram");
		logo.setFont(new Font("Broadway", Font.BOLD, 50));
		logo.setBounds(118, -2, 201, 79);
		mainMenu.add(logo);
		
		textField = new JTextField();
		textField.setBounds(350, 34, 172, 24);
		textField.setColumns(10);
		mainMenu.add(textField);
		if(searchChk) textField.setVisible(true);
		else		  textField.setVisible(false);
		
		adminPage = new JLabel(new ImageIcon(MainView.PATH + "image\\MainImage\\adminPage.jpg"));
		adminPage.setBounds(540, 26, 30, 32);
		adminPage.setVisible(false);
		mainMenu.add(adminPage);
		
		if(MemberController.m != null) {
			if(MemberController.m.getId().equals("admin") && MemberController.m.getPwd().equals("1234")) {
				adminPage.setVisible(true);
			}
			else {
				adminPage.setVisible(false);
			}
		}
		
		search = new JLabel(new ImageIcon(MainView.PATH + "image\\MainImage\\search.jpg"));
		search.setBounds(583, 26, 30, 32);
		mainMenu.add(search);
		
		if(MemberController.m != null)
			login = new JLabel(new ImageIcon(MainView.PATH + "image\\MainImage\\logout.jpg"));
		else
			login = new JLabel(new ImageIcon(MainView.PATH + "image\\MainImage\\login.jpg"));
		login.setBounds(627, 26, 30, 32);
		mainMenu.add(login);
		
		
		myPage = new JLabel(new ImageIcon(MainView.PATH + "image\\MainImage\\myPage.jpg"));
		myPage.setBounds(667, 26, 30, 32);
		mainMenu.add(myPage);
		
		JPanel boderPanel = new JPanel();
		boderPanel.setBackground(new Color(255, 255, 255));
		boderPanel.setBorder(new MatteBorder(1, 1, 1, 2, (Color) new Color(0, 0, 0)));
		boderPanel.setBounds(0, 2, 102, 75);
		mainMenu.add(boderPanel);
		
		kategorie.addMouseListener(new MainMouseEvent("서브메뉴열기"));	
		logo.addMouseListener(new MainMouseEvent("메인"));
		adminPage.addMouseListener(new MainMouseEvent("관리자페이지"));
		search.addMouseListener(new MainMouseEvent("검색"));
		login.addMouseListener(new MainMouseEvent("로그인"));
		myPage.addMouseListener(new MainMouseEvent("마이페이지"));
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(textField.getText().length() == 0) {
					MainView.setMainPage(new MainPanel(1).getMainPanel());
				}
				else {
					List<Product> productList = new ProductDao().fileRead();
					searchList = new ArrayList<>();
					for (int i = 0; i < productList.size(); i++) {
						if(productList.get(i).getProductName().contains(textField.getText())) {
							Product p = new Product();
							p = productList.get(i);
							searchList.add(p);
						}
					}
					MainView.setMainPage(new MainPanel(-1).getMainPanel());
				}
			}
		});
	}
}
