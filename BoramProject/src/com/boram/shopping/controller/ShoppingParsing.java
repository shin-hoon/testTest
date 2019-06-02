package com.boram.shopping.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.boram.manager.vo.Product;
import com.boram.manager.vo.ProductDao;
import com.boram.shopping.view.MainView;
public class ShoppingParsing {
	private ProductDao productDao = new ProductDao();
	private int cnt = 0;
	private int pNo = 1;
	
	private int[] category = {
		1,                  // Best Sellers
		11,12,13,14,        // 코트, 자켓, 블레이저, 가디건
		21,22,23,24,25,     // 긴팔, 반팔/조끼, 맨투맨/후드티, 니트, 오버핏
		31,32,33,           // 기본셔츠, 패턴셔츠, 반팔셔츠
		41,42,43,44,45,46,  // 슬랙스, 면팬츠, 청바지, 반바지, 트래이닝, UP데님팬츠
		51,52,53            // 스니커즈, 구두/워커, 슬리퍼/샌들
	};
	
	private String[] categoryEng = {
		"Best Sellers",														          
		"coat", "jacket", "blazer", "cardigan",                                       
		"LongSleeve", "ShortSleeves", "hoodT", "neat", "overFit",                     
		"nomalShirt", "patternShirt", "shortShirt",                                   
		"Slacks", "cottonPants",  "blueJeans",  "shorts", "training", "upDenimPants", 
		"Sneakers", "Walker", "slipper"
	};
	
	private String[] connect = {
			"https://m.mutnam.com/",                               // Best Sellers
			"https://m.mutnam.com/product/list.html?cate_no=50",   // 코트
			"https://m.mutnam.com/product/list.html?cate_no=82",   // 자켓
			"https://m.mutnam.com/product/list.html?cate_no=165",  // 블레이저
			"https://m.mutnam.com/product/list.html?cate_no=397",  // 가디건
			"https://m.mutnam.com/product/list.html?cate_no=140",  // 긴팔
			"https://m.mutnam.com/product/list.html?cate_no=123",  // 반팔/조끼
			"https://m.mutnam.com/product/list.html?cate_no=398",  // 맨투맨/후드티
			"https://m.mutnam.com/product/list.html?cate_no=76",   // 니트
			"https://m.mutnam.com/product/list.html?cate_no=318",  // 오버핏
			"https://m.mutnam.com/product/list.html?cate_no=105",  // 기본셔츠
			"https://m.mutnam.com/product/list.html?cate_no=92",   // 패턴셔츠
			"https://m.mutnam.com/product/list.html?cate_no=301",  // 반팔셔츠
			"https://m.mutnam.com/product/list.html?cate_no=111",  // 슬렉스
			"https://m.mutnam.com/product/list.html?cate_no=169",  // 면팬츠
			"https://m.mutnam.com/product/list.html?cate_no=75",   // 청바지
			"https://m.mutnam.com/product/list.html?cate_no=116",  // 반바지
			"https://m.mutnam.com/product/list.html?cate_no=282",  // 트레이닝
			"https://m.mutnam.com/product/list.html?cate_no=348",  // UP데님팬츠
			"https://m.mutnam.com/product/list.html?cate_no=161",  // 스니커즈
			"https://m.mutnam.com/product/list.html?cate_no=246",  // 구두/워커
			"https://m.mutnam.com/product/list.html?cate_no=211",  // 슬리퍼/샌들
	};
	
