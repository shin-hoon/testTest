package com.boram.manager.vo;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.boram.shopping.view.MainView;

public class ProductDao {

	private List<Product> productList = new ArrayList<Product>();

	public void fileSave(List<Product> list) {

		try {
			File fileDir = new File(MainView.PATH + "file\\product\\");
			if(!fileDir.exists()) fileDir.mkdirs();

			File imgFile = new File(fileDir,"productList.txt");

			ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(imgFile,true)));
			
			for (int i = 0; i < list.size(); i++) {
				oos.writeObject(list.get(i));
			}
			oos.close();
		} catch (Exception e) {
			System.out.println("ProductDao클레스에서 fileSave() 에러 : " + e.getMessage());
		}
	}

	public List<Product> fileRead() {

		try {
			File imgFile = new File(MainView.PATH + "file\\product\\","productList.txt");
			ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(imgFile)));
			
			try {
				while(true) {
					productList.add((Product)ois.readObject());
				}				
			} catch(EOFException eof) {
				ois.close();
			}
				
		} catch (Exception e) {
			System.out.println("ProductDao클레스에서 fileRead() 에러 : " + e.getMessage());
		}
		
		return productList;
	}

}
