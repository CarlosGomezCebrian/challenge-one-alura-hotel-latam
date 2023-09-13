package com.hotel.jdbc.controller;






import com.hotel.jdbc.dao.LoginDAO;
import com.hotel.jdbc.factory.ConnectionFactory;
import com.hotel.jdbc.modelo.Login;


public class LoginController {

	private LoginDAO loginDAO;
	
	public LoginController() {
		this.loginDAO = new LoginDAO(new ConnectionFactory().recuperaConexion());
		
	}
	
	public void login(Login login) {
		loginDAO.login(login);
	}
	


	 

}
