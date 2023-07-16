package ar.com.codoacodo.controller;

import java.io.IOException;
import java.util.List;

import ar.com.codoacodo.dao.iEmployeeDAO;
import ar.com.codoacodo.dao.impl.EmployeeDAOImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DeleteEmployeeController")
public class DeleteEmployeeController extends HttpServlet {

	private static final long serialVersionUID = -4198034750179920408L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Long dni = Long.parseLong(req.getParameter("dni"));

		iEmployeeDAO dao = new EmployeeDAOImpl();
		
		try {
			dao.delete(dni);
			// Mensaje de exito
			req.setAttribute("success", List.of("Se he eliminado el empleado con dni:" + dni));
		} catch (Exception e) {
			e.printStackTrace();
			// Mensaje de error
			req.setAttribute("errors", List.of("No se a eliminado el empleado :" + e.getMessage()));
		}
		
		getServletContext().getRequestDispatcher("/GetAllEmployeeController").forward(req, resp);
		
	}
	

}
