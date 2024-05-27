package logicaGrafo;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Clase que representa un grafo, donde sus vértices poseen un peso (positivo).
 * Un grafo admite agregar vértices, agregar aristas entre vértices, y la
 * verificación de datos, tales como consultar el peso de un vértice, la
 * existencia de una arista entre dos vértices o la cantidad de vértices.
 */
public class Grafo {

	private TreeMap<Vertice, TreeSet<Vertice>> listaVecinos;

	/**
	 * Crea un grafo sin vértices iniciales
	 */
	public Grafo() {
		listaVecinos = new TreeMap<>();
	}

	/**
	 * 
	 * @return la cantidad de vertices de este grafo
	 */
	public int cantidadVertices() {
		return listaVecinos.size();
	}

	/**
	 * Agrega todos los vértices a este grafo
	 * 
	 * @param vertices vértices nuevos para agregar
	 */
	public void agregarVertices(Vertice... vertices) {
		if (cantidadVertices() == 0) {
			for (Vertice v : vertices)
				agregarVerticeSinChequear(v);
		} else {
			for (Vertice v : vertices)
				agregarVertice(v);
		}
	}

	/**
	 * Agrega el vértice a este grafo
	 * 
	 * @param v nuevo vértice para agregar
	 * @throws IllegalArgumentException si el vértice ya es parte del grafo
	 */
	public void agregarVertice(Vertice v) {
		verificarQueElVerticeNoExiste(v);
		agregarVerticeSinChequear(v);
	}

	private void agregarVerticeSinChequear(Vertice v) {
		listaVecinos.put(v, new TreeSet<Vertice>());
	}

	/**
	 * Agrega una arista entre dos vértices
	 * 
	 * @param v1 primer vértice
	 * @param v2 segundo vértice
	 * @throws IllegalArgumentException si alguno de los dos vértices no es parte de
	 *                                  este grafo o si los dos vértices son iguales
	 *                                  (no se admiten bucles)
	 */
	public void agregarAristaEntreVertices(Vertice v1, Vertice v2) {
		verificarQueElVerticeExiste(v1);
		verificarQueElVerticeExiste(v2);
		verificarQueSonVerticesDistintos(v1, v2);
		agregarAristaEntreVerticesSinChequear(v1, v2);
	}

	private void agregarAristaEntreVerticesSinChequear(Vertice v1, Vertice v2) {
		listaVecinos.get(v1).add(v2);
		listaVecinos.get(v2).add(v1);
	}

	/**
	 * Verifica si existe arista entre dos vértices
	 * 
	 * @param v1 primer vértice
	 * @param v2 segundo vértice
	 * @return {@code true} si existe arista entre los dos vértices
	 * @throws IllegalArgumentException si alguno de los dos vértices no es parte de
	 *                                  este grafo
	 */
	public boolean existeAristaEntreVertices(Vertice v1, Vertice v2) {
		verificarQueElVerticeExiste(v1);
		verificarQueElVerticeExiste(v2);
		return existeAristaEntreVerticesSinChequear(v1, v2);
	}

	/**
	 * No es privado para optimizar en {@link SolverCliqueMasPesada}
	 */
	boolean existeAristaEntreVerticesSinChequear(Vertice v1, Vertice v2) {
		return listaVecinos.get(v1).contains(v2);
	}

	private boolean elVerticeExiste(Vertice v) {
		return listaVecinos.containsKey(v);
	}

	protected void verificarQueElVerticeExiste(Vertice v) {
		if (!elVerticeExiste(v)) {
			throw new IllegalArgumentException(v + " no es un vértice de este grafo.");
		}
	}

	protected void verificarQueElVerticeNoExiste(Vertice v) {
		if (elVerticeExiste(v)) {
			throw new IllegalArgumentException(v + " ya es un vértice de este grafo.");
		}
	}

	private void verificarQueSonVerticesDistintos(Vertice v1, Vertice v2) {
		if (v1.equals(v2))
			throw new IllegalArgumentException("No se admite arista entre un mismo vértice (bucle).");
	}

