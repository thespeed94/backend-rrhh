package com.cibertecdaas.ws.rest.mysql;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.sql.*;
import java.util.Random;

import com.cibertecdaas.ws.rest.vo.VOUsuario;

public class UsuariosDao {
	
	protected static SecureRandom random = new SecureRandom();
	public VOUsuario validaLogin(VOUsuario vo) {
		String password = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(
			"jdbc:mysql://localhost:3306/ciber_rrhh?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","");  
			String consulta = "select * from Usuario where nombre_usuario = ? limit 1"; 
		    PreparedStatement ps = con.prepareStatement(consulta);
		    // recibe el usuario del post event
		    ps.setString(1, vo.getUsuario());
		    ResultSet rs = ps.executeQuery();
		    
		    password = rs.getInt(5)+"";
		} catch(Exception e){
			System.out.println(e);
		}
	    
	    vo.setUsarValido(false);
		if(vo.getPassword().equals(password)) {
			vo.setUsarValido(true);
		}
		vo.setPassword("###########################");
	    
	    return vo;
	}
	public static String formatToken(String token) {
	    if (token == null) return null;
	    char delimiter = '-';
	    return token.replaceAll(".{4}(?!$)", "$0" + delimiter);
	}
	public static String creaToken() {
		long longToken = Math.abs( random.nextLong() );
        String random = Long.toString( longToken, 16 );
		return formatToken(random);
	}
	public static boolean asignaToken(String user, String token) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/ciber_rrhh?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","");  
			String consulta = "UPDATE usuario SET token = ? WHERE nombre_usuario = ?"; 
		    PreparedStatement ps = con.prepareStatement(consulta);
		    ps.setString(1,token);
		    ps.setString(2,user);
		    
		    // call executeUpdate to execute our sql update statement
		    ps.executeUpdate();
		    ps.close();
		} catch(Exception e){
			return false;
		}
		return true;
	}
	public boolean validaToken() {
		return true;
	}
}
