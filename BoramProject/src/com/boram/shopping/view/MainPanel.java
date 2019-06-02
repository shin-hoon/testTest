package com.boram.shopping.view;

import java.awt.Color;
import java.awt.Font;
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.boram.manager.vo.Category;
import com.boram.manager.vo.CategoryDao;
import com.boram.manager.vo.Product;
import com.boram.manager.vo.ProductDao;
import com.boram.shopping.controller.MainMouseEvent;
import com.boram.shopping.controller.MainThreadEvent;

public class MainPanel{
	private JPanel mainPage;
	private int imgWidth = 15;
	private int imgHeight = 100;
	private int priceWidth_1 = 15;
	private int priceHeight_1 = 536;
	private int priceWidth_2 = 15;
	private int priceHeight_2 = 560;
	private JLabel mainImage;
	private JLabel productName;
	private JLabel price;

	public JPanel getMainPanel() {
		return mainPage;
	}

	/**
	 *  메인 페이지
	 */
	public MainPanel(int num) {
		if(num != 1)
			new MainThreadEvent(-376,0,"서브메뉴닫기").start();
		mainPage = new JPanel();
		mainPage.setBackground(new Color(255, 255, 255));
		mainPage.setBounds(0, 259, 718, 890);
		mainPage.setLayout(null);
		mainPage.setVisible(true);

		List<Product> ProductList = new ProductDao().fileRead();
		mainImage = new JLabel();
		productName = new JLabel();
		price = new JLabel();
		
		List<Category> categoryChk = new CategoryDao().fileRead();
		
		for(int i = 0; i < categoryChk.size(); i++) {
			if(num == categoryChk.get(i).getNum()) {
				int pageWidth = mainPage.getWidth()/2;
				String kind = categoryChk.get(i).getKind();
				int labelWidth = kind.length() < 4 ? pageWidth-50 : pageWidth-80;
				JLabel category = new JLabel(kind);
				category.setLayout(null);
				category.setFont(new Font("휴먼엑스포", Font.PLAIN, 30));
				category.setBounds(labelWidth, 20, 258, 58);
				mainPage.add(category);
				break;
			}
		}	
		
		DecimalFormat comma = new DecimalFormat("###,###");
		int cnt = 1;
		for(int i = ProductList.size()-1 ; i >= 0; i--) {
			if( num == ProductList.get(i).getCategory() ) {
				if( cnt % 2 == 0 ) {
					imgWidth += 344;
					priceWidth_1 += 344;
					priceWidth_2 += 344;
				}
				
				mainImage = new JLabel(new ImageIcon(MainView.PATH + ProductList.get(i).getImgFilePath()));
				mainImage.setName(String.valueOf(ProductList.get(i).getpNo()));
				mainImage.setBounds(imgWidth, imgHeight, 325, 432);
				mainPage.add(mainImage);
				
				mainImage.addMouseListener(new MainMouseEvent(ProductList.get(i),"상세페이지"));
				
				productName = new JLabel(ProductList.get(i).getProductName());
				productName.setFont(new Font("휴먼엑스포", Font.PLAIN, 15));
				productName.setBounds(priceWidth_1, priceHeight_1, 300, 18);
				mainPage.add(productName);
				
				price = new JLabel(comma.format(ProductList.get(i).getPrice())+"원" );
				price.setForeground(new Color(255, 153, 0));
				price.setFont(new Font("휴먼엑스포", Font.PLAIN, 15));
				price.setBounds(priceWidth_2, priceHeight_2, 127, 18);
				mainPage.add(price);
				
				if( cnt % 2 == 0 ) {
					imgHeight += 500;
					priceHeight_1 += 500;
					priceHeight_2 += 500;
					imgWidth = 15;
					priceWidth_1 = 15;
					priceWidth_2 = 15;
				}
				cnt++;
			} // end if
		} // end for
		mainPage.setSize(718, ((cnt-1)%2 != 0 ? priceHeight_2+100 : priceHeight_2-400));
	} // end method
} // end class

/*	
	Image image = null;
try {
	System.out.println(MainView.PATH + list.get(i).getImgFilePath());
	URL url = new URL(MainView.PATH + list.get(i).getImgFilePath());
	File file = new File(MainView.PATH + list.get(i).getImgFilePath());
	image = ImageIO.read(file).getScaledInstance(325, 430, Image.SCALE_SMOOTH);
} catch (IOException e) {
	System.out.println("이미지 불러오기 에러 : " + e.getMessage());
}
*/