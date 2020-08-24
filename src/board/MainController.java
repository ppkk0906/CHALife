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
		//response.setCharacterEncoding("UTF-8");
		requestPro(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//response.setCharacterEncoding("UTF-8");
		requestPro(request, response);
	}
	private void requestPro (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String firstURI="main", others=null, URI=request.getRequestURI(), path=null;
		String projectPath=request.getContextPath();
		CommandProcess com = null;
		RequestDispatcher dispatcher = null;
		//PrintWriter out = response.getWriter();
		String commandRaw=URI.substring(projectPath.length()); //우선 URI를 뽑고
		String urlPatterns="/main";
		try {
			commandRaw=commandRaw.substring(urlPatterns.length()); // "/main" 뒷부분만 뽑아낸다
			if(commandRaw.equals("")) { //명령의 이름이 명시됐지 않았다면
			path="/main.jsp";
			dispatcher =request.getRequestDispatcher(path);
			dispatcher.forward(request, response);
			return;
			}else if(commandRaw.equals("/")){ // 명령의 이름이 없는데 뒷부분이 "/"로 끝나면 /를 없앤 페이지로 이동
				response.sendRedirect(projectPath+"/main");
				return;
			}else {//명령의 이름이 명시됐다면?
				firstURI=commandRaw.substring(1);//우선 첫번째 "/"를 잘라낸다
				if(firstURI.lastIndexOf("/")!=-1) {// 잘라냈는데도 입력한 uri에 "/"가 하나 이상 포함된다면 
					others=firstURI.substring(firstURI.indexOf("/")+1); // 첫"/"의 오른쪽은 action으로
					firstURI=firstURI.substring(0,firstURI.indexOf("/")); // 첫"/"의 왼쪽은 firstURI로지정한다
				}
			}
			com = (CommandProcess)commandMap.get(firstURI);
		if(com!=null) {
			request.setAttribute("others", others);
			path=com.requestPro(request,response); //맵핑된 클래스를 찾았다면
			dispatcher =request.getRequestDispatcher(path);
			dispatcher.forward(request, response);
		}else{
				response.sendRedirect(projectPath+"/404");
				return;
		}
		}catch(Throwable e) {
			throw new ServletException(e);
		}
		/* //디버깅용
		out.print("<br>path:"+path);
		out.print("<br>firstURI:"+firstURI);
		out.print("<br>others:"+others);
		*/
		}
}
