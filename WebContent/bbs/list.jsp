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
<title>게시글 목록과 검색</title>
</head>
<body>

<%
String board = null, query=null, list="1";

if(request.getAttribute("board")!=null){
	board = request.getAttribute("board").toString();
}else if(board==null&&request.getParameter("board")!=null){
	board = request.getParameter("board");
}else{
	board="none";
}

if(request.getParameter("q")!=null){
	query=request.getParameter("q");
	out.print("검색 단어: "+board+"<br>");
	}
if(request.getParameter("l")!=null){
	list=request.getParameter("l");
	out.print("목록 범위: "+list+"<br>");
	}

out.print("게시글 목록 표시<br>");
out.print("게시판 이름: "+board+"<br>");

%>
 
</body>
</html>
