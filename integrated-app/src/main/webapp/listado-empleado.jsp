<%@page import="ar.com.codoacodo.domain.Employee"%>
<%@page import="java.util.List"%>
<!doctype html>
<html lang="es">
	<head>
		<!-- Required meta tags -->
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<!-- Animated-->
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css">
		<!-- Logos-->
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/remixicon@2.5.0/fonts/remixicon.css">
		<!-- Bootstrap CSS -->
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
		
		<title>Listado Empleados</title>
	</head>

	<body>
		<!-- Aca va el navbar.jsp -->
		<div class="container bg-ligth">
			<jsp:include page="navbar.jsp" />
		</div>
	
		<main>
			<div class="container bg-light mt-5">
				<section>
					<h1>Listado de empleados</h1>
					<table class="table">
						<thead>
							<tr>
								<th scope="col">DNI</th>
								<th scope="col">NOMBRE</th>
								<th scope="col">APELLIDO</th>
								<th scope="col">DEPARTAMENTO</th>
								<th scope="col">ACCIONES</th>
	
							</tr>
						</thead>
						<%
							@SuppressWarnings("unchecked")
							List<Employee> listado = (List<Employee>) request.getAttribute("listado");
						%>
						<tbody>
							<% for (Employee e : listado) { %>
								<tr>
									<th scope="row"><%=e.getDni()%></th>
									<td><%=e.getNombre()%></td>
									<td><%=e.getApellido()%></td>
									<td><%=e.getDepartamento().getNombre()%></td>
	
									<td>
										<a class="btn btn-info" role="button" href="<%=request.getContextPath()%>/UpdateEmployeeController?dni=<%=e.getDni()%>">
											Editar 
										</a> | 
										<!-- Button trigger modal -->
										<button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#exampleModal" onclick="setEmpleadoDni(<%=e.getDni()%>)">
											Eliminar
										</button>
									</td>
								</tr>
							<% } %>
						</tbody>
					</table>
				</section>
			</div>
		</main>
		
		<!-- Modal -->
		<div class="modal fade" id="exampleModal" tabindex="-1"  aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<form action="<%=request.getContextPath()%>/DeleteEmployeeController">
						<input type="hidden" name="dni" id="dni">
						<div class="modal-header">
							<h5 class="modal-title" id="exampleModalLabel">Eliminar Empleado</h5>
							<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
						</div>
						<div class="modal-body">¿Confirma que desea eliminar?</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
							<button type="submit" class="btn btn-danger">Eliminar</button>
						</div>
					</form>
				</div>
			</div>
		</div>
		
		<!-- SE CREO, ACTUALIZO O ELIMINO UN EMPLEADO EN LA BASE DE DATOS -->
		<% if(request.getAttribute("success") != null) { %>          
        	<script>alert("<%=request.getAttribute("success")%>") </script>
        <% } %>
        
        <!-- ERROR AL ACTUALIZAR O ELIMINAR UN EMPLEADO -->
		<% if(request.getAttribute("errors") != null) { %>          
        	<script>alert("<%=request.getAttribute("errors")%>") </script>
        <% } %>
        
	</body>
	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

	<script>
		function setEmpleadoDni(dni) {
			document.getElementById("dni").value = dni;
		}
	</script>
</html>