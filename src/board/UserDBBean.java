package board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
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
    public int getUserMax() {
    	int r=9999;
    	Connection conn=null;
    	PreparedStatement pstmt=null;
    	ResultSet rs = null;
    	try {
    		conn=getConnection();
    		String sql="SELECT MAX(user_index) FROM users";
    		pstmt=conn.prepareStatement(sql);
    		rs=pstmt.executeQuery();
    		rs.first();
    		r=rs.getInt(1);
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	return r+1;
    }
    
    //회원 가입 처리에 사용하는 메소드
    public String join(UserDataBean u) {
        Connection conn = null;
        PreparedStatement pstmt = null;
		ResultSet rs = null;
		String r=null;
		String sql=null;
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		try {
			conn=getConnection();
			String orgPass=u.getPassword();
			//String shaPass=BCrypt.(orPass,BCrypt.genSalt());
			u.setTime_reg(timestamp);
			u.setUser_index(getUserMax());
			sql = "INSERT INTO users VALUES(?,?,?,?,?,?,CURRENT_TIMESTAMP,0,null)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, getUserMax());
			pstmt.setString(2, u.getUser_id());
			pstmt.setString(3, u.getPassword());
			pstmt.setString(4, u.getReal_name());
			pstmt.setString(5, u.getNick_name());
			pstmt.setString(6, u.getEmail());
			r = sql;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(pstmt, conn, rs);
		}
		return r;
    }
    //로그인 메소드
    public byte login(String id, String pw) {
    	byte r = -1;
    	Connection conn = null;
        PreparedStatement pstmt = null;
        PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		String sql=null, sql2=null;
		try {
			conn=getConnection();
			sql="SELECT * FROM users WHERE user_id=? AND password=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				boolean is_available=rs.getBoolean("is_available");
				Timestamp today = new Timestamp(System.currentTimeMillis());
				Timestamp date_punishment=getPunishmentDate(id);
					if(is_available) { //정지를 안당했니?
						r=0;
					}else if(!is_available && today.after(date_punishment)) { //정지를 당했는데 오늘 풀리는 날이니?
						sql2="UPDATE users SET is_available=1, date_punishment=CURRENT_TIMESTAMP WHERE user_id="+"'"+id+"'";
						pstmt2=conn.prepareStatement(sql2);
						pstmt2.executeUpdate(sql2);
						r=0;
					}
			}else {
				r=1; //로그인 실패!
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(pstmt, conn, rs);
			if(pstmt2!=null) try {pstmt2.close();}catch(Exception ex) {ex.printStackTrace();}
		}
    	return r;
    }
    public Timestamp getPunishmentDate(String id) {
    	Connection conn = null;
        PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql=null;
		Timestamp r=null;
		try {
			conn=getConnection();
			sql="SELECT date_punishment FROM users WHERE user_id=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				r = rs.getTimestamp("date_punishment");
			}
    }catch(Exception e) {
    	e.printStackTrace();
    }finally {
		closeConnection(pstmt, conn, rs);
    }
		return r;
    }
}