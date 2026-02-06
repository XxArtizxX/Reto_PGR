package excepciones;

public class NegativeStockException extends Exception {
	public NegativeStockException(String mensaje) {
		super(mensaje);
	}
}
