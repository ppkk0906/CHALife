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
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append("Served at: ").append(request.getContextPath()).append("/joiN");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		requestPro(request, response);
	}
	private void requestPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		UserDataBean u = new UserDataBean();
		UserDBBean uDB = UserDBBean.getInstance();
		u.setUser_id(request.getParameter("id"));
		u.setPassword(request.getParameter("pw"));
		u.setPassword(request.getParameter("real_name"));
		u.setPassword(request.getParameter("nick_name"));
		u.setPassword(request.getParameter("email"));
		String result = uDB.join(u);
		out.print(result);
	}

}
