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
<title>게시글 수정 페이지</title>
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

if(board==null&&request.getParameter("board")!=null){
	board = request.getParameter("board");
	}else if(articleNo==null&&request.getParameter("articleNo")!=null){
	articleNo = request.getParameter("articleNo");
	}

out.print("게시판 이름: "+board+"<br>");
out.print("게시글 번호: "+articleNo+"<br>");
%>
</body>
</html>