package com.cibertecdaas.ws.rest.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.sql.*;

import com.cibertecdaas.ws.rest.mysql.UsuariosDao;
import com.cibertecdaas.ws.rest.vo.VOUsuario;

@Path("/cibertecDaas")
public class ServiceLoginCD {
	UsuariosDao udao = new UsuariosDao();
	@POST
	@Path("/validaUsuario")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public VOUsuario validaUsuario(VOUsuario vo) {
		String usuario = vo.getUsuario();
		String password = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/ciber_rrhh?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","");  
			String consulta = "select * from usuario where nombre_usuario = ? limit 1"; 
		    PreparedStatement ps = con.prepareStatement(consulta);
		    // recibe el usuario del post event
		    ps.setString(1, usuario);
		    ResultSet rs = ps.executeQuery();
		    while(rs.next()) {
		    	password = rs.getInt(5)+"";
		    }
		    con.close();
		} catch(Exception e){
			System.out.println(e);
		}
	    
	    vo.setUsarValido(false);
		if(vo.getPassword().equals(password)) {
			vo.setUsarValido(true);
			String token = UsuariosDao.creaToken().toString();
			System.out.println(token);
			UsuariosDao.asignaToken(usuario, token);
			vo.setToken(token);
		} else {
			vo.setMsj("El usuario o contraseña es incorrecto");
		}
		vo.setPassword("###########################");
	    
	    return vo;
		
		
		
	}
}
