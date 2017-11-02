package bbs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BbsDAO {
	private Connection con;
	
	private ResultSet rs;
	
	public BbsDAO() {
		String dbURL = "jdbc:oracle:thin:@localhost:1521:xe";
		String dbID = "Sup";
		String dbPassword = "1";			
			
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(dbURL,dbID,dbPassword);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 				
	}
	
	public String getDate() {
		String query = "select to_char(sysdate,'yyyymmdd hh24:mi:ss') from dual;";
		try {
			PreparedStatement pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public int getNext() {
		String query = "Select bbsID FROM BBS ORDER BY bbsID DESC";
		try {
			PreparedStatement pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1) + 1;
			}
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public int write(String bbsTitle,String userID,String bbsContent) {
		String query = "Insert Into BBs values (?,?,?,?,?,?)";
		try {
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, getNext());
			pstmt.setString(2, bbsTitle);
			pstmt.setString(3, userID);
			pstmt.setString(4, getDate());
			pstmt.setString(5, bbsContent);
			pstmt.setInt(6, 1);			
			return pstmt.executeUpdate();			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // ������ ���̽� ����
	}
	
	public ArrayList<Bbs> getlist(int pageNumber) {
		
		String query = "select * from (select * FROM BBS Where bbsID < ? AND bbsAvailable = 1 ORDER BY bbsID DESC) where rownum <= 10";
		ArrayList<Bbs> list  = new ArrayList<Bbs>();
		try {
			PreparedStatement pstmt = con.prepareStatement(query);
			
			pstmt.setInt(1, getNext() - (pageNumber - 1) * 10);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Bbs bbs = new Bbs();
				bbs.setBbsID(rs.getInt(1));
				bbs.setBbsTitle(rs.getString(2));
				bbs.setBbsUserID(rs.getString(3));
				bbs.setBbsDate(rs.getString(4));
				bbs.setBbsContent(rs.getString(5));
				bbs.setBbsAvailable(rs.getInt(6));				
				list.add(bbs);	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return list;		
	}
	
	public boolean nexPage(int pageNumber) {
		
		String query = "select * from (Select * FROM BBS Where bbsID < ? AND bbsAvailable = 1 ORDER BY bbsID DESC) where rownum <= 10" ;
		try {
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, getNext() - (pageNumber -1 ) * 10);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;		
		
	}
	
	public Bbs getBbs(int bbsID) {
		String query = "select * from BBS where bbsID = ?" ;
		try {
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1,bbsID);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				Bbs bbs = new Bbs();
				bbs.setBbsID(rs.getInt(1));
				bbs.setBbsTitle(rs.getString(2));
				bbs.setBbsUserID(rs.getString(3));
				bbs.setBbsDate(rs.getString(4));
				bbs.setBbsContent(rs.getString(5));
				bbs.setBbsAvailable(rs.getInt(6));				
				return bbs;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;		
		
	}
	
	public int update(int bbsID, String bbsTitle, String bbsContent) {
		String query = "Update BBS SET bbsTitle = ?, bbsContent = ? WHERE bbsID = ?";
		try {
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, bbsTitle);
			pstmt.setString(2, bbsContent);
			pstmt.setInt(3, bbsID);
			return pstmt.executeUpdate();			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // ������ ���̽� ����		
	}
	
	public int delete(int bbsID) {
		String query = "Update BBS set bbsAvailable = 0 where bbsID = ?";
		try {
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, bbsID);			
			return pstmt.executeUpdate();			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // ������ ���̽� ����		
	}
}
