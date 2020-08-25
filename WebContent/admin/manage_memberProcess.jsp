<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="admin.UserDataBean, admin.UserManager" %>
<%request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>처리</title>
</head>
<body>
<%
if(session.getAttribute("admin")!=null){
UserManager m = UserManager.getInstance();
String[] users=request.getParameterValues("user_index");
if(users==null){
	out.print("<script>alert('하나 이상의 item을 선택하세요');history.back();</script>");
	return;
}
String reason=request.getParameter("reason");
String command=request.getParameter("command");
String time_string=request.getParameter("time");
int time=0;
try{
time=Integer.parseInt(time_string);
}catch(Exception e){
	out.print("<script>alert('시간 란에는 숫자만 입력하세요!'); history.back();</script>");
	return;
}
int r = m.manageUser(command, time, reason, users);
if(r==0){
	out.print("<script>alert('성공적으로 처리됐습니다'); window.location.href='manage_member.jsp';</script>");
}else{
	out.print("<script>alert('실패'); window.location.href='manage_member.jsp';</script>");
}
%>
<br>
<%}else{%>
<script>
alert("올바르지 않은 접근입니다");
history.back();
</script>
<%}%>
</body>
</html>