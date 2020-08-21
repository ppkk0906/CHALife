<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
request.setCharacterEncoding("UTF-8");
response.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>게시글 삭제 확인 페이지</title>
</head>
<body>
<%
String board = null, articleNo = null;
if(request.getAttribute("board")!=null){
	board = request.getAttribute("board").toString();
}else if(board==null&&request.getParameter("board")!=null){
	board = request.getParameter("board");
}else{
	board="none";
}

if(request.getAttribute("articleNo")!=null){
	articleNo = request.getAttribute("articleNo").toString();
}else if(articleNo==null&&request.getParameter("articleNo")!=null){
	articleNo = request.getParameter("articleNo");
}else{
	articleNo="none";
}

out.print("게시판 이름: "+board+"<br>");
out.print("게시글 번호: "+articleNo+"<br>");
%>
<form method="post" action="/delete/<%=board %>/<%=articleNo %>">
정말로 삭제하시겠습니까?
<input type=hidden name=route value="good">
<input type=submit value="삭제하기">
<input type=button onclick=history.back() value="취소">
</body>
</body>
</html>