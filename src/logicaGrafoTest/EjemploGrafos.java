package logicaGrafoTest;

import logicaGrafo.Grafo;
import logicaGrafo.Vertice;

public class EjemploGrafos {

	private static Grafo grafo1;
	private static Grafo grafo2;
	private static Grafo grafo3;

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
		Grafo g = new Grafo();
		Vertice v1 = new Vertice(1,11.0);
		Vertice v2 = new Vertice(2,5.5);
		Vertice v3 = new Vertice(3,1.1);
		Vertice v4 = new Vertice(4,7.0);
		Vertice v5 = new Vertice(5, 2.5);
		Vertice v6 = new Vertice(6,3.5);
		g.agregarVertice(v1);
		g.agregarVertice(v2);
		g.agregarVertice(v3);
		g.agregarVertice(v4);
		g.agregarVertice(v5);
		g.agregarVertice(v6);
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
		grafo1 = g;
	}

	private static void inicializarGrafo2() {
		Grafo g = new Grafo();
		Vertice v1 = new Vertice(1,50);
		Vertice v2 = new Vertice(2,100);
		Vertice v3 = new Vertice(3,30);
		Vertice v4 = new Vertice(4,10);
		Vertice v5 = new Vertice(5, 8);
		g.agregarVertice(v1);
		g.agregarVertice(v2);
		g.agregarVertice(v3);
		g.agregarVertice(v4);
		g.agregarVertice(v5);
		g.agregarAristaEntreVertices(v1, v2);
		g.agregarAristaEntreVertices(v2, v3);
		g.agregarAristaEntreVertices(v3, v4);
		g.agregarAristaEntreVertices(v4, v1);
		g.agregarAristaEntreVertices(v5, v2);
		g.agregarAristaEntreVertices(v5, v3);
		g.agregarAristaEntreVertices(v5, v4);
		grafo2 = g;
	}

	private static void inicializarGrafo3() {
		
	}

}
