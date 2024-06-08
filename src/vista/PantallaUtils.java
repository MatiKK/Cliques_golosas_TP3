package vista;

import java.awt.Toolkit;
import java.awt.Dimension;

public final class PantallaUtils {

	private final static Toolkit miPantalla = Toolkit.getDefaultToolkit();

	private final static Dimension dimensionMiPantalla = miPantalla.getScreenSize();

	public final static int anchoPantalla = dimensionMiPantalla.width;
	public final static int altoPantalla = dimensionMiPantalla.height;

}
