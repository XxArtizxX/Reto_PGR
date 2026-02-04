package clases;

import java.io.Serializable;
import java.time.LocalDateTime;
import enumeraciones.TipoCarta;

public class Venta implements Serializable {
	private static final long serialVersionUID = 1L;

	private TipoCarta tipoProducto;
	private int cantidad;
	private LocalDateTime fecha;


	public Venta(TipoCarta tipoProducto, int cantidad, LocalDateTime fecha) {
		this.tipoProducto = tipoProducto;
		this.cantidad = cantidad;
		this.fecha = fecha;
	}

	public TipoCarta getTipoProducto() {
		return tipoProducto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	@Override
	public String toString() {
		return "Venta [tipoProducto=" + tipoProducto + ", cantidad=" + cantidad + ", fecha=" + fecha + "]";
	}
}