	/**
	 *  웹주소에 해당하는
	 *  HTML태그를 끌어와서 원하는 이미지 및 데이터를 가져오는 기능
	 *  초기화 하고싶을 때는 삭제 했다가 다시 파싱
	 *  단, 파싱 작업은 몇 분정도 소요시간 있음
	 *  파싱이 끝났거나 파일 삭제가 끝나면
	 *  프로젝트 한번 클릭한 후 새로고침 해야 변경완료
	 *  사용 후 주석 필수!!
	 */
	public static void main(String[] args) {
		ShoppingParsing sp = new ShoppingParsing();
		// 파싱 start
		//sp.fileSave();
		
		// 파일 지우기
	/*	
		sp.deleteFile(MainView.PATH + "file");
		System.out.println("file 폴더가 삭제 되었습니다.");
		sp.deleteFile(MainView.PATH + "image\\category");
		System.out.println("category 폴더가 삭제 되었습니다.");
	*/	
	}
	
	
	public void fileSave() {
		productDao.fileSave(parsingStart());
		System.out.println("성공");
	}
	
	
	public void deleteFile(String path) {
		File deleteFolder = new File(path);

		if(deleteFolder.exists()){
			File[] deleteFolderList = deleteFolder.listFiles();
			
			for (int i = 0; i < deleteFolderList.length; i++) {
				if(deleteFolderList[i].isFile()) {
					deleteFolderList[i].delete();
				}else {
					deleteFile(deleteFolderList[i].getPath());
				}
				deleteFolderList[i].delete(); 
			}
			deleteFolder.delete();
		}
	}
	
	
	
	public List<Product> parsingStart() {
		List<Product> dataList = new ArrayList<>();
		
		try {
			for (int m = 0; m < category.length; m++) {
				Document doc = Jsoup.connect(connect[m]).get();
				Elements img = doc.select("div.thumbnail a img");
				Elements title = doc.select("div.description strong.name a");
				Elements price = doc.select("div.description div.spec p.price");
				
				for(int i = 0; i < img.size(); i++) {
					Element imgTmp = img.get(i);
					Element titleTmp = title.get(i);
					Element priceTmp = price.get(cnt);
					Document contentDoc = Jsoup.connect("https://m.mutnam.com/"+titleTmp.attr("href")).get();
					Elements contentExplain = contentDoc.select("ul.txt_area li ul li");
					
					
					StringBuilder sb = new StringBuilder();
					for (int j = 0; j < contentExplain.size(); j++) {
						Element contentExplainTmp = contentExplain.get(j);
						sb.append(contentExplainTmp.text());
					}
					if(sb.toString().equals("")) {
						Elements contentExplain2 = contentDoc.select("div#prdDetailContent ul li");
						for(int k = 0; k < contentExplain2.size(); k++) {
							Element contentExplainTmp2 = contentExplain2.get(k);
							sb.append(contentExplainTmp2.text());
						}
						if(sb.toString().equals("")) {
							sb.append("상품 설명이 등록되지 않았습니다.");
						}
					}
					
					Product vo = new Product();
					
					vo.setpNo(pNo++);        
					vo.setCategory(category[m]); 
					vo.setProductName(titleTmp.text());
					String replace = priceTmp.text().replaceAll(",", "");
					vo.setPrice(Integer.parseInt(replace.substring(0, replace.lastIndexOf("원"))));
					vo.setSize("free");
					vo.setExplain(sb.toString());

					File fileDir = new File(MainView.PATH + "image\\category\\"+categoryEng[m]+"\\");
					if(!fileDir.exists()) fileDir.mkdirs();

					String fileName = imgTmp.attr("src").substring(imgTmp.attr("src").lastIndexOf("/")+1, imgTmp.attr("src").length());
					File imgFile = new File(fileDir,fileName);
					URL url = null;
					BufferedImage bi = null;
					
					try {
						url = new URL("https:"+imgTmp.attr("src"));
						bi = ImageIO.read(url);
						ImageIO.write(bi, "png", imgFile);
					} catch (Exception ex) {
						System.out.println("파일 저장 오류 : " + ex.getMessage());
					} // end try-catch
					vo.setImgFilePath("image\\category\\"+categoryEng[m]+"\\"+imgFile.getName());
					vo.setStock(10);
					vo.setCount(10);
					dataList.add(vo);
					cnt += 2;
					System.out.println(vo.toString());
					if(i == 21) break;
				} // end for
				cnt=0;
			} // end for
		} catch(Exception e) {
			System.out.println("파싱에러 : " + e.getMessage());
		} // end try-catch
		return dataList;
	}
} // end class