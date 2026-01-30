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
	private int stock=200;

	public Producto(TipoCarta tipoCaja, double precio, int stock) {
		this.tipoCaja = tipoCaja;
		this.precio = precio;
		this.stock = stock;	
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
		this.stock = stock;
	}
	@Override
	public String toString() {
		return "Producto [tipoCaja=" + tipoCaja + ", precio=" + precio + ", stock=" + stock + "]";
	}


}


