package com.teste;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	
	public Connection getConnectio(String URL, String USUARIO, String SENHA) throws SQLException {
		
		Connection conn = DriverManager.getConnection( URL, USUARIO, SENHA );
		return conn;
	}

}
