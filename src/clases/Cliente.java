package clases;

public class Cliente extends Persona {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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

	public void agregarCompra(int cantidad) {
        this.totalCompras += cantidad;
    }

	@Override
	public String toString() {
		return "Cliente [codigoCliente=" + codigoCliente + ", dni=" + dni + ", nombre=" + nombre + ", tipoPersona="
				+ tipoPersona + ", total de compras= "+totalCompras+"]";
	}

	
}
