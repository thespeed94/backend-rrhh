package com.cibertecdaas.ws.rest.mysql;
import java.sql.*;

public class MysqlCon {
	public static void main(String args[]){
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/ciber_rrhh?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","");  
			//here sonoo is database name, root is username and password  
			/*Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select * from Usuario"); */
			
			String consulta = "select * from Usuario where nombre_usuario = ? "; 
		    PreparedStatement ps = con.prepareStatement(consulta);
		    ps.setString(1, "thespeed94");
		    ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getInt(5)+"  "+rs.getString(2)+"  "+rs.getString(3));  
			}
			
			con.close();
		} catch(Exception e){
			System.out.println(e);
		}
	}
}
