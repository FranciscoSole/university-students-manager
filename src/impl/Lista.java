package impl;
import api.ListaTDA;
import ejecucion.Menu; //es solamente para un print

public class Lista implements ListaTDA{ //entiendo que por el implements me obligue a poner funciones ya hechas en Alumno, pero no se puede evitar?
	Alumno primero, ultimo;
	int cantTotalAlumn;
	
	public void inicializarLista() {
		primero = ultimo = null;
	}
	
	public void agregarElemento(Alumno a) {
		if(noExiste(a.getLeg())) {
			cantTotalAlumn++;
			
			if(primero == null) {
				primero = a;
			} else {
				ultimo.sig = a;
			}
			ultimo = a;
			
			System.out.println("\n[Info] Se agrego el alumno '"+a.getNombre()+" "+a.getApellido()+"' con el legajo "+a.getLeg());
		} else {
			System.out.println("Error: ya existe un alumno con el legajo "+a.getLeg()+"\n");
		}
	}
	
	@Override
	public void eliminarElemento(int legajo) {
		if(!noExiste(legajo)) {
			Alumno anterior = primero, viajero = primero, aux;
			String donde; //donde funciona como bandera para despues de confirmar cómo hay que hacer el reemplazo, siento que es menos eficiente porque le agrega tiempo de comparación después pero al menos así es mas legible y da la posibilidad de confirmar
			
			if(primero.getLeg() == legajo) { //si el primer legajo es el que se quiere eliminar
				donde = "primero";
				aux = primero;
			} else { //si es uno entre el segundo y el ultimo
				while(viajero.haySiguiente() && viajero.siguiente().getLeg() != legajo) { //avanza mientras haya un siguiente, y a ese siguiente lo campara con el legajo que se busca eliminar
					viajero = viajero.siguiente();
				}
				
				if(viajero.haySiguiente()) { //si hay siguiente, significa que el while se detuvo porque encontró el legajo que se busca eliminar
					donde = "siguiente";
					aux = viajero.siguiente();
				} else { //si no hay siguiente (o sea que el legajo a eliminar es parte del ultimo objeto en la lista)
					while(anterior != viajero) { //avanza hasta encontrar el anterior a viajero, no es necesario el haySiguiente() porque si entró a este bloque significa que viajero está en la última posición, por lo que "anterior" nunca llegaría hasta ahí
						anterior = anterior.siguiente();
					}
					
					donde = "anterior";
					aux = anterior;
				}
			}
			
			Menu menu = new Menu();
			if(menu.validarStr("[Info] Esta eliminando a '"+aux.getNombre()+" "+aux.getApellido()+"' con el legajo "+aux.getLeg()+". Confirmar accion? S/N: ").equalsIgnoreCase("S")){
				if(donde.equals("primero")) {
					primero = primero.siguiente();
				} else if(donde.equals("siguiente")) {
					viajero.sig = viajero.siguiente().siguiente();
				} else {
					viajero.sig = anterior; //ahora siguiente apunta al anterior
					ultimo = anterior; //y el ultimo es el anterior
				}
			}
			
			cantTotalAlumn--;
			if(cantTotalAlumn == 0) {
				inicializarLista(); 
			} else if (cantTotalAlumn == 1) {
	            ultimo = primero;
	        }
		}
	}

	@Override
	public void ordenarLista() {
		for(int i = 0; i < cantTotalAlumn; i++) {
			Alumno viajero = primero;
			
			while(viajero != null && viajero.haySiguiente()) {
				Alumno aux = viajero.siguiente();
				String apellidoAct = viajero.getApellido();
				String apellidoSig = aux.getApellido();
				
				if(apellidoAct.compareToIgnoreCase(apellidoSig) > 0) { //si el apellido actual es "menor" al siguiente
					viajero.sig = aux.siguiente(); //el nodo al que apunta el siguiente del actual (o sea, aux) pasa a ser el siguiente del siguiente (o sea, el siguiente de aux)
					aux.sig = viajero; //y al nodo que apunta el siguiente del siguiente (o sea, el siguiente de aux), pasa a ser al actual. Basicamente intercambian punteros (o "posiciones" dicho de otra forma)
					
                    if (viajero == primero) { //Si cuando arrancó la comparación el apellido del siguiente directo era "menor", 
                        primero = aux;  //el "nuevo primero" pasa a ser el siguiente a viajero (aux) porque el viajero, implicitamente, pasaría a ser el primero. Ej: apellido primero = B, apellido del siguiente A -> el siguiente debería ser el primero, entonces intercambian posiciones para que quede: apellido primero = A, apellido siguiente = B  
                    } else { //si no son iguales
                        Alumno anterior = primero;
                        
                        while (anterior.haySiguiente() && anterior.siguiente() != viajero) { //busca el anterior a viajero para poder hacer que el siguiente (o sea, viajero cuando lo encuentre) apunte al siguiente de viajero
                            anterior = anterior.siguiente();
                        }
                        anterior.sig = aux; //y ahora el siguiente del anterior a viajero apunta al siguiente de viajero, al igual que viajero también apunta a ese mismo siguiente pero él espera que cambie
                    }
				}
				viajero = viajero.siguiente();
			}

			//if(viajero != null && huboCambio) {} //si en algun momento se eliminó algún elemento
			ultimo = viajero; //en cada iteración le da el valor actual del ultimo y en la proxima pasada lo acomoda como corresponde para que quede bien después al agregar otro alumno. Es medio raro y no termino de entender cómo funciona, lo probé por probar y funcionó
		}
	}

	public void imprimirLista() {
		ordenarLista();
		Alumno viajero = primero;
		
		System.out.println("--------- Lista de alumnos ---------");
		while(viajero.haySiguiente()) {
			System.out.println("Numero de legajo: "+viajero.getLeg()+" | Nombre: "+viajero.getNombre()+" | Apellido: "+viajero.getApellido());
			viajero = viajero.siguiente();
		}
		//es necesario hacer asi porque cuando viajero llegue al ultimo no lo va a imprimir. Podria arreglarse usando while(viajero != null){}, pero entiendo que el profesor quiere que usemos haySiguiente() justamente para estos casos
		System.out.println("Numero de legajo: "+viajero.getLeg()+" | Nombre: "+viajero.getNombre()+" | Apellido: "+viajero.getApellido());
	}

	public boolean noExiste(int legajo) {
		boolean noExiste = true;
		
		if(primero != null) { 
			Alumno viajero = primero;
			
			while(viajero.haySiguiente() && viajero.getLeg() != legajo) {
				viajero = viajero.siguiente();
			}
			
			if(viajero.getLeg() == legajo) { //es necesario hacer esto por como esta planteado el haySiguiente(). haySiguiente() valida si el siguiente valor != null, pero como en el ultimo elemento cargado el siguiente siempre va a ser null se pierde esa la ultima comparación por lo que hay que hacerlo manualmente
				noExiste = false;
			}
		}

		return noExiste;
	}
	
	@Override
	public boolean haySiguiente() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Alumno siguiente() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
