package logicaGrafo;

import java.util.*;

public class SolverCliqueMasPesada {

	// TODO
	// BORRAR TODOS LOS COMENTARIOS ANTES DE ENTREGAR

	private Grafo grafo;
	private ArrayList<Vertice> verticesDeLaClique;
	private ArrayList<Vertice> verticesDelGrafo;

	public static Clique cliqueMasPesada(Grafo g) {
		return new SolverCliqueMasPesada(g).cliqueMasPesada();
	}

	private SolverCliqueMasPesada(Grafo g) {
		grafo = g;
		verticesDelGrafo = new ArrayList<>(grafo.vertices());
		verticesDeLaClique = new ArrayList<>();
	}

	private Clique cliqueMasPesada(){
		resolver();
		return new Clique(verticesDeLaClique);
	}

	private void resolver() {
		
		/* la clase Vertice implementa Comparable de forma que se ordena
		 * de mayor a menor de acuerdo al peso.
		 * Por eso en vertices del grafo ya están ordenadas de esa forma
		 * y se pueden iterar sabiendo que la primera es la más pesada
		 * y la última la menos pesada y así
		*/
		for (int i = 0; i < verticesDelGrafo.size(); i++) {
			Vertice v = verticesDelGrafo.get(i);

			// verifico si agregando v, sigue siendo una clique
			if (siAgregoEsteVerticeFormoUnaClique(verticesDeLaClique, v)) {
				// si lo sigue siendo lo agrego
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
	private boolean siAgregoEsteVerticeFormoUnaClique(ArrayList<Vertice> subClique, Vertice v) {
		for (Vertice vertice: subClique) {
			if (!grafo.vecinosDelVertice(vertice).contains(v)) {
				return false;
			}
		}
		return true;
	}

}