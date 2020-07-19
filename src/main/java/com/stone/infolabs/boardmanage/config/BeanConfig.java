package com.stone.infolabs.boardmanage.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.stone.infolabs.boardmanage.business.I게시물업무관리자;
import com.stone.infolabs.boardmanage.business.게시물업무관리자;
import com.stone.infolabs.boardmanage.dataservice.BoardDAO;
import com.stone.infolabs.boardmanage.dataservice.IBoardDAO;
import com.stone.infolabs.boardmanage.dataservice.IMemberDAO;
import com.stone.infolabs.boardmanage.dataservice.MemberDAO;
import com.stone.infolabs.boardmanage.presentation.게시물요청제어자;
import com.stone.infolabs.loginoutmanage.business.ILogInOutManager;
import com.stone.infolabs.loginoutmanage.presentation.LogInOutController;
import com.stone.infolabs.loginoutmanage.business.LogInOutManager;

@Configuration
public class BeanConfig {
	@Bean
	public 게시물요청제어자 게시물요청제어자() {
		return new 게시물요청제어자();
	}
	
	@Bean
	public I게시물업무관리자 게시물업무관리자() {
		return new 게시물업무관리자();
	}
	
	@Bean
	public IBoardDAO BoardDAO() {
		return new BoardDAO();
	}
	
	@Bean
	public LogInOutController LogInOutController() {
		return new LogInOutController();
	}
	
	@Bean
	public ILogInOutManager LogInOutManager() {
		return new LogInOutManager();
	}
	
	@Bean
	public IMemberDAO MemberDAO() {
		return new MemberDAO();
	}
}
