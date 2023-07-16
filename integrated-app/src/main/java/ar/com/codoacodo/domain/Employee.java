package ar.com.codoacodo.domain;

public class Employee {
	
	private Long dni;
	private String nombre;
	private String apellido;
	private Department departamento;
	
	public Employee(Long dni, String nombre, String apellido, Department departamento) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.departamento = departamento;
	}

	public Long getDni() {
		return dni;
	}

	public void setDni(Long dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Department getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Department departamento) {
		this.departamento = departamento;
	}

	@Override
	public String toString() {
		return "Employee [dni=" + dni + ", nombre=" + nombre + ", apellido=" + apellido + ", departamento="
				+ departamento + "]";
	}

}
