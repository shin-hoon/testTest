package com.boram.myPage.view;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.boram.manager.vo.Order;
import com.boram.manager.vo.OrderDao;
import com.boram.member.controller.MemberController;
import com.boram.shopping.view.MainView;

public class WB_MyDeliveryView {
	public static final int FWID = 718;
	public static final int FHIT = 500;
	private OrderDao od = new OrderDao();
	private MemberController mc = new MemberController();
	private JPanel myDeliveryView;
	private JTable table;
//	private ArrayList<Order> order1 = od.fileRead();
	private ArrayList<Order> order1 = new ArrayList<Order>();

	public JPanel getMyDeliveryView() {
		return this.myDeliveryView;
	}

	public WB_MyDeliveryView() {
		myDeliveryView = new JPanel();
		myDeliveryView.setBackground(Color.white);
		myDeliveryView.setBounds(0, 0, FWID, 800);
		myDeliveryView.setLayout(null);

		JPanel panel1 = new JPanel();
		panel1.setBackground(Color.WHITE);
		panel1.setBounds(0, 0, FWID, 90);
		myDeliveryView.add(panel1);
		panel1.setLayout(null);

		JLabel label1 = new JLabel("My Delivery ");
		label1.setBounds(12, 10, 283, 58);
		panel1.add(label1);
		label1.setHorizontalAlignment(SwingConstants.CENTER);
		label1.setFont(new Font("굴림", Font.ITALIC, 50));

		JPanel panel2 = new JPanel();
		panel2.setBackground(Color.WHITE);
		panel2.setBounds(0, 90, 718, 698);
		panel2.setLayout(null);
		myDeliveryView.add(panel2);

		// 주문목록 보여주는 J테이블
		String colName[] = { "index"/* , "pic" */, "OrderNo", "State", "Payment", "address" };
		DefaultTableModel dtm = new DefaultTableModel(colName, 0);
		table = new JTable(dtm);
		table.setBackground(Color.WHITE);
		
		JScrollPane sp = new JScrollPane(table);
		sp.setBounds(14, 0, 676, 625);
		panel2.add(sp, "Center");

		// 오더리스트는 정제해서 본인것만 보여주어야함
		// OList에서 본인 번호것만 다른 list에 넣어주자.
		// 정제시 본인것 + state가 int값이니까 String으로 변환해서 해주기.
		// payment도 마찬가지.
		
		//테스트출력문
		order1 = od.fileRead();
		for (Order i : order1) {
			System.out.println(i);
		}


		// 주문목록이없으면(order1에 who것이없으면)
		ArrayList<Order> order2 = new ArrayList<Order>();

		try {
			// order1.get(who).getOrderNo();
			for (int i = 0; i < order1.size(); i++) {
				if (order1.get(i).getOrderId().equals(mc.nugu().getName())) {
					order2.add(order1.get(i));
					System.out.println("주문목록 있음.");
					
				}
			}
		} catch (NullPointerException | IndexOutOfBoundsException e) {
			System.out.println("널포인트에러 : 주문목록 없음.");
			JOptionPane.showMessageDialog(null, "주문목록이 없습니다.", "LogIn Error", JOptionPane.WARNING_MESSAGE);
			MainView.setMainPage(new WB_MyPage_Main().myPageMain());
		}

		for (int i = 0; i < order2.size(); i++) {

			int index=i;
//			Object pic = new Object();
			int oNo= order2.get(i).getOrderNo();
			int State = order2.get(i).getState();
			int Payment = order2.get(i).getPayment();
			String address = order2.get(i).getAddress();
			
			
			Object[] data = { index/* , pic */, oNo, State, Payment, address };
			dtm.addRow(data);

		}
	}
}
