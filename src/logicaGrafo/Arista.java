package logicaGrafo;

/** Clase que representa la arista entre dos vértices. <p>
 * Esta clase solo es usada cuando se itera un grafo y se desean conocer sus
 * aristas para poder graficarlas o mostrarlas por consola. Los grafos
 * implementan el método {@link Grafo#aristasIterator()} que devuelve un
 * {@link java.util.Iterator}, diseñado específicamente para que una misma
 * arista no sea devuelta dos veces.
 */
public class Arista<T> {

	private T inicio;
	private T destino;

	Arista(T in, T dest) {
		inicio = in;
		destino = dest;
	}

	/**
	 * @return el primer vértice extremo de esta arista
	 */
	public T verticeInicio() {
		return inicio;
	}

	/**
	 * @return el segundo vértice extremo de esta arista
	 */
	public T verticeDestino() {
		return destino;
	}

	@Override
	public String toString() {
		return inicio + " ---> " + destino;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Arista<?>) {
			Arista<?> ar = (Arista<?>) o;
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
