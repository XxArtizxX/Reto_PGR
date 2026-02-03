package principal;

import clases.Producto;
import enumeraciones.TipoCarta;
import excepciones.NoStockException;
import excepciones.OverStockExcepcion;
import utilidades.MyObjectOutputStream;
import utilidades.Utilidades;

public class Programa_Principal {

	public static void menu() {

	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int opcion;

		do {
			System.out.println("===== MENÚ =====");
			System.out.println("1.- Alta Persona");
			System.out.println("2.- Alta Producto");
			System.out.println("3.- Realizar Venta");
			System.out.println("4.- Consultar ventas por fecha");
			System.out.println("5.- Listar inventario de productos");
			System.out.println("6.- Modificar precio pidiendo el tipo de producto");
			System.out.println("7.- Ventas totales por cada producto");
			System.out.println("8.- Ranking de compradores");
			System.out.println("9. Salir");
			System.out.print("Elige una opción: ");


			opcion = utilidades.Utilidades.leerInt(1, 9);

			switch (opcion) {
			case 1:
				System.out.println("Has elegido la opción 1");
				break;

			case 2:
				System.out.println("Has elegido la opción 2");
				break;

			case 3:
				System.out.println("Has elegido la opción 3");
				break;

			case 4:
				System.out.println("Has elegido la opción 3");
				break;

			case 5:
				System.out.println("Has elegido la opción 3");
				break;

			case 6:
				System.out.println("Has elegido la opción 3");
				break;

			case 7:
				System.out.println("Has elegido la opción 3");
				break;

			case 8:
				System.out.println("Has elegido la opción 3");
				break;

			case 9:
				System.out.println("Saliendo del programa...");
				break;

			default:

				System.out.println("Opción no válida");
			}

			System.out.println(); 

		} while (opcion != 9);
	}
	
}