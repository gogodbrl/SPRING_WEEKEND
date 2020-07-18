<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.stone.infolabs.boardmanage.common.Board"%>

<%
	List<Board> boards = (List<Board>) request.getAttribute("boards");
     long row = (long) request.getAttribute("row");//요청했던 행번호
     
     //출력할 페이지 범위 계산
     //현재페이지 계산
     
     int 페이지당게시물수 = 10;
     int 페이지블럭당최대페이지수=10;
     int 현재페이지=(int)(row-1+페이지당게시물수)/페이지당게시물수;
                            //바닥((현재페이지-1)/페이지블럭당최대페이지수)*페이지블럭당최대페이지수+1  
     int 출력될시작페이지번호 = ((int)Math.floor((double)((현재페이지-1)/페이지블럭당최대페이지수)))*페이지블럭당최대페이지수+1;
     long 총게시물수 = 22;
     
     int 총페이지수=(int)Math.ceil(총게시물수/(double)페이지당게시물수 );
     int 출력될마지막페이지번호 =총페이지수;
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
<!-- 페이지 번호 출력 -->
<%
	if(출력될시작페이지번호 !=1) {
		int 이전의출력될시작될시작페이지번호 = 출력될시작페이지번호-10;
		int 이전의행번호 = (이전의출력될시작될시작페이지번호 -1) * 페이지당게시물수 + 1;
	}
  for(int 페이지번호=출력될시작페이지번호; 페이지번호<=출력될마지막페이지번호; 페이지번호++){
	  int 행번호=(페이지번호-1)*페이지당게시물수+1;
%>
<%if(현재페이지==페이지번호 ){ %>
    <a href="list_board?row=<%=행번호 %>&size=<%=페이지당게시물수%>"><b>[<%= 페이지번호 %>]</b></a>&nbsp;&nbsp;
<%}else{ %>
    <a href="list_board?row=<%=행번호 %>&size=<%=페이지당게시물수%>">[<%= 페이지번호 %>]</a>&nbsp;&nbsp;
<%} %>
<%	  
  }
%>
<a href="prepare_board">글쓰기</a>

</body>
</html>