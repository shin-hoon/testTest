package com.boram.manager.vo;

public class Category {
	/*
	 * 11 ÄÚÆ®
	 * 12 ÀÚÄÏ
	 * 13 ºí·¹ÀÌÀú
	 * 14 °¡µğ°Ç
	 * 21±äÆÈ
	 * 22 ¹İÆÈ/Á¶³¢
	 * 23 ¸ÇÅõ¸Ç/ ÈÄµåÆ¼
	 * 24´ÏÆ®
	 * 25¿À¹öÇÍ
	 * 31 ±âº»¼ÅÃ÷
	 * 32 ÆĞÅÏ¼ÅÃ÷
	 * 33¹İÆÈ¼ÅÃ÷
	 * 41 ½½·¢½º
	 * 42 ¸éÆÒÃ÷
	 * 43Ã»¹ÙÁö
	 * 44 ¹İ¹ÙÁö
	 * 45Æ®·¡ÀÌ´×
	 * 46 UP µ¥´ÔÆÒÃ÷
	 * 51 ½º´ÏÄ¿Áî
	 * 52 ±¸µÎ/¿öÄ¿
	 * 53 ½½¸®ÆÛ/»÷µé
	 */
	
	
	private int num;
	private String kind;
	
	
	
	public Category(int num, String kind) {
		super();
		this.num = num;
		this.kind = kind;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	@Override
	public String toString() {
		return "Category [num=" + num + ", kind=" + kind + "]";
	}
	
	
}
