package com.stone.infolabs.boardmanage.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.stone.infolabs.boardmanage.common.Board;

@Component
public interface I게시물업무관리자 {
	void 게시물등록하다(Board board);
	ArrayList<Board> 목록을출력하다();
	List<Board> 페이징목록을출력하다(long row, int size);
	Board 게시물을조회하다(long no);

}