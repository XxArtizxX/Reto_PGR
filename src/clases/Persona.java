package clases;

public  class Persona {
	protected String dni;
	protected String nombre;
	protected String tipoPersona;
	
	public Persona(String dni, String nombre, String tipoPersona) {
		this.dni=dni;
		this.nombre=nombre;
		this.tipoPersona=tipoPersona;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipoPersona() {
		return tipoPersona;
	}

	public void setTipoPersona(String tipoPersona) {
		this.tipoPersona = tipoPersona;
	}

	@Override
	public String toString() {
		return "Persona [dni=" + dni + ", nombre=" + nombre + ", tipoPersona=" + tipoPersona + "]";
	}
}
