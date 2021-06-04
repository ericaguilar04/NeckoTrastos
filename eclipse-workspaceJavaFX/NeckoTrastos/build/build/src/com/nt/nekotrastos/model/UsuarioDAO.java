package com.nt.nekotrastos.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.nt.nekotrastos.util.ConnectionDB;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
public class UsuarioDAO {
	
	
	/*** VARIABLES GLOBALES ***/
	private  Connection connection;
	private  PreparedStatement ps;
	private ResultSet rs;
	
	
	/**
	 * Carga la connexión a la base de datos especificada en la casse ConnectionDB
	 * @return
	 * @throws SQLException
	 */
	private Connection getConnection() throws SQLException {
		Connection conn;
		ConnectionDB connDB = new ConnectionDB();
		conn = connDB.getConnection();
		return conn;
	}
	
	/**
	 * Carga todos los usuarios de la base de datos
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
	/**
	 * Crea el usuario en la base de datos (Aún no se ha implementado)
	 * @param usuario
	 */
	public void createUser(UsuarioVO usuario) {
        try {
            connection = getConnection();
            PreparedStatement psCreateUser = connection.prepareStatement("INSERT INTO USER(ID_Usuario, Nombre, Apellido, Contraseña, Telefono, Correo)" + " VALUES(?, ?, ?, ?, ?, ?)");
            psCreateUser.setString(1, usuario.getId_Usuario());
            psCreateUser.setString(2, usuario.getNombre());
            psCreateUser.setString(3, usuario.getApellido());
            psCreateUser.setString(4, usuario.getContrasenya());
            psCreateUser.setInt(5, usuario.getTelefono());
            psCreateUser.setString(6, usuario.getCorreo());
            psCreateUser.executeUpdate();
        } catch (SQLException e) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("ERROR!");
            alert.setHeaderText("Problema con la creación de usuario");
            alert.setContentText("Usuario incorrecto");
            alert.showAndWait();
        }
    }
	/**
	 * Borra el usuario de la base de datos
	 * @param id_Usuario
	 * @throws SQLException
	 */
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
	/**
	 * Comprueba si el usuario existe en la base de datos
	 * @param id_Usuario
	 * @param contraseña
	 * @return
	 * @throws SQLException
	 */
	public UsuarioVO comprovacionUsuario(String id_Usuario, String contraseña) throws SQLException {
		System.out.println("iniciando comprovacionUsuario ...");
		boolean bUsuarioValido = false;
		UsuarioVO usuario = null;
		
		try {
			connection = getConnection();
			System.out.println("Dins buscarUsuario con id " + id_Usuario + " contraseña " + contraseña);
			// Cuidao canviar
			ps = connection.prepareStatement("SELECT * FROM Usuario WHERE  ID_Usuario LIKE ? AND Contraseña LIKE ?");
			ps.setString(1, id_Usuario);
			ps.setString(2, contraseña);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				usuario = new UsuarioVO(rs.getString(1), rs.getString(2));
			}
			
			System.out.println("comprovacionUsuario usuario valido " + bUsuarioValido);
			return usuario;
		
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
