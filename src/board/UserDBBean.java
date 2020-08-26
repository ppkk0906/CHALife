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

import secure.BCrypt;


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
    //현재 회원수+1를 구하는 메소드
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
    public int join(UserDataBean u) {
        Connection conn = null;
        PreparedStatement pstmt = null;
		ResultSet rs = null;
		int r=0;
		String sql=null;
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		try {
			conn=getConnection();
			String orgPass=u.getPassword();
			String shaPass=BCrypt.hashpw(orgPass,BCrypt.gensalt()); //암호화
			u.setTime_reg(timestamp);
			u.setUser_index(getUserMax());
			sql = "INSERT INTO users VALUES(?,?,?,?,?,?,CURRENT_TIMESTAMP,0,CURRENT_TIMESTAMP,'신입')";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, getUserMax());
			pstmt.setString(2, u.getUser_id());
			pstmt.setString(3, shaPass);
			pstmt.setString(4, u.getReal_name());
			pstmt.setString(5, u.getNick_name());
			pstmt.setString(6, u.getEmail());
			r=pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(pstmt, conn, rs);
		}
		return r;
    }
    //로그인 메소드 0:성공 1:ID,PW 불일치 2: 정지 3: 가입 미승인
    public int login(String id, String pw) {
    	int r = 0;
    	Connection conn = null;
        PreparedStatement pstmt = null;
        PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		String sql=null, sql2=null;
		try {
			conn=getConnection();
			sql="SELECT * FROM users WHERE user_id=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			if(rs.next()) {//아이디가 있다면?
				boolean is_available=rs.getBoolean("is_available");
				Timestamp today = new Timestamp(System.currentTimeMillis());
				Timestamp date_punishment=getPunishmentDate(id);
				if(BCrypt.checkpw(pw, rs.getString("password"))) {//비밀번호가 맞다면?
					if(!is_available) { //가입승인이 안났니?
						r=3;
					}else if(is_available && today.after(date_punishment)) {
						//정지를 당했는데 오늘 풀리는 날이니?
						sql2=String.format("UPDATE users SET is_available=1, date_punishment=CURRENT_TIMESTAMP WHERE user_id='%s'", id);
						pstmt2=conn.prepareStatement(sql2);
						pstmt2.executeUpdate(sql2);
						r=0;
					}else{ 
						//정지를 당했는데 풀리는 날짜는 아니니?
						r=2;
						System.out.println("정지 유저 로그인 시도");
					}
				}else {
					r=1;//비밀번호가 틀렸다면
					System.out.println("비밀번호 불일치");
				}
			}else {
				r=1;//존재하지 않는 아이디
				System.out.println("존재하지 않는 아이디");
			}	
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(pstmt, conn, rs);
			if(pstmt2!=null) try {pstmt2.close();}catch(Exception ex) {ex.printStackTrace();}
		}
    	return r;
    }
    //회원의 정지 날짜를 구하는 메소드
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
    //회원정보를 수정하는 메소드 0=실패 1=성공
    public int updateUser(String id, String pw, String nick, String email) {
    	int r=-1;
        Connection conn = null;
        PreparedStatement pstmt = null, pstmt2 = null;
		ResultSet rs = null;
		String sql=null;
		try {
			conn=getConnection();
			if(pw==null || pw.equals("")) {
				sql="UPDATE users SET nick_name=?, email=? WHERE user_id=?";
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, nick);
				pstmt.setString(2, email);
				pstmt.setString(3, id);
				r=pstmt.executeUpdate();
			}else {
				sql="UPDATE users SET password=?, nick_name=?, email=? WHERE user_id=?";
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, BCrypt.hashpw(pw, BCrypt.gensalt()));
				pstmt.setString(2, nick);
				pstmt.setString(3, email);
				pstmt.setString(4, id);
				r=pstmt.executeUpdate();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(pstmt, conn, rs);
			if(pstmt2!=null) try {pstmt2.close();}catch(Exception ex) {ex.printStackTrace();}
		}
    	return r;
    }
    // 회원정보 수정에서 회원정보를 얻어오는 메소드
    public UserDataBean getUser(String id) {
    	UserDataBean u = null;
    	String sql = null;
    	Connection conn = null;
        PreparedStatement pstmt = null, pstmt2 = null;
 		ResultSet rs = null;
 		try {
			conn=getConnection();
			sql="SELECT * FROM users WHERE user_id=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
		    	u = new UserDataBean();
				u.setUser_id(rs.getString(2));
				u.setReal_name(rs.getString(4));
				u.setNick_name(rs.getString(5));
				u.setEmail(rs.getString(6));
			}
 		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(pstmt, conn, rs);
			if(pstmt2!=null) try {pstmt2.close();}catch(Exception ex) {ex.printStackTrace();}
		}
    	return u;
    }
    //회원정보를 말소하는 메소드  -1=로그인 실패 0=실패 1=성공
    public int deleteUser(String userID, String userPW) {
    	int r=0;
    	int login=9999;
    	login=login(userID, userPW);
    	Connection conn = null;
        PreparedStatement pstmt = null;
 		ResultSet rs = null;
 		String sql=null;
    	if(login==0) {
    		try {
    			conn=getConnection();
    			sql="UPDATE users SET reason_banned='직접 탈퇴', password=? WHERE user_id=?";
				pstmt=conn.prepareStatement(sql);
				pstmt.setNString(1, BCrypt.hashpw("pvO6Ohnv34jzK4vmrZg8uXFJxTuvMcuXE3zC7PHZT8d2wijwq6W", BCrypt.gensalt()));
				pstmt.setString(2, userID);
				r=pstmt.executeUpdate();
    		}catch(Exception e) {
    			e.printStackTrace();
    		}finally {
    			closeConnection(pstmt, conn, rs);
    		}
    	}else {
    		r=-1;
    		System.out.println("탈퇴 처리 실패-아이디나 비번이 맞지 않음");
    	}
    	return r;
    }
}