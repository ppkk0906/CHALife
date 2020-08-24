<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>회원가입</title>
</head>
<body>
<%String projectPath=request.getContextPath(); %>
<form method="post" action="<%=projectPath %>/join">
<input type=text name=id placeholder="4~20자 숫자 아이디 영문"><br>
<input type=password name=pw placeholder="6~20자 숫자 아이디 영문"><br>
<input type=text name=real_name placeholder="실명"><br>
<input type=text name=nick_name placeholder="커뮤니티 내에서 쓸 별명"><br>
<input type=text name=email placeholder="이메일 (회원 찾기 용도로만 쓰입니다)"><br>
<input type=submit value="회원가입"><br>
</form>
</body>
</html>