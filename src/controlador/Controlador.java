package controlador;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Stroke;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.JOptionPane;
import javax.swing.JToolTip;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.Layer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import org.openstreetmap.gui.jmapviewer.Style;
import org.openstreetmap.gui.jmapviewer.interfaces.ICoordinate;
import org.openstreetmap.gui.jmapviewer.interfaces.MapPolygon;

import logicaGrafo.Arista;
import logicaGrafo.*;
import vista.Main;

@SuppressWarnings("unused")
public class Controlador {

	private Grafo grafo;
	private Clique cliqueMasPesada;
	private JMapViewer map;
	
	private Set<Arista> aristasG;
	private Main vista;

	private boolean cliqueEnPantalla = false;

	private static final Color colorVerticesGrafo = Color.YELLOW;
	private static final Color colorAristasGrafo = Color.BLACK;
	private static final Color colorVerticesClique = Color.RED;
	private static final Color colorAristasClique = Color.RED;


	public Controlador(JMapViewer map) {
		this.map = map;
		grafo = new Grafo();
		cliqueMasPesada = null;
	}

	public void nuevoVertice(Vertice v) {
		if (cliqueEnPantalla) {
			dibujarGrafoOriginal();
			cliqueEnPantalla = false;
		}
		grafo.agregarVertice(v);
		dibujarVertice(v, colorVerticesGrafo);
		System.out.println("----------------------");
		grafo.data();
	}

	public void nuevaAristaEntreVertices(Vertice v1, Vertice v2) {
		try {
			grafo.agregarAristaEntreVertices(v1, v2);
			if (cliqueEnPantalla) {
				dibujarGrafoOriginal();
				cliqueEnPantalla = false;
			}
			dibujarLineaEntrePuntos(v1.getCordenada(), v2.getCordenada(), colorAristasGrafo);
			System.out.println("----------------------");
			grafo.data();
		} catch (NullPointerException e) {
			// puede pasar cuando no hay vertices o hay uno solo
			mostrarAlerta("Vértice inválido");
		}
	}

	public void dibujarGrafoOriginal() {
		dibujarRedVertices(grafo, colorVerticesGrafo, colorAristasGrafo);
	}

	public void dibujarCliqueMasPesadaPorPeso() {
		try {
			cliqueMasPesada = grafo.cliqueMasPesadaOrdenandoPorPeso();
			cliqueEnPantalla = true;
			dibujarClique();
		} catch (IllegalArgumentException e) {
			mostrarAlerta("El grafo aún no tiene vértices");
		}
	}

	public void dibujarCliqueMasPesadaPorCantidadVecinos() {
		try {
			cliqueMasPesada = grafo.cliqueMasPesadaOrdenandoPorPeso();
			cliqueEnPantalla = true;
			dibujarClique();
		} catch (IllegalArgumentException e) {
			mostrarAlerta("El grafo aún no tiene vértices");
		}
	}

	public void mostrarAlerta(String msg) {
		JOptionPane.showMessageDialog(null, msg);
	}

	public void limpiarMapa() {
		map.removeAllMapPolygons();
		map.removeAllMapMarkers();
	}

	private void dibujarClique() {
		dibujarRedVertices(cliqueMasPesada, colorVerticesClique, colorAristasClique);
	}

	private void dibujarRedVertices(RedVertices g, Color colorVertices, Color colorAristas) {
		for (Vertice v: g.vertices())
			dibujarVertice(v, colorVertices);
		Iterator<Arista> it = g.aristasIterator();
		while (it.hasNext()) {
			dibujarArista(it.next(), colorAristas);
		}
	}

	private void dibujarVertice(Vertice v, Color color)
	{
		Coordinate c;
		String text;
		
		c = v.getCordenada();
		text = v.toString();
		
		// Crear un marcador para el vértice
		MapMarkerDot marcador = new MapMarkerDot(text, c);
		marcador.setColor(color);
		
		// Agregar el marcador al JMapViewer
		map.addMapMarker(marcador);
	}
	
	private void dibujarArista(Arista arista, Color color) {
		Vertice v1 = arista.verticeInicio();
		Vertice v2 = arista.verticeDestino();
		Coordinate c1 = v1.getCordenada();
		Coordinate c2 = v2.getCordenada();
		dibujarLineaEntrePuntos(c1,c2, color);
	}

	private void dibujarLineaEntrePuntos(Coordinate c1, Coordinate c2, Color color) {
		MapPolygonImpl linea = new MapPolygonImpl(c1,c2,c1);
		linea.setColor(color);
		map.addMapPolygon(linea);
	}

	/**
	 * agrego nueva arista al grafo
	 * 
	 * @param p1
	 * @param p2
	 * @param peso
	 */
    public void nuevaArista(Vertice p1, Vertice p2, double peso) {
    	try {
    		System.out.println("el valor de p1 es: " + p1);
    		System.out.println("el valor de p2 es: " + p2);
    		grafo.agregarAristaEntreVertices(p1, p2, peso);
    		Arista ar = new Arista(p1,p2,peso);
    		if (agregarArista(ar)) {
    			//graficarArista(vista.getMapViewer(), ar);
    			dibujarArista(ar, colorAristasGrafo);
    		} else {
    			// No enconté forma de poder hacerlo
    			mostrarAlerta("¡No puede cambiar el peso de la arista!");
    		}
    	} catch (IllegalArgumentException e) {
    		mostrarAlerta("¡No puede añadir una relación entre un mismo vértice!");
    	}
    }
    
    public boolean agregarArista(Arista ar) {
    	return aristasG.add(ar);
    }
    
    
    
	
	

}
