package logicaGrafoTest;

import logicaGrafo.*;

public class Test {

	public static void main(String[] args) {

		Grafo g;
		Clique clique;

//		g = EjemploGrafos.grafoNull();
		g = EjemploGrafos.GrafoSoloUnaArista();
//		g = EjemploGrafos.GrafoSoloUnVertice();
//		g = EjemploGrafos.grafoSinVertices();
//		g = EjemploGrafos.GrafoSinAristas();
//		g = EjemploGrafos.grafo1();
//		g = EjemploGrafos.grafo2();
//		g = EjemploGrafos.grafo3();
//		g = EjemploGrafos.grafoCompletoK3();
//		g = EjemploGrafos.grafoCompletoK4();
//		g = EjemploGrafos.grafoCompletoK5();
		g.data();

		System.out.println("\nClique más pesada:");
		System.out.println("---------------------------------------");
		System.out.println("Ordenando por peso\n");

		clique = g.cliqueMasPesadaOrdenandoPorPeso();
		clique.data();

		System.out.println("\n---------------------------------------");
		System.out.println("Ordenando por cantidad de vértices\n");

		clique = g.cliqueMasPesadaOrdenandoPorCantidadVecinos();
		clique.data();

	}
}