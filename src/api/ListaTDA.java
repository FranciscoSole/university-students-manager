package api;
import impl.Alumno;

public interface ListaTDA {
	public void inicializarLista();
	public void agregarElemento(Alumno x);
	public void eliminarElemento(int legajo);
	public void ordenarLista();
	public boolean haySiguiente();
	public Alumno siguiente();
}
