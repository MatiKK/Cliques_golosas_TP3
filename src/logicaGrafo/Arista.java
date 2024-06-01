package logicaGrafo;

/** Clase que representa la arista entre dos vértices. <p>
 * Esta clase solo es usada cuando se itera un grafo y se desean conocer sus
 * aristas para poder graficarlas o mostrarlas por consola. Los grafos
 * implementan el método {@link Grafo#aristasIterator()} que devuelve un
 * {@link java.util.Iterator}, diseñado específicamente para que una misma
 * arista no sea devuelta dos veces.
 */
public class Arista {

	private Vertice inicio;
	private Vertice destino;
	private double peso;

	Arista(Vertice in, Vertice dest) {
		inicio = in;
		destino = dest;
	}
	
	public Arista(Vertice in, Vertice dest, double pe) {
		inicio = in;
		destino = dest;
		peso = pe;
		
	}
	
	

	/**
	 * @return el primer vértice extremo de esta arista
	 */
	public Vertice verticeInicio() {
		return inicio;
	}

	/**
	 * @return el segundo vértice extremo de esta arista
	 */
	public Vertice verticeDestino() {
		return destino;
	}

	@Override
	public String toString() {
		return inicio + "(" + inicio.obtenerPeso() + ")--->" + destino + "(" + destino.obtenerPeso() + ")";
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Arista) {
			Arista ar = (Arista) o;
			return (inicio.equals(ar.destino) && destino.equals(ar.inicio))
					|| (inicio.equals(ar.inicio) && destino.equals(ar.destino));
		}
		return false;
	}

	@Override
	public int hashCode() {
		return inicio.hashCode() + destino.hashCode();
	}

}
