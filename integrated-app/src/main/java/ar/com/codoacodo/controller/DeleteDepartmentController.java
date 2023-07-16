package ar.com.codoacodo.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import ar.com.codoacodo.dao.iDepartmentDAO;
import ar.com.codoacodo.dao.impl.DepartmentDAOImpl;

@WebServlet("/DeleteDepartmentController")
public class DeleteDepartmentController extends HttpServlet {
	
	private static final long serialVersionUID = 3054856510376629250L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Long id = Long.parseLong(req.getParameter("idDepartamento"));

		iDepartmentDAO dao = new DepartmentDAOImpl();
		
		try {
			dao.delete(id);
			// Mensaje de exito
			req.setAttribute("success", List.of("Se he eliminado el departamento con id:" + id));
		} catch (Exception e) {
			e.printStackTrace();
			// Mensaje de error
			req.setAttribute("errors", List.of("No se a eliminado el departamento :" + e.getMessage()));
		}
		
		getServletContext().getRequestDispatcher("/GetAllDepartmentController").forward(req, resp);
		
	}

}
