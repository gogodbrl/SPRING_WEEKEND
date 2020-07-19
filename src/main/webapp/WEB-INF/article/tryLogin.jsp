<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String name = (String) request.getAttribute("name");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<b> WelCome !!! </b>
<%if(name == null) { %>
<form action = "login" method="post">
	id <input type="text" name="id"/> <br>
	password <input type="text" name="password"/> <br>
	<input type="submit" value="login"/>
</form>
<a href="main">취소</a>
<a href="#">회원등록</a>
<%} else { %>
	안녕하세요! <%= name %> 님 환영합니다!
<form action="logout" mehtod="get">
	<input type="submit" value="logout"/>
<%} %>
</form>
</body>
</html>