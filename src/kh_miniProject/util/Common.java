package kh_miniProject.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Common {
    final static String ORACLE_URL = "jdbc:oracle:thin:@localhost:1521:xe";
    final static String ORACLE_ID = "PRACTICE";
    final static String ORACLE_PWD = "1234";
    final static String ORACLE_DRV = "oracle.jdbc.driver.OracleDriver";

    public static Connection getConnection() {
        Connection conn = null; // Connection은 반드시 예외처리 해줘야 함
        try {
            Class.forName(ORACLE_DRV); // 오라클 연결하기 위해 드라이브 로딩
            conn = DriverManager.getConnection(ORACLE_URL, ORACLE_ID, ORACLE_PWD); // 자바-오라클 연결 얻기
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
    public static void close(Connection conn) {
        try {
            if(conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Connection 해제 성공");
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public static void close(Statement stmt) {
        try {
            if(stmt != null && !stmt.isClosed()) {
                stmt.close();
                System.out.println("Connection 해제 성공");
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public static void close(ResultSet rSet) {
        try {
            if(rSet != null && !rSet.isClosed()) {
                rSet.close();
                System.out.println("Connection 해제 성공");
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}