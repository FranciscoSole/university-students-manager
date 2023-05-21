package ejecucion;
import java.util.Scanner;

public class Menu {
	private Scanner sc = new Scanner(System.in);
	
	public void inicio() {
		boolean hayAlumnos = false;
		int opt;
		
		System.out.println("Bienvenido al sistema\n");
		System.out.println("1) Agregar un alumno");
		if(Ejecucion.cant > 0) {
			hayAlumnos = true;
			System.out.println("2) Listar alumnos");
			System.out.println("3) Salir\n"); opt = validarInt(1, 3);
		} else {
			System.out.println("2) Salir\n"); opt = validarInt(1, 2);
		}
		
		if(opt == 1) {
			cargarAlumno();
		} else {
			if((hayAlumnos && opt == 3) || !hayAlumnos) {
				salir();
			}
			
			if(hayAlumnos) {
				listarAlumnos();
			}
		}
	}
	
	public void cargarAlumno() {
		System.out.println("\nBienvenido al sistema de carga de alumnos");
		Ejecucion.test();
		//Ejecucion.agregar();
	}
	
	public void listarAlumnos() {
		System.out.println("\nBienvenido al listado de alumnos\n");
		Ejecucion.eliminarYListar();
	} 
	
	public void salir() {
		System.out.println("\nGracias por usar el sistema");
		sc.close();
		Ejecucion.salir();
	}
	
	public int validarInt(int min, int max) {
		System.out.print("Ingrese una opcion: "); int opt = sc.nextInt(); sc.nextLine();
		
		while(opt < min || opt > max) {
			System.out.println("Error: ingrese un valor entre "+min+" y "+max);
			System.out.print("\nIngrese una opcion: "); opt = sc.nextInt(); sc.nextLine();
		}
		
		return opt;
	}
	
	public String validarStr(String msg) {
		String seguir;
		
		System.out.print(msg); seguir = sc.nextLine();
		while(!seguir.equalsIgnoreCase("N") && !seguir.equalsIgnoreCase("S")) {
			System.out.println("Error: ingrese 'S' o 'N'");
			System.out.print("\nIngrese una opcion: "); seguir = sc.nextLine();
		}
			
		return seguir;
	}
}
