package com.boram.shopping.controller;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.boram.shopping.view.MainView;

public class MainMouseEvent extends MouseAdapter{
	JPanel subMenu;
	JScrollPane subMenuScroll;
	JPanel mainPage;
	JFrame frame;
	String what;

	public MainMouseEvent(JPanel subMenu, JScrollPane subMenuScroll,String what) {
		this.subMenu= subMenu;
		this.subMenuScroll = subMenuScroll;
		this.what = what;
	}
	
/*	public MainMouseEvent(JFrame frame, JPanel mainPage) {
		
		mainPage.removeAll();

//		MainView.mainPageScroll.setViewportView(null);
		this.mainPage = mainPage;
		mainPage.setPreferredSize(new Dimension(450, 1000));
		MainView.mainPageScroll.setViewportView(mainPage);

		frame.revalidate();
		frame.repaint();
	}
	*/
	@Override
	public void mouseClicked(MouseEvent e) {
		if(what.equals("서브메뉴닫기")) {
			new MainThreadEvent(subMenu,subMenuScroll,-376,0,"서브메뉴닫기").start();
		}
		else if(what.equals("서브메뉴열기")) {
			new MainThreadEvent(subMenu,subMenuScroll,-376,0,"서브메뉴열기").start();
		}
		
	}
}
