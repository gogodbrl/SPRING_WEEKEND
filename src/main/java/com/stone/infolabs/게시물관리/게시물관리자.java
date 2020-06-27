package com.stone.infolabs.게시물관리;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class 게시물관리자 {
	@RequestMapping("/prepare_board")
	public String 게시물등록준비하다() {
		return "board";
	}
	
	@RequestMapping(value = "/write_board0", method=RequestMethod.GET)
	public String 게시물등록하다1(@RequestParam("title") String title, 
							  @RequestParam("content") String content) {
		return "board";
	}
	
	@RequestMapping(value = "/write_board2", method=RequestMethod.GET)
	public String 게시물등록하다1(HttpServletRequest request) {
//		String title = request.getParameter("title");
//		String content = request.getParameter("content");
		return "board";
	}
	
	@RequestMapping(value = "/write_board", method=RequestMethod.POST)
	public ModelAndView WriterInfo(Board board) {
		ModelAndView data = new ModelAndView();
		data.setViewName("writeinfo");
		data.addObject("title", board.getTitle());
		data.addObject("board", board);
		return data;
	}
}
