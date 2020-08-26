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
<title>로그인 페이지</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/custom.css">
</head>
<body>
	<jsp:include page="topnav.jsp" flush="false" />
<div class="container">
		<div class="col-lg-4">
			<div class="jumbotron" style="padding-top: 20px">
			<form method="post" action="<%=request.getContextPath() %>/login">
			<h3 style="text-align: center;">로그인</h3>
			<div class="form-group">
				<input type=text name=id class="form-control" placeholder="아이디">
			</div>
			<div class="form-group">
				<input type=password name=pw class="form-control" placeholder="비밀번호" required>
			</div>
				<input type=submit class="btn-primary form-control" value="로그인">
			</form>
			</div>		
		</div>
</div>
</form>
</body>
</html>