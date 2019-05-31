package com.boram.shopping.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import com.boram.shopping.controller.MainMouseEvent;

public class MainView{
	// 메인 프레임
	public static JFrame frame;
	// 고정 페이지 JPanel(맨 위쪽)
	JPanel mainMenu;
	// 고정 페이지 JPanel에 담기는 JLabel(맨 위쪽 => 메인 베너, 검색, 로그인, 마이 페이지, 관리자 페이지)
	JLabel kategorie, logo, search, login, myPage, adminPage;	
	// 서브메뉴 목록과 서브메뉴 상세 분류를 담고있는 JPanel. kategorie를 누르면 나오는 메뉴
	JPanel subMenu;
	// 서브메뉴 목록
	JLabel outer, top, shirt, pants, shoes, acc;
	// 서브메뉴 상세 분류 목록
	JPanel outerSubMenu, topSubMenu, shirtSubMenu, pantsSubMenu, shoesSubMenu, accSubMenu;
	// 서브메뉴 상세 카테고리
	JLabel coat, jacket, blazer, cardigan, LongSleeve, ShortSleeves, hoodT, neat, overFit,
		   nomalShirt, patternShirt, shortShirt, Slacks, cottonPants, blueJeans, shorts,
		   training, upDenimPants, Sneakers, Walker, slipper;
	// subMenu 닫기
	JLabel subMenuClose;
	// subMenu 스크롤
	JScrollPane subMenuScroll;
	// 바뀌는 페이지 
	public static JPanel mainPage;
	// 바뀌는 페이지 스크롤
	public static JScrollPane mainPageScroll;
	// 파일 경로 세팅을 위한 변수
	public static final String PATH;
	
