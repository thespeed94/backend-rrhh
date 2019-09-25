package com.cibertecdaas.ws.rest.vo;

public class VOUsuario {
	private String usuario;
	private String password;
	private boolean usarValido;
	private String msj;
	private String token;
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isUsarValido() {
		return usarValido;
	}
	public void setUsarValido(boolean usarValido) {
		this.usarValido = usarValido;
	}
	public String getMsj() {
		return msj;
	}
	public void setMsj(String msj) {
		this.msj = msj;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	
}
