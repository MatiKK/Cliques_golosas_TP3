package controlador;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Stroke;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.Layer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import org.openstreetmap.gui.jmapviewer.Style;
import org.openstreetmap.gui.jmapviewer.interfaces.ICoordinate;
import org.openstreetmap.gui.jmapviewer.interfaces.MapPolygon;

import logicaGrafo.*;

@SuppressWarnings("unused")
public class Controlador {

	private GrafoPonderado<Vertice> grafo;
	private Clique<Vertice> cliqueMasPesada;
	private JMapViewer map;

	public Controlador(JMapViewer map) {
		this.map = map;
		grafo = new GrafoPonderado<>();
		cliqueMasPesada = null;
	}

	public void nuevoVertice(Vertice v) {
		grafo.agregarVertice(v, v.obtenerPeso());
		dibujarVertice(v);
		System.out.println("----------------------");
		grafo.data();
	}

	public void nuevaAristaEntreVertices(Vertice v1, Vertice v2) {
		grafo.agregarAristaEntreVertices(v1, v2);
		dibujarLineaEntrePuntos(v1.obtenerCoordenada(), v2.obtenerCoordenada());
		System.out.println("----------------------");
		grafo.data();
	}

	public void dibujarGrafoOriginal() {
		dibujarRedVertices(grafo);
	}

	public void dibujarCliqueMasPesadaPorPeso() {
		try {
			cliqueMasPesada = grafo.cliqueMasPesadaOrdenandoPorPeso();
			dibujarRedVertices(cliqueMasPesada);
		} catch (IllegalArgumentException e) {
			mostrarAlerta("El grafo aún no tiene vértices");
		}
	}

	public void dibujarCliqueMasPesadaPorCantidadVecinos() {
		try {
			cliqueMasPesada = grafo.cliqueMasPesadaOrdenandoPorCantidadVecinos();
			dibujarRedVertices(cliqueMasPesada);
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

	private void dibujarRedVertices(Grafo<Vertice> g) {
		limpiarMapa();
		for (Vertice v: g.vertices())
			dibujarVertice(v);
		Iterator<Arista<Vertice>> it = g.aristasIterator();
		while (it.hasNext()) {
			dibujarArista(it.next());
		}
	}

	private void dibujarVertice(Vertice v)
	{
		Coordinate c;
		String text;
		
		c = v.obtenerCoordenada();
		text = v.toString();
		
		// Crear un marcador para el vértice
		MapMarkerDot marcador = new MapMarkerDot(text, c);
		//marcador.setColor(Color.YELLOW);
		
		// Agregar el marcador al JMapViewer
		map.addMapMarker(marcador);
	}
	
	private void dibujarArista(Arista<Vertice> arista) {
		Vertice v1 = arista.verticeInicio();
		Vertice v2 = arista.verticeDestino();
		Coordinate c1 = v1.obtenerCoordenada();
		Coordinate c2 = v2.obtenerCoordenada();
		dibujarLineaEntrePuntos(c1,c2);
	}

	private void dibujarLineaEntrePuntos(Coordinate c1, Coordinate c2) {
		map.addMapPolygon(new MapPolygonImpl(c1, c2, c1));
	}

}
