package com.boram.myPage.controller;

import java.awt.Choice;
import java.util.ArrayList;

import com.boram.member.controller.MemberController;
import com.boram.member.vo.Member;
import com.boram.member.vo.MemberDao;

public class ChangeMember {
	private MemberDao md = new MemberDao();
	private ArrayList<Member> MemberList = md.fileRead();
	private int mNo = MemberController.m.getmNo();

	public ChangeMember() {
	}

//	{// 초기임시데이터 추가 : 용준
//		// "name1",950417,"id1","pwd2","01029346330","seoul","email.com"
//		MemberList.add(new Member(1,"name1", 950411, "id1", "pwd1", "01029346331", "seoul", "1mail.com"));
//		MemberList.add(new Member(2,"name2", 950412, "id2", "pwd2", "01029346332", "seou1", "2mail.com"));
//		MemberList.add(new Member(3,"name3", 950413, "id3", "pwd3", "01029346333", "seou2", "3mail.com"));
//		MemberList.add(new Member(4,"name4", 950414, "id4", "pwd4", "01029346334", "seou3", "4mail.com"));
//		MemberList.add(new Member(5,"name5", 950415, "id5", "pwd5", "01029346335", "seou4", "5mail.com"));
//	}

	/**
	 * 변경. 이하동일..
	 * 
	 * @param mNo
	 *            멤버번호 -> 본인것만 변경할수 있도록 수정해야함.
	 * @param new*
	 *            바꿀항목
	 * @return 성공시1, 실패시0반환.
	 */
	// id
	public int idChange(String newId) {
		int result = 0;
		try {
			for(Member i:MemberList) {
				System.out.println(i);
			}
			MemberList.get(mNo).setId(newId);
			result = 1;
		} catch (NullPointerException | IndexOutOfBoundsException e) {
			
			System.out.println("1잘못된 값입니다!");
			result = 0;
		}

		return result;
	}

	// pwd
	public int pwChange(String newPw) {
		int result = 0;
		try {
			MemberList.get(mNo).setPwd(newPw);
			result = 1;
		} catch (NullPointerException | IndexOutOfBoundsException e) {
			System.out.println("2잘못된 값입니다!");
			result = 0;
		}
		return result;
	}

	// phone
	public int phoneChange(String newPhone) {
		int result = 0;
		try {
			MemberList.get(mNo).setPhone(newPhone);
			result = 1;
		} catch (NullPointerException | IndexOutOfBoundsException e) {
			System.out.println("3잘못된 값입니다!");
			result = 0;
		}
		return result;
	}

	// address
	public int addressChange(String newAddress) {
		int result = 0;
		try {
			MemberList.get(mNo).setAddress(newAddress);
			result = 1;
		} catch (NullPointerException | IndexOutOfBoundsException e) {
			System.out.println("4잘못된 값입니다!");
			result = 0;
		}
		return result;
	}

	// email
	public int emailChange(String newEmail) {
		int result = 0;
		try {
			MemberList.get(mNo).setEmail(newEmail);
			result = 1;
		} catch (NullPointerException | IndexOutOfBoundsException e) {
			System.out.println("5잘못된 값입니다!");
			result = 0;
		}
		return result;
	}

	// age
	public void ageChange(String newyear, String newmonth, String newday) {
		
		MemberList.get(mNo).setYear(newyear);
		MemberList.get(mNo).setMonth(newmonth);
		MemberList.get(mNo).setDay(newday);
		
//		int=0;
//		try {
//			MemberList.get(mNo).setAge(newAge);
//			result = 1;
//		} catch (NullPointerException | IndexOutOfBoundsException e) {
//			System.out.println("6잘못된 값입니다!");
//			result = 0;
//		}
//		return result;
 }
	
	public void saveChange() {
		//MemberList는 이미 변경되어있음.
		// *Change 에서 nugu.get(i).*change 에서 본인이 로그인한 것만 변경해놓음.
		//그렇기때문에 MemberList를 dao를 통해 그냥 덮어씌워주면됨.
		md.fileSave(MemberList);
	}

	/**
	 * 조회용 임시메뉴
	 */
	public String printMem() {
		String name=MemberList.get(mNo).getName();
		String id=MemberList.get(mNo).getId();
		String year = MemberList.get(mNo).getYear();
		String month = MemberList.get(mNo).getMonth();
		String day = MemberList.get(mNo).getDay();
		String phone=MemberList.get(mNo).getPhone();
		int address=MemberList.get(mNo).getmNo();
		String email=MemberList.get(mNo).getEmail();
		
		return name+"님의 정보입니다.\nid : "+id+"\n생년월일 : "+year+month+day+"\n전화번호 : "+phone+"\n이메일 : "+email;
	}
	public void printAllMem() {
		System.out.println("MemberList 전체출력");
		System.out.println("dao통해서 바로 읽어온값.");
		MemberList.clear();
		ArrayList<Member> MemberList = md.fileRead();
		for(Member i:MemberList) {
			System.out.println(i.toString());
		}
	}
	
}
