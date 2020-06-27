<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 게시물 등록
 <form action="write_board" method="post">
 제목 : <input type="text" name="title"/><br>
 내용 : <textarea rows="20" cols="20" name="contents"></textarea> <br>
 <input type="submit" value="등록" />
 </form>
</body>
</html>