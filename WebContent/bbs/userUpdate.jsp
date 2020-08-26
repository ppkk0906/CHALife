<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="board.UserDBBean,board.UserDataBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/custom.css">
</head>
<body>
<%
//클래스 획득
UserDBBean db = UserDBBean.getInstance();
UserDataBean u = new UserDataBean();
//변수 초기화
String projectPath=request.getContextPath();
int step=0, result=4;
String userID=null, pw=null;
//필요한 변수를 여러 군데에서 획득
if(session.getAttribute("userID")!=null){
	userID=(String)session.getAttribute("userID");
	step=1;
}
if(request.getParameter("pw")!=null){
	pw=request.getParameter("pw");
	result=db.login(userID, pw);
}
if(result==0){
	step=2;
}
%>
<%if(step==0){ %>
<script>
window.location.href='<%=request.getContextPath()%>/main';
</script>
<%}else if(step==1){ %>
	<jsp:include page="topnav.jsp" flush="false" />
<div class="container">
		<div class="col-lg-4">
			<div class="jumbotron" style="padding-top: 20px">
			<form method=post action=#>
			<h3 style="text-align: center;">유저 정보를 다시 확인합니다</h3>
			<div class="form-group">
				<input class="form-control" type=text name=id value=<%=userID %> readonly>
			</div>
			<div class="form-group">
				<input class="form-control" type=password name=pw placeholder="비밀번호" required>
			</div>
			<input type=button onclick="history.back();" class="btn-primary form-control" value="이전">
			<br>
				<input type=submit class="btn-primary form-control" value="비밀번호 확인">
			</form>
			</div>		
		</div>
</div>
<%}else{
	u=db.getUser(userID);
%>

	<jsp:include page="topnav.jsp" flush="false" />
	<div class="container">
		<div class="col-lg-4"></div>		
		<div class="col-lg-4">
			<div class="jumbotron" style="padding-top: 20px">
				<form method="post" action="<%=projectPath %>/userUpdate">
					<h3 style="text-align: center;">회원정보 수정</h3>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="아이디" name="userID" maxlength="20" value="<%=u.getUser_id() %>" readonly>
					</div>
					<div class="form-group">
						<input type="password" class="form-control" placeholder="새 비밀번호" name="userPassword" maxlength="20">
					</div>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="이름" name="userName" value="<%=u.getReal_name() %>" maxlength="20" readonly>
					</div>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="닉네임" name="userNick" value="<%=u.getNick_name() %>" maxlength="20">
					</div>
					<div class="form-group">
						<input type="email" class="form-control" placeholder="이메일" name="userEmail" value="<%=u.getEmail() %>" maxlength="50">
					</div>
					<input type="submit" class="btn-primary form-control" value="회원 정보 수정">					
				</form>
				<form method=post action="<%=projectPath %>/userDelete">
				<input type=hidden name=id value='<%=u.getUser_id() %>'>
				<input type=hidden name=pw value='<%=pw %>'>
				<br>
				<input type="submit" onclick="var c=confirm('정말 탈퇴하시겠습니까?'); if(c==0){return;}" class="btn-primary form-control" value="회원 탈퇴">
				</form>	
			</div>		
		</div>
		<div class="col-lg-4"></div>	
	</div>
	
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="<%=request.getContextPath() %>/js/bootstrap.js"></script>
<%} %>
</body>
</html>