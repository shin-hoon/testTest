package com.boram.shopping.controller;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.boram.shopping.view.MainView;

public class MainThreadEvent extends Thread{
	private int min, max;
	private String what;
	
	public MainThreadEvent(int min, int max, String what) {
		this.min = min;
		this.max = max;
		this.what = what;
	}
	
	@Override
	public void run() {
		try {
			if(what.equals("서브메뉴닫기")) {
				for(int i = max; i >= min; i-=2) {
					MainView.subMenuScroll.setLocation(i,0);
					Thread.sleep(1);
				}
			}
			else if(what.equals("서브메뉴열기")) {
				MainView.subMenuScroll.setVisible(true);
				MainView.subMenu.setVisible(true);
				for(int i = min; i <= max; i+=2) {
					MainView.subMenuScroll.setLocation(i,0);
					Thread.sleep(1);
				}
			}
		} catch (Exception e) {
			System.out.println("서브메뉴 오류 : "+e.getMessage());
		}
	}

}
