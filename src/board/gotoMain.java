package board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class gotoMain implements CommandProcess{
		public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
			request.setAttribute("message","그딴거없다");
			return "/bbs/main.jsp";
		}
	}
