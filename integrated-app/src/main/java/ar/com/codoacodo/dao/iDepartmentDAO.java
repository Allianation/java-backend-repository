package ar.com.codoacodo.dao;

import java.util.List;

import ar.com.codoacodo.domain.Department;

// En esta interface vamos a definir metodos de acceso a la tabla departamentos

public interface iDepartmentDAO {
	
	public Department getById(Long id) throws Exception; // Devuelve de un id todos los campos 
	
	public List<Department> getAll() throws Exception;	// Devuelve todos los registros de la tabla departamentos
	
	public void create(Department newDepartment) throws Exception; // Inserta un registro en la tabla departamentos
	
	public void update(Department departamento) throws Exception; // Actualiza un registro de la tabla departamentos
	
	public void delete(Long id) throws Exception; // Borra un registro por el id del departamento
	
	public List<Department> getByName(String nombre) throws Exception; // Devuelve registros de la tabla departamentos por nombre

}
