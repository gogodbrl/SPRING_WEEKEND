package com.stone.infolabs.boardmanage.dataservice;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.ModelAndView;

import com.stone.infolabs.boardmanage.common.Board;
import com.stone.infolabs.boardmanage.common.Member;

@Primary
@Repository
public class BoardDAO implements IBoardDAO {
	private static final String FMT_INSERT_SQL = "insert into board(title, contents, writer) values ('%s','%s','%s')";
	private static final String FMT_SELECT_SQL = "select no, title, writer, writedate, views from board";
	
	MemberDAO memberDAO = new MemberDAO();
	
	@Override
	public void Save(Board board) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection 연결자 = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "test","1234");
			String 행삽입SQL=String.format(FMT_INSERT_SQL, board.getTitle(), board.getContents(), board.getWriter().getNo());
			Statement 명령자 = 연결자.createStatement();
			명령자.executeUpdate(행삽입SQL);
			연결자.close();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	@Override
	public ArrayList<Board> SelectAll() {
		ArrayList<Board> boards=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection 연결자 =DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root","1234");
			String 표수집SQL=String.format(FMT_SELECT_SQL);
			Statement 명령자 = 연결자.createStatement();
			boards = new ArrayList<Board>();
			ResultSet 수집된표관리자= 명령자.executeQuery(표수집SQL);
			while(수집된표관리자.next()) {
				long 번호 = 수집된표관리자.getLong("no");
				String 제목=수집된표관리자.getString("title");			
//				String 작성자=수집된표관리자.getString("writer");
				Date 작성일=수집된표관리자.getDate("writedate");
				long 조회수 = 수집된표관리자.getLong("views");
				//현재 행을 객체로 변환
				Member writerMember = memberDAO.FindByNo(번호);
				
				Board board =new Board();
				board.setNo(번호);
				board.setTitle(제목);
				board.setWriter(writerMember);
				board.setWritedate(작성일);
				board.setViews(조회수);
				
				boards.add(board);				
			}
			수집된표관리자.close();
			연결자.close();
		}
		catch(Exception e) {
			System.out.println(e.toString());			
		}
		return boards;
	}
	
	@Override
	public Board FindByNo(long no) {
		Board board=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection 연결자 =DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "test","1234");
			String 표수집SQL=String.format("select * from board where no=%d", no);
			Statement 명령자 = 연결자.createStatement();
			ResultSet 수집된표관리자= 명령자.executeQuery(표수집SQL);
			if(수집된표관리자.next()) {		
				String 제목=수집된표관리자.getString("title");
				String 내용=수집된표관리자.getString("contents");
//				String 작성자=수집된표관리자.getString("writer");
				Date 작성일=수집된표관리자.getDate("writedate");
				long 조회수 = 수집된표관리자.getLong("views");
				
				//현재 행을 객체로 변환
				Member writerMember = memberDAO.FindByNo(no);
				board =new Board();
				board.setNo(no);				
				board.setTitle(제목);
				board.setContents(내용);
				board.setWriter(writerMember);
				board.setWritedate(작성일);
				board.setViews(조회수);							
			}
			수집된표관리자.close();
			연결자.close();		
		}
		catch(Exception e) {
			ModelAndView mv = new ModelAndView();
			mv.setViewName("통신장애알림");
		}
		return board;
	}
}
