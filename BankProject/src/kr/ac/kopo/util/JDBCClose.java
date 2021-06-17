package kr.ac.kopo.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCClose {

	public static   void close(Connection conn, Statement pstmt) {
		
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} // IF :: stmt 자원 해제

		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} // IF :: conn 자원 해제
		
	}
}
