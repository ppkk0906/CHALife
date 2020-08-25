<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
response.setCharacterEncoding("UTF-8");
%>
<%@page import="board.Bbs, board.BoardDBBean, java.util.ArrayList" %>
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
String board = null, query=null;
int pageNumber=1;

if(request.getAttribute("board")!=null){
	board = request.getAttribute("board").toString();
}else if(board==null&&request.getParameter("board")!=null){
	board = request.getParameter("board");
}else{
	board="free";
}

if(request.getParameter("q")!=null){
	query=request.getParameter("q");
	out.print("검색 단어: "+query+"<br>");
	}

if(request.getParameter("l")!=null){
	try{
	pageNumber=Integer.parseInt(request.getParameter("l"));
	}catch(Exception e){
		System.out.println("파싱 오류");
	}
}
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
					<%
						BoardDBBean db = BoardDBBean.getInstance();
						ArrayList<Bbs> list = db.getList(board, pageNumber);
						for (int i = 0; i <list.size(); i++) {

					%>
					<tr>
						<td><%=list.get(i).getBbsID() %></td>
						<td><a href="<%=request.getContextPath() %>/board/<%=board %>/<%=list.get(i).getBbsID()%>"><%=list.get(i).getBbsTitle()%></a></td>
						<td><%=list.get(i).getUserID() %></td>
						<td><%=list.get(i).getBbsDate().toString().substring(0,11) + list.get(i).getBbsDate().toString().substring(11,13)+"시" +list.get(i).getBbsDate().toString().substring(14,16)+"분" %></td>
					</tr>
					<%
						}
					%>
				</tbody>
				</table>
				<!-- 게시글 목록 번호 영역 -->
				<%
				if (pageNumber !=1) {
			%>
				<a href="?l=<%=pageNumber - 1 %>" class="btn btn-success btn-arrow-left">이전</a>
			<%
				} if(db.nextPage(board, pageNumber + 1)) {
					
			%>
				<a href="?l=<%=pageNumber+1%>" class="btn btn-success btn-arrow-left">다음</a>
			<%
				}			
			%>
 <a href="<%=request.getContextPath() %>/board/write/<%=board %>" class="btn btn-primary pull-right">글쓰기</a>
		</div>
		<jsp:include page="../leftmenu.jsp" flush="false" />
	</div>

	
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="<%=request.getContextPath() %>/js/bootstrap.js"></script>
</body>
</html>
