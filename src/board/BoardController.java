package board;
/*
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

//import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
*/
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "board", 
	urlPatterns = { "/board/*" },
	initParams = { 
		@WebInitParam(name = "propertyConfig", value = "boardCommand.properties")
}
)
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/* /property/boardCommand.properties에서 지정한 uri와 매핑한 클래스를 불러오는 코드
	    private Map<String,Object> commandMap = new HashMap<String,Object>();	
	    
	    public void init(ServletConfig config) throws ServletException{
	    	String props = config.getInitParameter("propertyConfig");
	    	String realFolder = "/property";
	    	ServletContext context = config.getServletContext();
	    	String realPath = context.getRealPath(realFolder)+"\\"+props;
	    	Properties pr = new Properties();
	    	FileInputStream f = null;
	    	try {
	    		f = new FileInputStream(realPath);
	    		pr.load(f);
	    	}catch(Exception e) {
	    		e.printStackTrace();
	    	}finally {
	    		if(f!=null) try {f.close();}catch(Exception ex) {}
	    	}
	    	
	    	Iterator<?> keyIter = pr.keySet().iterator();
	    	
	    	while(keyIter.hasNext()) {
	    		String command = (String)keyIter.next();
	    		String className = pr.getProperty(command);
	    		try {
	    			Class<?> commandClass = Class.forName(className);
	    			Object commandInstance = commandClass.newInstance();
	    			commandMap.put(command, commandInstance);
	    		} catch (ClassNotFoundException e) {
	                throw new ServletException(e);
	            } catch (InstantiationException e) {
	                throw new ServletException(e);
	            } catch (IllegalAccessException e) {
	                throw new ServletException(e);
	            }
	    	}
	    }     
	*/
    public BoardController() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		requestPro(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		requestPro(request,response);
	}
	
	private void requestPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String boardName=null, action=null, URI=request.getRequestURI(), path=null;
		String projectPath=request.getContextPath();
		
		//URI로부터 보드명과 게시글 번호를 뽑는 코드
		String commandRaw=URI.substring(projectPath.length()); //우선 URI를 뽑고
		String urlPatterns="/board/"; //이 서블릿에 연결되는 URI패턴을 지정하고나서
		try {
			commandRaw=commandRaw.substring(urlPatterns.length()); // "board/" 뒷부분만 뽑아낸다
			if(commandRaw.indexOf("/")!=-1) { //보드의 이름을 명시한 부분 뒤에 추가적으로 명시된 부분이 있다면
				boardName = commandRaw.substring(0, commandRaw.indexOf("/")); // 첫"/"를 기준으로 앞부분을 boardName로 한다
			}else { //URL에 보드의 이름만 지정된 경우 commandRaw를 그대로 가져다 쓴다
				boardName = commandRaw;
			}
			if(commandRaw.indexOf("/")!=-1) {//뒷부분을 action으로 지정한다
				action = commandRaw.substring(commandRaw.indexOf("/")+1);
				if(action.indexOf("/")!=-1) {// boardName/action/*의 경우 이 *를 생략
					action = action.substring(0,action.indexOf("/")); 
			}}
			if(action==null||action.equals("")) {
				request.setAttribute("board", boardName);
				path="/bbs/list.jsp";
			}else {
				request.setAttribute("board", boardName);
				request.setAttribute("articleNo", action);
				path="/bbs/view.jsp";
			}
			}catch(Throwable e) {
			e.printStackTrace();
		}
		RequestDispatcher dispatcher =request.getRequestDispatcher(path);
		dispatcher.forward(request, response);
	}
}
