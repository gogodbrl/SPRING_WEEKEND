package com.stone.infolabs.boardmanage.presentation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.stone.infolabs.boardmanage.common.Board;
import com.stone.infolabs.boardmanage.util.JDBCConnection;

@Controller
public class 게시물관리자 {
	public static final String FMT_INSERT_SQL = "insert into board(title, contents, writer) values ('%s','%s','%s')";
	public static final String FMT_SELECT_SQL = "select no, title, writer, writedate, views from board";
	
	/*******************************************************
	 * GET : prepare_board
	 * @return board.jsp
	 *******************************************************/
	@RequestMapping(value = "/prepare_board", method=RequestMethod.GET)
	public String 준비하다() {
		return "board";
	}
	
	/*******************************************************
	 * POST : write_board
	 * @return add_info.jsp
	 *******************************************************/
	@RequestMapping(value = "/write_board", method=RequestMethod.POST)
	public ModelAndView 등록하다(Board board) {
		/** Set DataName **/
		ModelAndView data = new ModelAndView();
		data.setViewName("add_info");
		try {
			String title = board.getTitle();
			String contents = board.getContents();
			String writer = board.getWriter();
			String sql = String.format(FMT_INSERT_SQL, title, contents, writer);
			/** Get Connection **/
			JDBCConnection.GetJDBCConnection();
			/** Get Cursor **/
			if(JDBCConnection.ExecuteSql(sql)) {
				data.addObject("title", title);
				data.addObject("contents",contents);
				data.addObject("writer", writer);
				return data;
			};
			JDBCConnection.Close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	
	/*******************************************************
	 * GET : list
	 * @return list.jsp
	 *******************************************************/
	@RequestMapping(value = "/list", method=RequestMethod.GET)
	public ModelAndView 목록출력() {
		ModelAndView data = new ModelAndView();
		data.setViewName("list");
		
		Connection 연결자 = null;
		Statement 커서 = null;
		List<Board> boards = new ArrayList<Board>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			연결자 = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "test", "1234");
			
			String 표수집SQL = String.format(FMT_SELECT_SQL);
			System.out.println(표수집SQL);
			
			커서 = 연결자.createStatement();
			
			ResultSet resultSet = 커서.executeQuery(표수집SQL);
			while(resultSet.next()) {
				Board board = new Board();
				board.setNo(resultSet.getLong("no"));
				board.setTitle(resultSet.getString("title"));
				board.setWriter(resultSet.getString("writer"));
				board.setWritedate(resultSet.getDate("writedate"));
				board.setViews(resultSet.getLong("views"));
				boards.add(board);
				System.out.println(board);
			}
			커서.close();
			연결자.close();
			data.addObject("boards", boards);
			return data;
		} catch (Exception e) {
			e.printStackTrace();
			try { 커서.close(); } catch(Exception e1) {e1.printStackTrace();}
			try { 연결자.close(); } catch(Exception e2) {e2.printStackTrace();}
		}
		return data;
	}
	/*******************************************************
	 * GET : detail_board
	 * @return detail.jsp
	 *******************************************************/
	@RequestMapping(value = "/detail_board", method=RequestMethod.GET)
	public ModelAndView 자세히보다(@RequestParam("no") long no) {
		/** Set DataName **/
		ModelAndView data = new ModelAndView();
		data.setViewName("detail");
		
		if(no < 0) { return null; }
		
		
		
		return data;
	}
	
}
