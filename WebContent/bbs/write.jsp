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
<title>게시글 작성 페이지</title>
</head>
<body>
<%
String board=null;
if(request.getAttribute("board")!=null){
	board = request.getAttribute("board").toString();
}else if(board==null&&request.getParameter("board")!=null){
	board = request.getParameter("board");
}else{
	board="none";
}


%>
작성 페이지 - <%=board %>
<form method="post" action="/board/write/<%=board %>">
<textarea name=content>
</textarea>
<input type=submit value=작성 />
</form>
</body>
</html>