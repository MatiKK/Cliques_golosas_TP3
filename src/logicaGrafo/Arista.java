package logicaGrafo;

/**
 * Esta clase solo es usada cuando se itera un grafo
 * y se desean conocer sus aristas para poder graficarlas
 * o mostrarlas por consola.
 * Los grafos implementan el método {@link Grafo#aristasIterator()}
 * que devuelve un {@link java.util.Iterator}, diseñado específicamente
 * para que una misma arista no sea devuelta dos veces.
 */
public class Arista {

	private Vertice inicio;
	private double inicioPeso;
	private Vertice destino;
	private double destinoPeso;

	Arista(Vertice in, double inPeso, Vertice dest, double destPeso) {
		inicio = in;
		inicioPeso = inPeso;
		destino = dest;
		destinoPeso = destPeso;
	}

	public Vertice verticeInicio() {
		return inicio;
	}

	public double verticeInicioPeso() {
		return inicioPeso;
	}

	public Vertice verticeDestino() {
		return destino;
	}

	public double verticeDestinoPeso() {
		return destinoPeso;
	}

	@Override
	public String toString() {
		return inicio + "("+inicioPeso+")--->"+destino+"("+destinoPeso+")";
	}

	public boolean equals(Object o) {
		if (o instanceof Arista) {
			Arista ar = (Arista) o;
			return (inicio.equals(ar.destino) && destino.equals(ar.inicio))
					||
					(inicio.equals(ar.inicio) && destino.equals(ar.destino));
		}
		return false;
	}

	@Override
	public int hashCode() {
		return inicio.hashCode() + destino.hashCode();
	}

}
