package logicaGrafoTest;

import org.openstreetmap.gui.jmapviewer.Coordinate;

import logicaGrafo.Grafo;
import logicaGrafo.Vertice;

public class EjemploGrafos {

	private static Grafo grafo1;
	private static Grafo grafo2;
	private static Grafo grafo3;
	public final static Coordinate coord_default = new Coordinate (10.0,10.0);

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

		Vertice v1 = new Vertice(1, 11.0,coord_default);
		Vertice v2 = new Vertice(2, 5.5,coord_default);
		Vertice v3 = new Vertice(3, 1.1,coord_default);
		Vertice v4 = new Vertice(4, 7.0,coord_default);
		Vertice v5 = new Vertice(5, 2.5,coord_default);
		Vertice v6 = new Vertice(6, 3.5,coord_default);

		agregarVertices(g,
		v1, v2, v3, v4, v5, v6);

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

		Vertice v1 = new Vertice(1, 50,coord_default);
		Vertice v2 = new Vertice(2, 100,coord_default);
		Vertice v3 = new Vertice(3, 30,coord_default);
		Vertice v4 = new Vertice(4, 10,coord_default);
		Vertice v5 = new Vertice(5, 8,coord_default);

		agregarVertices(g,
				v1, v2, v3, v4, v5);

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
		Grafo g = new Grafo();

		Vertice v0 = new Vertice(0, 4,coord_default);
		Vertice v1 = new Vertice(1, 3,coord_default);
		Vertice v2 = new Vertice(2, 4,coord_default);
		Vertice v3 = new Vertice(3, 1,coord_default);
		Vertice v4 = new Vertice(4, 3,coord_default);
		Vertice v5 = new Vertice(5, 1,coord_default);
		Vertice v6 = new Vertice(6, 3,coord_default);
		Vertice v7 = new Vertice(7, 1,coord_default);
		Vertice v8 = new Vertice(8, 2,coord_default);
		Vertice v9 = new Vertice(9, 2,coord_default);

		agregarVertices(g,
				v0, v1, v2, v3, v4, v5, v6, v7, v8, v9);

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

		grafo3 = g;
	}

	public static Grafo grafoSinVertices() {
		return new Grafo();
	}

	public static Grafo grafoNull() {
		return null;
	}

	public static Grafo GrafoSoloUnVertice() {
		Grafo g = new Grafo();
		g.agregarVertice(new Vertice("v1", 10, coord_default));
		return g;
	}

	public static Grafo GrafoSinAristas() {
		Grafo g = new Grafo();
		g.agregarVertice(new Vertice("v1", 2, coord_default));
		g.agregarVertice(new Vertice("v2", 12, coord_default));
		g.agregarVertice(new Vertice("v3", 5, coord_default));
		g.agregarVertice(new Vertice("v4", 4.5, coord_default));
		g.agregarVertice(new Vertice("v5", 10, coord_default));
		return g;
	}

	public static Grafo GrafoSoloUnaArista() {
		Grafo g = GrafoSinAristas();
		Vertice v1 = new Vertice("a", 21, coord_default);
		Vertice v2 = new Vertice("b", 9, coord_default);
		g.agregarVertice(v1);
		g.agregarVertice(v2);
		g.agregarAristaEntreVertices(v1, v2);
		return g;
	}

	public static Grafo grafoCompletoK3() {
		Grafo g = new Grafo();
		Vertice v1 = new Vertice(1, 23, coord_default);
		Vertice v2 = new Vertice(2, 8, coord_default);
		Vertice v3 = new Vertice(3, 13, coord_default);
		agregarVertices(g,v1,v2,v3);
		g.agregarAristaEntreVertices(v1, v2);
		g.agregarAristaEntreVertices(v1, v3);
		g.agregarAristaEntreVertices(v2, v3);
		return g;
	}

	public static Grafo grafoCompletoK4() {
		Grafo g = new Grafo();
		Vertice v1 = new Vertice(1, 9.75, coord_default);
		Vertice v2 = new Vertice(2, 4, coord_default);
		Vertice v3 = new Vertice(3, 0.3, coord_default);
		Vertice v4 = new Vertice(4, 1, coord_default);
		agregarVertices(g,v1,v2,v3,v4);
		g.agregarAristaEntreVertices(v1, v2);
		g.agregarAristaEntreVertices(v1, v3);
		g.agregarAristaEntreVertices(v2, v3);
		g.agregarAristaEntreVertices(v4, v1);
		g.agregarAristaEntreVertices(v4, v2);
		g.agregarAristaEntreVertices(v4, v3);
		return g;
	}

	public static Grafo grafoCompletoK5() {
		Grafo g = new Grafo();
		Vertice v1 = new Vertice(1, 10, coord_default);
		Vertice v2 = new Vertice(2, 2, coord_default);
		Vertice v3 = new Vertice(3, 41, coord_default);
		Vertice v5 = new Vertice(4, 21, coord_default);
		Vertice v4 = new Vertice(5, 8, coord_default);
		agregarVertices(g,v1,v2,v3,v4,v5);
		g.agregarAristaEntreVertices(v1,v2);
		g.agregarAristaEntreVertices(v1,v3);
		g.agregarAristaEntreVertices(v1,v4);
		g.agregarAristaEntreVertices(v1,v5);
		g.agregarAristaEntreVertices(v2,v3);
		g.agregarAristaEntreVertices(v2,v4);
		g.agregarAristaEntreVertices(v2,v5);
		g.agregarAristaEntreVertices(v3,v4);
		g.agregarAristaEntreVertices(v3,v5);
		g.agregarAristaEntreVertices(v4,v5);
		return g;
	}

	// Solo para EjemploGrafos.java
	private static void agregarVertices(Grafo g, Vertice... vertices) {
		for (Vertice v : vertices)
			g.agregarVertice(v);
	}

}
