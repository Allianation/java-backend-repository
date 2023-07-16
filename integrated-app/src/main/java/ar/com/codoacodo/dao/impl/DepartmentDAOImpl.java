package ar.com.codoacodo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ar.com.codoacodo.dao.iDepartmentDAO;
import ar.com.codoacodo.db.DatabaseConfiguration;
import ar.com.codoacodo.domain.Department;

public class DepartmentDAOImpl implements iDepartmentDAO {
	
	@Override
	public Department getById(Long id)  throws Exception{
		// 1- Necesito la conection a la base
		Connection connection = DatabaseConfiguration.getConnection();
		
		// 2- Armo el statement
		String sql = "SELECT * FROM DEPARTAMENTOS WHERE ID = " + id;
	   
		Statement statement  = connection.createStatement();
		
		// 3- Obtengo el resulSet
		ResultSet resultSet = statement.executeQuery(sql);
		
		// Verifico si hay datos 
		if (resultSet.next()){
			Long idBd = resultSet.getLong("id");
			String nombreBd = resultSet.getString("nombre");
			Double presupuestoBd = resultSet.getDouble("presupuesto");
			
			return new Department(idBd, nombreBd, presupuestoBd);
			
		}
		
		connection.close();
		
		return null; // Si no hay datos, entonces no devuelvo nada
	}
	
	@Override
	public List<Department> getAll() throws Exception {
		// 1- Necesito la conection a la base
		Connection connection = DatabaseConfiguration.getConnection();
		
		// 2- Armo el statement
	    String sql = "SELECT * FROM DEPARTAMENTOS";
			   
		Statement statement  = connection.createStatement();
				
		// 3- Obtengo el resulSet
		ResultSet resulSet = statement.executeQuery(sql);
		// El resultset devuelve un registro de una tabla 
				
		// Creo una lista de departamentos
		List<Department> departamentos = new ArrayList<Department>();	
			
		// Mientras encontremos resultados de la base 
		while (resulSet.next()){
			Long idBd = resulSet.getLong("id");
			String nombreBd = resulSet.getString("nombre");
			Double presupuestoBd = resulSet.getDouble("presupuesto");
			
			// Creo un departamento y lo agrego a la lista 
			Department d = new Department(idBd, nombreBd, presupuestoBd);
			departamentos.add(d);
		}
		
		connection.close();
		
		// Devuelvo los departamentos		
		return departamentos; 
	}
	
	@Override
	public void create(Department newDepartment) throws Exception {
		// 1- Necesito la conection a la base
		Connection connection = DatabaseConfiguration.getConnection();
		
		// 2- Armo el statement
		String sql = "INSERT INTO DEPARTAMENTOS (ID, NOMBRE, PRESUPUESTO) VALUES (?, ?, ?)" ;
		
		PreparedStatement statement  = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
		statement.setLong(1, newDepartment.getId());
		statement.setString(2, newDepartment.getNombre());
		statement.setDouble(3, newDepartment.getPresupuesto());
		
		// 3- Devuelvo un entero (1 o 0)
		statement.execute();
		 
		ResultSet res = statement.getGeneratedKeys(); // Retorna la key que se genero
		if (res.next()) {
			System.out.println("Se creo el departamento correctamente");
		}
		
		connection.close();
	}
	
	@Override
	public void update(Department departamento) throws Exception {
		// 1- Necesito la conection a la base
		Connection connection = DatabaseConfiguration.getConnection();
		
		// 2- Armo el statement
		String sql = "UPDATE DEPARTAMENTOS SET NOMBRE = ?, PRESUPUESTO = ? WHERE ID = ?";
		
		PreparedStatement statement  = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
	    statement.setString(1, departamento.getNombre());
		statement.setDouble(2, departamento.getPresupuesto());
		statement.setLong(3, departamento.getId());
		
		// 3- Devuelvo un entero (1 o 0), pero no hace falta confirmar para este caso 
		statement.execute();
		
		connection.close();
	}
	
	@Override
	public void delete(Long id) throws Exception {
		// 1- Necesito la conection a la base
		Connection connection = DatabaseConfiguration.getConnection();
		
		// 2- Armo el statement
	    String sql = "DELETE FROM DEPARTAMENTOS WHERE ID = " + id;
	    
	 	Statement statement  = connection.createStatement();
	 	
	 	// 3- Devuelvo un entero (1 o 0)
		statement.executeUpdate(sql);
		 
		connection.close(); 
	}
	
	@Override
	public List<Department> getByName(String nombre) throws Exception {
		// 1- Necesito la conection a la base
		Connection connection = DatabaseConfiguration.getConnection();

		// 2- Armo el statement
		String sql = "SELECT * FROM DEPARTAMENTOS WHERE NOMBRE LIKE ?";
		
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setString(1, "%" + nombre + "%");
		
		// 3- Obtengo el resulSet
		ResultSet resultSet = statement.executeQuery();

		List<Department> departamentos = new ArrayList<Department>();

		// Verifico si hay datos 
		while (resultSet.next()) {
			Long idBd = resultSet.getLong("id");
			String nombreBd = resultSet.getString("nombre");
			Double presupuestoBd = resultSet.getDouble("presupuesto");
			
			departamentos.add(new Department(idBd, nombreBd, presupuestoBd));
		}
		
		connection.close(); 
		
		return departamentos;
	}
	
}
