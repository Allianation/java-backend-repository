package ar.com.codoacodo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import ar.com.codoacodo.dao.iEmployeeDAO;
import ar.com.codoacodo.dao.impl.EmployeeDAOImpl;
import ar.com.codoacodo.domain.Employee;

@WebServlet("/GetAllEmployeeController")
public class GetAllEmployeeController extends HttpServlet {

	private static final long serialVersionUID = 920056111606435446L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		iEmployeeDAO dao = new EmployeeDAOImpl();
		List<Employee> empleados = new ArrayList<>();

		try {
			empleados = dao.getAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		req.setAttribute("listado", empleados);

		// Redireccion a listado-empleado.jsp
		getServletContext().getRequestDispatcher("/listado-empleado.jsp").forward(req, resp);

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	// Version de consola
	/*public static void main(String[] args) {
		
		iEmployeeDAO dao = new EmployeeDAOImpl();
		List<Employee> empleados = new ArrayList<>();

		try {
			empleados = dao.getAll();
		} catch (Exception e) {
			empleados = null;
		}
			
		if (empleados != null) {
			for(Employee aux : empleados) {
				System.out.println(aux.toString());
				System.out.println("--------------------------------");
			}	
		} else {
				System.err.println("No hay registros en la tabla empleados");
		}
		
	}*/

}
