package com.boram.shopping.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.boram.manager.view.ManageViewFinal;
import com.boram.member.controller.MemberController;
import com.boram.member.view.MemberView1;
import com.boram.member.vo.Member;
import com.boram.myPage.view.WB_MyPage_Main;
import com.boram.shopping.view.MainPanel;
import com.boram.shopping.view.MainView;

public class MainMouseEvent extends MouseAdapter{
	private MemberController mc = new MemberController();
	JPanel subMenu;
	JScrollPane subMenuScroll;
	String what;
	JLabel mainImage;

	public MainMouseEvent(JPanel subMenu, JScrollPane subMenuScroll,String what) {
		this.subMenu= subMenu;
		this.subMenuScroll = subMenuScroll;
		this.what = what;
	}
	
	public MainMouseEvent(String what) {
		this.what = what;
	}
	
	public MainMouseEvent(JLabel mainImage, String what) {
		this.mainImage = mainImage;
		this.what = what;
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(what.equals("서브메뉴닫기")) {
			new MainThreadEvent(subMenu,subMenuScroll,-376,0,"서브메뉴닫기").start();
		}
		else if(what.equals("서브메뉴열기")) {
			new MainThreadEvent(subMenu,subMenuScroll,-376,0,"서브메뉴열기").start();
		}
		else if(what.equals("메인")) {
			MainView.setMainPage(new MainPanel().getMainPanel());
		}
		else if(what.equals("로그인")) {
			
			if(mc.nugu()!=null) {
				// 로그인 되어있을 때 다시 로그인 버튼 누르면 로그아웃되고!
				mc.logOut();
				System.out.println("logout");
				MainView.setMainPage(new MainPanel().getMainPanel());
				//로그아웃 팝업창
				JOptionPane.showMessageDialog(null, "정상적으로 로그아웃 되었습니다.");
			}else {
				MainView.setMainPage(new MemberView1().getLoginView());
			}
			
		}
		else if(what.equals("마이페이지")) {
			if(mc.nugu()==null) {
				JOptionPane.showMessageDialog(null, "로그인 먼저 해주세요", "LogIn Error", JOptionPane.WARNING_MESSAGE);	
			}else {
				MainView.setMainPage(new WB_MyPage_Main().myPageMain());
			}
		}
		else if(what.equals("관리자페이지")) {
			System.out.println(mc.nugu().getmNo());
			/*if(mc.nugu().getName()==null ) {
				JOptionPane.showMessageDialog(null, "로그인 먼저 해주세요", "LogIn Error", JOptionPane.WARNING_MESSAGE);
			}else if(mc.nugu().getmNo() != 0) {
				JOptionPane.showMessageDialog(null, "권한이 없는 사용자 입니다.", "LogIn Error", JOptionPane.WARNING_MESSAGE);
			}else {*/
				MainView.setMainPage(new ManageViewFinal().manageMain());
			/* } */
		}
		else if(what.equals("상세페이지")) {
			System.out.println(mainImage.getName());
		}
		
	}
}
