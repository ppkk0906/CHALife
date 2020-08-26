package board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Delete
 */
@WebServlet("/delete")
public class DeleteProcess extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteProcess() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out= response.getWriter();
		out.println("<!DOCTYPE html><meta charset='UTF-8'>");
		out.println("<script>alert('올바르지 않은 접근입니다.'); history.back();</script>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//한글 깨짐 방지
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		//각 클래스의 객체를 만든다
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		BoardDBBean db = BoardDBBean.getInstance();
		//값들을 받아온다
		String board=request.getParameter("board");
		int bbsID=Integer.parseInt(request.getParameter("bbsID"));
		String userID=(String) session.getAttribute("userID");
		Bbs bbs = db.getBbs(board, bbsID);
		String writter=bbs.getUserID();
		int result = 1;
		//자바스크립트를 작동시키기 위해 브라우저에게 html 페이지로 인식시킨다
		out.println("<!DOCTYPE html><meta charset='UTF-8'>");
		//요청을 처리한다
		/*
		System.out.println("게시글 삭제 요청");
		System.out.println("게시판: "+board);
		System.out.println("게시글: "+bbsID);
		System.out.println("요청자: "+userID);
		System.out.println("작성자: "+writter);
		*/
		if(writter.equals(userID)) {
			result=db.delete(board, bbsID);
			if(result==1) {
				out.print("<script>"
						+ "alert('삭제했습니다.');"
						+ "window.location.href='"+request.getContextPath()+"/board/"+board+"';"
						+"</script>");
			}else {
				out.print("<script>"
						+ "alert('삭제에 실패했습니다.');"
						+ "window.location.href='"+request.getContextPath()+"/board/"+board+"/"+bbsID+"';"
						+"</script>");
				return;
			}
		}else {
		out.print("<script>"
				+ "alert('작성자와 요청자가 다릅니다.');"
				+ "window.location.href='"+request.getContextPath()+"/board/"+board+"/"+bbsID+"';"
				+"</script>");
		return;
		}
	}

}
