<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
request.setCharacterEncoding("UTF-8");
response.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/custom.css">
<title>게시글 작성 페이지</title>
</head>
<body>
<%
String board=null;
if(request.getAttribute("others")!=null){
	board = request.getAttribute("others").toString();
}else if(request.getParameter("board")!=null){
	board = request.getParameter("board");
}
String userID = null;
if (session.getAttribute("userID") != null) {
	userID = (String) session.getAttribute("userID");
}
%>
	<jsp:include page="topnavactive.jsp" flush="false" />
	<div class="container">
		<div class="row">
			<form method="post" action="<%=request.getContextPath() %>/write">
				<table class="table table-striped" style="text-align: center; border: 1px solid #dddddd">
					<thead> 
						<tr> 
							<th colspan="2" style="background-color: #eeeeee; text-align: center;">게시판 글쓰기 양식 
							<input type=hidden name=board value=<%=board %>>
							<input type=hidden name=userID value=<%=userID %>>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><input type="text" class="form-control" placeholder="글 제목" name="bbsTitle" maxlength="50"> </td>
						</tr>
						<tr>
							<td><textarea class="form-control" placeholder="글 내용" name="bbsContent" maxlength="2048" style="height:350px"></textarea></td>
						</tr>	
					</tbody>
					
				</table>
				<input type="submit" class="btn btn-primary pull-right" value="글쓰기">
			</form>
		</div>
	
	</div>

    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="<%=request.getContextPath() %>/js/bootstrap.js"></script>
</body>
</html>