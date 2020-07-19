package com.stone.infolabs.loginoutmanage.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.stone.infolabs.boardmanage.util.Result;
import com.stone.infolabs.loginoutmanage.business.ILogInOutManager;

@Controller
public class LogInOutController {
	@Autowired
	ILogInOutManager loginoutManager;
	
	@PostMapping("/login")
	public ModelAndView Login(@RequestParam String id, @RequestParam String password) {
		Result result = loginoutManager.login(id, password);
		ModelAndView mv = new ModelAndView();
		
		if(result.resultCode == 0) {
			mv.setViewName("main");
			mv.addObject("name", result.value);
		} else {
			mv.setViewName("trylogin");
			mv.addObject("message", result.message);
		}
		return mv;
	}
}
