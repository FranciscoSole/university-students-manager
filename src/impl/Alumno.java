package impl;

public class Alumno{
	private String nombre, apellido;
	private int legajo;
	public Alumno sig;

	public Alumno() {}
	public Alumno(String nombre, String apellido, int legajo) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.legajo = legajo;
		this.sig = null;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	public void setLeg(int legajo) {
		this.legajo = legajo;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getApellido() {
		return apellido;
	}
	
	public int getLeg() {
		return legajo;
	}
	
	public boolean haySiguiente() {
		return(sig != null);
	}
	
	public Alumno siguiente() {
		return sig;
	}

}