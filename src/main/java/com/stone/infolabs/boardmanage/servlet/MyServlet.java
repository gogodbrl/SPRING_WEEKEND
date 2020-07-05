package com.stone.infolabs.boardmanage.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** 주소가 request1일 경우 **/
@WebServlet("/request1")
public class MyServlet extends HttpServlet{
	
	/*******************************************************
	 * Static
	 *******************************************************/
	private static final long serialVersionUID = 1L;
	
	/*******************************************************
	 * Variable
	 *******************************************************/
	MyController myController = new MyController();
	
	/*******************************************************
	 * doGet
	 * @throws ServletException, IOException 
	 *******************************************************/
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1. 요청 매칭을 컨트롤러::myController라고 가정하면 다음과 같이 ModelAndView 객체에 값을 넣는다.
		MyModelAndView modelAndView = myController.GetRequestOneController(request);
		
		//2. View 포워딩을 통해 컨트롤러에서 값을 가져올 수 있도록 한다.
		request.setAttribute(modelAndView.attributeName, modelAndView.attributeValue);
		
		//3. RequestDispatcher를 통해서만 서블릿간에 전달할 수 있다. 결국 DispatcherServlet의 역할이 Forwarding이다. 
		//이런식으로 포워딩 하여 request를 controller로 넘겨서 처리하도록 하는거다.
		RequestDispatcher dispatcher = request.getRequestDispatcher(modelAndView.viewName);
		dispatcher.forward(request, response);
	}
	
	/*******************************************************
	 * doPost
	 *******************************************************/
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		
	}
}
