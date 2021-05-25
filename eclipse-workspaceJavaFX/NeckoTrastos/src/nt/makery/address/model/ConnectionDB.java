package nt.makery.address.model;
import java.sql.*;
import java.sql.SQLException;

public class ConnectionDB {
	/*** ATRIBUTES ***/
	private String url = "jdbc:mysql://localhost:3306/companyCSV";
	private String user = "usuario";
	private String passwd = "password";
	
	
	
	public Connection getConnection() throws SQLException {
		Connection comm = null;
		comm = DriverManager.getConnection(url, user, passwd);
		return comm;
	}
	
	
	
}
