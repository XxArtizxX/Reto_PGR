package clases;

import java.util.ArrayList;
import enumeraciones.TipoCarta;

public class Producto {
	private TipoCarta tipoCaja;
	private double precio;
	private int stock;
	private ArrayList<Integer> Cantidad;

	public Producto(TipoCarta tipoCaja, double precio, int stock, ArrayList<Integer> cantidad) {
		this.tipoCaja = tipoCaja;
		this.precio = precio;
		this.stock = 200;
		Cantidad = cantidad;
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
	public ArrayList<Integer> getCantidad() {
		return Cantidad;
	}
	public void setCantidad(ArrayList<Integer> cantidad) {
		Cantidad = cantidad;
	}
	@Override
	public String toString() {
		return "Producto [tipoCaja=" + tipoCaja + ", precio=" + precio + ", stock=" + stock + ", Cantidad=" + Cantidad
				+ "]";
	}	
}


