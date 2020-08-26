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
 * Servlet implementation class UserUpdateProcess
 */
@WebServlet("/userUpdate")
public class UserUpdateProcess extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserUpdateProcess() {
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
		UserDBBean db = UserDBBean.getInstance();
		//정보를 불러온다
		String userID=request.getParameter("userID");
		String userPassword=request.getParameter("userPassword");
		String userNick=request.getParameter("userNick");
		String userEmail=request.getParameter("userEmail");
		int result=9999;
		//브라우저에게 html 페이지로 인식시킨다
		out.println("<!DOCTYPE html><meta charset='UTF-8'>");
		//요청을 처리한다
		result=db.updateUser(userID, userPassword, userNick, userEmail);
		if(result==0) {
			out.print("<script>"
					+ "alert('수정에 실패했습니다. \\n원인으로는 중복된 닉네임, 이메일이 있습니다.');"
					+ "history.back();"
					+"</script>");
			return;
		}else if(result==1) {
			session.invalidate();
			out.print("<script>"
					+ "alert('수정에 성공했습니다. \\n다시 로그인하세요.');"
					+ "window.location.href='"
					+request.getContextPath()
					+"/main';"
					+"</script>");
		}else {
			out.print("<script>"
					+ "alert('알 수 없는 오류입니다.');"
					+ "history.back();"
					+"</script>");
		}
	}

}
