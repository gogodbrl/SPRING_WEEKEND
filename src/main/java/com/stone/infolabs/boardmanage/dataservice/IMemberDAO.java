package com.stone.infolabs.boardmanage.dataservice;

import org.springframework.stereotype.Repository;

import com.stone.infolabs.boardmanage.common.Member;

@Repository
public interface IMemberDAO {
	void Save(Member member);
	Member FindByNo(long no);
	String FindByIdAndPassword(String id, String password);
	boolean isInById(String id);
}