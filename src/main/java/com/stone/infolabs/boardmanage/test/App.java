package com.stone.infolabs.boardmanage.test;

import com.stone.infolabs.boardmanage.common.Member;
import com.stone.infolabs.boardmanage.dataservice.IMemberDAO;
import com.stone.infolabs.boardmanage.dataservice.MemberDAO;

public class App {
	public static void main(String[] args) {
		IMemberDAO memberDAO = new MemberDAO();
		
		Member member = new Member();
		member.setId("id1");
		member.setPassword("pass1");
		
		member.setName("name1");
		member.setPhone("01012345678");
		member.setEmail("1234@gmail.com");
		
		memberDAO.Save(member);
		
		Member member2 = memberDAO.FindByNo(1);
		System.out.println(member2.getName());
	}
}
