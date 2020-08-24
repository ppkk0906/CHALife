package board;

import java.io.FileInputStream;
//import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

//import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;

import java.io.IOException;
import java.io.PrintWriter;

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
	// /property/boardCommand.properties에서 지정한 uri와 매핑한 클래스를 불러오는 코드
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
	
    public BoardController() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		requestPro(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		requestPro(request,response);
	}
	
	private void requestPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String firstURI="main", others=null, URI=request.getRequestURI(), path=null;
		String projectPath=request.getContextPath();
		//CommandProcess com = null;
		PrintWriter out = response.getWriter();
		String commandRaw=URI.substring(projectPath.length()); //우선 URI를 뽑고
		String urlPatterns="/board";
		try {
			commandRaw=commandRaw.substring(urlPatterns.length()); // "/board" 뒷부분만 뽑아낸다
			if(commandRaw.equals("")||commandRaw.equals("/")) { //명령의 이름이 명시됐지 않았다면
				response.sendRedirect(projectPath+"/main");
				return;
			}else {//명령의 이름이 명시됐다면?
				firstURI=commandRaw.substring(1);//우선 첫번째 "/"를 잘라낸다
				if(firstURI.lastIndexOf("/")!=-1) {// 잘라냈는데도 입력한 uri에 "/"가 하나 이상 포함된다면 
					others=firstURI.substring(firstURI.indexOf("/")+1); // 첫"/"의 오른쪽은 action으로
					firstURI=firstURI.substring(0,firstURI.indexOf("/")); // 첫"/"의 왼쪽은 firstURI로지정한다
				}
			}
			if(others==null||others.equals("")) {
				request.setAttribute("board", firstURI);
				path="/bbs/list.jsp";
			}else {
				request.setAttribute("board", firstURI);
				request.setAttribute("articleNo", others);
				path="/bbs/view.jsp";
			}
			}catch(Throwable e) {
			e.printStackTrace();
		}
		RequestDispatcher dispatcher =request.getRequestDispatcher(path);
		dispatcher.forward(request, response);
	}
}
