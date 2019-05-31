package com.boram.myPage.view;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.boram.manager.vo.Order;
import com.boram.manager.vo.OrderDao;
import com.boram.member.controller.MemberController;


public class WB_MyDeliveryView{
	public static final int FWID = 718;
	public static final int FHIT = 500;
	private OrderDao od =new OrderDao();
	private MemberController mc = new MemberController();
	
	private JPanel myDeliveryView;
	private JTable table;

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
		
		JLabel label1 = new JLabel("My Delivery ");
		panel1.add(label1);
		label1.setHorizontalAlignment(SwingConstants.CENTER);
		label1.setFont(new Font("굴림", Font.ITALIC, 50));
		
		JPanel panel2 = new JPanel();
		panel2.setBackground(Color.WHITE);
		panel2.setBounds(0, 90, 718, 698);
		panel2.setLayout(null);
		myDeliveryView.add(panel2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(14, 12, 690, 579);
		panel2.add(scrollPane);
		
		JScrollPane sp = new JScrollPane(table);
		sp.setBounds(14,0,676,625);
		panel2.add(sp,"Center");
		
		//주문목록 보여주는 J테이블
		String colName[]= {"index","pic","cat","pName","Size","Amount","Price"	};
		DefaultTableModel dtm = new DefaultTableModel(colName,0);
		table = new JTable(dtm);
		
		
		//오더리스트는 정제해서 본인것만 보여주어야함
		//OList에서 본인 번호것만 다른 list에 넣어주자.
		order1 = od.fileRead();
		//mc.nugu().get
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
