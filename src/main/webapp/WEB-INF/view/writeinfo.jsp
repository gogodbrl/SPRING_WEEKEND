<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.stone.infolabs.게시물관리.Board" %>
<%
	String title = (String) request.getAttribute("title");
	Board 게시물 = (Board) request.getAttribute("board");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%= title %> 글이 잘 저장되었습니다.
	<%= 게시물.getTitle() %> 글이 잘 저장되었습니다.
</body>
</html>