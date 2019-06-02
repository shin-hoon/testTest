package com.boram.shopping.view;

import java.awt.Color;

import javax.swing.JPanel;

public class ContentPanel{
	private JPanel content;
	
	public JPanel getContent() {
		return this.content;
	}
	/**
	 *  상품 상세페이지
	 */
	public ContentPanel(String pNo) {
		content = new JPanel();
		content.setBackground(new Color(255, 255, 255));
		content.setBounds(0, 259, 718, 890);
		content.setLayout(null);
		content.setVisible(true);
		
		
	}

}
