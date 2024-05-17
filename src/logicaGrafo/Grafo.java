package logicaGrafo;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.TreeMap;
import java.util.TreeSet;

public class Grafo {

	private TreeMap<Vertice, TreeSet<Vertice>> listaVecinos;

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

	public void agregarVertice(Vertice v) {
		verificarQueElVerticeNoExiste(v);
		agregarVerticeSinChequear(v);
	}

	private void agregarVerticeSinChequear(Vertice v) {
		listaVecinos.put(v, new TreeSet<Vertice>());
	}

	public double pesoDeVertice(Vertice v) {
		verificarQueElVerticeExiste(v);
		return v.obtenerPeso();
	}

	public void agregarAristaEntreVertices(Vertice v1, Vertice v2) {
		verificarQueElVerticeExiste(v1);
		verificarQueElVerticeExiste(v2);
		verificarQueSonVerticesDistintos(v1, v2);
		agregarAristaEntreVerticesSinChequear(v1,v2);
	}

	private void agregarAristaEntreVerticesSinChequear(Vertice v1, Vertice v2) {
		listaVecinos.get(v1).add(v2);
		listaVecinos.get(v2).add(v1);
	}

	public boolean existeAristaEntreVertices(Vertice v1, Vertice v2) {
		verificarQueElVerticeExiste(v1);
		verificarQueElVerticeExiste(v2);
		return existeAristaEntreVerticesSinChequear(v1,v2);
	}

	boolean existeAristaEntreVerticesSinChequear(Vertice v1, Vertice v2) {
		return listaVecinos.get(v1).contains(v2);
	}

	private boolean elVerticeExiste(Vertice v) {
		return listaVecinos.containsKey(v);
	}

	private void verificarQueElVerticeExiste(Vertice v){
		if (!elVerticeExiste(v)) {
			throw new IllegalArgumentException(v + " no es un vértice");
		}
	}

	private void verificarQueElVerticeNoExiste(Vertice v){
		if (elVerticeExiste(v)) {
			throw new IllegalArgumentException(v + " ya es un vértice");
		}
	}

	private void verificarQueSonVerticesDistintos(Vertice v1, Vertice v2) {
		if (v1.equals(v2))
			throw new IllegalArgumentException("No se admiten aristas entre el mismo vértice");
	}

	public Collection<Vertice> vertices(){
		return new TreeSet<>(listaVecinos.keySet());
	}

	public TreeSet<Vertice> vecinosDelVertice(Vertice v){
		verificarQueElVerticeExiste(v);
		return listaVecinos.get(v);
	}

	public void data() {
		System.out.println("Grafo");
		System.out.println("Vertices: " + vertices());
		for (Vertice v: vertices()) {
			System.out.print("Vecinos de " + v + "(peso " + v.obtenerPeso()+ "): ");
			System.out.println(listaVecinos.get(v));
		}
	}

	/**
	 * Devuelve un {@link java.util.Iterator} de las aristas de este grafo.
	 * Está implementado de tal forma que no devuelve una misma arista dos veces,
	 *  es decir, si existe una arista entre los vertices A y B,
	 *  si este iterador devuelve la arista A-B, entonces NO devolverá la arista B-A.
	 *  Este diseño se pensó en la posible necesidad de querer mostrar por
	 *  consola las aristas de un grafo o de querer graficarlas,
	 *  haciendo que cada arista sea iterada una única vez.
	 * @return un iterador de las aristas de este grafo.
	 */
	public Iterator<Arista> aristasIterator(){

		return new Iterator<Arista>() {

			private HashSet<Arista> yaDevueltas = new HashSet<>();
			private Iterator<Vertice> porIterar = listaVecinos.keySet().iterator();
			private Vertice actual = porIterar.next();
			private Iterator<Vertice> vecinosDelActual = listaVecinos.get(actual).iterator();
			private Arista next;

			private void setNext(){
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
					double p1 = v1.obtenerPeso();
					double p2 = v2.obtenerPeso();
					next = new Arista(v1, p1, v2, p2);
				}
				while (yaDevueltas.contains(next));
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

	public Clique cliqueMasPesada() {
		return SolverCliqueMasPesada.cliqueMasPesada(this);
	}

}