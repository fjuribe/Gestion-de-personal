package net.latam.empleo.util.paginator;

public class PageItemCategorias {
	private int numero;
	private boolean actual;
	public PageItemCategorias(int numero, boolean actual) {
		super();
		this.numero = numero;
		this.actual = actual;
	}
	public int getNumero() {
		return numero;
	}
	public boolean isActual() {
		return actual;
	}
}
