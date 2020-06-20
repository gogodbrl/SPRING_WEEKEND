package com.infoslab.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BController {
	/** 얘를 객체로 만들어야 되는데, 그걸 관리하는 객체가 bean이다. **/
	@RequestMapping(value="bbb", method=RequestMethod.GET)
	public String Bprocessing() {
		System.out.println("B Processing");
		return "sayhello";
	}
}
