package com.boram.shopping.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.boram.shopping.controller.ShoppingParsing;
import com.boram.shopping.vo.ShoopingVO;

public class MainPanel{
	private JPanel mainPage;

	public JPanel getMainPanel() {
		return mainPage;
	}

	/**
	 *  메인 페이지
	 */
	public MainPanel() {
		mainPage = new JPanel();
		mainPage.setBackground(new Color(255, 255, 255));
		mainPage.setBounds(0, 259, 700, 490);
		mainPage.setLayout(null);
		mainPage.setVisible(true);

		//ShoppingParsing
		List<ShoopingVO> list = new ShoppingParsing().testParsing();
		int imgWidth = 15;
		int imgHeight = 0;
		int priceWidth_1 = 15;
		int priceHeight_1 = 436;
		int priceWidth_2 = 15;
		int priceHeight_2 = 460;

		int cnt = 1;

		for(int i = 0 ; i < list.size(); i++) {
			Image image = null;

			try {
				System.out.println(list.get(i).getImage());
				URL url = new URL(list.get(i).getImage());
				image = ImageIO.read(url).getScaledInstance(325, 430, Image.SCALE_SMOOTH);
			} catch (IOException e) {
				System.out.println("이미지 불러오기 에러 : " + e.getMessage());
			}

			if( cnt % 2 == 0 ) {
				imgWidth += 344;
				priceWidth_1 += 344;
				priceWidth_2 += 344;
			}

			JLabel mainImage1 = new JLabel(new ImageIcon(image));
			mainImage1.setBounds(imgWidth, imgHeight, 325, 432);
			mainPage.add(mainImage1);

			JLabel label_1 = new JLabel(list.get(i).getTitle());
			label_1.setFont(new Font("휴먼엑스포", Font.PLAIN, 15));
			label_1.setBounds(priceWidth_1, priceHeight_1, 250, 18);
			mainPage.add(label_1);

			JLabel label_2 = new JLabel(list.get(i).getPrice());
			label_2.setForeground(new Color(255, 153, 0));
			label_2.setFont(new Font("휴먼엑스포", Font.PLAIN, 15));
			label_2.setBounds(priceWidth_2, priceHeight_2, 87, 18);
			mainPage.add(label_2);

			if( cnt % 2 == 0 ) {
				imgHeight += 500;
				priceHeight_1 += 500;
				priceHeight_2 += 500;
				imgWidth = 15;
				priceWidth_1 = 15;
				priceWidth_2 = 15;
			}
			cnt++;
		}
	}
}
