package logicaGrafo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Clase que representa una clique.
 * Una clique es un conjunto de vértices donde todos los vértoces son vecinos
 * entre sí. Un grafo puede tener cliques como subgrafos.
 */
public class Clique extends RedVertices {

	private final ArrayList<Vertice> vertices;
	private double peso;

	/**
	 * Crea una clique a partir de los vértices dados
	 * 
	 * @param verticesClique vértices que se desean para la clique
	 * @throws NullPointerException si vericesClique es {@code null}
	 */
	public Clique(ArrayList<Vertice> verticesClique) {
		this.vertices = new ArrayList<>();
		for (Vertice v : verticesClique) {
			vertices.add(v);
			peso += v.obtenerPeso();
		}
	}

	public void agregarVertice(Vertice v) {
		if (vertices.contains(v))
			throw new IllegalArgumentException();
		vertices.add(v);
	}

	public void agregarAristaEntreVertices(Vertice v1, Vertice v2) {
		throw new UnsupportedOperationException();
	}

	public void quitarAristaEntreVertices(Vertice v1, Vertice v2) {
		throw new UnsupportedOperationException();
	}

	public int cantidadVertices() {
		return vertices.size();
	}

	public double peso() {
		return peso;
	}

	public Collection<Vertice> vertices() {
		return vertices;
	}

	public Iterator<Arista> aristasIterator() {
		return new Iterator<Arista>() {
			private int cantidadVertices = cantidadVertices(),
						ind1 = 0,
						ind2 = 0;

			@Override
			public boolean hasNext() {
				boolean a, b;
				a = ind1 >= cantidadVertices - 2;
				b = ind2 >= cantidadVertices - 1;
				return !(a && b);
			}

			@Override
			public Arista next() {
				try {
					ind2++;
					if (ind2 == cantidadVertices) {
						ind1++;
						ind2 = ind1 + 1;
					}
					Vertice v1 = vertices.get(ind1);
					Vertice v2 = vertices.get(ind2);
					return new Arista(v1, v2);
				} catch (IndexOutOfBoundsException e) {
					throw new NoSuchElementException();
				}
			}
		};
	}

	public void data() {
		System.out.println("Clique de peso " + peso());
		System.out.println("Vertices: " + vertices());
		for (Vertice v : vertices()) {
			System.out.print("Vecinos de " + v + "(peso " + v.obtenerPeso() + "): ");
			@SuppressWarnings("unchecked")
			ArrayList<Vertice> ver = (ArrayList<Vertice>)vertices.clone();
			ver.remove(v);
			System.out.println(ver);
		}			
	}

}