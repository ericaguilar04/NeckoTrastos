package com.nt.NekoTrastos.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
public class TrastoDAO {
	
	
	/*** VARIABLES GLOBALS ***/
	private  Connection connection;
	private  PreparedStatement ps;
	private ResultSet rs;
	
	

	private Connection getConnection() throws SQLException {
		Connection conn;
		ConnectionDB connDB = new ConnectionDB();
		conn = connDB.getConnection();
		return conn;
	}
	

	
	
	/**
	 * Obté tots els empleats de la base de dades
	 * @throws SQLException 
	 */
	public ArrayList<TrastoVO> obtenirTotsTrastos() throws SQLException{
		
		TrastoVO trastoVOAux;
		ArrayList<TrastoVO> TrastoList ;
		
		try {
			connection = getConnection();
			System.out.println("Dins");
			ps = connection.prepareStatement("SELECT * FROM Trastos");
			rs = ps.executeQuery();
			TrastoList = new ArrayList<TrastoVO>();
			while (rs.next()) {
				trastoVOAux = new TrastoVO();
				trastoVOAux.setID_Producto(Integer.parseInt(rs.getString(1)));
				trastoVOAux.setNombreTrasto(rs.getString(2));
				trastoVOAux.setDescripcion(rs.getString(3));
				trastoVOAux.setPrecio(Float.parseFloat(rs.getString(4)));
				trastoVOAux.setID_Propietario(rs.getString(5));
				
				TrastoList.add(trastoVOAux);
			}
			return TrastoList;
		}
		catch(SQLException e) {
			System.err.println(e.getMessage());
			throw e;
		}
		finally {
			try {
				if(rs!= null) rs.close();
				if(ps!=null) ps.close();
				if(connection!=null) connection.close();
			}catch(SQLException e) {
				System.err.println(e.getMessage());
			}catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
		
	}
	
	public  void deleteTrasto(int nEmpno) throws SQLException {
		try {
			connection = getConnection();
			System.out.println("Dins deleteEMP");
			ps = connection.prepareStatement("DELETE FROM Trastos WHERE ID_Producto = ?");
			ps.setInt(1, nEmpno);
			
			ps.executeUpdate();
		}
		catch(SQLException e) {
			System.err.println("deleteEMP" + e.getMessage());
			throw e;
		}
		finally {
			try {
				if(ps!=null) ps.close();
				if(connection!=null) connection.close();
			}catch(SQLException e) {
				System.err.println("deleteEMP" + e.getMessage());
			}catch (Exception e) {
				System.err.println("deleteEMP" + e.getMessage());
			}
		}
	}
}
	
	
