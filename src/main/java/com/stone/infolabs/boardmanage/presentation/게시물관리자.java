package com.stone.infolabs.boardmanage.presentation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.stone.infolabs.boardmanage.common.Board;

@Controller
public class 게시물관리자 {
	
	@RequestMapping(value = "/prepare_board", method=RequestMethod.GET)
	public String 준비하다() {
		return "board";
	}
	
	@RequestMapping(value = "/write_board", method=RequestMethod.POST)
	public ModelAndView 등록하다(Board board) {
		Connection 연결자 = null;
		Statement 커서 = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			연결자 = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "test", "1234");
			
			String title = board.getTitle();
			String contents = board.getContents();
			String writer = board.getWriter();
			
			String sql = String.format("insert into board(title, contents, writer) values ('%s','%s','%s')", title, contents, writer);
			
			커서 = 연결자.createStatement();
			커서.executeUpdate(sql);
			
			커서.close();
			연결자.close();
			
			ModelAndView data = new ModelAndView();
			data.setViewName("add_info");
			data.addObject("title", title);
			data.addObject("contents",contents);
			data.addObject("writer", writer);
			return data;
		} catch (Exception e) {
			e.printStackTrace();
			try { 커서.close(); } catch(Exception e1) {e1.printStackTrace();}
			try { 연결자.close(); } catch(Exception e2) {e2.printStackTrace();}
		}
		return null;
	}
}
