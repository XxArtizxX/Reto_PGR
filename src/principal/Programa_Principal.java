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
				System.out.println("Dando alta a persona");
				break;

			case 2:
				System.out.println("Dando alta a producto");
				break;

			case 3:
				System.out.println("Realizando venta");
				break;

			case 4:
				System.out.println("Consultando ventas por fecha");
				break;

			case 5:
				System.out.println("Listando productos");
				break;

			case 6:
				System.out.println("Modificando precio por tipo de producto");
				break;

			case 7:
				System.out.println("ventas totales");
				break;

			case 8:
				System.out.println("ranking de compradores");
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