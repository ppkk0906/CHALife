<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String board="free";
	if(request.getAttribute("board")!=null){
		board = request.getAttribute("board").toString();
	}else if(request.getParameter("board")!=null){
		board = request.getParameter("board");
}%>
	<nav class="navbar navbar-default">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navebar-collapse-1" aria-expanded="false">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>	
				<span class="icon-bar"></span>	
						
			</button>
			<a class="navbar-brand" href="<%=request.getContextPath()%>/main">CHA Life</a>
		</div>
		<div class="collapse navbar-collapse" id="bs-example-navber-collapse-1">
				<ul class="nav navbar-nav">
				<li class="active"><a href="<%=request.getContextPath()%>/board/<%=board%>">게시판</a></li>
			</ul>
		
			<%	
			String userID = null;
			if (session.getAttribute("userID") != null) {
				userID = (String) session.getAttribute("userID");
			}
				if(userID == null) {
			%>
			<ul class="nav navber-nav navbar-right">
				<li class="dropdown">
					<a href="#" class="dropdown-toggle"
					    data-toggle="dropdown" role="button" aria-haspopup="ture"
						aria-expanded="false">접속하기<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="<%=request.getContextPath()%>/board/login">로그인</a></li>
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
						<li><a href="<%=request.getContextPath()%>/board/user">정보수정</a></li>
					</ul>
				</li>	
			</ul>		
			<%		
				}
			%>
			
		</div>
		</nav>