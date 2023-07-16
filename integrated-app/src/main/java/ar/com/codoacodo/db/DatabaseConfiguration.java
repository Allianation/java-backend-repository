package ar.com.codoacodo.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConfiguration {
	
	// Crear un metodo estatico, es decir que no vamos a crear un objeto para usar este metodo
	public static Connection getConnection() {
		
		String hosts = "localhost";  // 127.0.0.1
		String port = "3306";
		String password = "";
		String username = "root";
		String nombredb = "bd_desafio";
			
		// Driver de conexion a la base de datos
		String driveClassName = "com.mysql.cj.jdbc.Driver";
		
		// Aplicar manejo de excepciones
		Connection connection ;
		try {
			Class.forName(driveClassName);
			String url ="jdbc:mysql://" + hosts + ":" + port + "/" + nombredb + "?useSSL=false";
			connection = DriverManager.getConnection(url, username, password);
		} catch(Exception e){
			connection = null;
		}
		
		return connection;
		
	}
	
	// Version de consola
	/*public static void main(String[] args) {
	
		Connection con = DatabaseConfiguration.getConnection();
		
		if(con != null) {
			System.out.println("Database connected!");
		} else {
			System.err.println("Cannot connect the database!");
		}
		
	}*/

}
