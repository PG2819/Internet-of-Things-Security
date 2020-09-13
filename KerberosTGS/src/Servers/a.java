package Servers;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class a {
    static Statement stmt = null;
    static ResultSet rs = null;
    static Connection conn = null;
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://192.168.43.133:3306/test?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC","root","123456");
            System.out.println("database has connected");
        } catch (Exception e) {
            System.err.println("database has NOT connected " + e.getMessage());
        }
    }
}