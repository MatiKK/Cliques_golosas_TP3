package logicaGrafoTest;
import logicaGrafo.Clique;
import logicaGrafo.Grafo;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * Corroborar pesos de las cliques
 * en la clase Test.java
 */
public class TestsUnit {

	private Grafo g;
	private Clique cl;

	@Test(expected=NullPointerException.class)
	public void grafoNull() {
		g = EjemploGrafos.grafoNull();
		cl = g.cliqueMasPesadaOrdenandoPorPeso();
		assertEquals(cl.peso(), 20, 0);
	}

	@Test(expected=IllegalArgumentException.class)
	public void grafoSinVertices() {
		g = EjemploGrafos.grafoSinVertices();
		cl = g.cliqueMasPesadaOrdenandoPorPeso();
		assertEquals(cl.peso(), 20, 0);
	}

	@Test
	public void grafoSinAristas() {
		Grafo g = EjemploGrafos.GrafoSinAristas();

		cl = g.cliqueMasPesadaOrdenandoPorPeso();
		assertEquals(cl.peso(), 12, 0);

		cl = g.cliqueMasPesadaOrdenandoPorCantidadVecinos();
		assertEquals(cl.peso(), 12, 0);
	}

	@Test
	public void grafoSoloUnVertice() {
		g = EjemploGrafos.GrafoSoloUnVertice();

		cl = g.cliqueMasPesadaOrdenandoPorPeso();
		assertEquals(cl.peso(), 10, 0);

		cl = g.cliqueMasPesadaOrdenandoPorCantidadVecinos();
		assertEquals(cl.peso(), 10, 0);
	}

	@Test
	public void grafoSoloUnaArista() {
		g = EjemploGrafos.GrafoSoloUnaArista();

		cl = g.cliqueMasPesadaOrdenandoPorPeso();
		assertEquals(cl.peso(), 30, 0);

		cl = g.cliqueMasPesadaOrdenandoPorCantidadVecinos();
		assertEquals(cl.peso(), 30, 0);
	}

	@Test
	public void ejemploGrafo1() {
		g = EjemploGrafos.grafo1();

		// Clique {1,4,2}
		cl = g.cliqueMasPesadaOrdenandoPorPeso();
		assertEquals(cl.peso(), 23.5, 0);

		// Clique {2,4,5,6}
		cl = g.cliqueMasPesadaOrdenandoPorCantidadVecinos();
		assertEquals(cl.peso(), 18.5, 0);
	}

	@Test
	public void ejemploGrafo2() {
		g = EjemploGrafos.grafo2();

		// Clique {1,2}
		cl = g.cliqueMasPesadaOrdenandoPorPeso();
		assertEquals(cl.peso(), 150, 0);

		// Clique {2,3,5}
		cl = g.cliqueMasPesadaOrdenandoPorCantidadVecinos();
		assertEquals(cl.peso(), 138, 0);
	}

	@Test
	public void ejemploGrafo3() {
		g = EjemploGrafos.grafo3();

		// Clique {2,8}
		cl = g.cliqueMasPesadaOrdenandoPorPeso();
		assertEquals(cl.peso(), 6, 0);

		// Clique {0,1,3}
		cl = g.cliqueMasPesadaOrdenandoPorCantidadVecinos();
		assertEquals(cl.peso(), 8, 0);
	}

	@Test
	public void grafoCompletoK3() {
		g = EjemploGrafos.grafoCompletoK3();

		cl = g.cliqueMasPesadaOrdenandoPorPeso();
		assertEquals(cl.peso(),44, 0);

		cl = g.cliqueMasPesadaOrdenandoPorCantidadVecinos();
		assertEquals(cl.peso(), 44, 0);
	}

	@Test
	public void grafoCompletoK4() {
		g = EjemploGrafos.grafoCompletoK4();

		cl = g.cliqueMasPesadaOrdenandoPorPeso();
		assertEquals(cl.peso(),15.05, 0);

		cl = g.cliqueMasPesadaOrdenandoPorCantidadVecinos();
		assertEquals(cl.peso(), 15.05, 0);
	}

	@Test
	public void grafoCompletoK5() {
		g = EjemploGrafos.grafoCompletoK5();

		cl = g.cliqueMasPesadaOrdenandoPorPeso();
		assertEquals(cl.peso(), 82, 0);

		cl = g.cliqueMasPesadaOrdenandoPorCantidadVecinos();
		assertEquals(cl.peso(), 82, 0);
	}

}
