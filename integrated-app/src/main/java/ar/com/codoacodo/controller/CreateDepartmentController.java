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

@WebServlet("/CreateDepartmentController")
public class CreateDepartmentController extends HttpServlet {

	private static final long serialVersionUID = -2655527176806236489L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// Get request parameters
		String id = req.getParameter("id");
		String nombre= req.getParameter("nombre");
		String presupuesto = req.getParameter("presupuesto");
		
		// Validations
		List<String> errores = new ArrayList<>();
		if(id == null || "".equals(id)) {
			errores.add("Ingresar Id");
		}
		
		if(nombre == null || "".equals(nombre)) {
			errores.add("Ingresar Nombre");
		}
		
		if(presupuesto == null || "".equals(presupuesto)) {
			errores.add("Ingresar Presupuesto");
		}
		
		if(!errores.isEmpty()) {
			req.setAttribute("errors", errores);
			getServletContext().getRequestDispatcher("/nuevo-departamento.jsp").forward(req, resp);
			return;
		}
		
		iDepartmentDAO dao = new DepartmentDAOImpl();
		
		Department departamento = new Department(Long.parseLong(id), nombre, Double.parseDouble(presupuesto));
		
		try {
			 dao.create(departamento);
			 req.setAttribute("success", List.of("Alta de departamento exitosa"));
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		getServletContext().getRequestDispatcher("/GetAllDepartmentController").forward(req, resp);
		
	}
	
}
