package ar.com.codoacodo.controller;

import java.io.IOException;
import java.util.List;

import ar.com.codoacodo.dao.iEmployeeDAO;
import ar.com.codoacodo.dao.impl.EmployeeDAOImpl;
import ar.com.codoacodo.domain.Department;
import ar.com.codoacodo.domain.Employee;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/UpdateEmployeeController")
public class UpdateEmployeeController extends HttpServlet {

	private static final long serialVersionUID = -1552833731168820511L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String dni = req.getParameter("dni");
				
		iEmployeeDAO dao = new EmployeeDAOImpl();
				
		Employee employee = null;
			
		try {
			employee = dao.getByDni(Long.parseLong(dni));
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		req.setAttribute("empleado", employee);
				
		getServletContext().getRequestDispatcher("/editar-empleado.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String dni = req.getParameter("dni");
		String nombre= req.getParameter("nombre");
		String apellido = req.getParameter("apellido");
		String departamento = req.getParameter("departamento");

		iEmployeeDAO dao = new EmployeeDAOImpl();
		
		Department depto = new Department();
		depto.setId(Long.parseLong(departamento));
		Employee employee = new Employee(Long.parseLong(dni), nombre, apellido, depto);
		
		try { 
			dao.update(employee);
			req.setAttribute("success", List.of("Empleado dni: " + employee.getDni() + " actualizado correctamente"));
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("errors", List.of("Error actualizando empleado " + e.getMessage()));
		}
		
	    getServletContext().getRequestDispatcher("/GetAllEmployeeController").forward(req, resp);
		
	}

}
