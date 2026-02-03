package clases;
public class Empleado extends Persona{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int cod_Cliente;
	private int cantidad_Ventas;
	public Empleado() {
		super();
	}
	public Empleado(String dni, String nombre, String tipoPersona, int cod_Cliente) {
		super(dni, nombre, tipoPersona);
		this.cod_Cliente=cod_Cliente;
		this.cantidad_Ventas=0;
	}
	public int getCod_Cliente() {
		return cod_Cliente;
	}
	public void setCod_Cliente(int cod_Cliente) {
		this.cod_Cliente = cod_Cliente;
	}
	public int getCantidad_Compras() {
		return cantidad_Ventas;
	}
	public void setCantidad_Compras(int cantidad_Compras) {
		this.cantidad_Ventas = cantidad_Compras;
	}
	@Override
	public String toString() {
		return "Empleado [cod_Cliente=" + cod_Cliente + ", cantidad_Compras=" + cantidad_Ventas + ", dni=" + dni
				+ ", nombre=" + nombre + ", tipoPersona=" + tipoPersona + "]";
	}
}
