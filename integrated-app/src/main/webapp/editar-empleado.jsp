<%@page import="ar.com.codoacodo.domain.Employee"%>
<%
	Employee e = (Employee) request.getAttribute("empleado");
%>

<!Doctype html>
<html>
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
		
		<title>Editar Empleado</title>
	</head>
	
	<body>
		<div class="container bg-light">
			<jsp:include page="navbar.jsp" />
		</div>
	
	
		<main>
			<div class="container bg-light mt-5">
				<h1>Editar Empleado</h1>
		
				<div class="row mt-5">
					<div class="col-12">
						<form class="row g-3 needs-validation" action="<%=request.getContextPath()%>/UpdateEmployeeController" method="POST">
							<div class="col-md-0">
								<input type="hidden" name="dni" value="<%=e.getDni()%>">
							</div>
							<div class="col-md-4">
								<label for="validationCustom01" class="form-label">Nombre</label>
								<input type="text" name="nombre" class="form-control" id="validationCustom01" value="<%=e.getNombre()%>" required>
								<div class="valid-feedback">Looks good!</div>
							</div>
							<div class="col-md-4">
								<label for="validationCustom02" class="form-label">Apellido</label>
								<input type="text" name="apellido" class="form-control" id="validationCustom02" value="<%=e.getApellido()%>" required>
								<div class="valid-feedback">Looks good!</div>
							</div>
							<div class="col-md-4">
								<label for="validationCustom03" class="form-label">Departamento</label>
								<select name="departamento" class="form-select" aria-label="Default select">
									<option value="<%= e.getDepartamento().getId() %>"><%= e.getDepartamento().getNombre() %></option>
								</select>
							</div>
		
							<div class="col-12">
								<button class="btn btn-primary" type="submit">Editar</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</main>
	</body>
	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
	
</html>