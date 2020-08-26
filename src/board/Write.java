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
 * Servlet implementation class Write
 */
@WebServlet("/write")
public class Write extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Write() {
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
		//클래스를 불러온다
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		BoardDBBean db = BoardDBBean.getInstance();
		//정보를 불러온다
		String title=request.getParameter("bbsTitle");
		String content=request.getParameter("bbsContent");
		String board=request.getParameter("board");
		String userID=(String)session.getAttribute("userID");
		int result=0;
		//브라우저에게 html 페이지로 인식시킨다
		out.println("<!DOCTYPE html><meta charset='UTF-8'>");
		//제목과 내용의 whitespace를 제거한다
		title=title.trim();
		content=content.trim();
		/*
		System.out.println("작성 정보");
		System.out.println("게시판-"+board);
		System.out.println("작성자-"+userID);
		System.out.println("제목-"+title);
		System.out.println("내용");
		System.out.println(content);
		*/
		//오류상황을 처리한다
		if(userID==null || userID.equals("")) {
			out.print("<script>");
			out.print("alert('로그인 하세요'); window.location.href='./';");
			out.print("</script>");
			return;
		}
		if(board==null || board.equals("")) {
			out.print("<script>");
			out.print("alert('잘못된 게시판 이름입니다.'); window.location.href='./';");
			out.print("</script>");
			return;
		}		
		if(title==null || title.equals("")) {
			out.print("<script>");
			out.print("alert('제목을 입력하세요.'); history.back();");
			out.print("</script>");
			return;
		}
		if(content==null || content.equals("")) {
			out.print("<script>");
			out.print("alert('내용을 입력하세요.'); history.back();");
			out.print("</script>");
			return;
		}
		//작성을 실행한다
		result=db.write(board, title, userID, content);
		if(result==1) {
			response.sendRedirect(request.getContextPath()+"/board/"+board);
			return;
		}else if(result==-1) {
			System.out.println("DB오류");
			response.sendRedirect(request.getContextPath()+"/board/"+board);
			return;
		}else{
			System.out.println("쓰기 실패");
			response.sendRedirect(request.getContextPath()+"/board/"+board);
			return;
		}
	}

}
