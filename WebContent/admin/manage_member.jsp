<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입 승인/정지/제명</title>
</head>
<body>
<%if(session.getAttribute("admin")==null){ %>
<script>
alert("잘못된 접근입니다");
history.back();
</script>
<%}%>
</body>
</html>