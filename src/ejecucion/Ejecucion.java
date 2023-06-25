package ejecucion;
import java.util.Scanner;

import impl.*;

public class Ejecucion {
	private static Menu menu = new Menu();
	private static Lista listaDeAlumnos = new Lista();
	private static Scanner sc = new Scanner(System.in);
	protected static int cant;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		listaDeAlumnos.inicializarLista();
		menu.inicio();
	}
	
	public static void agregar() {
		String nombre, apellido, seguir = "S";
		int legajo;
		
		while(!seguir.equalsIgnoreCase("N")) {
			System.out.print("Ingrese un nombre: "); nombre = sc.nextLine();
			if(!nombre.isEmpty()) {
				System.out.print("Ingrese un apellido: "); apellido = sc.nextLine();
				if(!apellido.isEmpty()) {
					System.out.print("Ingrese un legajo: "); legajo = sc.nextInt(); sc.nextLine();
					if(legajo > 0) {
						listaDeAlumnos.agregarElemento(new Alumno(nombre, apellido, legajo));
						cant++;
						seguir = menu.validarStr("Desea agregar otro alumno? S/N: ");
					} else {
						System.out.print("Error: el legajo tiene que ser mayor a 0\n");
					}
				} else {
					System.out.print("Error: ingrese un apellido\n");
				}
			} else {
				System.out.print("Error: ingrese un nombre\n");
			}
			System.out.println();
		}
		
		menu.inicio();
	}
		
	public static void eliminarYListar() {
		String seguir = "S";
		int legajo;
				
		while(!seguir.equalsIgnoreCase("N") && cant > 0) {
			listaDeAlumnos.imprimirLista();

			System.out.println("\n1) Eliminar alumno");
			System.out.println("2) Volver al menu principal\n");
			
			if(menu.validarInt(1, 2) == 2) {
				System.out.println();
				break;
			}
			
			System.out.print("\nIngrese el legajo a eliminar: "); legajo = sc.nextInt(); sc.nextLine();
			
			while(legajo < 0 || listaDeAlumnos.noExiste(legajo)) {
				System.out.println("Error: ingrese un legajo valido");
				System.out.print("\nIngrese el legajo a eliminar: ");
				legajo = sc.nextInt(); sc.nextLine();
			}
				
			listaDeAlumnos.eliminarElemento(legajo);
			cant--;
			if(cant > 0) { //esto hay que hacerlo porque si se llega a eliminar todos los alumnos arrojaría igualmente el mensaje y quedaría mal porque obligaría al usuario a decir que sí o no innecesariamente porque no queda nada para eliminar y cuando se vuelva a iterar el bucle va a salir porque no hay alumnos cargados
				seguir = menu.validarStr("Desea eliminar otro alumno? S/N: ");
			}
			System.out.println();
		}
		
		menu.inicio();
	}
	
	public static void salir() {
		sc.close();
		System.exit(0);
	}

	public static void test() {
		if(cant == 0) {
			listaDeAlumnos.agregarElemento(new Alumno("azul", "perez", 55)); cant += 1;
			listaDeAlumnos.agregarElemento(new Alumno("carlos", "xorro", 13)); cant += 1;
			listaDeAlumnos.agregarElemento(new Alumno("jazmin", "lopez", 26)); cant += 1;
			listaDeAlumnos.agregarElemento(new Alumno("juan", "gomez", 33)); cant += 1;
			listaDeAlumnos.agregarElemento(new Alumno("NO DEBERIA FUNCIONAR, porque tiene el mismo legajo que juan gomez", "test", 33));
			System.out.println("4 alumnos agregados correctamente + 1 error por legajo ya existente");
			listaDeAlumnos.imprimirLista();
			System.out.println();
	
			listaDeAlumnos.eliminarElemento(26); cant -= 1;
			System.out.println("Lista habiendo eliminado a la alumna leg 26 (jazmin lopez)");
			listaDeAlumnos.imprimirLista();
			
			listaDeAlumnos.agregarElemento(new Alumno("SI DEBERIA FUNCIONAR, porque ya se elimino el legajo 26 de jazmin lopez", "test", 26)); cant += 1;
			System.out.println("Lista con un nuevo alumno con el mismo legajo que tenia 'jazmin lopez' para probar si no lo toma como ya existente");
			listaDeAlumnos.imprimirLista();
			System.out.println();
	
			System.out.println("Lista con todos los alumnos necesarios");
			listaDeAlumnos.agregarElemento(new Alumno("lujan", "tak", 2)); cant += 1;
			listaDeAlumnos.agregarElemento(new Alumno("santiago", "zorro", 5)); cant += 1;
			listaDeAlumnos.agregarElemento(new Alumno("paula", "perez", 51)); cant += 1;
			listaDeAlumnos.agregarElemento(new Alumno("gimena", "asd", 1512)); cant += 1;
			listaDeAlumnos.agregarElemento(new Alumno("josefina", "asad", 42)); cant += 1;
			listaDeAlumnos.agregarElemento(new Alumno("pedro", "alvarez", 32)); cant += 1;
			listaDeAlumnos.agregarElemento(new Alumno("facundo", "espada", 11)); cant += 1;
		} else {
			agregar();
		}
		
		System.out.println(); menu.inicio();
	}
	
}
