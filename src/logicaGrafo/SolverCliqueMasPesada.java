package logicaGrafo;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Clase Solver para la búsqueda de la clique más pesada de un grafo.
 */
public class SolverCliqueMasPesada {

	private Grafo grafo;
	private ArrayList<Vertice> verticesDeLaClique;
	private ArrayList<Vertice> verticesDelGrafo;

	/**
	 *  Para evitar iterar todos los vértices cuando no sea necesario
	 */
	private int cantidadVecinosDelMayorVertice;

	/**
	 * Devuelve la clique más pesada del grafo dado.
	 * @param g grafo del cual se quiere la clique más pesada
	 * @return la cliquee más pesada del grafo g
	 */
	public static Clique cliqueMasPesada(Grafo g) {
		return new SolverCliqueMasPesada(g).cliqueMasPesada();
	}

	/**
	 * Instancia un Solver para hallar la clique más pesada de un grafo.
	 * Acá solo se instancian las colleciones que se van a usar para el hallazgo
	 * de la clique, y se ordenan de mayor peso a menor peso los vértices del grafo.
	 * 
	 * @param g grafo del cual se quiere hallar la clique más pesada
	 */
	private SolverCliqueMasPesada(Grafo g) {
		grafo = g;
		cantidadVecinosDelMayorVertice = 0;
		verticesDeLaClique = new ArrayList<>();
		verticesDelGrafo = new ArrayList<>(grafo.vertices());
		Collections.reverse(verticesDelGrafo);
	}

	/**
	 * @return la clique más pesada del grafo de este Solver
	 */
	private Clique cliqueMasPesada() {

		if (verticesDelGrafo.isEmpty())
			throw new IllegalArgumentException("Grafo sin vértices");
		resolver();
		return new Clique(verticesDeLaClique);
	}

	/**
	 * Itera los vértices del grafo, del más pesado al menos pesado,
	 * verificando que sea factible que pertenezcan a la clique más pesada.
	 * En caso de serlo, se los agrega al ArrayList verticesDeLaClique
	 */
	private void resolver() {

		Vertice mayorVertice = verticesDelGrafo.get(0);
		cantidadVecinosDelMayorVertice = grafo.vecinosDelVertice(mayorVertice).size();
		verticesDeLaClique.add(mayorVertice);

		for (int i = 1; i < verticesDelGrafo.size(); i++) {
			Vertice v = verticesDelGrafo.get(i);
			if (siAgregoEsteVerticeSigueSiendoUnaClique(verticesDeLaClique, v)) {
				verticesDeLaClique.add(v);
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
	private boolean siAgregoEsteVerticeSigueSiendoUnaClique(ArrayList<Vertice> subClique, Vertice v) {
		for (Vertice vertice : subClique) {
			if (!grafo.vecinosDelVertice(vertice).contains(v))
				return false;
		}
		return true;
	}

}