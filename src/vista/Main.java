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
import javax.swing.SwingUtilities;
import org.openstreetmap.gui.jmapviewer.Coordinate;

import controlador.Controlador;
import logicaGrafo.Vertice;
import java.awt.BorderLayout;
import javax.swing.JLabel;

public class Main {

	private JFrame frame;
	private FixedMapViewer mapViewer;
	private Controlador controlador;
	private JComboBox<Vertice> comboBox1;
	private JComboBox<Vertice> comboBox2;
	private JFrame frameParaElegirRelacion;

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
		mapViewer = FixedMapViewer.fijadoEnMar();
//		mapViewer = FixedMapViewer.fijadoEnPantallaBlanco();
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
				if (controlador.cantidadVerticesGrafo() < 2) {
					controlador.mostrarAlerta("Insuficientes vértices para agregar una arista");
					return;
				}
				frameParaElegirRelacion.setVisible(true);
			}
		});
		panel.add(botonAgregarArista);

		JButton botonMostrarClique = new JButton("Clique más pesada");
		botonMostrarClique.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (controlador.cantidadVerticesGrafo() == 0) {
					controlador.mostrarAlerta("Insuficientes vértices para hallar la clique más pesada");
					return;
				}

				String[] opciones = {"Por peso", "Por cantidad de vecinos"};
				final int porPeso = 0;
				final int porCantidadVecinos = 1;

				int eleccion = JOptionPane.showOptionDialog(null,
						"Elige el método de búsqueda",
						"Clique más pesada",
						JOptionPane.DEFAULT_OPTION,
						JOptionPane.QUESTION_MESSAGE,
						null,
						opciones,
						null);

				if (eleccion == porPeso) {
					controlador.dibujarCliqueMasPesadaPorPeso();
				}
				else if (eleccion == porCantidadVecinos) {
					controlador.dibujarCliqueMasPesadaPorCantidadVecinos();
				}
				else {
					return;
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

		JLabel lblDobleClicDerecho = new JLabel("Doble clic izquierdo para agregar vertice");
		panel.add(lblDobleClicDerecho);
		frame.getContentPane().add(mapPanel, BorderLayout.CENTER);

		comboBox1 = new JComboBox<>();
		comboBox2 = new JComboBox<>();

		comboBox1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarComboBox2();
			}
		});

		mapViewer.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (SwingUtilities.isLeftMouseButton(e) && e.getClickCount() == 2) {

					String nombreVertice = JOptionPane.showInputDialog("Nombre para el vertice");

					if (nombreVertice == null)
						return;

					if (nombreVertice.isEmpty() || nombreVertice.isBlank()) {
						controlador.mostrarAlerta("Nombre inválido");
						return;
					}

					try {
						double pesoVertice =
							Double.parseDouble(
							JOptionPane.showInputDialog(
							"Peso para el vertice " + nombreVertice)
						);
						Point punto = e.getPoint();
						Coordinate c = (Coordinate) mapViewer.getPosition(punto);
						Vertice verticeNuevo = new Vertice(nombreVertice,pesoVertice,c);
						controlador.nuevoVertice(verticeNuevo);
						comboBox1.addItem(verticeNuevo);
						actualizarComboBox2();
					}
					catch(NumberFormatException ex) {
						controlador.mostrarAlerta("Número inválido");
						return;
					}

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

	private void crearFrameAgregarRelacion() {
		frameParaElegirRelacion = new JFrame();
		JPanel panel = new JPanel();
		panel.add(comboBox1);
		panel.add(comboBox2);
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
	}

}
