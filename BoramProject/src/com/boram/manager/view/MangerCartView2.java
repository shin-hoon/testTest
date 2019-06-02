package com.boram.manager.view;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.boram.manager.vo.Order;
import com.boram.manager.vo.OrderDao;
import com.boram.manager.vo.Product;
import com.boram.manager.vo.ProductDao;

public class MangerCartView2 {
	
	public class ManageCartView2 {

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
		public ManageCartView2() {
			initialize();
		}

		/**
		 * Initialize the contents of the frame.
		 */


		public void setTableData(String[] datas) {
			DefaultTableModel TableModel = TableModel.getModel();
			TableModel.setRowCount(0);
			TableModel.addRow(datas);
			JTabel.updateUI();
		}

		table = new JTable(dtmStorage);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		table.setAutoCreateRowSorter(true);
		table.setCellSelectionEnabled(rootPaneCheckingEnabled); 
		scrollPane.setViewportView(table); 
		dtmStorage.setColumnIdentifiers(new String[] {"주문번호", "주문상품", "주문갯수", "주문가격", "배송상태" });

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
