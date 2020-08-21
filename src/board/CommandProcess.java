package board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CommandProcess {
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
	throws Throwable; //이렇게 메소드를 만들면서 구체적인 내용을 제시 안한 것이 추상 메소드
}
