package com.nt.NekoTrastos.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.nt.NekoTrastos.util.ConnectionDB;

public class UsuarioDAO {
	
	
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
	public ArrayList<UsuarioVO> obtenirTotsUsuarios() throws SQLException{
		
		UsuarioVO usuarioVOAux;
		ArrayList<UsuarioVO> usuarioList ;
		
		try {
			connection = getConnection();
			System.out.println("Dins");
			ps = connection.prepareStatement("SELECT * FROM Usuario");
			rs = ps.executeQuery();
			usuarioList = new ArrayList<UsuarioVO>();
			while (rs.next()) {
				usuarioVOAux = new UsuarioVO();
				usuarioVOAux.setId_Usuario(rs.getString(1));
				usuarioVOAux.setNombre(rs.getString(2));
				usuarioVOAux.setApellido(rs.getString(3));
				usuarioVOAux.setContrasenya(rs.getString(4));
				usuarioVOAux.setTelefono(Integer.parseInt(rs.getString(5)));
				usuarioVOAux.setCorreo(rs.getString(6));
				
				usuarioList.add(usuarioVOAux);
			}
			return usuarioList;
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
	
	public  void deleteUsuario(String id_Usuario) throws SQLException {
		try {
			connection = getConnection();
			System.out.println("Dins deleteUsuario");
			ps = connection.prepareStatement("DELETE FROM Usuario WHERE ID_Producto = ?");
			ps.setString(1, id_Usuario);
			
			ps.executeUpdate();
		}
		catch(SQLException e) {
			System.err.println("deleteUsuario" + e.getMessage());
			throw e;
		}
		finally {
			try {
				if(ps!=null) ps.close();
				if(connection!=null) connection.close();
			}catch(SQLException e) {
				System.err.println("deleteUsuario" + e.getMessage());
			}catch (Exception e) {
				System.err.println("deleteUsuario" + e.getMessage());
			}
		}
	}
	
	public boolean comprovacionUsuario(String id_Usuario, String contraseña) throws SQLException {
		UsuarioVO usuarioVOAux;
		ArrayList<UsuarioVO> usuarioList;
		
		
		try {
			connection = getConnection();
			System.out.println("Dins buscarUsuario");
			// Cuidao canviar
			ps = connection.prepareStatement("SELECT * FROM Usuario WHERE ID_Usuario = ? AND Contraseña = ?");
			ps.setString(1, id_Usuario);
			ps.setString(2, contraseña);
			ResultSet rs = ps.executeQuery();
			
			//usuarioList = new ArrayList<UsuarioVO>();
			//System.out.println("El usuario es :" + rs.next());
			return rs.next();
		
		}
		catch(SQLException e) {
			System.err.println("Busqueda" + e.getMessage());
			throw e;
		}
		finally {
			try {
				if(ps!=null) ps.close();
				if(connection!=null) connection.close();
			}catch(SQLException e) {
				System.err.println("BusquedaUsuario" + e.getMessage());
			}catch (Exception e) {
				System.err.println("BusquedaUsuario" + e.getMessage());
			}
		}
	}
}
