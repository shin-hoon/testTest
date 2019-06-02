package com.boram.manager.vo;

import java.io.Serializable;

public class Product implements Serializable{
	private static final long serialVersionUID = 9085575968485119138L;
	
	private int pNo;				// 게시물번호
	private int category;			// 카테고리
	private String productName;		// 상품이름
	private int price;				// 상품가격
	private String size;			// 상품사이즈
	private String explain;			// 상품설명
	private String imgFilePath;		// 이미지경로
	private int stock;				// 상품재고
	private int count; 				// 조회수
	
	
	
	public Product() {
	}
	
	public Product(int pNo, int category, String productName, int price, String size, String explain,
			String imgFilePath, int stock, int count) {
		super();
		this.pNo = pNo;
		this.category = category;
		this.productName = productName;
		this.price = price;
		this.size = size;
		this.explain = explain;
		this.imgFilePath = imgFilePath;
		this.stock = stock;
		this.count = count;
	}

	public String getImgFilePath() {
		return imgFilePath;
	}
	public void setImgFilePath(String imgFilePath) {
		this.imgFilePath = imgFilePath;
	}
	public Product(int pNo, int category, String productName, int price, String size, int stock, int count) {
		super();
		this.pNo = pNo;
		this.category = category;
		this.productName = productName;
		this.price = price;
		this.size = size;
		this.stock = stock;
		this.count = count;
	}
	public int getpNo() {
		return pNo;
	}
	public void setpNo(int pNo) {
		this.pNo = pNo;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getExplain() {
		return explain;
	}
	public void setExplain(String explain) {
		this.explain = explain;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Product [pNo=" + pNo + ", category=" + category + ", productName=" + productName + ", price=" + price
				+ ", size=" + size + ", explain=" + explain + ", imgFilePath=" + imgFilePath + ", stock=" + stock + ", count=" + count + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + category;
		result = prime * result + count;
		result = prime * result + pNo;
		result = prime * result + price;
		result = prime * result + ((productName == null) ? 0 : productName.hashCode());
		result = prime * result + ((size == null) ? 0 : size.hashCode());
		result = prime * result + stock;
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (category != other.category)
			return false;
		if (count != other.count)
			return false;
		if (pNo != other.pNo)
			return false;
		if (price != other.price)
			return false;
		if (productName == null) {
			if (other.productName != null)
				return false;
		} else if (!productName.equals(other.productName))
			return false;
		if (size == null) {
			if (other.size != null)
				return false;
		} else if (!size.equals(other.size))
			return false;
		if (stock != other.stock)
			return false;
		return true;
	}
}