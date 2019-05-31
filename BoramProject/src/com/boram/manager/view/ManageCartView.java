package com.boram.manager.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

public class ManageCartView {

	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageCartView window = new ManageCartView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ManageCartView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 716, 596);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\uC7A5\uBC14\uAD6C\uB2C8");
		lblNewLabel.setFont(new Font("±¼¸²", Font.BOLD, 33));
		lblNewLabel.setBounds(259, 15, 171, 63);
		frame.getContentPane().add(lblNewLabel);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\uC8FC\uBB38\uBC88\uD638", "\uC0C1\uD488\uAE08\uC561", "\uBC30\uC1A1\uC0C1\uD0DC", "\uC218\uB7C9", "\uC8FC\uBB38\uC0C1\uD488"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(99);
		table.getColumnModel().getColumn(1).setPreferredWidth(97);
		table.getColumnModel().getColumn(2).setPreferredWidth(89);
		table.getColumnModel().getColumn(4).setPreferredWidth(116);
		table.setBounds(17, 77, 660, 50);
		frame.getContentPane().add(table);
		
		JButton btnNewButton = new JButton("\uC8FC\uBB38\uD558\uAE30");
		btnNewButton.setBounds(287, 481, 125, 29);
		frame.getContentPane().add(btnNewButton);
	}
}
