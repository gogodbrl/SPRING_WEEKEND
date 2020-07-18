package com.stone.infolabs.boardmanage.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.stone.infolabs.boardmanage.common.Board;
import com.stone.infolabs.boardmanage.dataservice.IBoardDAO;

@Service
public class 게시물업무관리자 implements I게시물업무관리자 {
	
	@Autowired(required = true)
	@Qualifier("BoardDAO")
	IBoardDAO boardDAO;
	
	@Override
	public void 게시물등록하다(Board board) {
		boardDAO.Save(board);
	}
	
	@Override
	public ArrayList<Board> 목록을출력하다(){
		return boardDAO.SelectAll();
	}
	
	@Override
	public Board 게시물을조회하다(long no) {
//		return boardDAO.FindByNo(no);
		return boardDAO.FindByNoIncreaseViews(no);
	}

	@Override
	public List<Board> 페이징목록을출력하다(long row, int size) {
		return boardDAO.SelectRange(row, size);
	}	
}
