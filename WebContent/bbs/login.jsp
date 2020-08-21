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
</head>
<body>
<form method="post" action="<%=request.getContextPath() %>/login">
<input type=text name=id placeholder="아이디">
<input type=password name=pw placeholder="비밀번호">
<input type=submit value="로그인">
</form>
</body>
</html>