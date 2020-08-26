<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="board.BoardDBBean, board.Bbs, java.util.ArrayList" %>
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
<title>게시글 보기 페이지</title>
</head>
<body>
<%		
	String board="free";
	if(request.getAttribute("board")!=null){
		board = request.getAttribute("board").toString();
	}else if(request.getParameter("board")!=null){
		board = request.getParameter("board");
	}
	String userID = null;
	if (session.getAttribute("userID") != null) {
		userID = (String) session.getAttribute("userID");
	}
	int bbsID = 0;
	if (request.getAttribute("articleNo") != null) {
		try{
		bbsID = Integer.parseInt(request.getAttribute("articleNo").toString());
		}catch(Exception e){
			out.print("<script>alert('올바르지 않은 글번호입니다'); window.location.href='"+request.getContextPath()+"/main'</script>");
			e.printStackTrace();
		}
	}else if (request.getParameter("bbsID") != null) {
		try{
		bbsID = Integer.parseInt(request.getAttribute("articleNo").toString());
		}catch(Exception e){
			out.print("<script>alert('올바르지 않은 글번호입니다'); window.location.href='"+request.getContextPath()+"/main'</script>");
			e.printStackTrace();
		}
	}
	BoardDBBean db = BoardDBBean.getInstance();
	Bbs bbs = db.getBbs(board, bbsID);
	if (bbsID == 0||bbs.getBbsAvailable().equals("0")){
		out.print("<script>alert('존재하지 않는 글입니다'); window.location.href='"+request.getContextPath()+"/board/"+board+"'</script>");
	}
	if(bbs==null){
		out.print("<script>alert('게시글을 불러올 수 없습니다'); history.back();</script>");	
		return;
	}
%>
	<jsp:include page="topnavactive.jsp" flush="false" />
	<div class="container">
		<div class="row">
			<table class="table table-striped" style="text-align: center; border: 1px solid #dddddd">
				<thead> 
					<tr> 
						<th colspan="3" style="background-color: #eeeeee; text-align: center;">게시판 글보기 </th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td style="width: 20%;">글제목</td>
						<td colspan="2"><%=bbs.getBbsTitle().replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>") %></td>
					</tr>
					<tr>
						<td>작성자</td>
						<td colspan="2"><%=bbs.getUserID() %></td>
					</tr>
					<tr>
						<td>작성일자</td>
						<td colspan="2"><%=bbs.getBbsDate().toString().substring(0,11) + bbs.getBbsDate().toString().substring(11,13)+"시" +bbs.getBbsDate().toString().substring(14,16)+"분"  %></td>
					</tr>
					<tr>
						<td>내용</td>
						<td colspan="2" style="height:200px; text-align:left;"><%=bbs.getBbsContent().replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>")%></td>
					</tr>
				</tbody>
			</table>
			
			<%
				if(userID != null && userID.equals(bbs.getUserID())){
			%>		
<script>
function del() {
	q = confirm("이 글을 정말로 삭제하시겠습니까?");
	
	if(q==1){
    // form name은?
    var f = document.deleteForm;
	
    //param과 value 설정
	//var board=f.board.value;
	//var bbsID=f.bbsID.value;
	
    // input태그의 값들을 전송하는 주소
    //f.action = "";

    // 전송 방식 : post
    f.method = "post"
    f.submit();
	}
  };
</script>
				<form method=post action="<%=request.getContextPath() %>/delete" name=deleteForm>
				<a href="./" class="btn btn-primary">목록</a>
				<a href="<%=request.getContextPath() %>/board/update?board=<%=board %>&bbsID=<%=bbsID %>" class="btn btn-primary">수정</a>
				<input type=hidden name=board value=<%=board %>>
				<input type=hidden name=bbsID value=<%=bbsID %>>
				<a href="javascript:void(0);" onclick="del();" class="btn btn-primary">삭제</a>
				</form>
			<%
				}else{			
			%>			
			<a href="./" class="btn btn-primary">목록</a>			
			<%} %>
		</div>
	</div>

    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="<%=request.getContextPath() %>/js/bootstrap.js"></script>

</body>
</html>