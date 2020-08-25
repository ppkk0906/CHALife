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
 * Servlet implementation class Update
 */
@WebServlet("/update")
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Update() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		String board=request.getParameter("board");
		int bbsID=Integer.parseInt(request.getParameter("bbsID"));
		String userID=(String) session.getAttribute("userID");
		String title=request.getParameter("bbsTitle");
		String content=request.getParameter("bbsContent");
		BoardDBBean db = BoardDBBean.getInstance();
		Bbs bbs = db.getBbs(board, bbsID);
		String writter=bbs.getUserID();
		System.out.println("게시글 수정 요청");
		System.out.println("게시판: "+board);
		System.out.println("게시글: "+bbsID);
		System.out.println("요청자: "+userID);
		System.out.println("작성자: "+writter);
		System.out.println("제목: "+title);
		System.out.println("내용");
		System.out.println(content);
	}

}
