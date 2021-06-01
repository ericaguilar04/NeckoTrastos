package com.nt.nekotrastos.model;

public class UsuarioVO {
	/*** ATRIBUTS ***/
	private String id_Usuario;
	private String nombre;
	private String apellido;
	private String contrasenya;
	private int telefono;
	private String correo;

/*** default CONSTRUCTOR ***/
	public UsuarioVO() {
	
	}

	public UsuarioVO(String id_Usuario,String nombre,String apellido,String contrasenya,int telefono, String correo) {
	
		this.id_Usuario = id_Usuario;
		this.nombre = nombre;
		this.apellido = apellido;
		this.contrasenya = contrasenya;
		this.telefono = telefono;
		this.correo = correo;
	}
	
	

	public UsuarioVO(String id_Usuario, String nombre) {
		this.id_Usuario = id_Usuario;
		this.nombre = nombre;
	}

	public String getId_Usuario() {
		return id_Usuario;
	}

	public void setId_Usuario(String id_Usuario) {
		this.id_Usuario = id_Usuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getContrasenya() {
		return contrasenya;
	}

	public void setContrasenya(String contrasenya) {
		this.contrasenya = contrasenya;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	
	
}
