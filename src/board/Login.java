package board;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;

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
		out.println("<script>alert('올바르지 않은 접근입니다.'); history.back();</script>");
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
		//로그인 실패 횟수 받아오기
		if(session.getAttribute("userLoginFailed")!=null) { 
			userLoginFailed=(int)session.getAttribute("userLoginFailed");
		}
		if (userLoginFailed>4) {
			out.println("<script>alert('로그인 시도횟수를 초과하셨습니다.'); history.back();</script>");
			return;
		}
		//비로그인 상태일 때
		if(session.getAttribute("userID")==null) {
			if(id.equals("")||pw.equals("")) {
				out.println("<script>alert('아이디나 비밀번호가 누락됐습니다.'); history.back();</script>");
				return;
		}
		UserDBBean u = UserDBBean.getInstance();
		byte r=u.login(id,pw);
			if (r==0) {
				//out.print("<br>로그인 성공");
				session.setAttribute("userID", id);
				response.sendRedirect(request.getContextPath()+"/main");
			}else if(r==1) {
				userLoginFailed++;
				session.setAttribute("userLoginFailed", userLoginFailed);
				out.print("<script>alert('ID또는 비밀번호가 틀렸습니다.\\n 로그인 시도 횟수"+userLoginFailed+"/5'); history.back();</script>");
			}else if(r==2){
				Timestamp today = new Timestamp(System.currentTimeMillis());
				Timestamp punishment_date = u.getPunishmentDate(id);
				out.print("<script>alert('정지당한 유저입니다\\n아이디:"+id+"\\n오늘 날짜:"+today+"\\n정지가 풀리는 날짜:"+punishment_date+"'); history.back();</script>");
			}else{
				out.print("<script>alert('아직 가입이 승인되지 않았습니다\\n아이디: "+id+"'); history.back();</script>");
			}
		}else {
			out.print("<script>alert('이미 로그인 하셨습니다.');</script>");
		}
	}
}
