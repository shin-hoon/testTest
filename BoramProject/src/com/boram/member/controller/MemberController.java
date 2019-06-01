package com.boram.member.controller;

import java.util.ArrayList;

import javax.swing.JTextField;

import com.boram.member.vo.Member;
import com.boram.member.vo.MemberDao;
import com.boram.shopping.view.MainView;

public class MemberController {

	MemberDao md = new MemberDao();

	// MyCart ca=new MyCart();

	ArrayList<Member> memberList = md.fileRead();
	public static Member m ;
	// md.fileSave(memberList);
	
//	{// 초기임시데이터 추가 : 용준
//		// "name1",950417,"id1","pwd2","01029346330","seoul","email.com"
//		memberList.add(new Member(1, "1", 1, "1", "1", "1", "1", "1"));
//		memberList.add(new Member(2, "name1", 950411, "id1", "pwd1", "01029346331", "seoul", "1mail.com"));
//		memberList.add(new Member(3, "name2", 950412, "id2", "pwd2", "01029346332", "seou1", "2mail.com"));
//		memberList.add(new Member(4, "name3", 950413, "id3", "pwd3", "01029346333", "seou2", "3mail.com"));
//		memberList.add(new Member(5, "name4", 950414, "id4", "pwd4", "01029346334", "seou3", "4mail.com"));
//		memberList.add(new Member(6, "name5", 950415, "id5", "pwd5", "01029346335", "seou4", "5mail.com"));
//	}
	
	/*
	 * 회원가입
	 */
	public void join(String name, int age, String id, String pwd, String phone, String address, String email) {

		// 회원번호 주기
		int mNo;
		for (Member m : memberList) {
			System.out.println( m.getName() + m.getAge() + m.getId() + m.getPwd() + m.getPhone()
					+ m.getAddress() + m.getEmail()); // mNo로 1씩 증가
			System.out.println("test : "+m.toString());
		}
		if(memberList.size()==0) {//memberList 초기화 후 새로시작할때 mNo=0으로 변경해주세요.
			//0번index에 mNo가 1 부터 시작하면 outofbound오류납니다.  
			mNo=0;
		}else {
			mNo=memberList.get(memberList.size()-1).getmNo()+1; //제일마지막번호 +1
		}
		   
		

		// 회원추가
		memberList.add(new Member(mNo, name, age, id, pwd, phone, address, email));
		System.out.println(memberList.size());
		for (int i = 0; i < memberList.size(); i++) {
			System.out.println("===============");
			System.out.println("i : "+i);
			System.out.println(memberList.get(i));
			
		}

		// 저장
		md.fileSave(memberList);
	}

	
	
	/* 
	 * 로그인
	 */
	public Member logIn(String id, String pwd) {

		for (int i = 0; i < memberList.size(); i++) {
			// 저장된 아이디랑 패스워드가 입력한 아이다랑 패스워드랑 같으면

			if (memberList.get(i).getId().equals(id) && memberList.get(i).getPwd().equals(pwd)) {

				// memberList.get(i)를 리턴함과동시에 nugu메소드를통해 누가 로그인햇는지 알려줌.
				m = memberList.get(i);
				System.out.println("i : "+i);
				System.out.println(m.toString());
				System.out.println("성공");
				
				// 로그인과 동시에 저장해놓앗던 장바구니 불러오기.
				// ca.loadCart();
				return m;
			}

		}

		System.out.println("fail");
		return null;
	}

	
	/*
	 * 아이디찾기
	 */
	public String searchId(JTextField nametext, JTextField emailtext) {

		memberList = md.fileRead();

		String id = null;

		for (int i = 0; i < memberList.size(); i++) {

			System.out.println(memberList.get(i));

			if (memberList.get(i).getEmail().equals(emailtext.getText())
					&& (memberList.get(i).getName().equals(nametext.getText()))) { // 저장된 회원 정보

				id = memberList.get(i).getId();
			}
		}

		return id;

	}

	
	/*
	 * 비밀번호찾기
	 */
	public String searchPwd(JTextField nametext, JTextField emailtext) {

		String pwd = null;

		for (int i = 0; i < memberList.size(); i++) {
			if (memberList.get(i).getEmail().equals(emailtext.getText())
					&& (memberList.get(i).getName().equals(nametext.getText()))) { // 저장된 회원 정보
				pwd = memberList.get(i).getPwd();
			}
		}

		return pwd;

	}

	public Member nugu() {
		return m;
	}
	
	
/*
 * 아이디체크
 */
	public int idCheck(JTextField idtext) {

		int result = 0;

		for (int i = 0; i < memberList.size(); i++) {

			if (memberList.get(i).getId().equals(idtext.getText())) {
				return 1; // 동일한 아이디가 존재
			}

		}
		return result;
	}
	
	
	
	/* 
	 * 로그아웃
	 */
	public void logOut(){
		
		this.m = null; 
		
		
	}

}
