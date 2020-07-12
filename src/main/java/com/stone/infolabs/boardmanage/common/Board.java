package com.stone.infolabs.boardmanage.common;

import java.sql.Date;

public class Board {
	long no;
	String title;
	String contents;
	Member member;
	long views;
	Date writedate;
	
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public Member getWriter() {
		return member;
	}
	public void setWriter(Member member) {
		this.member = member;
	}
	public long getViews() {
		return views;
	}
	public void setViews(long views) {
		this.views = views;
	}
	public Date getWritedate() {
		return writedate;
	}
	public void setWritedate(Date writedate) {
		this.writedate = writedate;
	}
}
