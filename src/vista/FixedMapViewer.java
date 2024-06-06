package vista;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;

/**
 * JMapViewer donde el usuario no puede scrollear ni hacer zoom 
 */
@SuppressWarnings("serial")
public class FixedMapViewer extends JMapViewer {

	private final static Coordinate	coords_mar = new Coordinate(-50,-130);
	private final static zoom_mar = 6;
	private final static Coordinate coords_pantalla_en_blanco = new Coordinate(-80,-40);
	private final static zoom_pantalla_blanco = 4;

	public FixedMapViewer() {
		super();
		setZoomControlsVisible(false);
	}

	/**
	 * FixedMapViewer fijado en el mar
	 * @return
	 */
	public static FixedMapViewer fijadoEnMar() {
		return fijadoEnCoords(coords_mar, zoom_mar);
	}

	/**
	 * FixedMapViewer fijado con la pantalla en blanco
	 * @return
	 */
	public static FixedMapViewer fijadoEnPantallaBlanco() {
		return fijadoEnCoords(coords_pantalla_en_blanco, zoom_pantalla_blanco);
	}

	private static FixedMapViewer fijadoEnCoords(Coordinate c, int zoom) {
		FixedMapViewer map = new FixedMapViewer();
		map.setZoom(5);
		map.setTileSource(new org.openstreetmap.gui.jmapviewer.tilesources.OsmTileSource.Mapnik());
		map.setDisplayPosition(c, zoom);
		return map;
	}

	public void zoomIn() {}
	public void zoomOut() {}
	public void setZoom(int zoom) {}
	public void setZoom(int zoom, java.awt.Point mapPoint) {}
	public void moveMap(int x, int y) {}
}
