package com.boram.shopping.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.boram.member.controller.MemberController;
import com.boram.member.view.MemberView1;
import com.boram.myPage.view.WB_MyPage_Main;
import com.boram.shopping.view.MainView;
import com.boram.shopping.view.MainPanel;

public class MainMouseEvent extends MouseAdapter{
	private MemberController mc = new MemberController();
	JPanel subMenu;
	JScrollPane subMenuScroll;
	String what;

	public MainMouseEvent(JPanel subMenu, JScrollPane subMenuScroll,String what) {
		this.subMenu= subMenu;
		this.subMenuScroll = subMenuScroll;
		this.what = what;
	}
	
	public MainMouseEvent(String what) {
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
			MainView.setMainPage(new MemberView1().getLoginView());
		}
		else if(what.equals("마이페이지")) {
			if(mc.nugu().getName()==null) {
				JOptionPane.showMessageDialog(null, "로그인 먼저 해주세요", "LogIn Error", JOptionPane.WARNING_MESSAGE);	
			}else {
				MainView.setMainPage(new WB_MyPage_Main().myPageMain());
			}
		}
		
	}
}
