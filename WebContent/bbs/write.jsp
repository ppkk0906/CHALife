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
<title>게시글 작성 페이지</title>
</head>
<body>
<%
String board=null;
if(request.getAttribute("others")!=null){
	board = request.getAttribute("others").toString();
}else if(request.getParameter("board")!=null){
	board = request.getParameter("board");
}
String userID = null;
if (session.getAttribute("userID") != null) {
	userID = (String) session.getAttribute("userID");
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
			<a class="navbar-brand" href="main.jsp">CHA Life</a>
		</div>
		<div class="collapse navbar-collapse" id="bs-example-navber-collapse-1">
			<ul class="nav navbar-nav">
				<li><a href="<%=request.getContextPath()%>/main">메인</a></li>
				<li class="active"><a href="<%=request.getContextPath()%>/board/<%=board%>">게시판</a></li>
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
						<li><a href="logoutAction.jsp">로그아웃</a></li>
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
			<form method="post" action="<%=request.getContextPath() %>/write">
				<table class="table table-striped" style="text-align: center; border: 1px solid #dddddd">
					<thead> 
						<tr> 
							<th colspan="2" style="background-color: #eeeeee; text-align: center;">게시판 글쓰기 양식 
							<input type=hidden name=board value=<%=board %>>
							<input type=hidden name=userID value=<%=userID %>>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><input type="text" class="form-control" placeholder="글 제목" name="bbsTitle" maxlength="50"> </td>
						</tr>
						<tr>
							<td><textarea class="form-control" placeholder="글 내용" name="bbsContent" maxlength="2048" style="height:350px"></textarea></td>
						</tr>	
					</tbody>
					
				</table>
				<input type="submit" class="btn btn-primary pull-right" value="글쓰기">
			</form>
		</div>
	
	</div>

    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="<%=request.getContextPath() %>/js/bootstrap.js"></script>
</body>
</html>