package com.stone.infolabs.boardmanage.presentation;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.stone.infolabs.boardmanage.business.I게시물업무관리자;
import com.stone.infolabs.boardmanage.common.Board;

@Controller
public class 게시물요청제어자 {
	
	@Autowired
	I게시물업무관리자 게시물업무관리자;
	
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
		/** Insert Board **/
		게시물업무관리자.게시물등록하다(board);
		
		/** Set DataName **/
		ModelAndView data = new ModelAndView();
		data.setViewName("add_info");
		data.addObject("title", board.getTitle());
		data.addObject("contents", board.getContents());
		data.addObject("member", board.getWriter());
		return data;
	}
	
//	/*******************************************************
//	 * GET : list
//	 * @return list.jsp
//	 * (NOT USED!!)
//	 *******************************************************/
//	@RequestMapping(value = "/list_board", method=RequestMethod.GET)
//	public ModelAndView 목록을출력하다() {
//		
//		/** Select Board **/
//		ArrayList<Board> boardList = 게시물업무관리자.목록을출력하다();
//		
//		ModelAndView data = new ModelAndView();
//		data.setViewName("list");
//		data.addObject("boards", boardList);
//		return data;
//	}
	
	/*******************************************************
	 * GET : list
	 * @return list.jsp
	 *******************************************************/
	@RequestMapping(value = "/list_board", method=RequestMethod.GET)
	public ModelAndView 페이징목록을출력하다(@RequestParam("row") long row, @RequestParam("size") int size) {
		
		/** Select Board **/
		ArrayList<Board> boardList = (ArrayList<Board>) 게시물업무관리자.페이징목록을출력하다(row, size);
		
		ModelAndView data = new ModelAndView();
		data.setViewName("list");
		data.addObject("boards", boardList);
		data.addObject("row", row);
		return data;
	}
	
	/*******************************************************
	 * GET : detail_board
	 * @return detail.jsp
	 *******************************************************/
	@RequestMapping(value = "/detail_board", method=RequestMethod.GET)
	public ModelAndView 자세히보다(@RequestParam("no") long no) {
		if(no < 0) { return null; }
		/** Select Board **/
		Board board = 게시물업무관리자.게시물을조회하다(no);
		/** Set DataName **/
		ModelAndView data = new ModelAndView();
		data.setViewName("detail");
		data.addObject("board", board);
		return data;
	}
	
}
