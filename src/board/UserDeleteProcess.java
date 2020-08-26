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
 * Servlet implementation class deleteUser
 */
@WebServlet("/userDelete")
public class UserDeleteProcess extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserDeleteProcess() {
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
		String id=request.getParameter("id");
		String pw=request.getParameter("pw");
		int result=9999;
		//브라우저에게 html 페이지로 인식시킨다
		out.println("<!DOCTYPE html><meta charset='UTF-8'>");
		//요청을 처리한다
		result=db.deleteUser(id, pw);
		System.out.println("탈퇴 요청");
		System.out.println("아이디"+ id);
		System.out.println("비밀번호"+ pw);
		if(result==0) {
			out.print("<script>");
			out.print("alert('DB 오류'); history.back();");
			out.print("</script>");
			return;
		}else if(result==1) {
			session.invalidate();
			out.print("<script>"
					+ "alert('탈퇴에 성공했습니다\\n 그동안 차라이프를 이용해주셔서 감사합니다.');"
					+ "window.location.href='"
					+request.getContextPath()
					+"/main';"
					+"</script>");
			return;
		}else {
			out.print("<script>");
			out.print("alert('아이디 또는 비밀번호 오류'); history.back();");
			out.print("</script>");
			return;
		}
	}

}