	static {
		// 파일 경로를 세팅하기 위한 작업
		File file = new File("FilePathLocation.txt");
		String absolutePath = file.getAbsolutePath();
		PATH = absolutePath.substring(0, absolutePath.lastIndexOf("\\")+1);		
	}
	
	
	/**
	 * 메인 페이지
	 */
	public MainView() {
		frame = new JFrame();
		frame.setTitle("대한민국 NO.1 쇼핑몰 Boram닷컴");
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setBackground(new Color(255, 255, 255));
		frame.getContentPane().setLayout(null);
        frame.setSize(718, (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()-43 );
		frame.setLocationRelativeTo(null); 
		
		// 고정 페이지(위쪽, 서브 메뉴)
		this.fixedSubMenu();
		// 고정 페이지 JPanel(위쪽, 서브메뉴, 메인 베너, 검색, 로그인, 마이 페이지, 관리자 페이지)
		this.fixedMainMenu();
		// 바뀌는 페이지(아래쪽, JPanel)
		mainPage = new MainPanel(1).getMainPanel();
		// 이벤트 처리 
		this.mainMouseEvent();

		
        mainPageScroll = new JScrollPane();
		mainPageScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		mainPageScroll.getVerticalScrollBar().setUnitIncrement(36);// 스크롤 속도 
		mainPageScroll.setBorder(null);
		mainPageScroll.setBounds(0, 80, 715, 975);

		mainPage.setPreferredSize(new Dimension(450, 3200));
		mainPageScroll.setViewportView(mainPage);
		frame.getContentPane().add(mainPageScroll); 
		
		frame.setVisible(true);
		frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	/**
	 *  고정 페이지 JPanel(맨 위쪽)
	 *  메뉴 목록과 서브메뉴 목록을 담고있는 JPanel subMenu; 
	 *  서브메뉴 목록과 서브메뉴 상세 분류를 담고있는 JPanel. kategorie를 누르면 나오는 메뉴
	 */
	public void fixedSubMenu() {
		subMenu = new JPanel();
		subMenu.setBackground(Color.WHITE);
		subMenu.setBounds(0,0,228,1015);
		subMenu.setLayout(null);
		subMenu.setVisible(true);
		
		subMenuClose = new JLabel("X");
		subMenuClose.setForeground(Color.GRAY);
		subMenuClose.setFont(new Font("굴림", Font.PLAIN, 35));
		subMenuClose.setBackground(new Color(255, 255, 255));
		subMenuClose.setBounds(197, 12, 22, 17);
		subMenu.add(subMenuClose);

		
		// OUTER 메뉴
		outer = new JLabel("OUTER");
		outer.setFont(new Font("Bodoni MT Black", Font.PLAIN, 30));
		outer.setBounds(40, 74, 125, 35);
		subMenu.add(outer);
		
		// OUTER 서브메뉴
		outerSubMenu = new JPanel();
		outerSubMenu.setBackground(Color.WHITE);
		outerSubMenu.setBounds(40, 124, 116, 121); 
		outerSubMenu.setLayout(null);
		subMenu.add(outerSubMenu);

		coat = new JLabel("코트");
		coat.setFont(new Font("굴림", Font.BOLD, 18));
		coat.setBounds(0, 0, 62, 21);
		outerSubMenu.add(coat);

		jacket = new JLabel("자켓");
		jacket.setFont(new Font("굴림", Font.BOLD, 18));
		jacket.setBounds(0, 31, 62, 21);
		outerSubMenu.add(jacket);

		blazer = new JLabel("블레이저");
		blazer.setFont(new Font("굴림", Font.BOLD, 18));
		blazer.setBounds(0, 62, 76, 21);
		outerSubMenu.add(blazer);

		cardigan = new JLabel("가디건");
		cardigan.setFont(new Font("굴림", Font.BOLD, 18));
		cardigan.setBounds(0, 93, 62, 21);
		outerSubMenu.add(cardigan);
		// end OUTER 서브메뉴
		
		

		
		// TOP 메뉴
		top = new JLabel("TOP");
		top.setFont(new Font("Bodoni MT Black", Font.PLAIN, 30));
		top.setBounds(40, 260, 91, 35); // 50, 15
		subMenu.add(top);
		
		// TOP 서브메뉴
		topSubMenu = new JPanel();
		topSubMenu.setBackground(Color.WHITE);
		topSubMenu.setLayout(null);
		topSubMenu.setBounds(40, 310, 133, 147);
		subMenu.add(topSubMenu);
		
		LongSleeve = new JLabel("긴팔");
		LongSleeve.setFont(new Font("굴림", Font.BOLD, 18));
		LongSleeve.setBounds(0, 0, 62, 21);
		topSubMenu.add(LongSleeve);
		
		ShortSleeves = new JLabel("반팔/조끼");
		ShortSleeves.setFont(new Font("굴림", Font.BOLD, 18));
		ShortSleeves.setBounds(0, 31, 85, 21);
		topSubMenu.add(ShortSleeves);
		
		hoodT = new JLabel("맨투맨/후드티");
		hoodT.setFont(new Font("굴림", Font.BOLD, 18));
		hoodT.setBounds(0, 62, 133, 21);
		topSubMenu.add(hoodT);
		
		neat = new JLabel("니트");
		neat.setFont(new Font("굴림", Font.BOLD, 18));
		neat.setBounds(0, 93, 62, 21);
		topSubMenu.add(neat);
		
		overFit = new JLabel("오버핏");
		overFit.setFont(new Font("굴림", Font.BOLD, 18));
		overFit.setBounds(0, 124, 62, 21);
		topSubMenu.add(overFit);
		// end TOP 서브메뉴
		
		
		
		
		// SHIRT 메뉴
		shirt = new JLabel("SHIRT");
		shirt.setFont(new Font("Bodoni MT Black", Font.PLAIN, 30));
		shirt.setBounds(40, 472, 116, 30); 
		subMenu.add(shirt);
		
		// SHIRT 서브메뉴
		shirtSubMenu = new JPanel();
		shirtSubMenu.setBackground(Color.WHITE);
		shirtSubMenu.setBounds(40, 522, 80, 84);
		subMenu.add(shirtSubMenu);
		shirtSubMenu.setLayout(null);
		
		nomalShirt = new JLabel("기본셔츠");
		nomalShirt.setFont(new Font("굴림", Font.BOLD, 18));
		nomalShirt.setBounds(0, 0, 84, 18);
		shirtSubMenu.add(nomalShirt);
		
		patternShirt = new JLabel("패턴셔츠");
		patternShirt.setFont(new Font("굴림", Font.BOLD, 18));
		patternShirt.setBounds(0, 31, 84, 18);
		shirtSubMenu.add(patternShirt);
		
		shortShirt = new JLabel("반팔셔츠");
		shortShirt.setFont(new Font("굴림", Font.BOLD, 18));
		shortShirt.setBounds(0, 62, 84, 18);
		shirtSubMenu.add(shortShirt);
		// end SHIRT 서브메뉴
		
		
		
		
		// PANTS 메뉴
		pants = new JLabel("PANTS");
		pants.setFont(new Font("Bodoni MT Black", Font.PLAIN, 30));
		pants.setBounds(40, 621, 116, 35);
		subMenu.add(pants);
		
		// PANTS 서브메뉴
		pantsSubMenu = new JPanel();
		pantsSubMenu.setBackground(Color.WHITE);
		pantsSubMenu.setBounds(40, 671, 125, 176);
		subMenu.add(pantsSubMenu);
		pantsSubMenu.setLayout(null);
		
		Slacks = new JLabel("슬랙스");
		Slacks.setFont(new Font("굴림", Font.BOLD, 18));
		Slacks.setBounds(0, 0, 62, 18);
		pantsSubMenu.add(Slacks);
		
		cottonPants = new JLabel("면팬츠");
		cottonPants.setFont(new Font("굴림", Font.BOLD, 18));
		cottonPants.setBounds(0, 31, 62, 18);
		pantsSubMenu.add(cottonPants);
		
		blueJeans = new JLabel("청바지");
		blueJeans.setFont(new Font("굴림", Font.BOLD, 18));
		blueJeans.setBounds(0, 62, 62, 18);
		pantsSubMenu.add(blueJeans);
		
		shorts = new JLabel("반바지");
		shorts.setFont(new Font("굴림", Font.BOLD, 18));
		shorts.setBounds(0, 93, 62, 18);
		pantsSubMenu.add(shorts);
		
		training = new JLabel("트레이닝");
		training.setFont(new Font("굴림", Font.BOLD, 18));
		training.setBounds(0, 124, 76, 18);
		pantsSubMenu.add(training);
		
		upDenimPants = new JLabel("UP 데님팬츠");
		upDenimPants.setFont(new Font("굴림", Font.BOLD, 18));
		upDenimPants.setBounds(0, 155, 111, 18);
		pantsSubMenu.add(upDenimPants);
		// end PANTS 서브메뉴
		
		
		
		
		// SHOES 메뉴
		shoes = new JLabel("SHOES");
		shoes.setFont(new Font("Bodoni MT Black", Font.PLAIN, 30));
		shoes.setBounds(40, 862, 116, 35);
		subMenu.add(shoes);
		
		// SHOES 서브메뉴
		shoesSubMenu = new JPanel();
		shoesSubMenu.setBackground(Color.WHITE);
		shoesSubMenu.setBounds(40, 912, 140, 84);
		subMenu.add(shoesSubMenu);
		shoesSubMenu.setLayout(null);
		
		Sneakers = new JLabel("스니커즈");
		Sneakers.setFont(new Font("굴림", Font.BOLD, 18));
		Sneakers.setBounds(0, 0, 76, 18);
		shoesSubMenu.add(Sneakers);
		
		Walker = new JLabel("구두/워커");
		Walker.setFont(new Font("굴림", Font.BOLD, 18));
		Walker.setBounds(0, 31, 85, 18);
		shoesSubMenu.add(Walker);
		
		slipper = new JLabel("슬리퍼/샌들");
		slipper.setFont(new Font("굴림", Font.BOLD, 18));
		slipper.setBounds(0, 62, 104, 18);
		shoesSubMenu.add(slipper);
		// end SHOES 서브메뉴
		
		
		subMenuScroll = new JScrollPane();
		subMenuScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		subMenuScroll.getVerticalScrollBar().setUnitIncrement(36);// 스크롤 속도 
		subMenuScroll.setBorder(null);
		subMenuScroll.setBounds(0, 80, 246, 975);
		subMenuScroll.setVisible(false);
		subMenu.setPreferredSize(new Dimension(246, 1000));
		subMenuScroll.setViewportView(subMenu);
		
		frame.getContentPane().add(subMenuScroll); 

	} // end fixedSubMenu method
	
	
	
	/**
	 *   고정 페이지 JPanel(맨 위쪽, 서브메뉴, 메인 베너, 검색, 로그인, 마이 페이지, 관리자 페이지)
	 *   고정 페이지 JPanel에 담기는 JLabel(맨 위쪽 => 메인 베너, 검색, 로그인, 마이 페이지, 관리자 페이지)
	 */
	public void fixedMainMenu() {
		mainMenu = new JPanel();
		mainMenu.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		mainMenu.setBackground(new Color(255, 255, 255));
		mainMenu.setBounds(-16, 0, 729, 79);
		mainMenu.setLayout(null);
		
		kategorie = new JLabel(new ImageIcon(PATH + "image\\MainImage\\kategorie.jpg"));
		kategorie.setBackground(new Color(255, 204, 51));
		kategorie.setBounds(30, 12, 55, 57);
		mainMenu.add(kategorie);
		
		logo = new JLabel("Boram");
		logo.setFont(new Font("Broadway", Font.BOLD, 50));
		logo.setBounds(118, -2, 201, 79);
		mainMenu.add(logo);
		
		search = new JLabel(new ImageIcon(PATH + "image\\MainImage\\search.jpg"));
		search.setBounds(583, 26, 30, 28);
		mainMenu.add(search);
		
		login = new JLabel(new ImageIcon(PATH + "image\\MainImage\\login.jpg"));
		login.setBounds(627, 26, 30, 28);
		mainMenu.add(login);
		
		
		myPage = new JLabel(new ImageIcon(PATH + "image\\MainImage\\myPage.jpg"));
		myPage.setBounds(667, 26, 30, 28);
		mainMenu.add(myPage);
		
		adminPage = new JLabel(new ImageIcon(PATH + "image\\MainImage\\adminPage.jpg"));
		adminPage.setBounds(540, 26, 30, 28);
		mainMenu.add(adminPage);
		
		JPanel boderPanel = new JPanel();
		boderPanel.setBackground(new Color(255, 255, 255));
		boderPanel.setBorder(new MatteBorder(1, 1, 1, 2, (Color) new Color(0, 0, 0)));
		boderPanel.setBounds(0, 2, 102, 75);
		mainMenu.add(boderPanel);
		
		frame.getContentPane().add(mainMenu);
	} // end fixedMainMenu method
	
	
	
	
	// 이벤트 처리
	public void mainMouseEvent() {
		
		kategorie.addMouseListener(new MainMouseEvent(subMenu, subMenuScroll, "서브메뉴열기") );	
		subMenuClose.addMouseListener(new MainMouseEvent(subMenu, subMenuScroll, "서브메뉴닫기") );	
		logo.addMouseListener(new MainMouseEvent("메인") );
		login.addMouseListener(new MainMouseEvent("로그인") );
		myPage.addMouseListener(new MainMouseEvent("마이페이지") );
		adminPage.addMouseListener(new MainMouseEvent("관리자페이지"));
		coat.addMouseListener(new MainMouseEvent("11"));
		jacket.addMouseListener(new MainMouseEvent("12"));
		blazer.addMouseListener(new MainMouseEvent("13"));
		cardigan.addMouseListener(new MainMouseEvent("14"));
		LongSleeve.addMouseListener(new MainMouseEvent("21"));
		ShortSleeves.addMouseListener(new MainMouseEvent("22"));
		hoodT.addMouseListener(new MainMouseEvent("23"));
		neat.addMouseListener(new MainMouseEvent("24"));
		overFit.addMouseListener(new MainMouseEvent("25"));
		nomalShirt.addMouseListener(new MainMouseEvent("31"));
		patternShirt.addMouseListener(new MainMouseEvent("32"));
		shortShirt.addMouseListener(new MainMouseEvent("33"));
		Slacks.addMouseListener(new MainMouseEvent("41"));
		cottonPants.addMouseListener(new MainMouseEvent("42"));
		blueJeans.addMouseListener(new MainMouseEvent("43"));
		shorts.addMouseListener(new MainMouseEvent("44"));
		training.addMouseListener(new MainMouseEvent("45"));
		upDenimPants.addMouseListener(new MainMouseEvent("46"));
		Sneakers.addMouseListener(new MainMouseEvent("51"));
		Walker.addMouseListener(new MainMouseEvent("52"));
		slipper.addMouseListener(new MainMouseEvent("53"));
	} // end mainMouseEvent method
	
	
	
	
	// 바뀌는 페이지를 세팅 해주는 메소드
	public static void setMainPage(JPanel panel) {
		mainPage.removeAll();

		mainPageScroll.setViewportView(null);
		mainPage = panel;
		mainPage.setPreferredSize(new Dimension(450, 3200));
		mainPageScroll.setViewportView(mainPage);

		frame.revalidate();
		frame.repaint();
	} // end setMainPage method
} // end class
