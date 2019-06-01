package com.boram.myPage.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.boram.member.controller.MemberController;
import com.boram.shopping.view.MainView;
import java.awt.Color;
public class WB_MyPage_Main{
	public static final int FWID = 718;
	public static final int FHIT = 500;
	private JPanel contentPane;
	private EmptyBorder eb = new EmptyBorder(5,5,5,5);
	MemberController mc = new MemberController();
	
	// 메인 페이지 호출을 위한 JPanel 반환
	public JPanel myPageMain() {
		return this.contentPane;
	}
	
	public WB_MyPage_Main() {
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBounds(0,0,FWID,FHIT);
		contentPane.setLayout(null);
		contentPane.setVisible(true);
		
		JButton btnNewButton = new JButton("<HTML><font face = ><Center> 회원정보<br>수정</HTML>");
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainView.setMainPage(new WB_MyPageView().getmyPageView());
			}
		});
		btnNewButton.setBounds(128, 299, 103, 94);
		btnNewButton.setBorder(eb);
		contentPane.add(btnNewButton);
		
		
		JButton button = new JButton("장바구니");
		button.setBackground(Color.WHITE);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainView.setMainPage(new WB_MyCartView().getMyCartView());
			}
		});
		button.setBounds(303, 299, 103, 94);
		button.setBorder(eb);
		contentPane.add(button);
		
		JButton button_1 = new JButton("<html><center>구매 내역 및 <br>배송확인</html>");
		button_1.setBackground(Color.WHITE);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainView.setMainPage(new WB_MyDeliveryView().getMyDeliveryView());
			}
		});
		button_1.setBounds(482, 299, 103, 94);
		button_1.setBorder(eb);
		contentPane.add(button_1);
		
		JLabel lblMyCart = new JLabel("<html><left>My Page</html>");
		lblMyCart.setBounds(0,20, 223,74);
		lblMyCart.setHorizontalAlignment(SwingConstants.CENTER);
		lblMyCart.setFont(new Font("굴림", Font.ITALIC, 50));
		contentPane.add(lblMyCart);
		
		String st = MemberController.m.getName();
		JLabel lblNewLabel = new JLabel(st+"님의 MyPage입니다.");
		lblNewLabel.setFont(new Font("굴림", Font.ITALIC, 15));
		lblNewLabel.setBounds(25, 104, 325, 40);
		contentPane.add(lblNewLabel);
	}
}
