<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="board.BoardDBBean, board.Bbs, java.util.ArrayList" %>
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
<title>게시글 보기 페이지</title>
</head>
<body>
<%		
	String board="free";
	if(request.getAttribute("board")!=null){
		board = request.getAttribute("board").toString();
	}else if(request.getParameter("board")!=null){
		board = request.getParameter("board");
	}
	String userID = null;
	if (session.getAttribute("userID") != null) {
		userID = (String) session.getAttribute("userID");
	}
	int bbsID = 0;
	if (request.getAttribute("articleNo") != null) {
		try{
		bbsID = Integer.parseInt(request.getAttribute("articleNo").toString());
		}catch(Exception e){
			out.print("<script>alert('올바르지 않은 글번호입니다'); window.location.href='"+request.getContextPath()+"/main'</script>");
			e.printStackTrace();
		}
	}else if (request.getParameter("bbsID") != null) {
		bbsID = Integer.parseInt(request.getParameter("bbsID"));
	}
	if (bbsID == 0){
		out.print("<script>alert('존재하지 않는 글입니다'); window.location.href='"+request.getContextPath()+"/main'</script>");	
	}
	BoardDBBean db = BoardDBBean.getInstance();
	Bbs bbs = db.getBbs(board, bbsID);
	if(bbs==null){
		out.print("게시글을 불러올 수 없습니다");
		return;
	}
	%>
	<nav class="navbar navbar-default">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navebar-collapse-1" aria-expanded="false">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>	
				<span class="icon-bar"></span>	
						
			</button>
			<a class="navbar-brand" href="main.jsp">JSP 게시판 웹 사이트</a>
		</div>
		<div class="collapse navbar-collapse" id="bs-example-navber-collapse-1">
			<ul class="nav navbar-nav">
				<li><a href="main.jsp">메인</a></li>
				<li class="active"><a href="bbs.jsp">게시판</a></li>
			</ul>
			<%
				if(userID == null) {
			%>
			<ul class="nav navber-nav navbar-right">
				<li class="dropdown">
					<a href="#" class="dropdown-toggle"
					    data-toggle="dropdown" role="button" aria-haspopup="ture"
						aria-expanded="false">접속하기<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<!-- <li><a href="<%//=request.getContextPath()%>/">로그인</a></li>-->
						<li><a href="<%=request.getContextPath()%>/board/join">회원가입</a></li>
					</ul>
				</li>	
			</ul>		
			<%
				} else {
			%>
			<ul class="nav navber-nav navbar-right">
				<li class="dropdown">
					<a href="#" class="dropdown-toggle"
					    data-toggle="dropdown" role="button" aria-haspopup="ture"
						aria-expanded="false">회원관리<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="<%=request.getContextPath()%>/logout">로그아웃</a></li>
					</ul>
				</li>	
			</ul>		
			<%		
				}
			%>
			
		</div>
	</nav>
	<div class="container">
		<div class="row">
			<table class="table table-striped" style="text-align: center; border: 1px solid #dddddd">
				<thead> 
					<tr> 
						<th colspan="3" style="background-color: #eeeeee; text-align: center;">게시판 글보기 </th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td style="width: 20%;">글제목</td>
						<td colspan="2"><%=bbs.getBbsTitle().replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>") %></td>
					</tr>
					<tr>
						<td>작성자</td>
						<td colspan="2"><%=bbs.getUserID() %></td>
					</tr>
					<tr>
						<td>작성일자</td>
						<td colspan="2"><%=bbs.getBbsDate().toString().substring(0,11) + bbs.getBbsDate().toString().substring(11,13)+"시" +bbs.getBbsDate().toString().substring(14,16)+"분"  %></td>
					</tr>
					<tr>
						<td>내용</td>
						<td colspan="2" style="height:200px; text-align:left;"><%=bbs.getBbsContent().replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>")%></td>
					</tr>
				</tbody>
			</table>
			<a href="./" class="btn btn-primary">목록</a>
			<%
				if(userID != null && userID.equals(bbs.getUserID())){
			%>		
				<a href="<%=request.getContextPath() %>/board/update?board=<%=board %>&bbsID=<%=bbsID %>" class="btn btn-primary">수정</a>
				<a onclick="return confirm('정말로 삭제하시겠습니까?')" href="deleteAction.jsp?bbsID=<%=bbsID%>" class="btn btn-primary">삭제</a>
			<%
				}			
			%>			
		</div>
	
	</div>

    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="<%=request.getContextPath() %>/js/bootstrap.js"></script>

</body>
</html>