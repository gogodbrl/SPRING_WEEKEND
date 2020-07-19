package com.stone.infolabs.boardmanage.dataservice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.stereotype.Repository;

import com.stone.infolabs.boardmanage.common.Member;

@Repository
public class MemberDAO implements IMemberDAO {

	@Override
	public void Save(Member member) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection 연결자 = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "test","1234");
			
			String 행삽입SQL="insert into member(name, id, password, email, phone) values(?,?,?,?,?)";
			PreparedStatement 준비된명령자 = 연결자.prepareStatement(행삽입SQL);
			
			준비된명령자.setString(1, member.getName());
			준비된명령자.setString(2, member.getId());
			준비된명령자.setString(3, member.getPassword());
			준비된명령자.setString(4, member.getEmail());
			준비된명령자.setString(5, member.getPhone());
			
			준비된명령자.executeUpdate();
			연결자.close();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public Member FindByNo(long no) {
		Member member = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection 연결자 = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "test","1234");
			
			String 행조회SQL="select * from member where no=?";
			PreparedStatement 준비된명령자 = 연결자.prepareStatement(행조회SQL);
			준비된명령자.setLong(1, no);
			
			ResultSet 수집된표관리자 = 준비된명령자.executeQuery();
			if(수집된표관리자.next()) {
				String name = 수집된표관리자.getString("name");
				String id = 수집된표관리자.getString("id");
				String password = 수집된표관리자.getString("password");
				String email = 수집된표관리자.getString("email");
				String phone = 수집된표관리자.getString("phone");
				
				member = new Member();
				member.setName(name);
				member.setId(id);
				member.setPassword(password);
				member.setEmail(email);
				member.setPhone(phone);
			}
			연결자.close();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return member;
	}
	
	@Override
	public String FindByIdAndPassword(String id, String password) {
		String name = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection 연결자 = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "test","1234");
			
			String 행조회SQL="select * from member where id=? and password=?";
			PreparedStatement 준비된명령자 = 연결자.prepareStatement(행조회SQL);
			준비된명령자.setString(1, id);
			준비된명령자.setString(2, password);
			
			ResultSet 수집된표관리자 = 준비된명령자.executeQuery();
			if(수집된표관리자.next()) {
				name = 수집된표관리자.getString("name");
			}
			연결자.close();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return name;
	}
	
	public boolean isInById(String id) {
		boolean isId = false;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection 연결자 = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "test","1234");
			
			String 행조회SQL="select count(*) as cnt from member where id=?";
			PreparedStatement 준비된명령자 = 연결자.prepareStatement(행조회SQL);
			준비된명령자.setString(1, id);
			
			ResultSet 수집된표관리자 = 준비된명령자.executeQuery();
			if(수집된표관리자.next()) {
				int cnt = 수집된표관리자.getInt("cnt");
				if(cnt > 0) { isId = true; }
			}
			연결자.close();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return isId;
	}
	
}
