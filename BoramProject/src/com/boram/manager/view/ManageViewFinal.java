package com.boram.manager.view;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.swing.DebugGraphics;
import javax.swing.DropMode;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import com.boram.manager.controller.ManagerController2;
import com.boram.manager.vo.Category;
import com.boram.manager.vo.CategoryDao;
import com.boram.manager.vo.Order;
import com.boram.manager.vo.OrderDao;
import com.boram.manager.vo.Product;
import com.boram.manager.vo.ProductDao;
import com.boram.member.vo.Member;
import com.boram.member.vo.MemberDao;
import com.boram.shopping.view.MainView;

public class ManageViewFinal {
	private ProductDao pDao = new ProductDao();
	private ArrayList<Product> pArr = pDao.fileRead();
	private OrderDao oDao = new OrderDao();
	private ArrayList<Order> oArr = oDao.fileRead();
	private ManagerController2 mc = new ManagerController2();
	private Date d = new Date();
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	private int date = Integer.parseInt(sdf.format(d));
	private CategoryDao cDao = new CategoryDao();
	private ArrayList<Category> cArr = cDao.fileRead();
	private MemberDao mDao = new MemberDao();
	private ArrayList<Member> mArr = mDao.fileRead();

	public JPanel manageMain() {
		JPanel manageMain = new JPanel();
		manageMain.setBounds(100, 100, 745, 691);
		manageMain.setLayout(null);

		JLabel label_1 = new JLabel("\uAD00\uB9AC\uC790 \uD398\uC774\uC9C0");
		label_1.setFont(new Font("굴림체", Font.BOLD | Font.ITALIC, 20));
		label_1.setBounds(249, 34, 153, 67);
		manageMain.add(label_1);

		JPanel searchMember = new JPanel();
		searchMember.setBounds(155, 100, 343, 67);
		manageMain.add(searchMember);

		JLabel LsearchMember = new JLabel("1. \uD68C\uC6D0\uC815\uBCF4 \uC870\uD68C");
		searchMember.add(LsearchMember);

		JPanel insertProduct = new JPanel();
		insertProduct.setBounds(155, 179, 343, 67);
		manageMain.add(insertProduct);

		JLabel LinsertProduct = new JLabel("2. \uBB3C\uD488\uB4F1\uB85D");
		insertProduct.add(LinsertProduct);

		JPanel manageProduct = new JPanel();
		manageProduct.setBounds(155, 261, 343, 67);
		manageMain.add(manageProduct);

		JLabel LmanageProduct = new JLabel("3.\uC81C\uD488\uAD00\uB9AC");
		manageProduct.add(LmanageProduct);

		JPanel analysis = new JPanel();
		analysis.setBounds(155, 343, 343, 67);
		manageMain.add(analysis);

		JLabel Lanalysis = new JLabel("4. \uD310\uB9E4 \uBD84\uC11D");
		analysis.add(Lanalysis);

		JPanel salesState = new JPanel();
		salesState.setBounds(155, 425, 343, 67);
		manageMain.add(salesState);

		JLabel LsalesState = new JLabel("5. \uD310\uB9E4\uD604\uD669");
		salesState.add(LsalesState);

		JPanel inforDelivery = new JPanel();
		inforDelivery.setBounds(155, 507, 343, 67);
		manageMain.add(inforDelivery);

		JLabel label = new JLabel("6. \uBC30\uC1A1\uC815\uBCF4");
		inforDelivery.add(label);

		manageMain.setVisible(true);

		searchMember.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MainView.setMainPage(searchMember());
			}

		});
		insertProduct.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MainView.setMainPage(insertProduct());
			}

		});
		manageProduct.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MainView.setMainPage(manageProduct());
			}

		});
		analysis.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MainView.setMainPage(analyzeSale());
			}

		});
		salesState.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MainView.setMainPage(saleState());
			}

		});
		inforDelivery.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// MainView.setMainPage();
			}
		});

		return manageMain;
	}

	// 상품관리페이지-------------------------------------------------------------
	public JPanel manageProduct() {

		JPanel manageProduct = new JPanel();
		manageProduct.setBounds(62, 15, 772, 585);
		manageProduct.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(92, 76, 562, 438);
		manageProduct.add(scrollPane);

		/*
		 * Object[][] o = new Object[size][5]; for (int i = 0; i < o.length; i++) { for
		 * (int j = 0; j < o[i].length; j++) { switch (j) { case 0: o[i][j] =
		 * mArr.get(i).getmNo(); break; case 1: o[i][j] = mArr.get(i).getId(); break;
		 * case 2: o[i][j] = mArr.get(i).getName(); break; case 3: o[i][j] =
		 * mArr.get(i).getPhone(); break; case 4: o[i][j] = mArr.get(i).getEmail();
		 * break; default: break; } } }
		 */

		int size = pArr.size();
		Object[][] o = new Object[size][6];
		for (int i = 0; i < o.length; i++) {
			for (int j = 0; j < o[i].length; j++) {
				switch (j) {
				case 0:
					o[i][j] = pArr.get(i).getpNo();
					break;
				case 1:
					for (int j2 = 0; j2 < cArr.size(); j2++) {
						if (cArr.get(j2).getNum() == pArr.get(i).getCategory()) {
							o[i][j] = cArr.get(j2).getNum() + " : " + cArr.get(j2).getKind();
						}
					}
					break;
				case 2:
					o[i][j] = pArr.get(i).getProductName();
					break;
				case 3:
					o[i][j] = pArr.get(i).getSize();
					break;
				case 4:
					o[i][j] = pArr.get(i).getPrice();
					break;
				case 5:
					o[i][j] = pArr.get(i).getStock();
					break;

				default:
					break;
				}
			}
		}

		JTable table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(o, new String[] { "\uC0C1\uD488\uBC88\uD638", "\uCE74\uD14C\uACE0\uB9AC",
				"\uC81C\uD488\uBA85", "\uC0AC\uC774\uC988", "\uAC00\uACA9", "\uC7AC\uACE0" }));

		table.setRowSelectionAllowed(true);
		JButton lastPage = new JButton("\uC774\uC804\uD398\uC774\uC9C0");
		lastPage.setBounds(92, 529, 125, 29);
		manageProduct.add(lastPage);

		JLabel lblNewLabel = new JLabel("\uC0C1\uD488\uC815\uBCF4");
		lblNewLabel.setBounds(297, 15, 91, 27);
		manageProduct.add(lblNewLabel);

		JButton update = new JButton("\uC218\uC815");
		update.setBounds(308, 529, 125, 29);
		manageProduct.add(update);

		JButton delete = new JButton("\uC0AD\uC81C");
		delete.setBounds(529, 529, 125, 29);
		manageProduct.add(delete);

		manageProduct.setVisible(true);

		lastPage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MainView.setMainPage(manageMain());
			}
		});
		delete.addMouseListener(new MouseAdapter() {
			//여기부터
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			@Override
			public void mouseClicked(MouseEvent e) {
				pArr.get(0).setCategory(11);
				Object a;
				int temp = -1;
				try {
					a = table.getValueAt(0, table.getSelectedColumn());
					for (int i = 0; i < pArr.size(); i++) {
						if (pArr.get(i).getpNo() == (int)a) {
							temp = i;
							break;
						}
					}
				} catch (ArrayIndexOutOfBoundsException e2) {
					temp = -1;
				}
				
				

				if (temp < 0) {
					JOptionPane.showMessageDialog(null, "선택한 값이 없습니다","warning",JOptionPane.ERROR_MESSAGE);
				} else {
					int result = JOptionPane.showConfirmDialog(null, "정말 삭제하시겠습니까?", "Confirm",
							JOptionPane.YES_NO_OPTION);
					if (result == JOptionPane.CLOSED_OPTION) {

					} else if (result == JOptionPane.YES_OPTION) {
						mc.deleteProduct(temp);
						pDao.fileSave(pArr);
						MainView.setMainPage(manageProduct());
					} else {

					}
				}
			}
		});
		
		

		return manageProduct;
	}

	public JPanel analyzeSale() {

		JPanel analyzeSale = new JPanel();
		analyzeSale.setBounds(17, 15, 747, 675);
		analyzeSale.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(138, 143, 452, 438);
		analyzeSale.add(scrollPane);

		JTable table = new JTable();
		scrollPane.setViewportView(table);

		JLabel lblNewLabel = new JLabel("\uD310\uB9E4\uBD84\uC11D");
		lblNewLabel.setBounds(207, 15, 222, 36);
		analyzeSale.add(lblNewLabel);

		String[] kind = { "조회수 분석", " 판매율분석" };
		JComboBox comboBox = new JComboBox(kind);
		comboBox.setBounds(138, 101, 198, 27);
		analyzeSale.add(comboBox);

		HashMap<Integer, Double> copy1 = mc.analysis();
		Set<Integer> key = copy1.keySet();
		Iterator<Integer> itKey = key.iterator();
		ArrayList<Integer> pNo1 = new ArrayList<Integer>();
		ArrayList<Double> sales1 = new ArrayList<Double>();
		while (itKey.hasNext()) {
			int value = itKey.next();
			double result = copy1.get(value);
			pNo1.add(value);
			sales1.add(result);
		}
		int iTemp = 0;
		double dTemp = 0.0;
		for (int i = 0; i < sales1.size(); i++) {
			for (int j = 0; j < i; j++) {
				if (pNo1.get(i) < pNo1.get(j)) {
					iTemp = pNo1.get(i);
					pNo1.set(i, pNo1.get(j));
					pNo1.set(j, iTemp);

					dTemp = sales1.get(i);
					sales1.set(i, sales1.get(j));
					sales1.set(j, dTemp);
				}
			}
		}

		HashMap<Integer, Double> copy2 = mc.analysis();
		Set<Integer> key2 = copy2.keySet();
		Iterator<Integer> itKey2 = key.iterator();
		ArrayList<Integer> pNo2 = new ArrayList<Integer>();
		ArrayList<Double> sales2 = new ArrayList<Double>();
		while (itKey.hasNext()) {
			int value = itKey.next();
			double result = copy2.get(value);
			pNo2.add(value);
			sales2.add(result);
		}
		iTemp = 0;
		dTemp = 0.0;
		for (int i = 0; i < sales1.size(); i++) {
			for (int j = 0; j < i; j++) {
				if (sales2.get(i) < sales2.get(j)) {
					iTemp = pNo2.get(i);
					pNo2.set(i, pNo2.get(j));
					pNo2.set(j, iTemp);

					dTemp = sales2.get(i);
					sales2.set(i, sales2.get(j));
					sales2.set(j, dTemp);
				}
			}
		}
		Object[][] o = new Object[pNo1.size()][3];
		for (int i = 0; i < o.length; i++) {
			for (int j = 0; j < o[i].length; j++) {
				if (j == 0) {
					o[i][j] = pNo1.get(i);
				} else if (j == 1) {
					for (int k = 0; k < pArr.size(); k++) {
						if (pNo1.get(i) == pArr.get(k).getpNo()) {
							o[i][j] = pArr.get(k).getCount();
							break;
						}
					}
				} else {
					o[i][j] = sales1.get(i);
				}
			}
		}
		table.setModel(new DefaultTableModel(o,
				new String[] { "\uC0C1\uD488\uBC88\uD638", "\uC870\uD68C\uC218", "\uD310\uB9E4\uC728" }));

		comboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox) e.getSource();
				String index = (String) cb.getSelectedItem();
				if (cb.equals(kind[0])) {

				} else {
					Object[][] o = new Object[pNo2.size()][3];
					for (int i = 0; i < o.length; i++) {
						for (int j = 0; j < o[i].length; j++) {
							if (j == 0) {
								o[i][j] = pNo2.get(i);
							} else if (j == 1) {
								for (int k = 0; k < pArr.size(); k++) {
									if (pNo1.get(i) == pArr.get(k).getpNo()) {
										o[i][j] = pArr.get(k).getCount();
										break;
									}
								}
							} else {
								o[i][j] = sales2.get(i);
							}
						}
					}
				}

			}
		});

		table.setModel(new DefaultTableModel(o,
				new String[] { "\uC0C1\uD488\uBC88\uD638", "\uC870\uD68C\uC218", "\uD310\uB9E4\uC728" }));

		JButton lastPage = new JButton("\uC774\uC804 \uD398\uC774\uC9C0");
		lastPage.setBounds(138, 612, 125, 29);
		analyzeSale.add(lastPage);

		JButton update = new JButton("\uC218\uC815");
		update.setBounds(465, 612, 125, 29);
		analyzeSale.add(update);

		JButton delete = new JButton("\uC0AD\uC81C");
		delete.setBounds(304, 612, 125, 29);
		analyzeSale.add(delete);

		analyzeSale.setVisible(true);

		return analyzeSale;

	}

	public JPanel saleState() {

		String[] term = { "1개월", "2 개월", "3 개월", "4 개월", "5개월" };
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		ArrayList<Integer> result = new ArrayList<Integer>();
		result = mc.salesState(date, 2);
		for (int i = 0; i < result.size(); i++) {
			// i+1한 곳에 result.get(i)로 바꿔줘야함
			dataset.addValue(i + 1, (date / 100 - i) + "", "Value");
		}
		JFreeChart chart = ChartFactory.createBarChart("판매현황", "Category", "Value", dataset, PlotOrientation.VERTICAL,
				true, true, true);

		ChartPanel cp = new ChartPanel(chart);

		JPanel saleState = new JPanel();
		saleState.setBounds(32, 50, 572, 510);
		saleState.setLayout(null);

		JLabel label = new JLabel("\uD310\uB9E4\uD604\uD669");
		label.setBounds(148, 26, 78, 21);
		saleState.add(label);

		JPanel graph = new JPanel();
		graph.setBounds(57, 126, 446, 344);
		saleState.add(graph);
		graph.add(cp);
		JLabel label_1 = new JLabel("\uBD84\uC11D\uAE30\uAC04(\uC6D4)");
		label_1.setBounds(57, 79, 114, 21);
		saleState.add(label_1);

		JComboBox<String> comboBox = new JComboBox<String>( /* term */ );
		comboBox.setBounds(188, 76, 93, 27);
		saleState.add(comboBox);

		graph.setVisible(true);
		saleState.setVisible(true);
		return saleState;
	}

	public JPanel insertProduct() {

		JPanel InsertProduct = new JPanel();
		InsertProduct.setBounds(101, 41, 465, 505);
		InsertProduct.setLayout(null);

		JLabel label = new JLabel("\uC0C1\uD488 \uB4F1\uB85D");
		label.setBounds(193, 5, 78, 21);
		InsertProduct.add(label);

		JLabel lblNewLabel = new JLabel("\uC0C1\uD488\uBA85");
		lblNewLabel.setBounds(33, 60, 78, 21);
		InsertProduct.add(lblNewLabel);

		JLabel label_1 = new JLabel("\uCE74\uD14C\uACE0\uB9AC");
		label_1.setBounds(33, 97, 78, 21);
		InsertProduct.add(label_1);

		JComboBox<Category> category = new JComboBox<Category>(cArr.toArray(new Category[cArr.size()]));
		category.setBounds(140, 94, 115, 27);
		InsertProduct.add(category);

		String[] sArr = { "Free Size" };

		JTextField productName = new JTextField();
		productName.setBounds(140, 57, 156, 27);
		InsertProduct.add(productName);
		productName.setColumns(10);

		JLabel label_2 = new JLabel("\uC0AC\uC774\uC988");
		label_2.setBounds(33, 133, 78, 21);
		InsertProduct.add(label_2);

		JLabel label_3 = new JLabel("\uC7AC\uACE0");
		label_3.setBounds(33, 169, 78, 21);
		InsertProduct.add(label_3);

		JLabel label_4 = new JLabel("\uAC00\uACA9");
		label_4.setBounds(33, 205, 78, 21);
		InsertProduct.add(label_4);

		JLabel lblNewLabel_1 = new JLabel("\uC0C1\uC138\uC124\uBA85");
		lblNewLabel_1.setBounds(33, 241, 78, 21);
		InsertProduct.add(lblNewLabel_1);

		JTextArea explain = new JTextArea();
		explain.setAlignmentY(Component.TOP_ALIGNMENT);
		explain.setAlignmentX(Component.LEFT_ALIGNMENT);
		explain.setBounds(33, 277, 350, 144);
		InsertProduct.add(explain);
		// JComboBox<String> combox = new JComboBox<String>(array .toArray(new
		// String[array .size()]));
		JComboBox<String> size = new JComboBox<String>(sArr);

		size.setBounds(140, 130, 115, 27);
		InsertProduct.add(size);

		JTextField stock = new JTextField();
		stock.setBounds(140, 166, 156, 27);
		InsertProduct.add(stock);
		stock.setColumns(10);

		JTextField price = new JTextField();
		price.setColumns(10);
		price.setBounds(140, 202, 156, 27);
		InsertProduct.add(price);

		JButton button = new JButton("\uB4F1\uB85D");
		button.setBounds(170, 461, 125, 29);
		InsertProduct.add(button);

		InsertProduct.setVisible(true);

		return InsertProduct;
	}

	public JPanel updateProduct() {

		JPanel updateProduct = new JPanel();
		updateProduct.setBounds(64, 75, 506, 511);
		updateProduct.setLayout(null);

		JLabel lblNewLabel = new JLabel("\uC0C1\uD488\uC218\uC815");
		lblNewLabel.setBounds(177, 28, 78, 21);
		updateProduct.add(lblNewLabel);

		String[] sArr = { "Free Size" };

		JLabel lblNewLabel_1 = new JLabel("\uC0C1\uD488\uBA85");
		lblNewLabel_1.setBounds(35, 83, 78, 21);
		updateProduct.add(lblNewLabel_1);

		JLabel label = new JLabel("\uCE74\uD14C\uACE0\uB9AC");
		label.setBounds(35, 119, 78, 21);
		updateProduct.add(label);

		JLabel label_1 = new JLabel("\uC0AC\uC774\uC988");
		label_1.setBounds(35, 155, 78, 21);
		updateProduct.add(label_1);

		JLabel label_2 = new JLabel("\uC7AC\uACE0");
		label_2.setBounds(35, 191, 78, 21);
		updateProduct.add(label_2);

		JLabel label_3 = new JLabel("\uAC00\uACA9");
		label_3.setBounds(35, 229, 78, 21);
		updateProduct.add(label_3);

		JLabel label_4 = new JLabel("\uC0C1\uC138\uC124\uBA85");
		label_4.setBounds(35, 265, 78, 21);
		updateProduct.add(label_4);

		JTextField productName = new JTextField();
		productName.setBounds(130, 80, 156, 27);
		updateProduct.add(productName);
		productName.setColumns(10);

		JTextField stock = new JTextField();
		stock.setColumns(10);
		stock.setBounds(130, 188, 156, 27);
		updateProduct.add(stock);

		JTextField price = new JTextField();
		price.setColumns(10);
		price.setBounds(130, 226, 156, 27);
		updateProduct.add(price);

		JComboBox<Category> size = new JComboBox<Category>(cArr.toArray(new Category[cArr.size()]));// 이부분주석하면 빌더동작
		size.setBounds(130, 116, 156, 27);
		updateProduct.add(size);

		JComboBox<String> comboBox_1 = new JComboBox<String>(sArr);
		comboBox_1.setBounds(130, 152, 156, 27);
		updateProduct.add(comboBox_1);

		JTextField explain = new JTextField();
		explain.setAlignmentY(Component.TOP_ALIGNMENT);
		explain.setAlignmentX(Component.LEFT_ALIGNMENT);
		explain.setBounds(45, 301, 383, 149);
		updateProduct.add(explain);
		explain.setColumns(10);
		updateProduct.setVisible(true);

		return updateProduct;

	}

	public JPanel searchMember() {

		int size = mArr.size();

		JPanel searchMember = new JPanel();
		searchMember.setBounds(51, 38, 664, 551);
		searchMember.setLayout(null);

		String[] list = { "회원번호", "아이디", "이름", "휴대폰번호", "이메일" };

		DefaultTableModel model = new DefaultTableModel(list, 0);

		JTable table_1 = new JTable(model);

		table_1.setFillsViewportHeight(true);
		table_1.setDropMode(DropMode.ON_OR_INSERT_ROWS);
		table_1.setDragEnabled(true);
		table_1.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		table_1.setDebugGraphicsOptions(DebugGraphics.BUFFERED_OPTION);
		table_1.setFocusCycleRoot(true);
		table_1.setIgnoreRepaint(true);
		table_1.setVerifyInputWhenFocusTarget(false);
		table_1.setUpdateSelectionOnSort(false);
		for (int i = 0; i < mArr.size(); i++) {
			int no = mArr.get(i).getmNo();
			String id = mArr.get(i).getId();
			String name = mArr.get(i).getName();
			String phone = mArr.get(i).getPhone();
			String email = mArr.get(i).getEmail();
			Object[] data = { no, id, name, phone, email };
			model.addRow(data);
		}
		table_1.setPreferredScrollableViewportSize(new Dimension(500, 150));
		table_1.setAutoCreateRowSorter(true);

		JScrollPane scrollPane = new JScrollPane(table_1);
		scrollPane.setBounds(101, 64, 452, 438);
		searchMember.add(scrollPane);
		scrollPane.setViewportView(table_1);
		table_1.setShowGrid(true);
//			table_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//			table_1.setColumnSelectionAllowed(true);
		table_1.setRowSelectionAllowed(true);
//			table_1.setCellSelectionEnabled(false);
		table_1.setBorder(UIManager.getBorder("ComboBox.border"));

		JLabel label = new JLabel("\uD68C\uC6D0\uC815\uBCF4");
		label.setBounds(107, 15, 96, 34);
		searchMember.add(label);

		JButton delete = new JButton("\uD0C8\uD1F4");
		delete.setBounds(430, 517, 125, 29);
		searchMember.add(delete);

		delete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});

		JButton lastPage = new JButton("\uC774\uC804 \uD398\uC774\uC9C0");
		lastPage.setBounds(101, 517, 137, 29);
		searchMember.add(lastPage);

		searchMember.setVisible(true);

		return searchMember;

	}

}
