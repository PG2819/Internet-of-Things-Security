package DBManger;

import java.sql.*;

public class DataConn {

	static Statement stmt = null;
	static ResultSet rs = null;
	static Connection conn = null;
	//static String ip = "115.28.31.28:3306/test";

	static void OpenConn() {
		stmt = null;
		rs = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://192.168.43.40:3306/test?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC","root","123456");
			System.out.println("Database has connected");
			//conn = DriverManager.getConnection(url);
		} catch (Exception e) {
			System.err.println("Database has NOT connected" + e.getMessage());
		}
	}

	public static ResultSet executeQuery(String sql) {
		stmt = null;
		rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			System.err.println("select data " + e.getMessage());
		}
		return rs;
	}

	public static int executeUpdate(String sql) {
		stmt = null;
		rs = null;
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			stmt.executeUpdate(sql);
			// conn.commit();
		} catch (SQLException e) {
			System.err.println("update data " + e.getMessage());
			return 0;
		}
		return 1;
	}

	static void CloseConn() {
		try {
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			System.err.println("release connect");
		}
	}
}
