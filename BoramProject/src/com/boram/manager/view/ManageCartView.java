package com.boram.manager.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.boram.manager.vo.Order;
import com.boram.manager.vo.OrderDao;
import com.boram.manager.vo.Product;
import com.boram.manager.vo.ProductDao;

public class ManageCartView {

	private JFrame frame;
	private JTable table;
	private OrderDao od = new OrderDao();
	private ArrayList<Order> oArr = od.fileRead();
	private ProductDao pd = new ProductDao();
	private List<Product> pArr = pd.fileRead();


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
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(224, 0, 227, 67);
		frame.getContentPane().add(panel);
		
		JLabel lblNewLabel = new JLabel("\uC7A5\uBC14\uAD6C\uB2C8");
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 33));
		
		String[] list = {"주문번호", "주문상품", "주문갯수", "주문가격", "배송상태"};
		
		DefaultTableModel model = new DefaultTableModel(list,0);
		
		JTable table_1 = new JTable(model);
		
		for (int i = 0; i<oArr.size(); i++) {
			int orderNo = oArr.get(i).getOrderNo(); // 주문번호
			int[] pNo = new int[oArr.get(i).getpNo().size()]; // 상품번호로 불러옴
			String productName = " ";
			for(int j=0; j < pArr.size(); j++) {
				for(int j2= 0; j2 < pArr.size(); j2++){
					if(pNo[j] == pArr.get(j2).getpNo()) {
						productName += (pArr.get(j2).getProductName() + ""); // 주문상품 이름
					}
				}
				
			}
					
			ArrayList<Integer> amount = oArr.get(i).getAmount(); // 주문갯수 
			String amountTotal = " ";
			for(int j= 0; j < amount.size(); j++) {
				amountTotal += (amount.get(j)+ " ");
			}
			int payment = oArr.get(i).getPayment(); // 주문가격
			int state = oArr.get(i).getState();	// 배송상태
			Object[] data = {orderNo, productName, amount, payment, state};
			model.addRow(data);
			
		}
	
		
		table.getColumnModel().getColumn(0).setPreferredWidth(99);
		table.getColumnModel().getColumn(1).setPreferredWidth(97);
		table.getColumnModel().getColumn(2).setPreferredWidth(89);
		table.getColumnModel().getColumn(4).setPreferredWidth(116);
		table.setBounds(17, 77, 660, 50);
		frame.getContentPane().add(table);
		
		JButton btnNewButton = new JButton("\uC8FC\uBB38\uD558\uAE30");
		btnNewButton.setBounds(272, 480, 125, 29);
		frame.getContentPane().add(btnNewButton);
	}
	


}
