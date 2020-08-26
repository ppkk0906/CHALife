<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="admin.UserManager,admin.UserDataBean, java.util.Map, java.util.HashMap, java.util.Iterator, java.sql.Timestamp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 관리 - 제명</title>
</head>
<body>
<%if(session.getAttribute("admin")==null){ %>
<script>
alert("잘못된 접근입니다");
history.back();
</script>
<%}else{
UserManager u = UserManager.getInstance();
Map<Integer,UserDataBean> view = u.getAllUser();
Iterator<Integer> viewKey = view.keySet().iterator();
%>
<a href=login.jsp>통합 관리 페이지로</a>
<form method=post action=manage_memberProcess.jsp>
<select name=command id=command>
<option value=ban>제명</option>
<option value=unban>제명 해제</option>
<option value=delete>삭제</option>
</select>
제명 시간(초): <input type=number placeholder="제명 시간(초)" name=time id=time value=0>
<input type=text placeholder="사유" name=reason id=reason>
<input type=submit value='확인'>
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
<th>~까지 제명</th>
<th>사유</th>
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
	Timestamp date_punishment = view.get(user_index).getDate_punishment();
	String reason_banned = view.get(user_index).getReason_banned();
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
<td><%
if(date_punishment.before(new Timestamp(System.currentTimeMillis()))){
	out.print("x");
	}else{
	out.print(date_punishment);
}%>
</td>

<td><%=reason_banned%></td>
</tr>
<%} %>
</table>
</form>
<%}%>
</body>
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script>
$("#command").on("change", function() {
	if($("#command option:selected").val()=="ban"){
		$("#time").removeAttr("readonly");
		$("#reason").removeAttr("readonly");
	}else if($("#command option:selected").val()=="unban"){
		$("#time").attr("readonly",true);
		$("#reason").removeAttr("readonly");
	}else{
		$("#time").attr("readonly",true);
		$("#reason").attr("readonly",true);
	}
});

</script>
</html>