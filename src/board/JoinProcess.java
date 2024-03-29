package board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Join
 */
@WebServlet("/join")
public class JoinProcess extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JoinProcess() {
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
		//각종 클래스들을 인스턴스화한다
		PrintWriter out = response.getWriter();
		UserDataBean u = new UserDataBean();
		UserDBBean db = UserDBBean.getInstance();
		//값을 받아온다
		String userID=request.getParameter("userID");
		String userPassword=request.getParameter("userPassword");
		String userName=request.getParameter("userName");
		String userNick=request.getParameter("userNick");
		String userEmail=request.getParameter("userEmail");
		int result=0;
		//자바스크립트를 작동시키기 위해 브라우저에게 html 페이지로 인식시킨다
		out.println("<!DOCTYPE html><meta charset='UTF-8'>");
		//요청을 처리한다
			//우선 유저 데이터빈에 정보를 넣는다
			u.setUser_id(userID);
			u.setPassword(userPassword);
			u.setReal_name(userName);
			u.setNick_name(userNick);
			u.setEmail(userEmail);
			//유저 데이터빈의 준비를 완료했으니 정보를 DAO의 join 메서드에 전달한다
			result=db.join(u);
			if (result==1) {
				out.println("<script>");
				String script=String.format("alert('회원 가입에 성공했습니다.\\n관리자의 승인을 받으시면 로그인이 가능합니다'); window.location.href='%s';", 
						request.getContextPath()+"/main");
				out.println(script);
				out.println("</script>");
				return;
			}else {
				out.println("<script>");
				String script="alert('회원 가입에 실패했습니다.\\n 원인으로는 중복된 아이디,닉네임,이메일이 있을 수 있습니다.'); history.back();";
				out.println(script);
				out.println("</script>");
				return;
			}
	}
}
