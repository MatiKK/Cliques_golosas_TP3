package logicaGrafo;

import java.util.ArrayList;
import java.util.Collections;

public class SolverCliqueMasPesada {

	private Grafo grafo;
	private ArrayList<Vertice> verticesDeLaClique;
	private ArrayList<Vertice> verticesDelGrafo;

	public static Clique cliqueMasPesada(Grafo g) {
		return new SolverCliqueMasPesada(g).cliqueMasPesada();
	}

	private SolverCliqueMasPesada(Grafo g) {
		grafo = g;
		verticesDeLaClique = new ArrayList<>();
		verticesDelGrafo = new ArrayList<>(grafo.vertices());
		Collections.reverse(verticesDelGrafo);
	}

	private Clique cliqueMasPesada(){
		resolver();
		return new Clique(verticesDeLaClique);
	}

	private void resolver() {
		
		for (int i = 0; i < verticesDelGrafo.size(); i++) {
			Vertice v = verticesDelGrafo.get(i);
			if (siAgregoEsteVerticeSigueSiendoUnaClique(verticesDeLaClique, v)) {
				verticesDeLaClique.add(v);
			}
		}
	}

	/**
	 * Verifica si, agregando el vértice en el conjunto de vértices
	 * (que que es una clique), dicho conjunto  seguirá siendo una clique.
	 * @param subClique sub-clique del grafo
	 * @param v vértice con el que chequear
	 * 
	 * @return {@code true} si la sub-clique seguirá
	 * siendo una clique si se añade el vérice dado
	 */
	private boolean siAgregoEsteVerticeSigueSiendoUnaClique(ArrayList<Vertice> subClique, Vertice v) {
		for (Vertice vertice: subClique) {
			if (!grafo.vecinosDelVertice(vertice).contains(v)) {
				return false;
			}
		}
		return true;
	}

}