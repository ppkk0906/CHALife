package board;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(
		urlPatterns = { "/main/*" }, 
		initParams = { 
				@WebInitParam(name = "propertyConfig", value = "maincommand.properties")
		})



public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Map<String,Object> commandMap = new HashMap<String,Object>();	
    public MainController() {
        super();
    }

    public void init(ServletConfig config) throws ServletException{
    	String props = config.getInitParameter("propertyConfig");
    	String realFolder = "/properties";
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		requestPro(request, response);
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		requestPro(request, response);
	}
	private void requestPro (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String view="main", action=null, URI=request.getRequestURI(), path=null;
		String projectPath=request.getContextPath();
		CommandProcess com = null;
		PrintWriter out = response.getWriter();
		String commandRaw=URI.substring(projectPath.length()); //우선 URI를 뽑고
		String urlPatterns="/main/";
		try {
			commandRaw=commandRaw.substring(urlPatterns.length()); // "main/" 뒷부분만 뽑아낸다
			if(commandRaw.equals("")) {path="/bbs/main.jsp";}
			if(commandRaw.indexOf("/")!=-1) { //명령의 이름을 명시한 부분 뒤에 추가적으로 명시된 부분이 있다면
				view = commandRaw.substring(0, commandRaw.indexOf("/")); // 첫"/"를 기준으로 앞부분을 view로 한다
			}else { //URL에 보드의 이름만 지정된 경우 commandRaw를 그대로 가져다 쓴다
				view = commandRaw;
			}
			if(commandRaw.indexOf("/")!=-1) {//뒷부분을 action으로 지정한다
				action = commandRaw.substring(commandRaw.indexOf("/")+1);
				if(action.indexOf("/")!=-1) {// view/action/*의 경우 이 *를 생략
					action = action.substring(0,action.indexOf("/")); 
			}}
			out.print("<br>"+view+"<br>");
			com = (CommandProcess)commandMap.get(view);
			if(com!=null) {
			out.print("<br>"+com+"<br>");
			path=com.requestPro(request,response);
			}else {
				path="/404/";
			}
		}catch(Throwable e) {
			throw new ServletException(e);
		}
		RequestDispatcher dispatcher =request.getRequestDispatcher(path);
		dispatcher.forward(request, response);
	}

}
