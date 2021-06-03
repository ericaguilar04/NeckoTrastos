package com.nt.nekotrastos.model;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.nt.nekotrastos.util.ConnectionDB;

public class TrastoDAO {
	
	
	/*** VARIABLES GLOBALS ***/
	private  Connection connection;
	private  PreparedStatement ps;
	private ResultSet rs;
	private TrastoDAO updater;
	
	

	private Connection getConnection() throws SQLException  {
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
		ArrayList<TrastoVO> trastoList ;
		
		try {
			connection = getConnection();
			System.out.println("Dins");
			ps = connection.prepareStatement("SELECT * FROM Trastos");
			rs = ps.executeQuery();
			trastoList = new ArrayList<TrastoVO>();
			while (rs.next()) {
				trastoVOAux = new TrastoVO();
				trastoVOAux.setID_Producto(Integer.parseInt(rs.getString(1)));
				trastoVOAux.setNombreTrasto(rs.getString(2));
				trastoVOAux.setDescripcion(rs.getString(3));
				trastoVOAux.setPrecio(Float.parseFloat(rs.getString(4)));
				trastoVOAux.setID_Propietario(rs.getString(5));
				
				trastoList.add(trastoVOAux);
			}
			return trastoList;
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
	
	public void insertTrasto(TrastoVO trasto) {
		try {
			connection = getConnection();
			PreparedStatement statementTrasto = connection.prepareStatement("INSERT INTO "
					+ "Trasto(NombreTrasto, Descripción, Precio, ID_Propietario) "
					+ "VALUES(?, ?, ?, ?)");
			
			statementTrasto.setString(1, trasto.getNombreTrasto());
			statementTrasto.setString(2, trasto.getDescripcion());
			statementTrasto.setFloat(3, trasto.getPrecio());
			statementTrasto.setString(4, trasto.getID_Propietario());
			statementTrasto.executeUpdate();
			updater = new TrastoDAO();
			updater.obtenirTotsTrastos();
		} catch (SQLException e) {
		} finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
				if(connection != null) connection.close();
			} catch (SQLException e) {
				System.out.println("SQLException ERROR");
			} 
			catch (Exception e) {
				System.out.println("ERROR");
			} 
		}
	}
	
	public  void deleteTrasto(int id_Producto) throws SQLException {
		try {
			connection = getConnection();
			System.out.println("Dins deleteTrasto");
			ps = connection.prepareStatement("DELETE FROM Trastos WHERE ID_Producto = ?");
			ps.setInt(1, id_Producto);
			
			ps.executeUpdate();
		}
		catch(SQLException e) {
			System.err.println("delete Trasto" + e.getMessage());
			throw e;
		}
		finally {
			try {
				if(ps!=null) ps.close();
				if(connection!=null) connection.close();
			}catch(SQLException e) {
				System.err.println("delete Trasto" + e.getMessage());
			}catch (Exception e) {
				System.err.println("delete Trasto" + e.getMessage());
			}
		}
	}
	
	public void editarTrasto(String nombreTrasto, String descripcion, float precio, int id_Producto) throws SQLException {
		try {
			connection = getConnection();
			System.out.println("Dins editar Trasto");
			ps = connection.prepareStatement("UPDATE Trastos SET NombreTrasto = ?, Descripción = ?, Precio = ? WHERE ID_Producto = ?");
			ps.setString(1, nombreTrasto);
			ps.setString(2, descripcion);
			ps.setFloat(3, precio);
			ps.setInt(4, id_Producto);
			
			ps.executeUpdate();
		}
		catch(SQLException e) {
			System.err.println("editar Trasto" + e.getMessage());
			throw e;
		}
		finally {
			try {
				if(ps!=null) ps.close();
				if(connection!=null) connection.close();
			}catch(SQLException e) {
				System.err.println("editar Trasto" + e.getMessage());
			}catch (Exception e) {
				System.err.println("editar Trasto" + e.getMessage());
			}
		}
	}
	
	
	public ArrayList<TrastoVO> buscarTrasto(String nombreTrasto) throws SQLException {
		TrastoVO trastoVOAux;
		ArrayList<TrastoVO> trastoList ;
		
		try {
			connection = getConnection();
			System.out.println("Dins deleteEMP");
			ps = connection.prepareStatement("SELECT * FROM Trastos WHERE NombreTrasto LIKE ?");
			ps.setString(1, "%"+nombreTrasto+"%");
			ResultSet rs = ps.executeQuery();
			trastoList = new ArrayList<TrastoVO>();
			while (rs.next()) {
				trastoVOAux = new TrastoVO();
				trastoVOAux.setID_Producto(Integer.parseInt(rs.getString(1)));
				trastoVOAux.setNombreTrasto(rs.getString(2));
				trastoVOAux.setDescripcion(rs.getString(3));
				trastoVOAux.setPrecio(Float.parseFloat(rs.getString(4)));
				trastoVOAux.setID_Propietario(rs.getString(5));
				
				trastoList.add(trastoVOAux);
			}
			return trastoList;
		}
		catch(SQLException e) {
			System.err.println("BusquedaTrasto" + e.getMessage());
			throw e;
		}
		finally {
			try {
				if(ps!=null) ps.close();
				if(connection!=null) connection.close();
			}catch(SQLException e) {
				System.err.println("BusquedaTrasto" + e.getMessage());
			}catch (Exception e) {
				System.err.println("BusquedaTrasto" + e.getMessage());
			}
		}
	}
	
	public ArrayList<TrastoVO> obtenerTrastosDeMisTrastos(String id_Propietario) throws SQLException {
		TrastoVO trastoVOAux;
		ArrayList<TrastoVO> trastoList ;
		
		try {
			connection = getConnection();
			System.out.println("Dins");
			ps = connection.prepareStatement("SELECT * FROM Trastos WHERE ID_Propietario LIKE ?");
			ps.setString(1, "%"+id_Propietario+"%");
			rs = ps.executeQuery();
			trastoList = new ArrayList<TrastoVO>();
			while (rs.next()) {
				trastoVOAux = new TrastoVO();
				trastoVOAux.setID_Producto(Integer.parseInt(rs.getString(1)));
				trastoVOAux.setNombreTrasto(rs.getString(2));
				trastoVOAux.setDescripcion(rs.getString(3));
				trastoVOAux.setPrecio(Float.parseFloat(rs.getString(4)));
				trastoVOAux.setID_Propietario(rs.getString(5));
				
				trastoList.add(trastoVOAux);
			}
			return trastoList;
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
}