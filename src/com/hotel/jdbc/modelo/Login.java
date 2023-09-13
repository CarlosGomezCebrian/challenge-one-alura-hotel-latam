package com.hotel.jdbc.modelo;

public class Login {

	private String usuario;
	private String contraseña;
	private int acceso;

	
	public Login(String usuario, String contraseña, int acceso) {		
		this.usuario = usuario;
		this.contraseña = contraseña;
		this.acceso = acceso;
	}
	
	public Login(String usuario, String contraseña) {	
		this.usuario = usuario;
		this.contraseña = contraseña;
	
	}
	
	
	public String getUsuario() {
		return usuario;
	}

	public String getContraseña() {
		return contraseña;
	}

	public int getAcceso() {
	 return this.acceso;
	
	}

	public void setAcceso(int acceso) {
		this.acceso = acceso;
	}


	
}
