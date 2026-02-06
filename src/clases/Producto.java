package clases;

import java.io.Serializable;
import enumeraciones.TipoCarta;

public class Producto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TipoCarta tipoCaja;
	private double precio;
	public static int stock=200;
	public static int ventasMagic = 0;
    public static int ventasFutbol = 0;
    public static int ventasPokemon = 0;

	public Producto(TipoCarta tipoCaja, double precio, int stock) {
		this.tipoCaja = tipoCaja;
		this.precio = precio;
		Producto.stock = stock;	
		}
	public TipoCarta getTipoCaja() {
		return tipoCaja;
	}
	public void setTipoCaja(TipoCarta tipoCaja) {
		this.tipoCaja = tipoCaja;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		Producto.stock = stock;
	}
	@Override
	public String toString() {
		return "Producto [tipoCaja=" + tipoCaja + ", precio=" + precio + ", stock=" + stock + "]";
	}


}


