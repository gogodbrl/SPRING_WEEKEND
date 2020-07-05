package com.stone.infolabs.boardmanage.servlet;

import javax.servlet.http.HttpServletRequest;

/** 
 * 스프링에서는 @RequestMapping 에 있는 URL(/request1)을 MyServlet.java에서 @WebServlet(/request1)을 실행할 때 가지고 와서 그걸 호출하는 방식이다.
 * attributeValue, attributeName를 각각 MyModelAndView에 선언한다.
 */

//@Controller
public class MyController {
	//4. 이걸 HandlerMapping 객체라고 한다.
	//@RequestMapping("/request1")
	public MyModelAndView GetRequestOneController(HttpServletRequest request) {
		//실제 이렇게 변환하는걸 @Controller 어노테이션에서 하는 것 같다.
		MyModelAndView myModelAndView = new MyModelAndView();
		myModelAndView.viewName="/WEB-INF/myview/myview.jsp"; //setViewName(); 이게 스프링에서 변환하는 ViewResolver 클래스와 같다.
		myModelAndView.attributeName = "city";  // addObject(attributeName,               );
		myModelAndView.attributeValue= "Seoul"; // addObject(             , attributeValue);
		return myModelAndView;
	}
}
