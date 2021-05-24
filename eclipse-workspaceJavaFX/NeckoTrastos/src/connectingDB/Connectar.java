package connectingDB;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
import java.sql.*;
import javax.sql.*;



public class Connectar {
	public static void main(String[] args) throws ClassNotFoundException{
		//Class.forName("com.mysql.jdbc.Driver"); 
		String url = "jdbc:mariadb://localhost:3306/NekoTrastos";
		String user = "usuario";
		String passwd = "password";
		try (Connection con =
        DriverManager.getConnection(url, user, passwd);){
			System.out.println("Connexió exitosa a la base de dades!");
		} catch (SQLException e) {
			System.out.println("Error d'establiment de connexió: " + e.getMessage());
		}
	}
}

