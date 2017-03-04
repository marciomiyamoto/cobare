package cobare.core.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoOracle {
	public static final String url = "jdbc:oracle:thin:@localhost:1521:xe";
	public static final String usuario = "system";
	public static final String senha = "cobare";
	
	public static Connection getConnection() {
		
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, usuario, senha);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
