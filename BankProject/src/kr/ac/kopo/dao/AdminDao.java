package kr.ac.kopo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import kr.ac.kopo.util.ConnectionFactory;
import kr.ac.kopo.vo.Admin;

public class AdminDao {

	public Admin loginAdmin(String id, String password) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		Admin adminResult = null;
		try {
			conn = new ConnectionFactory().getConnection();
			
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM ADMIN WHERE ADMIN_ID = ? AND ADMIN_PWD = ?");
			
			pstmt = conn.prepareStatement(sql.toString());	
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {				
				
				int adminPk = rs.getInt("ADMIN_PK");
				String adminId = rs.getString("ADMIN_ID");
	            String adminPwd = rs.getString("ADMIN_PWD");
	            String adminInputDate = rs.getString("ADMIN_INPUT_DATE");
	            String adminLevel = rs.getString("ADMIN_LEVEL");
	            
	            adminResult = new Admin(adminPk,adminId, adminPwd,adminInputDate,adminLevel);
	            adminResult.setResultCodeOne();
			}	else {
				adminResult.setResultCodeMinus();
			}		
			
		}catch (Exception e){
			adminResult.setResultCodeZero();
		}			
			return adminResult;
	}

	
	

}
