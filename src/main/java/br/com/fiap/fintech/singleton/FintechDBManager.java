package br.com.fiap.fintech.singleton;

import java.sql.Connection;
import java.sql.DriverManager;

public class FintechDBManager {

	private static FintechDBManager connectionManager;

	private FintechDBManager() {
			
	}

	public static FintechDBManager getInstance() {
		if (connectionManager == null) {
			
			connectionManager = new FintechDBManager();
		}
		
		return connectionManager;
	}

	public Connection getConnection() {
		
		Connection connection = null;
		
		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

			connection = DriverManager.getConnection("jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL", "rm96103", "080602");
			
		} catch (Exception e) {
				
			e.printStackTrace();
		}
		
		return connection;
	}
}
