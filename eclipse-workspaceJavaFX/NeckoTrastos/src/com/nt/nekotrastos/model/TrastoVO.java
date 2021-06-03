package com.nt.nekotrastos.model;

public class TrastoVO {
	/*** ATRIBUTS ***/
		private int id_Producto;
		private String nombreTrasto;
		private String descripcion;
		private float precio;
		private String id_Propietario;
		private int telefono;
	
	/*** default CONSTRUCTOR ***/
		public TrastoVO() {
		
		}
		
		public TrastoVO(String nombreTrasto, String descripcion, float precio) {
			this.nombreTrasto = nombreTrasto;
			this.descripcion = descripcion;
			this.precio = precio;
		}
		
		public TrastoVO(int id_Producto,String nombreTrasto,String descripcion,float precio,String id_Propietario, int telefono) {
		
			this.id_Producto = id_Producto;
			this.nombreTrasto = nombreTrasto;
			this.descripcion = descripcion;
			this.precio = precio;
			this.id_Propietario = id_Propietario;
			this.telefono = telefono;
			
		}

		public int getTelefono() {
			return telefono;
		}

		public void setTelefono(int telefono) {
			this.telefono = telefono;
		}

		/**
		 * @return the iD_Producto
		 */
		public int getID_Producto() {
			return id_Producto;
		}

		/**
		 * @param iD_Producto the iD_Producto to set
		 */
		public void setID_Producto(int id_Producto) {
			this.id_Producto = id_Producto;
		}

		/**
		 * @return the nombreTrasto
		 */
		public String getNombreTrasto() {
			return nombreTrasto;
		}

		/**
		 * @param nombreTrasto the nombreTrasto to set
		 */
		public void setNombreTrasto(String nombreTrasto) {
			this.nombreTrasto = nombreTrasto;
		}

		/**
		 * @return the descripcion
		 */
		public String getDescripcion() {
			return descripcion;
		}

		/**
		 * @param descripcion the descripcion to set
		 */
		public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
		}

		/**
		 * @return the precio
		 */
		public float getPrecio() {
			return precio;
		}

		/**
		 * @param precio the precio to set
		 */
		public void setPrecio(float precio) {
			this.precio = precio;
		}

		/**
		 * @return the iD_Propietario
		 */
		public String getID_Propietario() {
			return id_Propietario;
		}

		/**
		 * @param iD_Propietario the iD_Propietario to set
		 */
		public void setID_Propietario(String id_Propietario) {
			this.id_Propietario = id_Propietario;
		}
		
		
		
}