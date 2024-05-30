package logicaGrafo;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
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
public class GrafoPonderado<T extends Comparable<T>> extends Grafo<T>{

	private TreeMap<T, TreeSet<T>> listaVecinos;
	private HashMap<T, Double> pesos;

	/**
	 * Crea un grafo sin vértices iniciales
	 */
	public GrafoPonderado() {
		listaVecinos = new TreeMap<>();
		pesos = new HashMap<>();
	}

	public int cantidadVertices() {
		return listaVecinos.size();
	}

	/**
	 * Agrega el vértice a este grafo
	 * 
	 * @param v nuevo vértice para agregar
	 * @throws IllegalArgumentException si el vértice ya es parte del grafo
	 */
	public void agregarVertice(T v, double peso) {
		verificarQueElVerticeNoExiste(v);
		verificarPesoAceptable(peso);
		agregarVerticeSinChequear(v, peso);
	}

	public double pesoDeVertice(T v) {
		verificarQueElVerticeExiste(v);
		return pesos.get(v).doubleValue();
	}

	public void cambiarPesoDeVertice(T v, double nuevoPeso) {
		verificarQueElVerticeExiste(v);
		pesos.put(v, nuevoPeso);
	}

	private void verificarPesoAceptable(double peso) {
		if (peso < 0)
			throw new IllegalArgumentException("Peso negativo no aceptable");
	}

	private void agregarVerticeSinChequear(T v, double peso) {
		listaVecinos.put(v, new TreeSet<T>());
		pesos.put(v, peso);
	}

	public void agregarAristaEntreVertices(T v1, T v2) {
		verificarQueElVerticeExiste(v1);
		verificarQueElVerticeExiste(v2);
		verificarQueSonVerticesDistintos(v1, v2);
		agregarAristaEntreVerticesSinChequear(v1, v2);
	}

	private void agregarAristaEntreVerticesSinChequear(T v1, T v2) {
		listaVecinos.get(v1).add(v2);
		listaVecinos.get(v2).add(v1);
	}

	public void quitarAristaEntreVertices(T v1, T v2) {
		verificarQueElVerticeExiste(v1);
		verificarQueElVerticeExiste(v2);
		quitarAristaEntreVerticesSinChequear(v1, v2);
	}

	private void quitarAristaEntreVerticesSinChequear(T v1, T v2) {
		listaVecinos.get(v1).remove(v2);
		listaVecinos.get(v2).remove(v1);
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
	public boolean existeAristaEntreVertices(T v1, T v2) {
		verificarQueElVerticeExiste(v1);
		verificarQueElVerticeExiste(v2);
		return existeAristaEntreVerticesSinChequear(v1, v2);
	}

	/**
	 * No es privado para optimizar en {@link SolverCliqueMasPesada}
	 */
	boolean existeAristaEntreVerticesSinChequear(T v1, T v2) {
		return listaVecinos.get(v1).contains(v2);
	}

	private boolean elVerticeExiste(T v) {
		return listaVecinos.containsKey(v);
	}

	protected void verificarQueElVerticeExiste(T v) {
		if (!elVerticeExiste(v)) {
			throw new IllegalArgumentException(v + " no es un vértice de este grafo.");
		}
	}

	protected void verificarQueElVerticeNoExiste(T v) {
		if (elVerticeExiste(v)) {
			throw new IllegalArgumentException(v + " ya es un vértice de este grafo.");
		}
	}

	private void verificarQueSonVerticesDistintos(T v1, T v2) {
		if (v1.equals(v2))
			throw new IllegalArgumentException("No se admite arista entre un mismo vértice (bucle).");
	}

	public Collection<T> vertices() {
		return new TreeSet<>(listaVecinos.keySet());
	}

	/**
	 * Devuelve los vecinos de un vértice
	 * 
	 * @param v vértice del cual se desean sus vecinos
	 * @return los vecinos del vértice
	 * @throws IllegalArgumentException si el vértice no es parte de este grafo
	 */
	public TreeSet<T> vecinosDelVertice(T v) {
		verificarQueElVerticeExiste(v);
		return listaVecinos.get(v);
	}

	public void data() {
		System.out.println("Grafo");
		System.out.println("Vertices: " + vertices());
		for (T v : vertices()) {
			System.out.print("Vecinos de " + v + "(peso " + pesos.get(v) + "): ");
			System.out.println(listaVecinos.get(v));
		}
	}

	public Iterator<Arista<T>> aristasIterator() {

		return new Iterator<Arista<T>>() {

			private HashSet<Arista<T>> yaDevueltas = new HashSet<>();
			private Iterator<T> porIterar = listaVecinos.keySet().iterator();
			private T actual = porIterar.next();
			private Iterator<T> vecinosDelActual = listaVecinos.get(actual).iterator();
			private Arista<T> next;

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
					T v1 = actual;
					T v2 = vecinosDelActual.next();
					next = new Arista<>(v1, v2);
				} while (yaDevueltas.contains(next));
				yaDevueltas.add(next);
			}

			@Override
			public boolean hasNext() {
				setNext();
				return next != null;
			}

			@Override
			public Arista<T> next() {
				if (next == null)
					throw new NoSuchElementException();
				return next;
			}
		};
	}

	Comparator<T> comparadorPorPeso(){
		return new Comparator<T>() {
			@Override
			public int compare(T o1, T o2) {
				int x = pesos.get(o1).compareTo(pesos.get(o2));
				if (x == 0)
					x = o1.compareTo(o2);
				return x;
			}
		};
	}

	Comparator<T> comparadorPorCantidadVecinos(){
		return new Comparator<T>() {
			@Override
			public int compare(T o1, T o2) {
				// compara la cantidad de vecinos
				int x = vecinosDelVertice(o1).size() - vecinosDelVertice(o2).size();
				// si tienen la misma cantidad,
				// ordena por el peso
				if (x == 0) {
					x = pesos.get(o1).compareTo(pesos.get(o2));
					if (x == 0)
						x = o1.compareTo(o2);
				}
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
	public Clique<T> cliqueMasPesadaOrdenandoPorPeso() {
		return new SolverCliqueMasPesada<>(this,
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
	public Clique<T> cliqueMasPesadaOrdenandoPorCantidadVecinos() {
		return new SolverCliqueMasPesada<>(this,
				comparadorPorCantidadVecinos()).cliqueMasPesada();
	}

}