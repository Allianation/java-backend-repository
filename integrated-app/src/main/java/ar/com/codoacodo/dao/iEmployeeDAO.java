package ar.com.codoacodo.dao;

import java.util.List;

import ar.com.codoacodo.domain.Employee;

public interface iEmployeeDAO {
	
	public Employee getByDni(Long dni) throws Exception;
	
	public List<Employee> getAll() throws Exception;
	
	public void create(Employee newEmployee) throws Exception;
	
	public void update(Employee departamento) throws Exception;
	
	public void delete(Long dni) throws Exception;

}
