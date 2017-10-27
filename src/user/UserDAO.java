package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public UserDAO() {
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
	
	public int login(String userID, String userPassword) {
		String query = "SELECT userPassword FROM USER1 WHERE userID = ?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString(1).equals(userPassword)) {
					return 1;	// 로그인 성공
				}else 
					return 0;   // 비밀번호 불일치
			} 
			return -1; // 아이디가 없음 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		return -2;  // 데이터 베이스 오류
	}
	
	public int join(User user) {
		String query = "INSERT INTO USER1 VALUES(?,?,?,?,?)";
		try {
			pstmt  = con.prepareStatement(query);
			pstmt.setString(1, user.getUserID());
			pstmt.setString(2, user.getUserPassword());
			pstmt.setString(3, user.getUserName());
			pstmt.setString(4, user.getUserGender());
			pstmt.setString(5, user.getUserEmail());
			
			return pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return -1; //데이터 베이스 오류
	}
}
