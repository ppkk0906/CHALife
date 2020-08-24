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
/*
Connection conn=null;
Statement stmt=null;
PreparedStatement pstmt = null; //주로 이 코드를 씁니다
CallableStatement cstmt = null;
ResultSet rs=null;
String errStr = null;
int i = 0;

try{
	Context initCtx = new InitialContext();
	Context envCtx = (Context) initCtx.lookup("java:comp/env");
	DataSource ds = (DataSource)envCtx.lookup("jdbc/db");
	conn = ds.getConnection();
	String sql = "SELECT * FROM users";
	pstmt=conn.prepareStatement(sql);
	rs=pstmt.executeQuery();
	while(rs.next()){ i++; }
	out.print("testmember 테이블의 레코드 수는"+ i +"입니다.");
}catch(SQLException e){
	out.println("<b>SQL 오류</b> <pre>");
    StringWriter errors = new StringWriter();
    e.printStackTrace(new PrintWriter(errors));
    errStr = errors.toString();
    out.print(errStr+"</pre>");
}catch(Exception e){
	out.println("알 수 없는 오류입니다.<br> <pre>");
    StringWriter errors = new StringWriter();
    e.printStackTrace(new PrintWriter(errors));
    errStr = errors.toString();
    out.print(errStr+"</pre>");
}finally{
	if (rs!=null){try{rs.close();}catch(Exception e){}}
	if (conn!=null){try{conn.close();}catch(Exception e){}}
	if (pstmt!=null){try{pstmt.close();}catch(Exception e){}}
	if(stmt!=null){try{stmt.close();}catch(Exception e){}}
	if(cstmt!=null){try{cstmt.close();}catch(Exception e){}}
}
*/
%>
</body>
</html>