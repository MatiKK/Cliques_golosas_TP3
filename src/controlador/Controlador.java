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

	private boolean cliqueEnPantalla = false;

	private static final Color colorVerticesGrafo = Color.YELLOW;
	private static final Color colorAristasGrafo = Color.BLACK;
	private static final Color colorVerticesClique = Color.RED;
	private static final Color colorAristasClique = Color.RED;
	private static Font fuenteDefault = MapMarkerDot.getDefaultStyle().getFont();

	private static final Style estiloVerticeGrafo =
			new Style(Color.BLACK, colorVerticesGrafo, null, fuenteDefault);

	private static final Style estiloVerticeClique =
			new Style(Color.BLACK, colorVerticesClique, null, fuenteDefault);

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
		dibujarVertice(v, estiloVerticeGrafo);
		grafo.data();
	}

	public int cantidadVerticesGrafo() {
		return grafo.cantidadVertices();
	}

	public void nuevaAristaEntreVertices(Vertice v1, Vertice v2) {
		try {
			grafo.agregarAristaEntreVertices(v1, v2);
			if (cliqueEnPantalla) {
				dibujarGrafoOriginal();
				cliqueEnPantalla = false;
			}
			dibujarLineaEntrePuntos(v1.getCordenada(), v2.getCordenada(), colorAristasGrafo);
			grafo.data();
		} catch (NullPointerException e) {
			// puede pasar cuando no hay vertices o hay uno solo
			mostrarAlerta("Vértice inválido");
		}
	}

	public void dibujarGrafoOriginal() {
		if (cliqueEnPantalla) {
			limpiarMapa();
			dibujarRedVertices(grafo, estiloVerticeGrafo, colorAristasGrafo);
			cliqueEnPantalla = false;
		}
	}

	public void dibujarCliqueMasPesadaPorPeso() {
		try {
			cliqueMasPesada = grafo.cliqueMasPesadaOrdenandoPorPeso();
			if (cliqueEnPantalla) {
				// para que no esten ambas pintadas al mismo tiempo
				dibujarGrafoOriginal();
			}
			cliqueEnPantalla = true;
			dibujarClique();
		} catch (IllegalArgumentException e) {
			mostrarAlerta("El grafo aún no tiene vértices");
		}
	}

	public void dibujarCliqueMasPesadaPorCantidadVecinos() {
		try {
			cliqueMasPesada = grafo.cliqueMasPesadaOrdenandoPorCantidadVecinos();
			if (cliqueEnPantalla) {
				// para que no esten ambas pintadas al mismo tiempo
				dibujarGrafoOriginal();
			}
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
		dibujarRedVertices(cliqueMasPesada, estiloVerticeClique, colorAristasClique);

		String msj = "Clique más pesada: " + cliqueMasPesada.vertices();
		msj = msj.concat("\nPeso: " + cliqueMasPesada.peso());
		mostrarAlerta(msj);
	}

	private void dibujarRedVertices(RedVertices g, Style estiloVertice, Color colorAristas) {
		for (Vertice v: g.vertices())
			dibujarVertice(v, estiloVertice);
		Iterator<Arista> it = g.aristasIterator();
		while (it.hasNext()) {
			dibujarArista(it.next(), colorAristas);
		}
	}

	private void dibujarVertice(Vertice v, Style s)
	{
		Coordinate c;
		String text;
		double pesoVertice;

		c = v.getCordenada();
		text = v.toString();
		pesoVertice = v.obtenerPeso();

		String textoPunto = text + " (" + pesoVertice + ")";
		
		// Crear un marcador para el vértice
		MapMarkerDot marcador = new MapMarkerDot(null, textoPunto, c, s);
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

}
