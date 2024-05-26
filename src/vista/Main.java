package vista;

import java.awt.EventQueue;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;

import logicaGrafo.Grafo;
import logicaGrafo.Vertice;


import java.awt.BorderLayout;
import javax.swing.JLabel;

public class Main {

	private JFrame frame;
	private JMapViewer mapViewer;
	
	private Grafo grafo;
	private int numeroVertice;
	
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		mapViewer = new JMapViewer();
		grafo = new Grafo();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel mapPanel = new JPanel(new BorderLayout());
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		
		JLabel lblDobleClicDerecho = new JLabel("Doble clic derecho para agregar vertice");
		panel.add(lblDobleClicDerecho);
		frame.getContentPane().add(mapPanel, BorderLayout.CENTER);
			
		
		mapViewer.setZoom(5);
		mapViewer.setTileSource(new org.openstreetmap.gui.jmapviewer.tilesources.OsmTileSource.Mapnik());
		mapViewer.setDisplayPosition(new Coordinate(-40.6037, -65.3816), 4);		
		mapViewer.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (SwingUtilities.isRightMouseButton(e) && e.getClickCount() == 2) {
					String nombreVertice;
					String textoVertice;
					numeroVertice++;	
					nombreVertice = Integer.toString(numeroVertice);
					textoVertice = JOptionPane.showInputDialog("Ha ingresado el vertice " + nombreVertice);
					double valorDefault = 0;
					
					Point punto = e.getPoint();
					Coordinate c = (Coordinate) mapViewer.getPosition(punto);
					
					Vertice verticeNuevo = new Vertice(nombreVertice,valorDefault,c); 
					
					grafo.agregarVertice(verticeNuevo);
					
					dibujoVertice(verticeNuevo);
					
					System.out.println("hay : " + grafo.cantidadVertices() + " cantidad de vertices en el grafo");

				}
			}
		});		
		
		mapPanel.add(mapViewer, BorderLayout.CENTER);
		
	}
	
	public void dibujoVertice(Vertice v)
	{
		Coordinate c;
		String text;
		
		c = v.getCordenada();
		text = v.toString();
				
		 // Crear un marcador para el vértice
        MapMarkerDot marcador = new MapMarkerDot(text, c);
        //marcador.setColor(Color.YELLOW);

        // Agregar el marcador al JMapViewer
        mapViewer.addMapMarker(marcador);
	}
}
