package board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class GoJoin implements CommandProcess{

		public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
			return "/bbs/join.jsp";
		}
	}
