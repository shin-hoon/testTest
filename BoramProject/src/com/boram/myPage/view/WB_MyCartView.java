package com.boram.myPage.view;

import java.awt.*;
import javax.swing.*;

public class WB_MyCartView  {
	private JPanel myCartView;
	public static final int FWID = 718;
	public static final int FHIT = 500;

	/**
	 * Launch the application.
	 */

	public JPanel getMyCartView() {
		return this.myCartView;
	}

	/**
	 * Create the frame.
	 */
	public WB_MyCartView() {
		
		myCartView = new JPanel();
		myCartView.setBackground(Color.white);
		myCartView.setBounds(0, 0, FWID, 800);
		myCartView.setLayout(null);
		//getContentPane().add(myCartView);
		
		JPanel panel1 = new JPanel();
		
		panel1.setBackground(Color.WHITE);
		panel1.setForeground(Color.WHITE);
		panel1.setBounds(0, 0, FWID, 90);
		myCartView.add(panel1);
		
		
		JLabel lblMyCart = new JLabel("My Cart ");
		panel1.add(lblMyCart);
		lblMyCart.setHorizontalAlignment(SwingConstants.CENTER);
		lblMyCart.setFont(new Font("±¼¸²", Font.ITALIC, 50));
		//lblMyCart.setBounds(0,0,55,100);
		
		JPanel panel2 = new JPanel();
		panel2.setBackground(Color.WHITE);
		panel2.setForeground(Color.WHITE);
		panel2.setBounds(0,90, FWID, 710);
		myCartView.add(panel2);
		
		
	}

	}

