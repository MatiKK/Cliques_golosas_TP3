package logicaGrafoTest;

import logicaGrafo.*;

public class Test {

	public static void main(String[] args) {

		Grafo g;
		Clique clique;

		// grafo de la diapositiva del tp3
		g = EjemploGrafos.grafo1();

//		g = EjemploGrafos.grafo2();
//		g = EjemploGrafos.grafo3();
//		g = EjemploGrafos.grafoNull();
//		g = EjemploGrafos.GrafoSoloUnaArista();
//		g = EjemploGrafos.GrafoSoloUnVertice();
//		g = EjemploGrafos.grafoSinVertices();
//		g = EjemploGrafos.GrafoSinAristas();
//		g = EjemploGrafos.grafoCompletoK3();
//		g = EjemploGrafos.grafoCompletoK4();
//		g = EjemploGrafos.grafoCompletoK5();
		g.data();

		System.out.println("Clique más pesada:");
		System.out.println("---------------------------------------");
		System.out.println("Ordenando por peso");

		clique = g.cliqueMasPesadaOrdenandoPorPeso();
		clique.data();

		System.out.println("---------------------------------------");
		System.out.println("Ordenando por cantidad de vértices");

		clique = g.cliqueMasPesadaOrdenandoPorCantidadVecinos();
		clique.data();

	}
}