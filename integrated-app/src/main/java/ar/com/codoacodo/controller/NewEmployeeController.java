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

@WebServlet("/NewEmployeeController")
public class NewEmployeeController extends HttpServlet {

	private static final long serialVersionUID = 4371215488426891423L;
	
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

		getServletContext().getRequestDispatcher("/nuevo-empleado.jsp").forward(req, resp);

	}
	
}
