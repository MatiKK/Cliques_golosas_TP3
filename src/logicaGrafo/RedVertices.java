package logicaGrafo;

public abstract class RedVertices {

	public abstract int cantidadVertices();

	public abstract void agregarAristaEntreVertices(Vertice v1, Vertice v2);

	public abstract void quitarAristaEntreVertices(Vertice v1, Vertice v2);

	public abstract void agregarVertice(Vertice v);

	public void agregarVertices(Vertice... vertices) {
		for (Vertice v: vertices)
			agregarVertice(v);
	}

	/**
	 * Usar solo para muestras en consola
	 */
	public abstract void data();

	/**
	 * @return los vértices de este grafo
	 */
	public abstract java.util.Collection<Vertice> vertices();

	/**
	 * Devuelve un {@link java.util.Iterator} de las aristas de este grafo. Está
	 * implementado de tal forma que no devuelve una misma arista dos veces, es
	 * decir, si existe una arista entre los vertices A y B, si este iterador
	 * devuelve la arista A-B, entonces NO devolverá la arista B-A. Este diseño se
	 * pensó en la posible necesidad de querer mostrar por consola las aristas de un
	 * grafo o de querer graficarlas, haciendo que cada arista sea iterada una única
	 * vez, evitando la redundancia de datos.
	 * 
	 * @return un iterador de las aristas de este grafo
	 */
	public abstract java.util.Iterator<Arista> aristasIterator();
}
