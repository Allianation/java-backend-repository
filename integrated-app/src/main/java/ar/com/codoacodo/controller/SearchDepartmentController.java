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
import ar.com.codoacodo.domain.Department;

@WebServlet("/SearchDepartmentController")
public class SearchDepartmentController extends HttpServlet {
	
	private static final long serialVersionUID = 1886583185136551747L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		iDepartmentDAO dao = new DepartmentDAOImpl();
		
		String nombreBusqueda = req.getParameter("nombreBusqueda");
		
		List<Department> departamentos;
		
		try {
			departamentos = dao.getByName(nombreBusqueda);
		} catch (Exception e) {
			departamentos = List.of(); // Crea una lista vacia
			e.printStackTrace();
		}
		
		req.setAttribute("listado", departamentos);
		
		getServletContext().getRequestDispatcher("/listado-departamento.jsp").forward(req, resp);
	}

}
