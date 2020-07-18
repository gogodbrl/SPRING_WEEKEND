<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.stone.infolabs.boardmanage.common.Board" %>
<%
	Board board = (Board) request.getAttribute("board");
	String views = String.valueOf(board.getViews());
	System.out.println(views);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>게시물 상세</h1>
제목:<%= board.getTitle() %><br>
내용<textarea rows=10 cols=30 readonly><%= board.getContents() %></textarea><br>
작성자:<%= board.getWriter() %><br>
작성일:<%= board.getWritedate().toString() %><br>
조회수:<%= views %><br>
<a href="list_board?row=1&size=10">목록으로</a>
</body>
</html>