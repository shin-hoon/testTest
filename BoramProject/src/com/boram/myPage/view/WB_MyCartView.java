package com.boram.myPage.view;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.boram.manager.vo.Product;
import com.boram.member.controller.MemberController;
import com.boram.member.vo.Member;
import com.boram.myPage.controller.*;

public class WB_MyCartView {
	private JPanel myCartView;
	public static final int FWID = 718;
	public static final int FHIT = 500;
	private JTable table;
	private MyCart ca = new MyCart();

	private MemberController mc = new MemberController();
	Member m = mc.nugu();

	/**
	 * Launch the application.
	 */

	public JPanel getMyCartView() {
		return this.myCartView;
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings("null")
	public WB_MyCartView() {
		myCartView = new JPanel();
		myCartView.setBackground(Color.white);
		myCartView.setBounds(0, 0, FWID, 800);
		myCartView.setLayout(null);
		// getContentPane().add(myCartView);

		JPanel panel1 = new JPanel();

		panel1.setBackground(Color.WHITE);
		panel1.setForeground(Color.WHITE);
		panel1.setBounds(0, 0, FWID, 90);
		myCartView.add(panel1);

		JLabel lblMyCart = new JLabel("My Cart ");
		panel1.add(lblMyCart);
		lblMyCart.setHorizontalAlignment(SwingConstants.CENTER);
		lblMyCart.setFont(new Font("굴림", Font.ITALIC, 50));
		// lblMyCart.setBounds(0,0,55,100);

		JPanel panel2 = new JPanel();
		panel2.setBackground(Color.WHITE);
		panel2.setForeground(Color.WHITE);
		panel2.setBounds(0, 90, FWID, 710);
		myCartView.add(panel2);

		JScrollPane sp = new JScrollPane();
		// 값 넣어야함.
		table = new JTable();
		table.setBackground(Color.LIGHT_GRAY);
		panel2.add(sp);
		sp.add(table);

		/**
		 * 장바구니 보여주는 테이블.
		 * 
		 * @return 테이블 내용 테이블콘텐츠. [장바구니사이즈][콤보박스,이미지,번호,카테고리,품목이름,사이즈,수량 ,가격](6)
		 * 
		 */
		// pNo, category, productName, price, size, stock, count(조회수)
		// 1,1,"hat",35000,one,1,1.
		// 현재 테이블 테스트불가.
		// 현재 테이블에 CList들어있음.
		// CList에서 뺴와서 새로구성해야함.

		// 이거 CList
		// String col[] = { "pNo", "Cat", "pName", "Price", "Size", "Stock", "Count" };
		// 실제사용
		String colName[] = { "chk", "Pic", "index", "Cat", "pName", "Size", "Amount", "Price" };
		ArrayList<Product> CList = ca.cartList();
		DefaultTableModel dtm = new DefaultTableModel(colName, 0);
		
		
		JCheckBox[] chk = new JCheckBox[CList.size()-1];
		for (int i = 0; i < CList.size(); i++) {
			
			// 체크박스 이렇게 넣으면됨?
			chk[i]= new JCheckBox();
			// 사진넣기 다시.
			Object image = new Object();
			int index = i;
			int cat = CList.get(i).getCategory();
			String pName = CList.get(i).getProductName();
			String size = CList.get(i).getSize();
			// 콤보박스로..?
			int amount = 1;
			int price = CList.get(i).getPrice();
			
			Object[] data = { chk, image, index, cat, pName, size, amount, price };
			dtm.addRow(data);
			// dtm.add(data);
		}

		// 버튼 : 삭제, 주문하기, 수량변경, 이전메뉴

	}

	

}
