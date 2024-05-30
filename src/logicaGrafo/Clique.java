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
public class Clique<T> extends Grafo<T>{

	private final ArrayList<T> vertices;
	private final double peso;

	/**
	 * Crea una clique a partir de los vértices dados
	 * 
	 * @param verticesClique vértices que se desean para la clique
	 * @throws NullPointerException si vericesClique es {@code null}
	 */
	Clique(ArrayList<T> verticesClique, double peso) {
		this.vertices = new ArrayList<>();
		for (T v : verticesClique) {
			vertices.add(v);
		}
		this.peso = peso;
	}

	public int cantidadVertices() {
		return vertices.size();
	}

	public double peso() {
		return peso;
	}

	public Collection<T> vertices() {
		return vertices;
	}

	public Iterator<Arista<T>> aristasIterator() {
		return new Iterator<Arista<T>>() {
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
			public Arista<T> next() {
				try {
					ind2++;
					if (ind2 == cantidadVertices) {
						ind1++;
						ind2 = ind1 + 1;
					}
					T v1 = vertices.get(ind1);
					T v2 = vertices.get(ind2);
					return new Arista<>(v1, v2);
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
		Iterator<Arista<T>> aristas = aristasIterator();
		while (aristas.hasNext()) {
			System.out.println(aristas.next());
		}
	}

}