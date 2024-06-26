package logicaGrafo;

import org.openstreetmap.gui.jmapviewer.Coordinate;

/**
 * Clase que representa el vértice de un grafo. La clase {@link Grafo} solo
 * trabaja con instancias de esta clase.
 */
public class Vertice implements Comparable<Vertice> {

	private String nombre; // para representarlo en un gráfico
	private double peso;
	private Coordinate coordenada;

	/**
	 * Crea un vértice con un nombre representativo y un peso
	 * 
	 * @param n nombre del vértice (si se pasa un objeto, se usará su representación
	 *          {@link Object#toString()})
	 * @param p peso del vertice
	 * @throws IllegalArgumentException si el peso es negativo
	 */
	public Vertice(Object n, double p, Coordinate cord) {
		if (p < 0) {
			throw new IllegalArgumentException("Peso negativo no es válido.");
		}
		nombre = n.toString();
		peso = p;
		coordenada = cord;
	}

	/**
	 * 
	 * @return el peso del vértice
	 */
	public double obtenerPeso() {
		return peso;
	}
	
	public Coordinate getCordenada() {
		return coordenada;
	}
	

	@Override
	public String toString() {
		return nombre;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Vertice) {
			Vertice v = (Vertice) o;
			return peso == v.peso && nombre.equals(v.nombre);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return 31 * Double.hashCode(peso) + nombre.hashCode();
	}

	@Override
	public int compareTo(Vertice v) {
		int x = Double.compare(peso, v.peso);

		// Sin esto no permite vértices de mismo peso y se rompe
		if (x == 0) {
			x = nombre.compareTo(v.nombre);
		}

		return x;
	}

}
