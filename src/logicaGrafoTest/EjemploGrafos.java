package logicaGrafoTest;

import logicaGrafo.Grafo;
import logicaGrafo.Vertice;

public class EjemploGrafos {

	/**
	 * Grafo de la diapositiva del TP3
	 */
	private static Grafo grafo1;
	private static Grafo grafo2;
	private static Grafo grafo3;

	/**
	 * @return Grafo de la diapositiva del TP3
	 */
	public static Grafo grafo1() {
		if (grafo1 == null)
			inicializarGrafo1();
		return grafo1;
	}

	public static Grafo grafo2() {
		if (grafo2 == null)
			inicializarGrafo2();
		return grafo2;
	}

	public static Grafo grafo3() {
		if (grafo3 == null)
			inicializarGrafo3();
		return grafo3;
	}

	private static void inicializarGrafo1() {
		// Grafo de la diapositiva del TP3
		Grafo g = new Grafo();

		Vertice v1 = new Vertice(1, 11.0);
		Vertice v2 = new Vertice(2, 5.5);
		Vertice v3 = new Vertice(3, 1.1);
		Vertice v4 = new Vertice(4, 7.0);
		Vertice v5 = new Vertice(5, 2.5);
		Vertice v6 = new Vertice(6, 3.5);

		g.agregarVertices(v1, v2, v3, v4, v5, v6);

		g.agregarAristaEntreVertices(v1, v2);
		g.agregarAristaEntreVertices(v1, v4);
		g.agregarAristaEntreVertices(v2, v4);
		g.agregarAristaEntreVertices(v2, v3);
		g.agregarAristaEntreVertices(v2, v5);
		g.agregarAristaEntreVertices(v2, v6);
		g.agregarAristaEntreVertices(v3, v5);
		g.agregarAristaEntreVertices(v4, v5);
		g.agregarAristaEntreVertices(v4, v6);
		g.agregarAristaEntreVertices(v5, v6);

		// la mayor clique es con los vertices {1,2,4}
		// Si no coincide es por culpa del algoritmo goloso
		grafo1 = g;
	}

	private static void inicializarGrafo2() {
		Grafo g = new Grafo();

		Vertice v1 = new Vertice(1, 50);
		Vertice v2 = new Vertice(2, 100);
		Vertice v3 = new Vertice(3, 30);
		Vertice v4 = new Vertice(4, 10);
		Vertice v5 = new Vertice(5, 8);

		g.agregarVertices(v1, v2, v3, v4, v5);

		g.agregarAristaEntreVertices(v1, v2);
		g.agregarAristaEntreVertices(v2, v3);
		g.agregarAristaEntreVertices(v3, v4);
		g.agregarAristaEntreVertices(v4, v1);
		g.agregarAristaEntreVertices(v5, v2);
		g.agregarAristaEntreVertices(v5, v3);
		g.agregarAristaEntreVertices(v5, v4);

		// la mayor clique es con los vertices {1,2}
		// Si no coincide es por culpa del algoritmo goloso
		grafo2 = g;
	}

	private static void inicializarGrafo3() {
		Grafo g = new Grafo();

		Vertice v0 = new Vertice(0, 4);
		Vertice v1 = new Vertice(1, 3);
		Vertice v2 = new Vertice(2, 4);
		Vertice v3 = new Vertice(3, 1);
		Vertice v4 = new Vertice(4, 3);
		Vertice v5 = new Vertice(5, 1);
		Vertice v6 = new Vertice(6, 3);
		Vertice v7 = new Vertice(7, 1);
		Vertice v8 = new Vertice(8, 2);
		Vertice v9 = new Vertice(9, 2);

		g.agregarVertices(v0, v1, v2, v3, v4, v5, v6, v7, v8, v9);

		g.agregarAristaEntreVertices(v7, v2);
		g.agregarAristaEntreVertices(v2, v5);
		g.agregarAristaEntreVertices(v2, v8);
		g.agregarAristaEntreVertices(v5, v6);
		g.agregarAristaEntreVertices(v5, v0);
		g.agregarAristaEntreVertices(v0, v3);
		g.agregarAristaEntreVertices(v1, v0);
		g.agregarAristaEntreVertices(v6, v3);
		g.agregarAristaEntreVertices(v6, v4);
		g.agregarAristaEntreVertices(v3, v4);
		g.agregarAristaEntreVertices(v3, v1);
		g.agregarAristaEntreVertices(v1, v8);
		g.agregarAristaEntreVertices(v4, v9);
		g.agregarAristaEntreVertices(v8, v9);

		// la mayor clique es con los vertices {0,1,3}
		// Si no coincide es por culpa del algoritmo goloso
		grafo3 = g;
	}

}
