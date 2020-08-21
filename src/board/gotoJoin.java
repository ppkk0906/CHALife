package board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class gotoJoin implements CommandProcess{

		public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
			request.setAttribute("message", "getParameter가 아니라 getAttribute로만 가져올 수 있다");
			return "/bbs/join.jsp";
		}
	}
