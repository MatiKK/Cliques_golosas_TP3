package logicaGrafo;

public class Vertice implements Comparable<Vertice> {

	private String nombre; // para representarlo en un gráfico
	private double peso;

	public Vertice(Object n, double p) {
		nombre = n.toString();
		peso = p;
	}

	public double obtenerPeso() {
		return peso;
	}

	@Override
	public String toString() {
		return nombre;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Vertice) {
			Vertice v = (Vertice) o;
			return peso == v.peso && nombre.equals(v.nombre);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return 31 * Double.hashCode(peso) + nombre.hashCode();
	}

	@Override
	public int compareTo(Vertice v) {
		int x = Double.compare(peso, v.peso);
		if (x == 0) {
			x = nombre.compareTo(v.nombre);
		}
		return x;
	}

}
