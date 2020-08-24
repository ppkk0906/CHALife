<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="board.UserDBBean" %>
    <%@ page import="java.sql.*, javax.sql.*, javax.naming.*,java.io.PrintWriter,java.io.StringWriter" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Insert title here</title>
</head>
<body>
<%
UserDBBean u=UserDBBean.getInstance();

out.print(u.getUserMax());

%>
</body>
</html>