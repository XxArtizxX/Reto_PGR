package principal;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeMap;


import clases.Cliente;
import clases.Empleado;
import clases.Producto;
import clases.Venta;
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
			opcion = Utilidades.leerInt(1, 9);

			switch (opcion) {
			case 1:
				System.out.println("Dando alta a persona...");
				altaPersona(fich_personas);
				break;

			case 2:
				System.out.println("Dando alta a producto...");
				altaProducto(fich_productos); 
				break;

			case 3:
				System.out.println("Realizando venta...");
				realizarVenta(fich_ventas);

				break;
			case 4:
				System.out.println("Consultando ventas por fecha...");
				consultarVentas(fich_ventas);
				break;

			case 5:
				System.out.println("Listando productos...");
				listarInventarioProductos(fich_productos);
				break;

			case 6:
				System.out.println("Modificando precio por tipo de producto...");
				modificarPrecio(fich_productos);
				break;

			case 7:
				System.out.println("ventas totales:");
				break;

			case 8:
				System.out.println("ranking de compradores:");
				break;

			case 9:
				System.out.println("Saliendo del programa...");
				break;

			default:

				System.out.println("Opción no válida");
			}

		} while (opcion != 9);
	}

	//Metodo 1
	public static void altaPersona(File fich_personas) {
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
	//metodo 2
	public static void altaProducto(File fich_Producto) {
		TipoCarta tipoCaja = null;
		String tipoProducto;
		double precio;
		int stock = 200;
		boolean error;
		ObjectOutputStream oos;
		MyObjectOutputStream moos;
		if(!fich_Producto.exists()) {
			try {
				oos = new ObjectOutputStream(new FileOutputStream(fich_Producto));
				do {
					error=false;
					System.out.println("Introduce el tipo de producto (Magic, Fútbol o Pokémon)");
					tipoProducto=Utilidades.introducirCadena();

					try {
						tipoCaja = TipoCarta.valueOf(tipoProducto); 
					} catch (IllegalArgumentException e) {

						System.err.println("El producto '" + tipoProducto + "' no es válido.");
						error=true;
					}
				}while(error==true);
				System.out.println("Introduce el precio del producto");
				precio=Utilidades.leerDouble();
				Producto p = new Producto(tipoCaja, precio, stock);
				oos.writeObject(p);
				oos.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(fich_Producto.exists()) {
			System.out.println("El fichero ya existe, se añadirán al final");

			try {
				moos = new MyObjectOutputStream(new FileOutputStream(fich_Producto));
				do {
					error=false;
					System.out.println("Introduce el tipo de producto (Magic, Fútbol o Pokémon)");
					tipoProducto=Utilidades.introducirCadena();

					try {
						tipoCaja = TipoCarta.valueOf(tipoProducto); 
					} catch (IllegalArgumentException e) {

						System.err.println("El producto '" + tipoProducto + "' no es válido.");
						error=true;
					}
				}while(error==true);
				System.out.println("Introduce el precio del producto");
				precio=Utilidades.leerDouble();
				Producto p = new Producto(tipoCaja, precio, stock);
				moos.writeObject(p);
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

	// Metodo 3 - realizarVenta sin variable tipoCaja
	public static void realizarVenta(File fich_ventas){
		String tipoProducto;
		int cantidad;
		boolean error;
		if(fich_ventas.exists()) {
			try {
				do {
					error = false;
					System.out.println("Introduce el tipo de producto (MAGIC, FUTBOL o POKEMON)");
					tipoProducto = Utilidades.introducirCadena().toUpperCase();
					try {

						TipoCarta.valueOf(tipoProducto);
					} catch (IllegalArgumentException e) {
						System.out.println("Tipo incorrecto");
						error = true;
					}
				} while (error);

				System.out.println("Introduce la cantidad:");
				cantidad = Utilidades.leerInt();
				Producto.stock-=cantidad;
				Venta v = new Venta(TipoCarta.valueOf(tipoProducto), cantidad, LocalDateTime.now());
				System.out.println("Venta guardada. Stock restante: " + Producto.stock);
				ObjectOutputStream oos;
				if (!fich_ventas.exists()) {
					oos = new ObjectOutputStream(new FileOutputStream(fich_ventas));
				} else {
					oos = new MyObjectOutputStream(new FileOutputStream(fich_ventas, true));
				}

				oos.writeObject(v);
				oos.close();

				System.out.println("Venta guardada correctamente");
			} catch (FileNotFoundException e) { 
				System.out.println("No se encontró el fichero"); 
			} catch (IOException e) { 
				System.out.println("Error leyendo el fichero"); 
			}
		}else{ 
			System.out.println("El fichero no existe"); 
		} 
	}

	//Metodo 4 
	public static void consultarVentas(File fich_ventas) { 
		ArrayList<Venta> listaVentas= new ArrayList<>();
		LocalDateTime fechaVenta; 
		ObjectInputStream ois; 
		boolean finArchivo=false; 
		if (fich_ventas.exists()) { 
			try { 
				ois=new ObjectInputStream(new FileInputStream(fich_ventas)); 
				while (!finArchivo) { 
					try {
						Venta v = (Venta) ois.readObject();
						listaVentas.add(v);
					} catch (EOFException e) {
						finArchivo = true;
					}
				} 
				ois.close(); 
			} catch (FileNotFoundException e) { 
				System.out.println("No se encontró el fichero"); 
			} catch (ClassNotFoundException e) { 
				System.out.println("La clase Animal no es válida"); 
			} catch (IOException e) { 
				System.out.println("Error leyendo el fichero"); 
			}
			listaVentas.sort(Comparator.comparing(Venta::getFecha));
			if (listaVentas.isEmpty()) {
				System.out.println("No hay ventas registradas.");
			} else {
				System.out.println("--- Listado de Ventas Ordenadas por Fecha ---");
				for (Venta v : listaVentas) {
					System.out.println(v.toString());
				} 
			}
		} else { 
			System.out.println("El fichero no existe"); 
		} 
	}

	//Metodo 5
	public static void listarInventarioProductos(File fich_productos) {
		ObjectInputStream ois;
		boolean finArchivo=false;
		if (fich_productos.exists()) {
			try {
				ois=new ObjectInputStream(new FileInputStream(fich_productos));
				while (!finArchivo) {
					try {
						Producto p = (Producto) ois.readObject();
						System.out.println(p);
					} catch (EOFException e) {
						finArchivo = true;
					}
				}
				ois.close();
			} catch (FileNotFoundException e) {
				System.out.println("No se encontró el fichero");
			} catch (ClassNotFoundException e) {
				System.out.println("La clase Producto no es válida");
			} catch (IOException e) {
				System.out.println("Error leyendo el fichero");
			}
		} else {
			System.out.println("El fichero no existe");
		}
	}
	
	//Metodo 6
	public static void modificarPrecio(File fich_productos) {
		TipoCarta tipoCaja = null;	
		String tipoProducto;
		double precio;
		boolean error=false;
		boolean fin = false;
		do {
			error=false;
			System.out.println("Introduce el tipo de producto (Magic, Fútbol o Pokémon)");
			tipoProducto=Utilidades.introducirCadena();

			try {
				tipoCaja = TipoCarta.valueOf(tipoProducto); 


			} catch (IllegalArgumentException e) {

				System.err.println("El producto '" + tipoProducto + "' no es válido.");
				error=true;
			}
		}while(error==true);

		System.out.println("introduce el nuevo precio del producto");
		precio=Utilidades.leerDouble(1,200);
		
		File fichAux = new File("productos_aux.dat");

		try (
				ObjectInputStream ois =
				new ObjectInputStream(new FileInputStream(fich_productos));
				ObjectOutputStream oos =
						new ObjectOutputStream(new FileOutputStream(fichAux))
				) {

			while (!fin) {
				try {
					Producto p = (Producto) ois.readObject();

					if (p.getTipoCaja() == tipoCaja) {
						p.setPrecio(precio);
					}

					oos.writeObject(p);

				} catch (EOFException e) {
					fin = true;
				}
			}

		} catch (IOException | ClassNotFoundException e) {
			System.out.println("Error al modificar el precio");
			return;
		}

		fich_productos.delete();
		fichAux.renameTo(fich_productos);

		System.out.println("Precio modificado ");


	}
	
	//Metodo 7
	public static void ventasTotalesProducto() {
		
	}
	//Metodo 8
	public static void rankingCompradores() {
		TreeMap<String, Integer> ranking = new TreeMap<>();
	}

}