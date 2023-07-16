package ar.com.codoacodo.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import ar.com.codoacodo.dao.iEmployeeDAO;
import ar.com.codoacodo.dao.impl.EmployeeDAOImpl;
import ar.com.codoacodo.domain.Department;
import ar.com.codoacodo.domain.Employee;

@WebServlet("/CreateEmployeeController")
public class CreateEmployeeController extends HttpServlet {

	private static final long serialVersionUID = -4780724986283397769L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// Get request parameters
		String dni = req.getParameter("dni");
		String nombre= req.getParameter("nombre");
		String apellido = req.getParameter("apellido");
		String depto = req.getParameter("departamento");
		
		iEmployeeDAO dao = new EmployeeDAOImpl();
		
		Department departamento = new Department();
		departamento.setId(Long.parseLong(depto));
		Employee empleado = new Employee(Long.parseLong(dni), nombre, apellido, departamento);
		
		try {
			 dao.create(empleado);
			 req.setAttribute("success", List.of("Alta de empleado exitosa"));
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		getServletContext().getRequestDispatcher("/GetAllEmployeeController").forward(req, resp);
		
	}

}
