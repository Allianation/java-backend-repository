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

@WebServlet("/UpdateDepartmentController")
public class UpdateDepartmentController extends HttpServlet {

	private static final long serialVersionUID = 2786434916214904272L;
	
	// Cargar el departamento y enviarlo a la jsp que va a editar los datos
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String id = req.getParameter("id");
				
		iDepartmentDAO dao = new DepartmentDAOImpl();
				
		Department department = null;
			
		try {
			department = dao.getById(Long.parseLong(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		req.setAttribute("departamento", department);
				
		getServletContext().getRequestDispatcher("/editar-departamento.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String id = req.getParameter("id");
		String nombre= req.getParameter("nombre");
		String presupuesto = req.getParameter("presupuesto");

		iDepartmentDAO dao = new DepartmentDAOImpl();
		
		Department department = new Department(Long.parseLong(id),nombre,Double.parseDouble(presupuesto));
		
		try { 
			dao.update(department);
			req.setAttribute("success", List.of("Departamento id: " + department.getId() + " actualizado correctamente"));
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("errors", List.of("Error actualizando departamento " + e.getMessage()));
		}
		
	    getServletContext().getRequestDispatcher("/GetAllDepartmentController").forward(req, resp);
		
	}

}
