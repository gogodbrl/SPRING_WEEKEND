<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<!--------------------------------------------- 
		HEAD
	----------------------------------------------->
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<!--------------------------------------------- 
		BODY 
	----------------------------------------------->
	<body>
		<h1>게시물 등록</h1>
		<form action="write_board" method="post">
			제목 <input type="text" name="title"/><br>
			내용 <textarea rows="20" cols="10" name="contents"></textarea> <br>
			작성자 <input type="text" name="writer"/><br>
			<input type="submit" value="등록" />
		</form>
	</body>
</html>