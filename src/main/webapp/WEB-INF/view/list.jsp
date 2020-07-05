<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.stone.infolabs.boardmanage.common.Board"%>

<%
	ArrayList<Board> boards = (ArrayList<Board>) request.getAttribute("boards");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 리스트</title>
</head>
<body>
	<ul> 
	<% for(Board board : boards){ %>
		<li>
			<a href="detail_board?no=<%= board.getNo() %>"><%= board.getNo() %></a>
			<%= board.getTitle() %>
			<%= board.getWriter() %>
			<%= board.getWritedate().toString() %>
			<%= board.getViews() %>
		</li>
	<% } %>
	</ul>
<a href="prepare_board">글쓰기</a>

</body>
</html>