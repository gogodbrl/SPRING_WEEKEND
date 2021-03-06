package com.stone.infolabs.boardmanage.dataservice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.stone.infolabs.boardmanage.common.Board;

@Repository
@Qualifier("BoardDAO")
public interface IBoardDAO {
	void Save(Board board);
	ArrayList<Board> SelectAll();
	Board FindByNo(long no);
	Board FindByNoIncreaseViews(long no);
	List<Board> SelectRange(long start, int size);
}