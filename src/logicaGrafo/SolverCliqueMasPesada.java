package logicaGrafo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Clase Solver para la búsqueda de la clique más pesada de un grafo.
 */
public class SolverCliqueMasPesada<T extends Comparable<T>> {

	private GrafoPonderado<T> grafo;
	private ArrayList<T> verticesDeLaClique;
	private ArrayList<T> verticesDelGrafo;
	private Comparator<T> comparadorVertices;
	private double pesoClique;

	/**
	 *  Para evitar iterar todos los vértices cuando no sea necesario
	 */
	private int cantidadVecinosDelMayorVertice;

	SolverCliqueMasPesada(GrafoPonderado<T> g, Comparator<T> comparador) {
		if (g.cantidadVertices() == 0) {
			throw new IllegalArgumentException("Grafo sin vértices");
		}
		pesoClique = 0;
		grafo = g;
		verticesDelGrafo = new ArrayList<>(grafo.vertices());
		comparadorVertices = comparador;
		cantidadVecinosDelMayorVertice = 0;
		verticesDeLaClique = new ArrayList<>();
	}

	/**
	 * @return la clique más pesada del grafo de este Solver
	 */
	Clique<T> cliqueMasPesada() {
		Collections.sort(verticesDelGrafo, comparadorVertices);
		Collections.reverse(verticesDelGrafo);
		resolver();
		return new Clique<>(verticesDeLaClique, pesoClique);
	}

	/**
	 * Itera los vértices del grafo, desde el mayor vértice al menor
	 * (de acuerdo al critero del comparador en uso).
	 * verificando que sea factible que pertenezcan a la clique más pesada.
	 * En caso de serlo, se los agrega al ArrayList {@code verticesDeLaClique}
	 */
	private void resolver() {

		T mayorVertice = verticesDelGrafo.get(0);
		pesoClique += grafo.pesoDeVertice(mayorVertice);
		cantidadVecinosDelMayorVertice = grafo.vecinosDelVertice(mayorVertice).size();
		verticesDeLaClique.add(mayorVertice);

		for (int i = 1; i < verticesDelGrafo.size(); i++) {
			T v = verticesDelGrafo.get(i);
			if (siAgregoEsteVerticeSigueSiendoUnaClique(v)) {
				verticesDeLaClique.add(v);
				pesoClique += grafo.pesoDeVertice(v);
				if (verticesDeLaClique.size() == cantidadVecinosDelMayorVertice + 1)
					return;
			}
		}
	}

	/**
	 * Verifica si, agregando el vértice dado en el array de vértices (que es una
	 * clique del grafo del solver), dicho conjunto seguiría siendo una clique.
	 * 
	 * @param subClique sub-clique del grafo
	 * @param v         vértice con el cual chequear
	 * 
	 * @return {@code true} si la sub-clique seguiría siendo una clique si se añade
	 *         el vérice dado
	 */
	private boolean siAgregoEsteVerticeSigueSiendoUnaClique(T v) {
		for (T vertice : verticesDeLaClique) {
			if (!grafo.vecinosDelVertice(vertice).contains(v))
				return false;
		}
		return true;
	}

}