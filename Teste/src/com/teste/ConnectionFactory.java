package com.teste;

import java.sql.Connection;
import java.sql.Statement;

public class ConnectionFactory {
	
	private static final String USUARIO = "system";
	private static final String SENHA= "cobare";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";

	public static int createTable() throws Exception {
		
		Database dataBase = new Database();
		Connection conn = dataBase.getConnectio(URL, USUARIO, SENHA);
		
		StringBuilder sql = new StringBuilder();
		sql.append("CREATE TABLE Cliente (");
		sql.append("id NUMBER(9) PRIMARY KEY,");
		sql.append("dtInsercao DATE,");
		sql.append("nome VARCHAR(50),");
		sql.append("telefone VARCHAR(20),");
		sql.append("email VARCHAR(30))");
		
		Statement statement = conn.createStatement();
		int result = statement.executeUpdate( sql.toString() );
		
		statement.close();
		conn.close();
		
		return result;
	}
	
	public static void main(String[] args) throws Exception {
		
		System.out.println(createTable());
			
	}
	

}
