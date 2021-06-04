package com.nt.nekotrastos.util;
import java.sql.*;
import java.sql.SQLException;

public class ConnectionDB {
	/*** VARIABLES GLOBALES ***/
	private String url = "jdbc:mysql://localhost:3306/NekoTrastos";
	private String user = "usuario";
	private String passwd = "password";
	
	
	/**
	 * Crea la connexión a la base de datos especifica con la variables globales
	 * @return
	 * @throws SQLException
	 */
	public Connection getConnection() throws SQLException {
		Connection comm = null;
		comm = DriverManager.getConnection(url, user, passwd);
		return comm;
	}
}
