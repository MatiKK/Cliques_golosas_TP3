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
public class Clique {

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

	/**
	 * @return la cantidad de vertices de esta clique
	 */
	public int cantidadVertices() {
		return vertices.size();
	}

	/**
	 * @return el peso de esta clique
	 */
	public double peso() {
		return peso;
	}

	/**
	 * @return los vértices de esta clique
	 */
	public Collection<Vertice> vertices() {
		return vertices;
	}

	/**
	 * Devuelve un {@link java.util.Iterator} de las aristas de esta clique. Está
	 * implementado de tal forma que no devuelve una misma arista dos veces, es
	 * decir, si existe una arista entre los vertices A y B, si este iterador
	 * devuelve la arista A-B, entonces NO devolverá la arista B-A. Este diseño se
	 * pensó en la posible necesidad de querer mostrar por consola las aristas de
	 * una cluque o de querer graficarlas, haciendo que cada arista sea iterada
	 * una única vez, evitando la redundancia de datos.
	 * 
	 * @return un iterador de las aristas de este grafo
	 */
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
		System.out.println("Aristas:");
		Iterator<Arista> aristas = aristasIterator();
		while (aristas.hasNext()) {
			System.out.println(aristas.next());
		}
	}

}