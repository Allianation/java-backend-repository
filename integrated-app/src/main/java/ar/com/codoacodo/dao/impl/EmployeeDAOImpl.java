package ar.com.codoacodo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ar.com.codoacodo.dao.iEmployeeDAO;
import ar.com.codoacodo.db.DatabaseConfiguration;
import ar.com.codoacodo.domain.Department;
import ar.com.codoacodo.domain.Employee;

public class EmployeeDAOImpl implements iEmployeeDAO {
	
	@Override
	public Employee getByDni(Long dni)  throws Exception{
		
		Connection connection = DatabaseConfiguration.getConnection();
		
		String sql = "SELECT EMPLEADOS.DNI, EMPLEADOS.NOMBRE, EMPLEADOS.APELLIDO, "
	    		+ "DEPARTAMENTOS.ID AS DEPTO_ID, DEPARTAMENTOS.NOMBRE AS DEPTO_NOMBRE, DEPARTAMENTOS.PRESUPUESTO AS DEPTO_PRESUPUESTO "
	    		+ "FROM EMPLEADOS "
	    		+ "INNER JOIN DEPARTAMENTOS "
	    		+ "ON (EMPLEADOS.DPTO_ID = DEPARTAMENTOS.ID) "
	    		+ "WHERE EMPLEADOS.DNI = " + dni;
	   
		Statement statement  = connection.createStatement();
		
		ResultSet resultSet = statement.executeQuery(sql);
		
		if (resultSet.next()){
			Long dniBd = resultSet.getLong("dni");
			String nombreBd = resultSet.getString("nombre");
			String apellidoBd = resultSet.getString("apellido");
			Long deptoIdBd = resultSet.getLong("depto_id");
			String deptoNombreBd = resultSet.getString("depto_nombre");
			Double deptoPresupuestoBd = resultSet.getDouble("depto_presupuesto");
			
			Department department = new Department(deptoIdBd, deptoNombreBd, deptoPresupuestoBd);
			return new Employee(dniBd, nombreBd, apellidoBd, department);
			
		}
		
		connection.close();
		
		return null;
	}
	
	@Override
	public List<Employee> getAll() throws Exception {
		
		Connection connection = DatabaseConfiguration.getConnection();
		
	    String sql = "SELECT EMPLEADOS.DNI, EMPLEADOS.NOMBRE, EMPLEADOS.APELLIDO, "
	    		+ "DEPARTAMENTOS.ID AS DEPTO_ID, DEPARTAMENTOS.NOMBRE AS DEPTO_NOMBRE, DEPARTAMENTOS.PRESUPUESTO AS DEPTO_PRESUPUESTO "
	    		+ "FROM EMPLEADOS "
	    		+ "INNER JOIN DEPARTAMENTOS "
	    		+ "ON (EMPLEADOS.DPTO_ID = DEPARTAMENTOS.ID)";
	    
		Statement statement  = connection.createStatement();
				
		ResultSet resultSet = statement.executeQuery(sql);

		List<Employee> empleados = new ArrayList<Employee>();	
			
		while (resultSet.next()){
			Long dniBd = resultSet.getLong("dni");
			String nombreBd = resultSet.getString("nombre");
			String apellidoBd = resultSet.getString("apellido");
			Long deptoIdBd = resultSet.getLong("depto_id");
			String deptoNombreBd = resultSet.getString("depto_nombre");
			Double deptoPresupuestoBd = resultSet.getDouble("depto_presupuesto");
			
			Department d = new Department(deptoIdBd, deptoNombreBd, deptoPresupuestoBd);
			Employee e = new Employee(dniBd, nombreBd, apellidoBd, d);
			empleados.add(e);
		}
		
		connection.close();
		
		return empleados;
	}
	
	@Override
	public void create(Employee newEmployee) throws Exception {
		Connection connection = DatabaseConfiguration.getConnection();
		
		String sql = "INSERT INTO EMPLEADOS (DNI, NOMBRE, APELLIDO, DPTO_ID) VALUES (?, ?, ?, ?)" ;
		
		PreparedStatement statement  = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
		statement.setLong(1, newEmployee.getDni().longValue());
		statement.setString(2, newEmployee.getNombre());
		statement.setString(3, newEmployee.getApellido());
		statement.setLong(4, newEmployee.getDepartamento().getId());
		
		statement.execute();
		 
		ResultSet res = statement.getGeneratedKeys(); // Retorna la key que se genero
		if (res.next()) {
			System.out.println("Se creo el empleado correctamente");
		}
		
		connection.close();
	}
	
	@Override
	public void update(Employee employee) throws Exception {
	
		Connection connection = DatabaseConfiguration.getConnection();
		
		String sql = "UPDATE EMPLEADOS SET NOMBRE = ?, APELLIDO = ?, DPTO_ID = ? WHERE DNI = ?";
		
		PreparedStatement statement  = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
	    statement.setString(1, employee.getNombre());
		statement.setString(2, employee.getApellido());
		statement.setLong(3, employee. getDepartamento().getId());
		statement.setLong(4, employee. getDni());
		
		statement.execute();
		
		connection.close();
	}
	
	@Override
	public void delete(Long dni) throws Exception {
		
		Connection connection = DatabaseConfiguration.getConnection();
		
	    String sql = "DELETE FROM EMPLEADOS WHERE DNI = " + dni;
	    
	 	Statement statement  = connection.createStatement();
	 	
		statement.executeUpdate(sql);
		 
		connection.close(); 
	}

}
