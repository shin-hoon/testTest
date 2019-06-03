package com.boram.manager.run;

import java.util.ArrayList;

import com.boram.member.vo.Member;
import com.boram.member.vo.MemberDao;

//юс╫ц

public class Run {
	
	public static void main(String[] agrs) {
		
		 ArrayList<Member> mArr = new ArrayList<>();
		 MemberDao md = new MemberDao();
		 mArr=md.fileRead();
		 System.out.println(mArr);
	}

}
