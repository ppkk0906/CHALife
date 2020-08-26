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
//로그인 상태 확인
if(session.getAttribute("admin")!=null){
//클래스 및 변수 준비
String command=null, reason=null;
String []users=null;
String time_string=request.getParameter("time");
int time=0;
UserManager m = UserManager.getInstance();
//정보 받아오고 null시 오류 처리
users=request.getParameterValues("user_index");
if(users==null){
	out.print("<script>alert('하나 이상의 item을 선택하세요');history.back();</script>");
	return;
}
reason=request.getParameter("reason");
command=request.getParameter("command");
if(command==null){
	out.print("<script>alert('명령이 비었습니다');history.back();</script>");
	return;
}
	//command가 ban일 경우에만 time_string을 int로 파싱
	if(command.equals("ban")){
		try{
			time=Integer.parseInt(time_string);
		}catch(Exception e){
			out.print("<script>alert('시간 란에는 숫자만 입력하세요!'); history.back();</script>");
			return;
		}
	}
//명령 처리
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