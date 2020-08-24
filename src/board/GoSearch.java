package board;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class GoSearch implements CommandProcess{

		public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
			String query = request.getParameter("q");
			request.setAttribute("query", query);
			return "/search.jsp";
			
		}
	}
