package com.stone.infolabs.boardmanage.dataservice;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.stone.infolabs.boardmanage.common.Board;

@Repository
public class 가짜게시물DAO implements IBoardDAO {

	@Override
	public void Save(Board board) {
		// TODO Auto-generated method stub
		System.out.println("BB");
		
	}

	@Override
	public ArrayList<Board> SelectAll() {
		System.out.println("A");
		return null;
	}

	@Override
	public Board FindByNo(long no) {
		// TODO Auto-generated method stub
		return null;
	}

}
