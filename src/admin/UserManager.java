package admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class UserManager {
	private static UserManager instance = new UserManager();
	private UserManager() {}
	public static UserManager getInstance() {
		return instance;
	}
    private Connection getConnection() throws Exception {
    	Context initCtx = new InitialContext();
    	Context envCtx = (Context) initCtx.lookup("java:comp/env");
    	DataSource ds = (DataSource)envCtx.lookup("jdbc/dba");
      return ds.getConnection();
    }
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
    //모든 유저를 부르는 메소드
    public Map<Integer, UserDataBean> getAllUser(){
    	Map<Integer, UserDataBean> output = new HashMap<Integer,UserDataBean>();
    	Connection conn=null;
    	PreparedStatement pstmt=null;
    	ResultSet rs = null;
    	try {
    		conn=getConnection();
    		String sql="SELECT * FROM users";
    		pstmt=conn.prepareStatement(sql);
    		rs=pstmt.executeQuery();
    		while(rs.next()) {
			UserDataBean u = new UserDataBean();
			u.setUser_id(rs.getString(2)); //user_id
			u.setReal_name(rs.getString(4)); //real_name
			u.setNick_name(rs.getString(5)); //nick_name
			u.setEmail(rs.getString(6)); //email
			u.setTime_reg(rs.getTimestamp(7)); //reg_date: 가입일
			u.setIs_available(rs.getBoolean(8));//승인여부
			u.setDate_punishment(rs.getTimestamp(9)); //제명이 풀리는 날짜
			u.setReason_banned(rs.getString(10)); //제명 사유
			output.put(rs.getInt(1), u);
		}
    	}catch(Exception e) {
    		e.printStackTrace();
    	}finally {
		closeConnection(pstmt, conn, rs);
    	}
    	return output;
    }
    //승인되지 않은 유저를 부르는 메소드
    public Map<Integer, UserDataBean> getUnAcceptedUser(){
    	Map<Integer, UserDataBean> output = new HashMap<Integer,UserDataBean>();
    	Connection conn=null;
    	PreparedStatement pstmt=null;
    	ResultSet rs = null;
    	try {
    		conn=getConnection();
    		String sql="SELECT * FROM users WHERE is_available=0";
    		pstmt=conn.prepareStatement(sql);
    		rs=pstmt.executeQuery();
    		while(rs.next()) {
    			UserDataBean u = new UserDataBean();
    			u.setUser_id(rs.getString(2)); //user_id
    			u.setReal_name(rs.getString(4)); //real_name
    			u.setNick_name(rs.getString(5)); //nick_name
    			u.setEmail(rs.getString(6)); //email
    			u.setTime_reg(rs.getTimestamp(7)); //reg_date: 가입일
    			u.setIs_available(rs.getBoolean(8));//승인여부-당연히 false겠지
    			u.setDate_punishment(rs.getTimestamp(9)); //제명이 풀리는 날짜
    			u.setReason_banned(rs.getString(10)); //제명 사유
    			output.put(rs.getInt(1), u);
    		}
    	}catch(Exception e) {
    		e.printStackTrace();
    	}finally {
    		closeConnection(pstmt, conn, rs);
    	}
    	return output;
    }
    //선택한 유저를 승인하는 메소드
    public int acceptUser(String[] users) {
    	Connection conn=null;
    	PreparedStatement pstmt=null;
    	ResultSet rs = null;
    	String sql = "UPDATE users SET is_available=1 WHERE ";
    	int r = 0;
    	int ulen=users.length;
    	//받아온 배열을 정수로 변환 및 sql구문 실행
    	//ArrayList<Integer> arraylist = new ArrayList<Integer>();
    	for (int i=0; i<ulen; i++){
    		//arraylist.add(Integer.parseInt(users[i]));
    		sql+= "user_index="+users[i];
    		if(i+1<ulen) {
    			sql+=" OR ";
    		}
    	}
    	try {
    		conn=getConnection();
    		pstmt=conn.prepareStatement(sql);
    		pstmt.executeUpdate();
    	}catch(Exception e) {
    		e.printStackTrace();
    		r=1;
    	}finally {
    		closeConnection(pstmt, conn, rs);
    	}
    	return r;
    }
    public int manageUser(String command, int time, String reason, String[] users) {
    	int r=0;
    	Connection conn=null;
    	PreparedStatement pstmt=null;
    	ResultSet rs = null;
    	String sql=null;
    	int userlen=users.length;
    	time *=1000;
    	long longtime = (long)time;
    	Timestamp timestamp = new Timestamp(System.currentTimeMillis()+longtime);
    	if(command.equals("ban")) {
    		try {
    			sql="UPDATE users SET date_punishment=?, reason_banned=? WHERE ";
    			for(int i =0; i<userlen; i++) {
    				sql+="user_index="+users[i];
    				if(i+1<userlen) {
    					sql+=" OR ";
    				}
    			}
    			conn=getConnection();
    			pstmt=conn.prepareStatement(sql);
    			
    			pstmt.setTimestamp(1, timestamp);
    			pstmt.setString(2, reason);
    			System.out.println(sql);
    			pstmt.executeUpdate();
    		}catch(Exception e) {
    			e.printStackTrace();
    			
    			r=1;
    		}finally {
    			closeConnection(pstmt, conn, rs);
    		}
    	}else if(command.equals("unban")){
    		try {
    			sql="UPDATE users SET date_punishment=CURRENT_TIMESTAMP, reason_banned=? WHERE ";
    			for(int i =0; i<userlen; i++) {
    				sql+="user_index="+users[i];
    				if(i+1<userlen) {
    					sql+=" OR ";
    				}
    			}
    			conn=getConnection();
    			pstmt=conn.prepareStatement(sql);
    			pstmt.setNString(1, reason);
    			pstmt.executeUpdate();
    		}catch(Exception e) {
    			e.printStackTrace();
    			r=1;
    		}finally {
    			closeConnection(pstmt, conn, rs);
    		}
    	}
    	return r;
    }
}

