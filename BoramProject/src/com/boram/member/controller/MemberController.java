package com.boram.member.controller;

import java.util.ArrayList;

import javax.swing.JTextField;

import com.boram.member.vo.Member;
import com.boram.member.vo.MemberDao;
import com.boram.myPage.controller.MyCart;

public class MemberController {

	MemberDao md = new MemberDao();

//	MyCart ca=new MyCart();

	ArrayList<Member> memberList = md.fileRead();
	static Member m= new Member();
	//md.fileSave(memberList);
	{//초기임시데이터 추가 : 용준
		//"name1",950417,"id1","pwd2","01029346330","seoul","email.com" 
		memberList.add(new Member("name1",950411,"id1","pwd1","01029346331","seoul","1mail.com"));
		memberList.add(new Member("name2",950412,"id2","pwd2","01029346332","seou1","2mail.com"));
		memberList.add(new Member("name3",950413,"id3","pwd3","01029346333","seou2","3mail.com"));
		memberList.add(new Member("name4",950414,"id4","pwd4","01029346334","seou3","4mail.com"));
		memberList.add(new Member("name5",950415,"id5","pwd5","01029346335","seou4","5mail.com"));
	}
	
	public void join(String name, int age, String id, String pwd, String phone, String address, String email) {
		
		memberList.add(new Member(name, age, id, pwd, phone, address, email));
	}

	public Member logIn(String id, String pwd) {

		for (int i = 0; i < memberList.size(); i++) {
			// 저장된 아이디랑 패스워드가 입력한 아이다랑 패스워드랑 같으면
			
			if (memberList.get(i).getId().equals(id) && memberList.get(i).getPwd().equals(pwd)) {


				System.out.println("success");
				//memberList.get(i)를 리턴함과동시에 nugu메소드를통해 누가 로그인햇는지 알려줌.
				m = memberList.get(i);
			
				System.out.println(m.toString());
				System.out.println("성공");
				//로그인과 동시에 저장해놓앗던 장바구니 불러오기.
				//ca.loadCart();
				return m;
			}
			
		}

		
		System.out.println("fail");
		return null;
	}

	public String searchId(JTextField nametext, JTextField emailtext) {

		String id = null;

		for (int i = 0; i < memberList.size(); i++) {

			if (memberList.get(i).getEmail().equals(emailtext) && (memberList.get(i).getName().equals(nametext))) { // 저장된 회원 정보
				id = memberList.get(i).getId();
			}
		}

		return id;

	}

	public String searchPwd(JTextField nametext, JTextField emailtext) {

		String pwd = null;

		for (int i = 0; i < memberList.size(); i++) {

			if (memberList.get(i).getEmail().equals(emailtext.getText()) && (memberList.get(i).getName().equals(nametext.getText()))) { // 저장된 회원 정보
				pwd = memberList.get(i).getPwd();
			}
		}

		return pwd;

	}
	
	public Member nugu() {
		return m;
	}
	
	public int idCheck(JTextField id) {
		
		for(int i=0; i<memberList.size(); i++) {
			if(memberList.get(i).getId().equals(id.getText())) {
				return 1; // 동일한 아이디가 존재
			}
		}
		return -1; // 동일한 아이디 없다
		}
	
}
