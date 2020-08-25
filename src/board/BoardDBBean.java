package board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class BoardDBBean {
	/*
	private int bbsID; 게시글 번호
	private String bbsTitle; 제목
	private String userID; 작성자 ID
	private String bbsDate; 작성 날짜
	private String bbsContent; 내용
	private String bbsAvailable; 삭제여부
	*/
	private static BoardDBBean instance = new BoardDBBean();
	private BoardDBBean() {}
	public static BoardDBBean getInstance() {
		return instance;
	}
	private Connection getConnection() throws Exception {
	    	Context initCtx = new InitialContext();
	    	Context envCtx = (Context) initCtx.lookup("java:comp/env");
	    	DataSource ds = (DataSource)envCtx.lookup("jdbc/db");
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
	//현재날짜 구하기 - CURRENT_TIMESTAMP를 쓰는게 낫지않나?
	public String getDate() {
    	Connection conn=null;
    	PreparedStatement pstmt=null;
    	ResultSet rs = null;
		String SQL = "select now()";
		try {
			conn=getConnection();
			pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getString(1);
			}
		} catch (Exception e){
			e.printStackTrace();
		} finally{
			closeConnection(pstmt, conn, rs);
		}
		return "";
	}
	//새로 입력될 게시글의 인덱스 구하는 메소드
	public int getNext(String board) {
    	Connection conn=null;
    	PreparedStatement pstmt=null;
    	ResultSet rs = null;
		String SQL = String.format("select MAX(bbsID) from board_%s", board);
		try {
			conn=getConnection();
			pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1) + 1;
			}
			return 1;
		} catch (Exception e){
			e.printStackTrace();
		}finally{
			closeConnection(pstmt, conn, rs);
		}
		return -1;
	}
	//누가봐도 게시글을 작성하는 메소드
	public int write(String board, String bbsTitle, String userID, String bbsContent) {
    	Connection conn=null;
    	PreparedStatement pstmt=null;
    	ResultSet rs = null;
		String SQL = String.format("insert into board_%s values (?,?,?,CURRENT_TIMESTAMP,?,?)", board);
		try {
			conn=getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, getNext(board));
			pstmt.setString(2, bbsTitle);
			pstmt.setString(3, userID);
			pstmt.setString(4, bbsContent);
			pstmt.setInt(5, 1);
			return pstmt.executeUpdate();
		} catch (Exception e){
			e.printStackTrace();
		}finally{
			closeConnection(pstmt, conn, rs);
		}
		return -1;		
	}
	//처음 10개의 게시글 목록을 구하는 메소드
	public ArrayList<Bbs> getList(String board, int pageNumber) {
    	Connection conn=null;
    	PreparedStatement pstmt=null;
    	ResultSet rs = null;
		String SQL = String.format("SELECT * FROM board_%s WHERE bbsID <? and bbsAvailable =1 order by bbsID desc limit 10",board);
		ArrayList<Bbs> list = new ArrayList<Bbs>();
		try {
			conn=getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, getNext(board) - (pageNumber - 1) * 10);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Bbs bbs = new Bbs();
				bbs.setBbsID(rs.getInt(1));
				bbs.setBbsTitle(rs.getString(2));
				bbs.setUserID(rs.getString(3));
				bbs.setBbsDate(rs.getTimestamp(4));
				bbs.setBbsContent(rs.getString(5));
				bbs.setBbsAvailable(rs.getString(6));
				list.add(bbs);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeConnection(pstmt, conn, rs);
		}
		return list;
	}
	//다음 페이지가 있는지 확인하는 메소드
	public boolean nextPage (String board, int pageNumber) {
    	Connection conn=null;
    	PreparedStatement pstmt=null;
    	ResultSet rs = null;
		String SQL = String.format("SELECT * FROM board_%s WHERE bbsID <? and bbsAvailable =1", board);
		try {
			conn=getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, getNext(board) - (pageNumber - 1) * 10);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeConnection(pstmt, conn, rs);
		}
		return false;	
	}
	//게시글을 보는 메소드
	public Bbs getBbs(String board, int bbsID){
    	Connection conn=null;
    	PreparedStatement pstmt=null;
    	ResultSet rs = null;
		String SQL = String.format("SELECT * FROM board_%s WHERE bbsID = ?",board);
		try {
			conn=getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, bbsID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				Bbs bbs = new Bbs();
				bbs.setBbsID(rs.getInt(1));
				bbs.setBbsTitle(rs.getString(2));
				bbs.setUserID(rs.getString(3));
				bbs.setBbsDate(rs.getTimestamp(4));
				bbs.setBbsContent(rs.getString(5));
				bbs.setBbsAvailable(rs.getString(6));
				return bbs;		
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeConnection(pstmt, conn, rs);
		}
		return null;	
	}
	//게시글을 수정하는 메소드
	public int update(String board, int bbsID, String bbsTitle, String bbsContent) {
		Connection conn=null;
    	PreparedStatement pstmt=null;
    	ResultSet rs = null;
		String SQL = "UPDATE BBS SET bbsTitle=?, bbsContent=? WHERE bbsID=?";
		try {
			conn=getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, bbsTitle);
			pstmt.setString(2, bbsContent);
			pstmt.setInt(3, bbsID);
			return pstmt.executeUpdate();
		} catch (Exception e){
			e.printStackTrace();
		}finally{
			closeConnection(pstmt, conn, rs);
		}
		return -1;//데이터 베이스 오류
	}
	//게시글을 삭제하는 메소드
	public int delete(String board, int bbsID) {
		Connection conn=null;
    	PreparedStatement pstmt=null;
    	ResultSet rs = null;
		String SQL = "update bbs set bbsavAilable = 0 where bbsID=?";
		try {
			conn=getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, board);
			pstmt.setInt(2, bbsID);
			return pstmt.executeUpdate();
		} catch (Exception e){
			e.printStackTrace();
		}finally{
			closeConnection(pstmt, conn, rs);
		}
		return -1;//데이터 베이스 오류
	}
}
