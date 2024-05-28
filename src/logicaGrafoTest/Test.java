package logicaGrafoTest;

import java.util.Iterator;

import logicaGrafo.*;

public class Test {

	public static void main(String[] args) {

		Grafo g = EjemploGrafos.grafo1();
		g.data();
		System.out.println("Aristas:");
		Iterator<Arista> aristasdeg = g.aristasIterator();
		while (aristasdeg.hasNext()) System.out.println(aristasdeg.next());

		System.out.println("\nClique más pesada:");
		System.out.println("---------------------------------------");
		System.out.println("Ordenando por peso\n");

		Clique clique;
		clique = g.cliqueMasPesadaOrdenandoPorPeso();
		clique.data();

		System.out.println("\n---------------------------------------");
		System.out.println("Ordenando por cantidad de vértices\n");

		clique = g.cliqueMasPesadaOrdenandoPorCantidadVecinos();
		clique.data();

	}
}