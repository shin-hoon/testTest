package com.boram.shopping.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.boram.manager.vo.Product;
import com.boram.manager.vo.ProductDao;
import com.boram.shopping.controller.MainMouseEvent;

public class ContentPanel{
	private JPanel content;
	
	public JPanel getContent() {
		return this.content;
	}
	/**
	 *  ªÛ«∞ ªÛºº∆‰¿Ã¡ˆ
	 */
	public ContentPanel(Product product) {
		content = new JPanel();
		content.setBackground(new Color(255, 255, 255));
		content.setBounds(0, 259, 718, 890);
		content.setLayout(null);
		content.setVisible(true);
		
		Image image = null;
		try {
			File file = new File(MainView.PATH + product.getImgFilePath());
			image = ImageIO.read(file).getScaledInstance(700 , 630, Image.SCALE_SMOOTH);
		} catch (IOException e) {
			System.out.println("¿ÃπÃ¡ˆ ∫“∑Øø¿±‚ ø°∑Ø : " + e.getMessage());
		}
		
		JLabel contentImage = new JLabel(new ImageIcon(image));
		contentImage.setBounds(0, 0, 700, 630);
		content.add(contentImage);
		
		ProductDao pd = new ProductDao();
		List<Product> productList = pd.fileRead();
		
		for (int i = 0; i < productList.size(); i++) {
			if(productList.get(i).getpNo() == product.getpNo()) {
				productList.get(i).setCount(productList.get(i).getCount()+1);
				break;
			}
		}
		
		pd.fileSave(productList);
		
		
		DecimalFormat df = new DecimalFormat("###,###");
		JLabel productName = new JLabel(product.getProductName());
		productName.setFont(new Font("±º∏≤", Font.BOLD, 25));
		productName.setBounds(14, 637, 631, 44);
		content.add(productName);
		
		JLabel priceTitle = new JLabel("∆«∏≈∞°");
		priceTitle.setForeground(Color.GRAY);
		priceTitle.setFont(new Font("±º∏≤", Font.BOLD, 20));
		priceTitle.setBounds(14, 696, 133, 44);
		content.add(priceTitle);
		
		JLabel price = new JLabel(df.format(product.getPrice())+"ø¯");
		price.setForeground(Color.BLACK);
		price.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 20));
		price.setBounds(141, 696, 133, 44);
		content.add(price);

		JLabel savings = new JLabel("¿˚∏≥±›");
		savings.setForeground(Color.GRAY);
		savings.setFont(new Font("±º∏≤", Font.BOLD, 20));
		savings.setBounds(14, 738, 133, 44);
		content.add(savings);
		
		JLabel savingsPay = new JLabel("420ø¯");
		savingsPay.setForeground(Color.GRAY);
		savingsPay.setFont(new Font("±º∏≤", Font.BOLD, 20));
		savingsPay.setBounds(141, 738, 133, 44);
		content.add(savingsPay);
		
		JLabel shippingFee = new JLabel("πËº€∫Ò");
		shippingFee.setForeground(Color.GRAY);
		shippingFee.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.PLAIN, 20));
		shippingFee.setBounds(14, 780, 133, 44);
		content.add(shippingFee);
		
		JLabel shippingFeePay = new JLabel("2,000ø¯ (50,000 ¿ÃªÛ ±∏∏≈ Ω√ π´∑·)");
		shippingFeePay.setForeground(Color.GRAY);
		shippingFeePay.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.PLAIN, 20));
		shippingFeePay.setBounds(141, 780, 356, 44);
		content.add(shippingFeePay);
		
		JLabel size = new JLabel("ªÁ¿Ã¡Ó");
		size.setForeground(Color.GRAY);
		size.setFont(new Font("±º∏≤", Font.BOLD, 20));
		size.setBounds(14, 822, 133, 44);
		content.add(size);
		
		JLabel free = new JLabel(product.getSize());
		free.setForeground(Color.BLACK);
		free.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 20));
		free.setBounds(141, 822, 133, 44);
		content.add(free);
		
		JButton cart = new JButton("¿ÂπŸ±∏¥œ");
		cart.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 26));
		cart.setForeground(Color.WHITE);
		cart.setBackground(Color.BLACK);
		cart.setEnabled(false);
		cart.setBounds(0, 887, 700, 61);
		content.add(cart);
		
		JButton back = new JButton("µ⁄∑Œ∞°±‚");
		back.setForeground(Color.BLACK);
		back.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 26));
		back.setEnabled(false);
		back.setBackground(Color.WHITE);
		back.setBounds(0, 960, 700, 61);
		content.add(back);
		
		JTextArea explain = new JTextArea();
		explain.setFont(new Font("±º∏≤", Font.BOLD, 20));
		explain.setEditable(false);
		explain.setLineWrap(true);
		explain.setText(product.getExplain());
		explain.setBounds(67, 1091, 565, 2099);
		content.add(explain);
		
		cart.addMouseListener(new MainMouseEvent(product,"¿ÂπŸ±∏¥œ"));
		back.addMouseListener(new MainMouseEvent(product.getCategory(),"µ⁄∑Œ∞°±‚"));
		content.setSize(718, 2290);
	}

}
