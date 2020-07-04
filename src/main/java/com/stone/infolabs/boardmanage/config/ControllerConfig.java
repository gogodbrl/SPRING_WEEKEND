package com.stone.infolabs.boardmanage.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.stone.infolabs.boardmanage.presentation.게시물관리자;

@Configuration
public class ControllerConfig {
	//여기서 autowired를 안쓰고 bean을 써도 잘 되는 이유
	//컨트롤러를 bean에다가 등록해주는 이유가 뭐지? 그냥 Controller만 쓰면 bean으로 등록이 안되나? springboot는 되는거 같은데..
	@Bean
	public 게시물관리자 BoardAdmin() {
		return new 게시물관리자();
	}
}
