<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="admin.UserManager" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
if(session.getAttribute("admin")!=null){
UserManager u = UserManager.getInstance();
int r = u.acceptUser(request.getParameterValues("user_index"));
String[] users = request.getParameterValues("user_index");

if(r==0){
	out.print("<script>alert('성공적으로 승인했습니다'); window.location.href='login.jsp';</script>");
}else{
	out.print("<script>alert('실패했습니다 다시 시도하시거나 문제가 지속될 경우 관리자에게 문의해주세요.'); history.back();</script>");
}
out.print("<br>Target: ");
for(int i=0; i<users.length; i++){
	out.print(users[i]);
}
%>
<a href=javascript:hostory.back()> 뒤로 가기</a>

<%}else{ %>

<script>
alert("잘못된 접근입니다");
history.back();
</script>

<%}%>
</body>
</html>