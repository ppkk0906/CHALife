package board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
public class GoUpdate implements CommandProcess{
		public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
			HttpSession session=request.getSession();
			if(session.getAttribute("userID")!=null) {
			request.setAttribute("userID", session.getAttribute("userID"));
			return "/bbs/update.jsp";
			}
			return "/bbs/login.jsp";
	}
}