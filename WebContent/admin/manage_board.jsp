<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 추가/제거</title>
</head>
<body>
<%if(session.getAttribute("admin")==null){ %>
<script>
alert("잘못된 접근입니다");
history.back();
</script>
<%}else{%>
<a href=login.jsp>통합 관리 페이지로</a>

<%} %>
</body>
</html>