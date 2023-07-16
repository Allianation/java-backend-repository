package ar.com.codoacodo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import ar.com.codoacodo.dao.iDepartmentDAO;
import ar.com.codoacodo.dao.impl.DepartmentDAOImpl;
import ar.com.codoacodo.domain.Department;

@WebServlet("/GetAllDepartmentController")
public class GetAllDepartmentController extends HttpServlet {

	private static final long serialVersionUID = -4483941556989806684L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		iDepartmentDAO dao = new DepartmentDAOImpl();
		List<Department> departamentos = new ArrayList<>();

		try {
			departamentos = dao.getAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		req.setAttribute("listado", departamentos);

		// Redireccion a listado-departamento.jsp
		getServletContext().getRequestDispatcher("/listado-departamento.jsp").forward(req, resp);

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	// Version de consola
	/*public static void main(String[] args) {
		
		iDepartmentDAO dao = new DepartmentDAOImpl();
		List<Department> departamentos = new ArrayList<>();

		try {
			departamentos = dao.getAll();
		} catch (Exception e) {
			departamentos = null;
		}
			
		if (departamentos != null) {
			for(Department aux : departamentos) {
				System.out.println(aux.toString());
				System.out.println("--------------------------------");
			}	
		} else {
				System.err.println("No hay registros en la tabla departamentos");
		}
		
	}*/

}
