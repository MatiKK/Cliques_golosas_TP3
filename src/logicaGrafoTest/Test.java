package logicaGrafoTest;

import logicaGrafo.*;

public class Test {

	public static void main(String[] args) {

		Grafo g = EjemploGrafos.grafo1();
		g.data();

		System.out.println("\n---------------------------------------\n");

		Clique clique = SolverCliqueMasPesada.cliqueMasPesada(g);
		clique.data();

	}
}