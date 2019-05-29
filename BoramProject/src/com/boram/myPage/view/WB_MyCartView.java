package com.boram.myPage.view;

import java.awt.*;
import javax.swing.*;

public class WB_MyCartView extends JFrame {
	public static final int FWID = 718;
	public static final int FHIT = 500;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WB_MyCartView frame = new WB_MyCartView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public WB_MyCartView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, FWID,FHIT);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, FWID, 90);
		getContentPane().add(panel);
		
		JLabel lblMyCart = new JLabel("My Cart");
		lblMyCart.setFont(new Font("Segoe Script", Font.ITALIC, 50));
		panel.add(lblMyCart);
	}
}
