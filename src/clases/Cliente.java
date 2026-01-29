package clases;

public class Cliente extends Persona {

	private int codigoCliente;
	private int totalCompras;

	public Cliente(String dni, String nombre, String tipoPersona, int codigoCliente) {
		super(dni, nombre, tipoPersona);
		this.codigoCliente = codigoCliente;
		this.totalCompras = 0;
	}

	public Cliente() {
		super();
	}

	public int getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(int codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public int getTotalCompras() {
		return totalCompras;
	}

	public void setTotalCompras(int totalCompras) {
		this.totalCompras = totalCompras;
	}

	public void realizarCompra() {
		this.totalCompras++;
	}

	@Override
	public String toString() {
		return "Cliente [codigoCliente=" + codigoCliente + ", totalCompras=" + 
					totalCompras + ", dni=" + dni + ", nombre=" + nombre +  ", tipoPersona=" + tipoPersona + "]";
	}
}
