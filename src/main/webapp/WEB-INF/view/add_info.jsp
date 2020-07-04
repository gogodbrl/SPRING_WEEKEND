<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.stone.infolabs.boardmanage.common.Board" %>
<%
	String title = (String) request.getAttribute("title");
	String contents = (String) request.getAttribute("contents");
	String writer = (String) request.getAttribute("writer");
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%= title %> 인 게시물이 저장되었습니다.
	<%= contents %> 인 게시물이 저장되었습니다.
	<%= writer %> 인 게시물이 저장되었습니다.
</body>
</html>