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
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/custom.css">
<title>게시글 목록과 검색</title>
<style type="text/css">
	a, a:hover {
		color: #000000;
		text-decoration:none;
	}
</style>
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
	<nav class="navbar navbar-default">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="main.jsp">자유게시판</a>
		</div>
		
	</nav>
	<div class="container">
		<div class="row">
			<table class="table table-striped" style="text-align: center; border: 1px solid #dddddd">
				<thead>
					<tr>
						<th style="background-color: #eeeeee; text-align: center;">번호</th>
						<th style="background-color: #eeeeee; text-align: center;">제목</th>
						<th style="background-color: #eeeeee; text-align: center;">작성자</th>
						<th style="background-color: #eeeeee; text-align: center;">작성일</th>
					</tr>
				</thead>
				<tbody>
<!-- 게시글 목록 영역 -->
				</tbody>
				</table>
				<!-- 게시글 목록 번호 영역 -->
 <a href="<%=request.getContextPath() %>/board/write?id=<%=request.getAttribute("others").toString() %>" class="btn btn-primary pull-right">글쓰기</a>
		</div>
	</div>
	
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.js"></script>
</body>
</html>
