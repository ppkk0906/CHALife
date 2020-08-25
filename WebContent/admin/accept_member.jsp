<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="admin.UserManager,admin.UserDataBean, java.util.Map, java.util.HashMap, java.util.Iterator, java.sql.Timestamp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>회원 승인 페이지</title>
</head>
<body>
<% if(session.getAttribute("admin")==null){ //관리자 로그인 페이지 %>
<script>
alert("잘못된 접근입니다.");
history.back();
</script>
<%}else{
UserManager u = UserManager.getInstance();
Map<Integer,UserDataBean> view = u.getUnAcceptedUser();
Iterator<Integer> viewKey = view.keySet().iterator();
%>
<a href=login.jsp>통합 관리 페이지로</a>
<form action=accept_memberProcess.jsp method=post>
<table>
<tr>
<th></th>
<th>유저 번호</th>
<th>유저 아이디</th>
<th>실명</th>
<th>닉네임</th>
<th>이메일</th>
<th>가입일</th>
<th>승인여부</th>
</tr>
<%
while(viewKey.hasNext()){
	int user_index = viewKey.next();
	String user_id = view.get(user_index).getUser_id();
	String real_name = view.get(user_index).getReal_name();
	String nick_name = view.get(user_index).getNick_name();
	String email = view.get(user_index).getEmail();
	Timestamp reg_date = view.get(user_index).getTime_reg();
	boolean is_available = view.get(user_index).isIs_available();
%>
<tr>
<td><input type=checkbox name=user_index value='<%=user_index%>'></td>
<td><%=user_index %></td>
<td><%=user_id %></td>
<td><%=real_name %></td>
<td><%=nick_name %></td>
<td><%=email %></td>
<td><%=reg_date %></td>
<td><%=is_available %></td>
</tr>
<%}%>
</table>
<input type=submit value='체크한 유저 승인'>
</form>
<%}%>
</body>
</html>