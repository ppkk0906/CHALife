<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그아웃</title>
</head>
<body>
<%
String projectPath=request.getContextPath();
String id=null;
if(session.getAttribute("admin")!=null){
	id = session.getAttribute("admin").toString();
	session.invalidate();
%>
<script>
alert('관리자 <%=id%>님, 성공적으로 로그아웃 하였습니다');
history.back();
</script>
<%
response.sendRedirect(projectPath+"/main");
}else{
%>
<script>
alert('잘못된 접근입니다');
history.back();
</script>
<%} %>
</body>
</html>