	/**
	 * @return los vértices de este grafo
	 */
	public Collection<Vertice> vertices() {
		return new TreeSet<>(listaVecinos.keySet());
	}

	/**
	 * Devuelve los vecinos de un vértice
	 * 
	 * @param v vértice del cual se desean sus vecinos
	 * @return los vecinos del vértice
	 * @throws IllegalArgumentException si el vértice no es parte de este grafo
	 */
	public TreeSet<Vertice> vecinosDelVertice(Vertice v) {
		verificarQueElVerticeExiste(v);
		return listaVecinos.get(v);
	}

	/**
	 * Usar solo para muestras en consola
	 */
	public void data() {
		System.out.println("Grafo");
		System.out.println("Vertices: " + vertices());
		for (Vertice v : vertices()) {
			System.out.print("Vecinos de " + v + "(peso " + v.obtenerPeso() + "): ");
			System.out.println(listaVecinos.get(v));
		}
	}

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
	public Iterator<Arista> aristasIterator() {

		return new Iterator<Arista>() {

			private HashSet<Arista> yaDevueltas = new HashSet<>();
			private Iterator<Vertice> porIterar = listaVecinos.keySet().iterator();
			private Vertice actual = porIterar.next();
			private Iterator<Vertice> vecinosDelActual = listaVecinos.get(actual).iterator();
			private Arista next;

			private void setNext() {
				do {
					while (!vecinosDelActual.hasNext()) {
						if (!porIterar.hasNext()) {
							next = null;
							return;
						} else {
							actual = porIterar.next();
							vecinosDelActual = listaVecinos.get(actual).iterator();
						}
					}
					Vertice v1 = actual;
					Vertice v2 = vecinosDelActual.next();
					next = new Arista(v1, v2);
				} while (yaDevueltas.contains(next));
				yaDevueltas.add(next);
			}

			@Override
			public boolean hasNext() {
				setNext();
				return next != null;
			}

			@Override
			public Arista next() {
				if (next == null)
					throw new NoSuchElementException();
				return next;
			}
		};
	}

	Comparator<Vertice> comparadorPorPeso(){
		return new Comparator<Vertice>() {
			@Override
			public int compare(Vertice o1, Vertice o2) {
				return o1.compareTo(o2);
			}
		};
	}

	Comparator<Vertice> comparadorPorCantidadVecinos(){
		return new Comparator<Vertice>() {
			@Override
			public int compare(Vertice o1, Vertice o2) {
				int x = vecinosDelVertice(o1).size() - vecinosDelVertice(o2).size();
				if (x == 0)
					x = o1.compareTo(o2);
				return x;
			}
		};
	}

	/**
	 * Devuelve la clique más pesada, donde el algoritmo de evaluación
	 * ordena los vértices del grafo de acuerdo su peso.
	 * Este criterio se basa en que la clique más pesada debe
	 * contener a los vértices más pesados.
	 * @return Devuelve la clique más pesada ordenando los vértices del más al menos pesado
	 */
	public Clique cliqueMasPesadaOrdenandoPorPeso() {
		return new SolverCliqueMasPesada(this,
				comparadorPorPeso()).cliqueMasPesada();
	}

	/**
	 * Devuelve la clique más pesada, donde el algoritmo de evaluación
	 * ordena los vértices del grafo de acuerdo la cantidad de vecinos de cada uno.
	 * Este criterio se basa en que, mientras más vértices, más peso tendrá la clique,
	 * entonces busca los vértices con más vecinos, con mayor posibilidad de obtener
	 * cliques de tamaño grandes.
	 * @return Devuelve la clique más pesada ordenando los vértices
	 * de los que tienen más a menos vecinos
	 */
	public Clique cliqueMasPesadaOrdenandoPorCantidadVertices() {
		return new SolverCliqueMasPesada(this,
				comparadorPorCantidadVecinos()).cliqueMasPesada();
	}

}