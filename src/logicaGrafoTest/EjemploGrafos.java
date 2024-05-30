package logicaGrafoTest;

import logicaGrafo.GrafoPonderado;

public class EjemploGrafos {

	/**
	 * Grafo de la diapositiva del TP3
	 */
	private static GrafoPonderado<Integer> grafo1;
	private static GrafoPonderado<Integer> grafo2;
	private static GrafoPonderado<Integer> grafo3;

	/**
	 * @return Grafo de la diapositiva del TP3
	 */
	public static GrafoPonderado<Integer> grafo1() {
		if (grafo1 == null)
			inicializarGrafo1();
		return grafo1;
	}

	public static GrafoPonderado<Integer> grafo2() {
		if (grafo2 == null)
			inicializarGrafo2();
		return grafo2;
	}

	public static GrafoPonderado<Integer> grafo3() {
		if (grafo3 == null)
			inicializarGrafo3();
		return grafo3;
	}

	private static void inicializarGrafo1() {
		// Grafo de la diapositiva del TP3
		GrafoPonderado<Integer> g = new GrafoPonderado<>();

		Integer v1 = 1;
		g.agregarVertice(v1,11);
		Integer v2 = 2;
		g.agregarVertice(v2,5.5);
		Integer v3 = 3;
		g.agregarVertice(v3,1.1);
		Integer v4 = 4;
		g.agregarVertice(v4,7);
		Integer v5 = 5;
		g.agregarVertice(v5,2.5);
		Integer v6 = 6;
		g.agregarVertice(v6,3.5);

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

		// la mayor clique es con los vertices {1,2,4} con peso 23.5
		// Si no coincide es por culpa del algoritmo goloso
		grafo1 = g;
	}

	private static void inicializarGrafo2() {
		GrafoPonderado<Integer> g = new GrafoPonderado<>();

		Integer v1 = 1;
		Integer v2 = 2;
		Integer v3 = 3;
		Integer v4 = 4;
		Integer v5 = 5;

		g.agregarVertice(v1,  50);
		g.agregarVertice(v2,  100);
		g.agregarVertice(v3,  30);
		g.agregarVertice(v4,  10);
		g.agregarVertice(v5,  8);

		g.agregarAristaEntreVertices(v1, v2);
		g.agregarAristaEntreVertices(v2, v3);
		g.agregarAristaEntreVertices(v3, v4);
		g.agregarAristaEntreVertices(v4, v1);
		g.agregarAristaEntreVertices(v5, v2);
		g.agregarAristaEntreVertices(v5, v3);
		g.agregarAristaEntreVertices(v5, v4);

		// la mayor clique es con los vertices {1,2} con peso 150
		// Si no coincide es por culpa del algoritmo goloso
		grafo2 = g;
	}

	private static void inicializarGrafo3() {
		GrafoPonderado<Integer> g = new GrafoPonderado<>();

		Integer v0 = 0;
		Integer v1 = 1;
		Integer v2 = 2;
		Integer v3 = 3;
		Integer v4 = 4;
		Integer v5 = 5;
		Integer v6 = 6;
		Integer v7 = 7;
		Integer v8 = 8;
		Integer v9 = 9;

		g.agregarVertice(v0, 4);
		g.agregarVertice(v1, 3);
		g.agregarVertice(v2, 4);
		g.agregarVertice(v3, 1);
		g.agregarVertice(v4, 3);
		g.agregarVertice(v5, 1);
		g.agregarVertice(v6, 3);
		g.agregarVertice(v7, 1);
		g.agregarVertice(v8, 2);
		g.agregarVertice(v9, 2);

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

		// la mayor clique es con los vertices {0,1,3} con peso 8
		// Si no coincide es por culpa del algoritmo goloso
		grafo3 = g;
	}

}
