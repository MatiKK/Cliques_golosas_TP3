package vista;

import java.awt.EventQueue;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
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
	private Controlador controlador;
	private JComboBox<Vertice> comboBox1;
	private JComboBox<Vertice> comboBox2;
	
	private JFrame frameParaElegirRelacion;
	private JTextField valorPesoEntradaUser;
	
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
		controlador = new Controlador(this.mapViewer);
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

		JButton botonAgregarArista = new JButton("Agregar relación");
		botonAgregarArista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameParaElegirRelacion.setVisible(true);
			}
		});
		panel.add(botonAgregarArista);

		JButton botonMostrarClique = new JButton("Clique más pesada");
		botonMostrarClique.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
									//eleccion1       //eleccion2
				String[] opciones = {"Por peso", "Por cantidad de vecinos"};
				int eleccion = JOptionPane.showOptionDialog(null,
						"Elige el método de búsqueda", "Clique más pesada",
						JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, null);
				if (eleccion == 0) {//eleccion1
					controlador.dibujarCliqueMasPesadaPorPeso();
				} else if (eleccion == 1) {
					controlador.dibujarCliqueMasPesadaPorCantidadVecinos();
				} else {
					// se cerró sin elegir opción
				}
			}
		});
		panel.add(botonMostrarClique);
		
		JButton botonMostrarGrafoOriginal = new JButton("Grafo original");
		botonMostrarGrafoOriginal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.dibujarGrafoOriginal();
			}
		});
		panel.add(botonMostrarGrafoOriginal );

		JLabel lblDobleClicDerecho = new JLabel("Doble clic derecho para agregar vertice");
		panel.add(lblDobleClicDerecho);
		frame.getContentPane().add(mapPanel, BorderLayout.CENTER);

		comboBox1 = new JComboBox<>();
		comboBox2 = new JComboBox<>();

		comboBox1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarComboBox2();
			}
		});
		

		mapViewer.setZoom(5);
		mapViewer.setTileSource(new org.openstreetmap.gui.jmapviewer.tilesources.OsmTileSource.Mapnik());
		mapViewer.setDisplayPosition(new Coordinate(-100,150), 6);
		mapViewer.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (SwingUtilities.isRightMouseButton(e) && e.getClickCount() == 2) {

					String nombreVertice;
					double pesoVertice;

					nombreVertice = JOptionPane.showInputDialog("Nombre para el vertice");
					if (nombreVertice == null || nombreVertice.isEmpty() || nombreVertice.isBlank()) {
						controlador.mostrarAlerta("Nombre inválido");
						return;
					}

					try {
						pesoVertice = Double.parseDouble(
								JOptionPane.showInputDialog("Peso para el vertice " + nombreVertice)
								);
					} catch(NumberFormatException ex) {
						controlador.mostrarAlerta("Número inválido");
						return;
					}

					Point punto = e.getPoint();
					Coordinate c = (Coordinate) mapViewer.getPosition(punto);
					Vertice verticeNuevo = new Vertice(nombreVertice,pesoVertice,c); 
					controlador.nuevoVertice(verticeNuevo);
					comboBox1.addItem(verticeNuevo);
					actualizarComboBox2();
				}
			}
		});		
		
		mapPanel.add(mapViewer, BorderLayout.CENTER);
		
		crearFrameAgregarRelacion();
		frameParaElegirRelacion.setVisible(false);
	}


	private void actualizarComboBox2() {
		comboBox2.removeAllItems();
		Vertice elegidoEnCB1 = (Vertice) comboBox1.getSelectedItem();
		for (int i = 0; i < comboBox1.getItemCount(); i++) {
			Vertice v = (Vertice) comboBox1.getItemAt(i);
			if (!v.equals(elegidoEnCB1))
				comboBox2.addItem(v);
		}
	}

	//TODO
	/**
	 * Agregar un boton para agregar arista
	 * donde salga una pantallita
	 * y salgan los dos comboboxes de los vértices
	 * y el usuario elige 2 para unir
	 */
	
	private void crearFrameAgregarRelacion() {
		frameParaElegirRelacion = new JFrame();
		JPanel panel = new JPanel();
		panel.add(comboBox1);
		panel.add(comboBox2);
		JLabel lblNewLabel_2 = new JLabel("       Indique peso:");
		panel.add(lblNewLabel_2);
		valorPesoEntradaUser = new JTextField();
		panel.add(valorPesoEntradaUser);
		valorPesoEntradaUser.setColumns(10);
		
		JButton cargarRelacion = new JButton("Cargar relación");
		panel.add(cargarRelacion);
		cargarRelacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarNuevaArista();				
			}
		});
		frameParaElegirRelacion.setBounds(100,100,550,200);
		frameParaElegirRelacion.getContentPane().add(panel);
		frameParaElegirRelacion.setVisible(true);
		
	}
	
	private void cargarNuevaArista() {

		Vertice p1 = (Vertice) comboBox1.getSelectedItem();
		Vertice p2 = (Vertice) comboBox2.getSelectedItem();
		controlador.nuevaAristaEntreVertices(p1,p2);
		//controlador.nuevaArista(p1, p2, peso);

	}
	
	public JMapViewer getMapViewer() {
		return mapViewer;
	}
	
	public void mostrarAlerta(String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje);
	}
}
