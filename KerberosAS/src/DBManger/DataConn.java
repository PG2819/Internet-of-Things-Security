package DBManger;
//连接数据库操作
import java.sql.*;

public class DataConn {

	static Statement stmt = null;
	static ResultSet rs = null;
	static Connection conn = null;
	//static String ip = "192.168.43.33:3306/test";

	static void OpenConn() {
		stmt = null;
		rs = null;
		try {
			//Class.forName("com.mysql.jdbc.Driver");
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://192.168.43.40:3306/test?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC","root","123456");
			System.out.println("Database connection successful !");
		} catch (Exception e) {
			System.err.println("Database connection failed !" + e.getMessage());
		}
	}

	public static ResultSet executeQuery(String sql) {
		stmt = null;
		rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			System.err.println("Data query" + e.getMessage());
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
			System.err.println("Update the data" + e.getMessage());
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
			System.err.println("Connection release");
		}
	}
}
