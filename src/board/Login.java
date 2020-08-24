package board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
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
		out.println("<script>alert('유효하지 않은 접근'); history.back();</script>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		PrintWriter out= response.getWriter();
		out.println("<!DOCTYPE html><meta charset='UTF-8'>");
		String id=request.getParameter("id");
		String pw=request.getParameter("pw");
		int userLoginFailed = 0;
		if(session.getAttribute("userLoginFailed")!=null) {
			userLoginFailed=(int)session.getAttribute("userLoginFailed");
		}
		if(id==null||id.equals("")||pw==null||pw.equals("")) {
			out.println("<script>alert('아이디나 비밀번호가 누락됐습니다.'); history.back();</script>");
		}else {
			out.print("<br>ID input: "+id);
			out.print("<br>PW input: "+pw);
		}
		UserDBBean u = UserDBBean.getInstance();
		byte r=u.login(id,pw);
		if (r==0) {
			out.print("loin successed");
		}else if(r==1) {
			out.print("login failed");
			userLoginFailed++;
			session.setAttribute("userLoginFailed", userLoginFailed);
		}
		response.sendRedirect(request.getContextPath()+"/main");
	}
}
