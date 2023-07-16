<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<div class="container-fluid">
		<a class="navbar-brand" href="<%=request.getContextPath()%>/index.jsp">CRUD</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNav">
			<ul class="navbar-nav me-auto mb-2 mb-lg-0">
				<li class="nav-item">
					<a class="nav-link" href="<%=request.getContextPath()%>/index.jsp">Inicio</a>
				</li>
				<li class="nav-item dropdown">
          			<a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink1" role="button" data-bs-toggle="dropdown">Listados</a>
		          	<ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink1">
		            	<li><a class="dropdown-item" href="<%=request.getContextPath()%>/GetAllDepartmentController">Departamentos</a></li>
		            	<li><a class="dropdown-item" href="<%=request.getContextPath()%>/GetAllEmployeeController">Empleados</a></li>
		          	</ul>
        		</li>
        		<li class="nav-item dropdown">
          			<a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink2" role="button" data-bs-toggle="dropdown">Agregar</a>
		          	<ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink2">
		            	<li><a class="dropdown-item" href="<%=request.getContextPath()%>/nuevo-departamento.jsp">Departamento</a></li>
		            	<li><a class="dropdown-item" href="<%=request.getContextPath()%>/NewEmployeeController">Empleado</a></li>
		          	</ul>
        		</li>
			</ul>
			<form class="d-flex" action="<%=request.getContextPath()%>/SearchDepartmentController">
				<input name="nombreBusqueda" class="form-control me-2" type="search" placeholder="Buscar Nombre Depto" aria-label="Search">
				<button class="btn btn-outline-success" type="submit">Buscar</button>
			</form>
		</div>
	</div>
</nav>