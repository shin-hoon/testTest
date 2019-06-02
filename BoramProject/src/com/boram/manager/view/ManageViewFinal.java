package com.boram.manager.view;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.DebugGraphics;
import javax.swing.DropMode;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;
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
	private List<Product> pArr = pDao.fileRead();
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
	private JFreeChart chart;

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
				"\uC81C\uD488\uBA85", "\uC0AC\uC774\uC988", "\uAC00\uACA9", "\uC7AC\uACE0" }) {

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		for (int j = 0; j < table.getRowCount(); j++) {
			table.isCellEditable(j, 0);
		}
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

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

		update.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Object a;

				int b = -1;
				int x = table.getSelectedRow();
				a = table.getValueAt(x, 0);
				String value = String.valueOf(a);
				for (int i = 0; i < pArr.size(); i++) {
					if (pArr.get(i).getpNo() == Integer.parseInt(value)) {
						b = i;
						break;
					}
				}

				if (b == -1) {
					JOptionPane.showMessageDialog(null, "값을 선택해주세요", "Error", JOptionPane.WARNING_MESSAGE);
				} else {
					Product p = new Product();

					p = pArr.get(b);

					MainView.setMainPage(updateProduct(p, b));
				}
			}
		});

		delete.addMouseListener(new MouseAdapter() {
			// 여기부터
			@Override
			public void mouseClicked(MouseEvent e) {
				pArr.get(0).setCategory(11);
				Object a;
				int temp = -1;
				System.out.println(table.getSelectedColumn() + ":" + table.getSelectedRow());
				try {
					a = table.getValueAt(table.getSelectedRow(), 0);
					for (int i = 0; i < pArr.size(); i++) {
						if (pArr.get(i).getpNo() == (int) a) {
							temp = i;
							break;
						}
					}
				} catch (ArrayIndexOutOfBoundsException e2) {
					temp = -1;
				}

				if (temp < 0) {
					JOptionPane.showMessageDialog(null, "선택한 값이 없습니다", "warning", JOptionPane.ERROR_MESSAGE);
				} else {
					int result = JOptionPane.showConfirmDialog(null, "정말 삭제하시겠습니까?", "Confirm",
							JOptionPane.YES_NO_OPTION);
					if (result == JOptionPane.CLOSED_OPTION) {

					} else if (result == JOptionPane.YES_OPTION) {
						pArr.remove(temp);
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
		JComboBox<String> comboBox = new JComboBox<String>(kind);
		comboBox.setBounds(138, 101, 198, 27);
		analyzeSale.add(comboBox);

		HashMap<Integer, Double> copy1 = mc.analysis();
		Set<Integer> key = copy1.keySet();
		Iterator<Integer> itKey = key.iterator();
		ArrayList<Integer> kpNo1 = new ArrayList<Integer>();
		ArrayList<Double> ksales1 = new ArrayList<Double>();
		while (itKey.hasNext()) {
			int value = itKey.next();
			double result = copy1.get(value);

			kpNo1.add(value);
			ksales1.add(result);

		}
		ArrayList<Integer> pNo1 = new ArrayList<>();
		ArrayList<Double> sales1 = new ArrayList<>();

		for (int i = 0; i < pArr.size(); i++) {
			pNo1.add(pArr.get(i).getpNo());
			sales1.add(0.0);
			for (int j = 0; j < kpNo1.size(); j++) {
				if (pArr.get(i).getpNo() == kpNo1.get(j)) {
					sales1.set(i, ksales1.get(j));
					break;
				}
			}
		}

		int iTemp = 0;
		double dTemp = 0.0;
		for (int i = 0; i < sales1.size(); i++) {
			for (int k = 0; k < pArr.size(); k++) {
				if (pNo1.get(i) == pArr.get(k).getpNo()) {
					for (int j = 0; j < i; j++) {
						if (pArr.get(i).getCount() < pArr.get(j).getCount()) {
							iTemp = (int) kpNo1.get(i);
							pNo1.set(i, pNo1.get(j));
							pNo1.set(j, iTemp);

							dTemp = sales1.get(i);
							sales1.set(i, sales1.get(j));
							sales1.set(j, dTemp);
						}
					}

				}
			}

		}

		HashMap<Integer, Double> copy2 = mc.analysis();
		Set<Integer> key2 = copy2.keySet();
		Iterator<Integer> itKey2 = key2.iterator();
		ArrayList<Integer> kpNo2 = new ArrayList<Integer>();
		ArrayList<Double> ksales2 = new ArrayList<Double>();

		while (itKey2.hasNext()) {
			int value = itKey2.next();
			double result = copy2.get(value);

			kpNo2.add(value);
			ksales2.add(result);
		}
		iTemp = 0;
		dTemp = 0.0;

		ArrayList<Integer> pNo2 = new ArrayList<Integer>();
		ArrayList<Double> sales2 = new ArrayList<Double>();
		for (int i = 0; i < pArr.size(); i++) {
			pNo2.add(pArr.get(i).getpNo());
			sales2.add(0.0);
			for (int j = 0; j < kpNo2.size(); j++) {
				if (pArr.get(i).getpNo() == kpNo2.get(j)) {
					sales2.set(i, ksales2.get(j));
					break;
				}
			}
		}

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
		// table.setModel(new DefaultTableModel(o,
		// new String[] { "\uC0C1\uD488\uBC88\uD638", "\uC870\uD68C\uC218",
		// "\uD310\uB9E4\uC728" }));

		comboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox) e.getSource();
				String index = (String.valueOf(cb.getSelectedItem()));
				if (index.equals(kind[0])) {
					table.setModel(new DefaultTableModel(o,
							new String[] { "\uC0C1\uD488\uBC88\uD638", "\uC870\uD68C\uC218", "\uD310\uB9E4\uC728" }) {
						@Override
						public boolean isCellEditable(int row, int column) {
							return false;
						}
					});
				} else {
					Object[][] o2 = new Object[pNo2.size()][3];
					for (int i = 0; i < o2.length; i++) {
						for (int j = 0; j < o2[i].length; j++) {
							if (j == 0) {
								o2[i][j] = pNo2.get(i);
							} else if (j == 1) {
								for (int k = 0; k < pArr.size(); k++) {
									if (pNo1.get(i) == pArr.get(k).getpNo()) {
										o2[i][j] = pArr.get(k).getCount();
										break;
									}
								}
							} else {
								o2[i][j] = sales2.get(i);
							}

						}
					}
					table.setModel(new DefaultTableModel(o2,
							new String[] { "\uC0C1\uD488\uBC88\uD638", "\uC870\uD68C\uC218", "\uD310\uB9E4\uC728" }) {
						@Override
						public boolean isCellEditable(int row, int column) {
							return false;
						}
					});
				}
				

			}
		});

		
		table.setModel(new DefaultTableModel(o,
				new String[] { "\uC0C1\uD488\uBC88\uD638", "\uC870\uD68C\uC218", "\uD310\uB9E4\uC728" }) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		
		for (int j = 0; j < table.getRowCount(); j++) {
			table.isCellEditable(j, 0);
		}
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setRowSelectionAllowed(true);
		
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
		lastPage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MainView.setMainPage(manageMain());
			}
		});

		update.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Object a;

				int b = -1;
				int x = table.getSelectedRow();
				a = table.getValueAt(x, 0);
				String value = String.valueOf(a);
				for (int i = 0; i < pArr.size(); i++) {
					if (pArr.get(i).getpNo() == Integer.parseInt(value)) {
						b = i;
						break;
					}
				}

				if (b == -1) {
					JOptionPane.showMessageDialog(null, "값을 선택해주세요", "Error", JOptionPane.WARNING_MESSAGE);
				} else {
					Product p = new Product();

					p = pArr.get(b);

					MainView.setMainPage(updateProduct(p, b));

				}
			}
		});
		delete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Object a;
				int temp = -1;
				try {
					a = table.getValueAt(table.getSelectedRow(), 0);
					for (int i = 0; i < pArr.size(); i++) {
						if (pArr.get(i).getpNo() == Integer.parseInt(String.valueOf(a))) {
							temp = i;
							break;
						}
					}
				} catch (ArrayIndexOutOfBoundsException e2) {
					temp = -1;
				}

				if (temp < 0) {
					JOptionPane.showMessageDialog(null, "선택한 값이 없습니다", "warning", JOptionPane.ERROR_MESSAGE);
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

		return analyzeSale;

	}

	// 한글바꾸는거 남음
	public JPanel saleState() {

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		ArrayList<Integer> result = new ArrayList<Integer>();
		result = mc.salesState(date, 2);
		for (int i = 0; i < result.size(); i++) {
			// i+1한 곳에 result.get(i)로 바꿔줘야함
			dataset.addValue(result.get(i), (date / 100 - i) + "", "Value");
		}
		chart = ChartFactory.createBarChart("판매현황", "Category", "Value", dataset, PlotOrientation.VERTICAL, true, true,
				true);

		JPanel saleState = new JPanel();
		saleState.setBounds(32, 50, 779, 649);
		saleState.setLayout(null);

		JLabel label = new JLabel("\uD310\uB9E4\uD604\uD669");
		label.setBounds(324, 29, 78, 21);
		saleState.add(label);

		JPanel graph = new JPanel();
		graph.setBounds(0, 106, 779, 461);
		saleState.add(graph);

		ChartPanel cp = new ChartPanel(chart);
		graph.add(cp);
		JLabel label_1 = new JLabel("\uBD84\uC11D\uAE30\uAC04(\uC6D4)");
		label_1.setBounds(57, 79, 114, 21);
		saleState.add(label_1);
		String[] term = { "2개월", "3 개월", "4 개월", "5 개월", "6개월" };
		JComboBox<String> comboBox = new JComboBox<String>(term);
		comboBox.setBounds(188, 76, 93, 27);
		saleState.add(comboBox);

		comboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox) e.getSource();
				String index = (String.valueOf(cb.getSelectedItem()));
				int temp = 0;
				if (index.equals(term[0])) {
					temp = 2;
				} else if (index.equals(term[1])) {
					temp = 3;
				} else if (index.equals(term[2])) {
					temp = 4;
				} else if (index.equals(term[3])) {
					temp = 5;
				} else if (index.equals(term[4])) {
					temp = 6;
				}

				dataset.clear();
				ArrayList<Integer> result = mc.salesState(date, temp);
				for (int i = 0; i < result.size(); i++) {
					// i+1한 곳에 result.get(i)로 바꿔줘야함
					dataset.addValue(result.get(i), (date / 100 - i) + "", "Value");
				}
				chart = ChartFactory.createBarChart("판매현황", "Category", "Value", dataset, PlotOrientation.VERTICAL,
						true, true, true);
			}
		});

		JButton lastPage = new JButton("\uC774\uC804\uC73C\uB85C");
		lastPage.setBounds(305, 582, 125, 29);
		saleState.add(lastPage);

		graph.setVisible(true);
		saleState.setVisible(true);

		lastPage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MainView.setMainPage(manageMain());
			}
		});

		return saleState;
	}

	public JPanel insertProduct() {

		JPanel insertProduct = new JPanel();
		insertProduct.setBounds(101, 41, 465, 558);
		insertProduct.setLayout(null);

		JLabel label = new JLabel("\uC0C1\uD488 \uB4F1\uB85D");
		label.setBounds(193, 5, 78, 21);
		insertProduct.add(label);

		JLabel lblNewLabel = new JLabel("\uC0C1\uD488\uBA85");
		lblNewLabel.setBounds(33, 60, 78, 21);
		insertProduct.add(lblNewLabel);

		JLabel label_1 = new JLabel("\uCE74\uD14C\uACE0\uB9AC");
		label_1.setBounds(33, 97, 78, 21);
		insertProduct.add(label_1);

		// JComboBox category = new JComboBox();
		JComboBox<Category> category = new JComboBox<Category>(cArr.toArray(new Category[cArr.size()]));
		category.setBounds(140, 94, 115, 27);
		insertProduct.add(category);

		JTextField productName = new JTextField();
		productName.setBounds(140, 57, 156, 27);
		insertProduct.add(productName);
		productName.setColumns(10);

		JLabel label_2 = new JLabel("\uC0AC\uC774\uC988");
		label_2.setBounds(33, 133, 78, 21);
		insertProduct.add(label_2);

		JLabel label_3 = new JLabel("\uC7AC\uACE0");
		label_3.setBounds(33, 169, 78, 21);
		insertProduct.add(label_3);

		JLabel label_4 = new JLabel("\uAC00\uACA9");
		label_4.setBounds(33, 205, 78, 21);
		insertProduct.add(label_4);

		JLabel lblNewLabel_1 = new JLabel("\uC0C1\uC138\uC124\uBA85");
		lblNewLabel_1.setBounds(33, 295, 78, 21);
		insertProduct.add(lblNewLabel_1);

		JTextArea explain = new JTextArea();
		explain.setAlignmentY(Component.TOP_ALIGNMENT);
		explain.setAlignmentX(Component.LEFT_ALIGNMENT);
		explain.setBounds(33, 334, 350, 144);
		insertProduct.add(explain);
		// JComboBox<String> combox = new JComboBox<String>(array .toArray(new
		// String[array .size()]));

		// JComboBox size = new JComboBox<String>();
		String[] sArr = { "free" };
		JComboBox<String> size = new JComboBox<String>(sArr);

		size.setBounds(140, 130, 115, 27);
		insertProduct.add(size);

		JTextField stock = new JTextField();
		stock.setBounds(140, 166, 156, 27);
		insertProduct.add(stock);
		stock.setColumns(10);

		JTextField price = new JTextField();
		price.setColumns(10);
		price.setBounds(140, 202, 156, 27);
		insertProduct.add(price);

		JButton button = new JButton("\uB4F1\uB85D");
		button.setBounds(256, 514, 150, 29);
		insertProduct.add(button);

		JButton lastPage = new JButton("\uC774\uC804\uD398\uC774\uC9C0\uB85C");
		lastPage.setBounds(47, 514, 150, 29);
		insertProduct.add(lastPage);

		JLabel fileChoose = new JLabel("\uD30C\uC77C\uC120\uD0DD");
		fileChoose.setBounds(33, 241, 78, 21);
		insertProduct.add(fileChoose);

		JLabel fileName = new JLabel("\uD30C\uC77C\uBA85");
		fileName.setBounds(33, 272, 403, 21);
		insertProduct.add(fileName);

		JButton fileSave = new JButton();
		// fileSave.setIcon(new
		// ImageIcon("C:\\Users\\jeniu\\Desktop\\mini_project\\fileIcon.png"));
		fileSave.setBounds(140, 237, 30, 30);
		insertProduct.add(fileSave);

		JFileChooser jfc = new JFileChooser();

		fileSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (jfc.showSaveDialog(insertProduct) == JFileChooser.APPROVE_OPTION) {
					// showSaveDialog 저장 창을 열고 확인 버튼을 눌렀는지 확인
					fileName.setText(jfc.getSelectedFile().toString());
				}

			}
		});
		jfc.setFileFilter(new FileNameExtensionFilter("png file", "png"));
		jfc.setFileFilter(new FileNameExtensionFilter("JPEG file", "jpg", "jpeg"));

		jfc.setMultiSelectionEnabled(false); // 다중선택불가

		lastPage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "제품이 추가되지 않았습니다 \n 이 페이지에서  나가시겠습니까?", "Confirm",
						JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.CLOSED_OPTION) {

				} else if (result == JOptionPane.YES_OPTION) {
					MainView.setMainPage(manageMain());
				}
			}
		});
		price.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});

		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (productName.getText().equals("") || price.getText().equals("") || stock.getText().equals("")
						|| fileName.getText().equals("") || explain.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "값을 모두 입력해주세요", "warning", JOptionPane.WARNING_MESSAGE);
				} else {
					try {
						int pNo1 = pArr.get(pArr.size() - 1).getpNo() - 1;
						String[] cat = (String.valueOf(category.getSelectedItem())).split("\\s");
						int category1 = Integer.parseInt(cat[0]);
						String productName1 = productName.getText();
						int price1 = Integer.parseInt(price.getText());
						String size1 = String.valueOf(size.getSelectedItem());
						int stock1 = Integer.parseInt(stock.getText());
						String filePath = fileName.getText();
						String explain1 = explain.getText();

						int result = JOptionPane.showConfirmDialog(null, "입력하신 제품을 등록하시겠습니까?", "Confirm",
								JOptionPane.YES_NO_OPTION);
						if (result == JOptionPane.CLOSED_OPTION) {

						} else if (result == JOptionPane.YES_OPTION) {
							pArr.add(new Product(pNo1, category1, productName1, price1, size1, explain1, filePath,
									stock1, 0));
							pDao.fileSave(pArr);
							MainView.setMainPage(manageMain());
						}

					} catch (NumberFormatException e2) {
						JOptionPane.showMessageDialog(null, "재고와 가격은 숫자만 넣어 주세요", "warning", JOptionPane.ERROR_MESSAGE);
					}

				}
			}
		});

		insertProduct.setVisible(true);

		return insertProduct;
	}

	public JPanel updateProduct(Product p, int index) {

		JPanel updateProduct = new JPanel();
		updateProduct.setBounds(64, 75, 506, 564);
		updateProduct.setLayout(null);

		JLabel lblNewLabel = new JLabel("\uC0C1\uD488\uC218\uC815");
		lblNewLabel.setBounds(177, 28, 78, 21);
		updateProduct.add(lblNewLabel);

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
		label_4.setBounds(35, 315, 78, 21);
		updateProduct.add(label_4);

		JTextField productName = new JTextField(p.getProductName());
		productName.setBounds(130, 80, 156, 27);
		updateProduct.add(productName);
		productName.setColumns(10);

		JTextField stock = new JTextField(String.valueOf(p.getStock()));
		stock.setColumns(10);
		stock.setBounds(130, 188, 156, 27);
		updateProduct.add(stock);

		JTextField price = new JTextField(String.valueOf(p.getPrice()));
		price.setColumns(10);
		price.setBounds(130, 226, 156, 27);
		updateProduct.add(price);

		JComboBox<Category> size = new JComboBox<Category>(cArr.toArray(new Category[cArr.size()]));// 이부분주석하면 빌더동작
		size.setBounds(130, 116, 156, 27);
		updateProduct.add(size);
		int[] totalCat = { 1, 11, 12, 13, 14, 21, 22, 23, 24, 25, 31, 32, 33, 41, 42, 43, 44, 45, 46, 51, 52, 53 };
		int a = 0;
		for (int i = 0; i < totalCat.length; i++) {
			if (totalCat[i] == p.getCategory()) {
				a = i;
				break;
			}
		}

		size.setSelectedIndex(a);

		String[] s = { "free" };
		JComboBox<String> comboBox_1 = new JComboBox<String>(s);
		comboBox_1.setBounds(130, 152, 156, 27);
		updateProduct.add(comboBox_1);

		JTextArea explain = new JTextArea(p.getExplain());
		explain.setAlignmentY(Component.TOP_ALIGNMENT);
		explain.setAlignmentX(Component.LEFT_ALIGNMENT);
		explain.setBounds(45, 351, 383, 149);
		updateProduct.add(explain);
		explain.setColumns(10);

		JLabel label_5 = new JLabel("\uC0AC\uC9C4 \uCD94\uAC00");
		label_5.setBounds(35, 265, 78, 21);
		updateProduct.add(label_5);

		JButton file = new JButton("");
		file.setBounds(130, 256, 30, 30);
		updateProduct.add(file);

		JLabel fileName = new JLabel("fileName");
		fileName.setBounds(35, 290, 329, 21);
		updateProduct.add(fileName);
		fileName.setText(p.getImgFilePath());

		JButton update = new JButton("\uC218\uC815");
		update.setBounds(303, 515, 125, 29);
		updateProduct.add(update);

		JButton lastPage = new JButton("\uC774\uC804\uC73C\uB85C");
		lastPage.setBounds(55, 515, 125, 29);
		updateProduct.add(lastPage);

		updateProduct.setVisible(true);

		JFileChooser jfc = new JFileChooser();

		file.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (jfc.showSaveDialog(updateProduct) == JFileChooser.APPROVE_OPTION) {
					// showSaveDialog 저장 창을 열고 확인 버튼을 눌렀는지 확인
					fileName.setText(jfc.getSelectedFile().toString());
				}

			}
		});
		jfc.setFileFilter(new FileNameExtensionFilter("png file", "png"));
		jfc.setFileFilter(new FileNameExtensionFilter("JPEG file", "jpg", "jpeg"));

		jfc.setMultiSelectionEnabled(false); // 다중선택불가

		lastPage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "제품이 수정되지 않았습니다 \n 이 페이지에서  나가시겠습니까?", "Confirm",
						JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.CLOSED_OPTION) {

				} else if (result == JOptionPane.YES_OPTION) {
					MainView.setMainPage(analyzeSale());
				}
			}
		});

		update.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (productName.getText().equals("") || price.getText().equals("") || stock.getText().equals("")
						|| fileName.getText().equals("") || explain.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "값을 모두 입력해주세요", "warning", JOptionPane.WARNING_MESSAGE);
				} else {
					try {
						int pNo1 = p.getpNo();
						String[] cat = (String.valueOf(size.getSelectedItem())).split("\\s");
						int category1 = Integer.parseInt(cat[0]);
						String productName1 = productName.getText();
						int price1 = Integer.parseInt(price.getText());
						String size1 = String.valueOf(size.getSelectedItem());
						int stock1 = Integer.parseInt(stock.getText());
						String filePath = fileName.getText();
						String explain1 = explain.getText();

						int result = JOptionPane.showConfirmDialog(null, "입력하신 제품을 등록하시겠습니까?", "Confirm",
								JOptionPane.YES_NO_OPTION);
						if (result == JOptionPane.CLOSED_OPTION) {

						} else if (result == JOptionPane.YES_OPTION) {
							pArr.get(index).setpNo(pNo1);
							pArr.get(index).setCategory(category1);
							pArr.get(index).setProductName(productName1);
							pArr.get(index).setPrice(price1);
							pArr.get(index).setSize("free");
							pArr.get(index).setExplain(explain1);
							pArr.get(index).setImgFilePath(filePath);
							pArr.get(index).setStock(stock1);

							pDao.fileSave(pArr);
							MainView.setMainPage(manageMain());
						}

					} catch (NumberFormatException e2) {
						JOptionPane.showMessageDialog(null, "재고와 가격은 숫자만 넣어 주세요", "warning", JOptionPane.ERROR_MESSAGE);
					}

				}
			}
		});

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
		// table_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// table_1.setColumnSelectionAllowed(true);
		table_1.setRowSelectionAllowed(true);
		// table_1.setCellSelectionEnabled(false);
		table_1.setBorder(UIManager.getBorder("ComboBox.border"));

		
		JLabel label = new JLabel("\uD68C\uC6D0\uC815\uBCF4");
		label.setBounds(107, 15, 96, 34);
		searchMember.add(label);

		JButton delete = new JButton("\uD0C8\uD1F4");
		delete.setBounds(430, 517, 125, 29);
		searchMember.add(delete);

		JButton lastPage = new JButton("\uC774\uC804 \uD398\uC774\uC9C0");
		lastPage.setBounds(101, 517, 137, 29);
		searchMember.add(lastPage);

		/*
		 * JButton update = new JButton("\uC218\uC815"); update.setBounds(268, 517, 125,
		 * 29); searchMember.add(update);
		 */
		searchMember.setVisible(true);

		lastPage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MainView.setMainPage(manageMain());
			}
		});

		/*
		 * update.addMouseListener(new MouseAdapter() {
		 * 
		 * @Override public void mouseClicked(MouseEvent e) { Object a; for (int i = 0;
		 * i < table_1.getRowCount(); i++) { for (int k = 0; k <
		 * table_1.getColumnCount(); k++) { a = table_1.getValueAt(i, k);
		 * 
		 * if (k == 0) {
		 * 
		 * int cat = Integer.parseInt(String.valueOf(a)); mArr.get(i).setmNo(cat); }
		 * else if (k == 1) { mArr.get(i).setId((String) a); } else if (k == 2) {
		 * mArr.get(i).setName((String) a); } else if (k == 3) {
		 * mArr.get(i).setPhone((String) a); } else if (k == 4) {
		 * mArr.get(i).setEmail((String) a); } } } mDao.fileSave(mArr);
		 * 
		 * MainView.setMainPage(searchMember());
		 * 
		 * } });
		 */

		delete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Object a;
				int temp = -1;
				System.out.println(table_1.getSelectedColumn() + ":" + table_1.getSelectedRow());
				try {
					a = table_1.getValueAt(table_1.getSelectedRow(), 0);
					for (int i = 0; i < mArr.size(); i++) {
						if (mArr.get(i).getmNo() == (int) a) {
							temp = i;
							break;
						}
					}
				} catch (ArrayIndexOutOfBoundsException e2) {
					temp = -1;
				}

				if (temp < 0) {
					JOptionPane.showMessageDialog(null, "선택한 값이 없습니다", "warning", JOptionPane.ERROR_MESSAGE);
				} else {
					int result = JOptionPane.showConfirmDialog(null, "정말 삭제하시겠습니까?", "Confirm",
							JOptionPane.YES_NO_OPTION);
					if (result == JOptionPane.CLOSED_OPTION) {

					} else if (result == JOptionPane.YES_OPTION) {
						mArr.remove(temp);
						mDao.fileSave(mArr);
						MainView.setMainPage(searchMember());
					} else {

					}
				}
			}
		});

		return searchMember;

	}

}
