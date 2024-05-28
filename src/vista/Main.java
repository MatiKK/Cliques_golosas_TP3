package vista;

import java.awt.EventQueue;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;

import controlador.Controlador;
import logicaGrafo.Vertice;

import java.awt.BorderLayout;
import javax.swing.JLabel;

public class Main {

	private JFrame frame;
	private JMapViewer mapViewer;
	private Controlador control;
	private JComboBox<Vertice> combobox1;
	private JComboBox<Vertice> combobox2;

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
		control = new Controlador(this.mapViewer);
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
					double pesoVertice;

					nombreVertice = JOptionPane.showInputDialog("Nombre para el vertice");
					if (nombreVertice == null || nombreVertice.isEmpty() || nombreVertice.isBlank()) {
						control.mostrarAlerta("Nombre inválido");
						return;
					}

					try {
						pesoVertice = Double.parseDouble(
								JOptionPane.showInputDialog("Peso para el vertice " + nombreVertice)
								);
					} catch(NumberFormatException ex) {
						control.mostrarAlerta("Número inválido");
						return;
					}

					Point punto = e.getPoint();
					Coordinate c = (Coordinate) mapViewer.getPosition(punto);
					Vertice verticeNuevo = new Vertice(nombreVertice,pesoVertice,c); 
					control.nuevoVertice(verticeNuevo);
				}
			}
		});		
		
		mapPanel.add(mapViewer, BorderLayout.CENTER);
		
	}

	//TODO
	/**
	 * Agregar un boton para agregar arista
	 * donde salga una pantallita
	 * y salgan los dos comboboxes de los vértices
	 * y el usuario elige 2 para unir
	 */

}
