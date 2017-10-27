package bbs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
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
		return -1; // 데이터 베이스 오류
	}
}
