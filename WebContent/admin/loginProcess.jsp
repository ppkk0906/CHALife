<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
String id="";
String pw="";
id=request.getParameter("id");
pw=request.getParameter("pw");
int loginFailed = 0;
if(session.getAttribute("adminLoginFailed")!=null){
loginFailed=(int)session.getAttribute("adminLoginFailed");
}
if(loginFailed>2){
%>
<script>
alert("로그인 시도 횟수를 초과했습니다");
history.back();
</script>
<%	
}else if(id==null||pw==null){
%>
<script>
alert("올바르지 않은 접근입니다");
history.back();
</script>
<%}else if(id.equals("")||pw.equals("")){ %>
<script>
alert("ID나 비밀번호 중 빠진 것이 있는지 다시 확인하세요");
history.back();
</script>
<%}else if(id.equals("admin")&&pw.equals("0906")){
session.setAttribute("admin", id);
response.sendRedirect("login.jsp");
session.setAttribute("adminLoginFailed", (int)0);
}else{
loginFailed++;
%>
<script>
alert('아이디 or(and) 비밀번호가 틀렸습니다\n로그인 시도 횟수: <%=loginFailed%> /3');
history.back();
</script>
<%
session.setAttribute("adminLoginFailed", loginFailed);
} %>
</body>
</html>