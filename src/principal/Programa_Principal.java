package principal;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import clases.Cliente;
import clases.Empleado;
import clases.Producto;
import enumeraciones.TipoCarta;
import excepciones.NoStockException;
import excepciones.OverStockExcepcion;
import utilidades.MyObjectOutputStream;
import utilidades.Utilidades;

public class Programa_Principal {

	public static void menu() {
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
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int opcion;
		File fich_personas = new File ("personas.txt");
		File fich_productos = new File("productos.dat");
		File fich_ventas = new File("ventas.dat");

		do {
			menu();
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
				listarInventarioProductos(fich_productos);
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

		} while (opcion != 9);
	}

	public static void altaEmpleado(File fich_personas) {
		String nombre, dni, tipoPersona;
		int cod_Cliente, cod_Empleado;
		double sueldo;
		ObjectOutputStream oos;
		MyObjectOutputStream moos;
		if(!fich_personas.exists()) {
			try {
				oos = new ObjectOutputStream(new FileOutputStream(fich_personas));
				System.out.println("Introduce el DNI del nuevo usuario : ");
				dni=Utilidades.introducirCadena();
				System.out.println("Introduce el nombre del nuevo usuario: ");
				nombre=Utilidades.introducirCadena();
				System.out.println("Introduce el apellido del empleado: ");
				tipoPersona=Utilidades.introducirCadena();
				if(tipoPersona.equalsIgnoreCase("Empleado")) {
					System.out.println("Introduzca el codigo del nuevo Empleado");
					cod_Empleado=Utilidades.leerInt();
					Empleado e=new Empleado(dni, nombre, tipoPersona, cod_Empleado);
					oos.writeObject(e);
				}else if(tipoPersona.equalsIgnoreCase("Cliente")) {
					System.out.println("Introduzca el codigo del nuevo cliente");
					cod_Cliente=Utilidades.leerInt();
					Cliente c=new Cliente(dni, nombre, tipoPersona, cod_Cliente);
					oos.writeObject(c);
				}else {
					System.out.println("Ese tipo de persona no existe");
				}
				oos.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(fich_personas.exists()) {
			System.out.println("El fichero ya existe, se añadirán al final");

			try {
				moos = new MyObjectOutputStream(new FileOutputStream(fich_personas,true));
				System.out.println("Introduce el DNI del nuevo usuario : ");
				dni=Utilidades.introducirCadena();
				System.out.println("Introduce el nombre del nuevo usuario: ");
				nombre=Utilidades.introducirCadena();
				System.out.println("Introduce el apellido del empleado: ");
				tipoPersona=Utilidades.introducirCadena();
				if(tipoPersona.equalsIgnoreCase("Empleado")) {
					System.out.println("Introduzca el codigo del nuevo Empleado");
					cod_Empleado=Utilidades.leerInt();
					Empleado e=new Empleado(dni, nombre, tipoPersona, cod_Empleado);
					moos.writeObject(e);
				}else if(tipoPersona.equalsIgnoreCase("Cliente")) {
					System.out.println("Introduzca el codigo del nuevo cliente");
					cod_Cliente=Utilidades.leerInt();
					Cliente c=new Cliente(dni, nombre, tipoPersona, cod_Cliente);
					moos.writeObject(c);
				}else {
					System.out.println("Ese tipo de persona no existe");
				}
				moos.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static void listarInventarioProductos(File fich_productos) {
		if (!fich_productos.exists()) {
			System.out.println("No hay productos registrados.");
			return;
		}
		try (ObjectInputStream ois =
				new ObjectInputStream(new FileInputStream(fich_productos))) {
			while (true) {
				try {
					Producto p = (Producto) ois.readObject();
					// Comprobación del stock
					if (p.getStock() < 0) {
						throw new OverStockExcepcion(
								"Stock negativo en " + p.getTipoCaja());
					}
					if (p.getStock() == 0) {
						throw new NoStockException(
								"Producto sin stock: " + p.getTipoCaja());
					}
					System.out.println(p);
				} catch (NoStockException | OverStockExcepcion e) {
					System.out.println(e.getMessage());
				}
			}
		} catch (EOFException e) {
			// Fin del fichero
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("Error al leer productos.");
		}
	}
	
	
	
	
	
	
}
