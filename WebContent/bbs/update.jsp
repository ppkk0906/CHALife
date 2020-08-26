<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
request.setCharacterEncoding("UTF-8");
response.setCharacterEncoding("UTF-8");
%>
<%@ page import="board.BoardDBBean,board.Bbs"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/custom.css">
<title>게시글 수정 페이지</title>
</head>
<body>
<%
String board = null, userID = null, writer=null;
int bbsID=0;
if(request.getParameter("board")!=null){
	board = request.getParameter("board");
}
if(request.getParameter("bbsID")!=null){
	try{
	bbsID = Integer.parseInt(request.getParameter("bbsID"));
	}catch(Exception e){
		out.print("<script>alert('올바르지 않은 글번호 입니다'); window.location.href='"+request.getContextPath()+"/main';</script>");
	}
}
if(session.getAttribute("userID")!=null){
	userID = (String)session.getAttribute("userID");
}
BoardDBBean db = BoardDBBean.getInstance();
Bbs bbs = db.getBbs(board, bbsID);
if(bbs==null){
	out.print("<script>alert('게시글이 존재하지 않습니다.'); window.location.href='"+request.getContextPath()+"/main';</script>");
	return;
}
if(bbs.getUserID()!=null){
	writer=bbs.getUserID();
}
if(writer!=null&&userID!=null&&userID.equals(writer)){
%>
	<jsp:include page="topnavactive.jsp" flush="false" />
	<div class="container">
		<div class="row">
			<form method="post" action="<%=request.getContextPath()%>/update">
				<table class="table table-striped" style="text-align: center; border: 1px solid #dddddd">
					<thead> 
						<tr> 
						<input type=hidden name=board value='<%=board %>'>
						<input type=hidden name=bbsID value='<%=bbsID %>'>
							<th colspan="2" style="background-color: #eeeeee; text-align: center;">게시판 글 수정 양식 </th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><input type="text" class="form-control" placeholder="글 제목" name="bbsTitle" maxlength="50" value="<%=bbs.getBbsTitle()%>"> </td>
						</tr>
						<tr>
							<td><textarea class="form-control" placeholder="글 내용" name="bbsContent" maxlength="2048" style="height:350px"><%=bbs.getBbsContent()%></textarea></td>
						</tr>	
					</tbody>
					
				</table>
				<input type="submit" class="btn btn-primary pull-right" value="글수정">
			</form>
		</div>
	
	</div>

	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="<%=request.getContextPath() %>/js/bootstrap.js"></script>

<%
}else{
	out.print("<script>alert('작성자가 다릅니다.\\n작성자: "+bbs.getUserID()+"\\n로그인 세션:"+userID+"'); window.location.href='"+request.getContextPath()+"/main';</script>");
	return;
}
%>
</body>
</html>