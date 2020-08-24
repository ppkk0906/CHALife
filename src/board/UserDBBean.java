package board;

import java.sql.*;
import javax.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class UserDBBean {
	
	private static UserDBBean instance = new UserDBBean();
	
    //.jsp페이지에서 DB연동빈인 BoardDBBean클래스의 메소드에 접근시 필요
    public static UserDBBean getInstance() {
        return instance;
    }
    
    private UserDBBean(){}
    
    //커넥션풀로부터 Connection객체를 얻어냄 : DB연동빈의 쿼리문을 수행하는 메소드에서 사용
    private Connection getConnection() throws Exception {
    	Context initCtx = new InitialContext();
    	Context envCtx = (Context) initCtx.lookup("java:comp/env");
    	DataSource ds = (DataSource)envCtx.lookup("jdbc/db");
      return ds.getConnection();
    }
   //SQL 쿼리 수행 후 각 객체를 닫는 메소드
    private void closeConnection(PreparedStatement pstmt, Connection conn, ResultSet rs) {
		if (rs != null) try{ rs.close(); }catch(SQLException ex) {
			ex.printStackTrace();
		}
        if (pstmt != null) try{ pstmt.close(); }catch(SQLException ex) {
        	ex.printStackTrace();
        }
        if (conn != null) try{ conn.close(); }catch(SQLException ex) {
        	ex.printStackTrace();
        }
    }
    //현재 회원수를 구하는 메소드
    public String getUserMax() {
    	String r="";
    	Connection conn=null;
    	PreparedStatement pstmt=null;
    	ResultSet rs = null;
    	try {
    		conn=getConnection();
    		String sql="SELECT MAX(user_index) FROM users";
    		pstmt=conn.prepareStatement(sql);
    		rs=pstmt.executeQuery();
    		rs.first();
    		r=rs.getString(1);
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	return r;
    }
    
    //회원 가입 처리에 사용하는 메소드
    public byte join(UserDataBean u) {
        Connection conn = null;
        PreparedStatement pstmt = null;
		ResultSet rs = null;
		byte r=0;
		int number=0; //user_index에 들어갈 번호
		String sql=null;
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		try {
			conn=getConnection();
			String orgPass=u.getPassword();
			//String shaPass=BCrypt.hashpw(orgPass, BCrypt.gensalt());
			u.setTime_reg(timestamp);
			sql = "INSERT INTO users VALUES(?,?,?,?,?,?,?,?)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, "1");
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(pstmt, conn, rs);
		}
		return r;
    }
}