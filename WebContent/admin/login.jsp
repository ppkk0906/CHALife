<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 페이지</title>
</head>
<body>
<% if(session.getAttribute("admin")==null){ //관리자 로그인 페이지 %>
<form method=post action="loginProcess.jsp">
<Table>
<tr>
<td>아이디: </td>
<td><input type=text name=id required></td>
</tr>
<tr>
<td>비밀번호: </td>
<td><input type=password name=pw required></td>
</tr>
<tr>
<td colspan=2><input type=submit value='관리자 로그인'></td>
</tr>
</Table>
</form>
<%}else{ //관리자 페이지
	String admin = session.getAttribute("admin").toString();
%>
<%=admin %>님 환영합니다<br>
<a href=manage_board.jsp>게시판 추가/제거</a><br>
<a href=accept_member.jsp>회원 가입 신청 승인</a><Br>
<a href=manage_member.jsp>회원 관리</a><Br>
<a href=logoutProcess.jsp>로그아웃</a>
<%} %>
</body>
</html>