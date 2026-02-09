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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


import clases.Cliente;
import clases.Empleado;
import clases.Producto;
import clases.Venta;
import enumeraciones.TipoCarta;
import excepciones.NegativeStockException;
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
		System.out.println("9.- Borrar cliente");
		System.out.println("10.- Salir");
		System.out.print("Elige una opción: ");
	}

	public static void main(String[] args) {
		int opcion;
		File fich_personas = new File ("personas.txt");
		File fich_productos = new File("productos.dat");
		File fich_ventas = new File("ventas.dat");

		do {
			menu();
			opcion = Utilidades.leerInt(1, 10);

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
				System.out.println("Ventas totales:");
				ventasTotalesProducto();
				break;

			case 8:
				System.out.println("Ranking de compradores:");
				rankingCompradores(fich_personas); 
				break;

			case 9:
				System.out.println("Borrando cliente:");
				borrarCliente(fich_personas); 
				break;

			case 10:
				System.out.println("Saliendo del programa...");
				break;

			default:

				System.out.println("Opción no válida");
			}

		} while (opcion != 10);
	}

	public static void altaPersona(File fich_personas) {
		String nombre, dni, tipoPersona;
		int cod_Cliente, cod_Empleado;
		if(!fich_personas.exists()) {
			try {
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fich_personas));
				System.out.println("Introduce el DNI del nuevo usuario : ");
				dni=Utilidades.introducirCadena();
				System.out.println("Introduce el nombre del nuevo usuario: ");
				nombre=Utilidades.introducirCadena();
				System.out.println("Introduce el tipo de persona: ");
				tipoPersona=Utilidades.introducirCadena();
				if(tipoPersona.equalsIgnoreCase("Empleado")) {
					System.out.println("Introduzca el codigo del nuevo Empleado");
					cod_Empleado=Utilidades.leerInt();
					Empleado e=new Empleado(dni, nombre, tipoPersona, cod_Empleado);
					System.out.println("Empleado añadido correctamente.");
					oos.writeObject(e);
				}else if(tipoPersona.equalsIgnoreCase("Cliente")) {
					System.out.println("Introduzca el codigo del nuevo cliente");
					cod_Cliente=Utilidades.leerInt();
					Cliente c=new Cliente(dni, nombre, tipoPersona, cod_Cliente);
					System.out.println("Cliente añadido correctamente.");
					oos.writeObject(c);
				}else {
					System.out.println("Ese tipo de persona no existe");
				}
				oos.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else if(fich_personas.exists()) {
			System.out.println("El fichero ya existe, se añadirán al final");

			try {
				MyObjectOutputStream moos = new MyObjectOutputStream(new FileOutputStream(fich_personas,true));
				System.out.println("Introduce el DNI del nuevo usuario : ");
				dni=Utilidades.introducirCadena();
				System.out.println("Introduce el nombre del nuevo usuario: ");
				nombre=Utilidades.introducirCadena();
				System.out.println("Introduce el tipo de persona: ");
				tipoPersona=Utilidades.introducirCadena();
				if(tipoPersona.equalsIgnoreCase("Empleado")) {
					System.out.println("Introduzca el codigo del nuevo Empleado");
					cod_Empleado=Utilidades.leerInt();
					Empleado e=new Empleado(dni, nombre, tipoPersona, cod_Empleado);
					System.out.println("Empleado añadido correctamente.");
					moos.writeObject(e);
				}else if(tipoPersona.equalsIgnoreCase("Cliente")) {
					System.out.println("Introduzca el codigo del nuevo cliente");
					cod_Cliente=Utilidades.leerInt();
					Cliente c=new Cliente(dni, nombre, tipoPersona, cod_Cliente);
					System.out.println("Cliente añadido correctamente.");
					moos.writeObject(c);
				}else {
					System.out.println("Ese tipo de persona no existe");
				}
				moos.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void altaProducto(File fich_Producto) {
		TipoCarta tipoCaja = null;
		String tipoProducto;
		double precio;
		int stock = 200;
		boolean error;
		
		if(!fich_Producto.exists()) {
			try {
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fich_Producto));
				do {
					error=false;
					System.out.println("Introduce el tipo de producto (Magic, Fútbol o Pokémon)");
					tipoProducto=Utilidades.introducirCadena().toUpperCase();

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
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else if(fich_Producto.exists()) {
			System.out.println("El fichero ya existe, se añadirán al final");

			try {
				MyObjectOutputStream moos = new MyObjectOutputStream(new FileOutputStream(fich_Producto));
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
				}while(error);
				
				System.out.println("Introduce el precio del producto");
				precio=Utilidades.leerDouble();
				Producto p = new Producto(tipoCaja, precio, stock);
				moos.writeObject(p);
				moos.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@SuppressWarnings("resource")
	public static void realizarVenta(File fich_ventas){
		String tipoProducto;
		int cantidad;
		boolean error, finarchivo=true;

		if(fich_ventas.exists()) {
			try {
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fich_ventas, true));

				do {
					error = false;
					System.out.println("Introduce el tipo de producto (MAGIC, FUTBOL o POKEMON)");
					tipoProducto = Utilidades.introducirCadena().toUpperCase();
					try {
						TipoCarta.valueOf(tipoProducto);
					} catch (IllegalArgumentException e) {
						System.out.println("Tipo incorrecto. Debe ser MAGIC, FUTBOL O POKEMON");
						error = true;
					}
				} while (error);

				System.out.println("Stock actual disponible: "+Producto.stock);
				System.out.println("Introduce la cantidad a vender (0 para cancelar la venta):");
				cantidad = Utilidades.leerInt();

				if(cantidad == 0) {
					System.out.println("Venta cancelada.");
					oos.close();
				}else if(cantidad < 0) {
					throw new NegativeStockException("El stock no puede ser negativo");
				}else if(cantidad > Producto.stock) {
					throw new OverStockExcepcion("No hay suficiente stock. Stock disponible: "+Producto.stock);
				}

				TipoCarta tipo = TipoCarta.valueOf(tipoProducto);
				switch (tipo) {
				case MAGIC:
					Producto.ventasMagic += cantidad;
					break;
				case FUTBOL:
					Producto.ventasFutbol += cantidad;
					break;
				case POKEMON:
					Producto.ventasPokemon += cantidad;
					break;
				}

				Producto.stock -= cantidad;

				Venta v = new Venta(tipo, cantidad, LocalDateTime.now());
				oos.writeObject(v);
				oos.close();

				System.out.println("Introduce el código del cliente:");
				int codCliente = Utilidades.leerInt();

				File fichAux = new File("personas_aux.dat");
				boolean encontrado = false;

				try (
						ObjectInputStream ois = new ObjectInputStream(new FileInputStream("personas.txt"));
						ObjectOutputStream oosCli = new ObjectOutputStream(new FileOutputStream(fichAux))
						) {
					while (!finarchivo) {
						try {
							Object obj = ois.readObject();

							if (obj instanceof Cliente) {
								Cliente c = (Cliente) obj;
								if (c.getCodigoCliente() == codCliente) {
									c.agregarCompra(cantidad);
									encontrado = true;
								}
								oosCli.writeObject(c);
							} else {
								oosCli.writeObject(obj);
							}
						} catch (EOFException e) {
							finarchivo = true;
						}
					}
				}

				if (encontrado) {
					new File("personas.txt").delete();
					fichAux.renameTo(new File("personas.txt"));
					System.out.println("Compra añadida al cliente correctamente");
				} else {
					fichAux.delete();
					System.out.println("Cliente no encontrado");
				}

				System.out.println("Venta guardada correctamente");
				System.out.println("Stock restante: " + Producto.stock);

			} catch (FileNotFoundException e) {
				System.err.println("No se encontró el fichero");
			} catch (IOException e) {
				System.err.println("Error leyendo o escribiendo fichero");
			} catch (NegativeStockException | OverStockExcepcion e) {
				System.err.println(e.getMessage());
			} catch (ClassNotFoundException e) {
				System.err.println("Error de clase");
			}
		} else {
			System.err.println("El fichero no existe");
		}
	}

	public static void consultarVentas(File fich_ventas) { 
		ArrayList<Venta> listaVentas= new ArrayList<>();
		boolean finArchivo=false; 
		if (fich_ventas.exists()) { 
			try { 
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fich_ventas)); 
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

	public static void listarInventarioProductos(File fich_productos) {
		boolean finArchivo=false;
		if (fich_productos.exists()) {
			try {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fich_productos));
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

	public static void modificarPrecio(File fich_productos) {
		TipoCarta tipoCaja = null;	
		String tipoProducto;
		double precio;
		boolean error=false;
		boolean finarchivo = false;
		
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
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fich_productos));
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fichAux))
				) {

			while (!finarchivo) {
				try {
					Producto p = (Producto) ois.readObject();

					if (p.getTipoCaja() == tipoCaja) {
						p.setPrecio(precio);
					}

					oos.writeObject(p);

				} catch (EOFException e) {
					finarchivo = true;
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

	public static void ventasTotalesProducto() {
		int ventasTotales = Producto.ventasMagic + 
				Producto.ventasFutbol + 
				Producto.ventasPokemon;

		int stockInicial = 200;
		int stockRestante = Producto.stock;

		System.out.println("=== VENTAS TOTALES POR PRODUCTO ===");
		System.out.println("Stock inicial común:     " + stockInicial);
		System.out.println("Stock restante actual:    " + stockRestante);
		System.out.println("----------------------------------------");
		System.out.println("MAGIC:    " + Producto.ventasMagic   + " unidades vendidas");
		System.out.println("FUTBOL:   " + Producto.ventasFutbol  + " unidades vendidas");
		System.out.println("POKEMON:  " + Producto.ventasPokemon + " unidades vendidas");
		System.out.println("----------------------------------------");
		System.out.println("Total unidades vendidas (todas categorías): " + ventasTotales);
	}

	public static void rankingCompradores(File fich_personas) {
		ArrayList<Cliente> clientes = new ArrayList<>();
		boolean finarchivo = false;

		if (!fich_personas.exists()) {
			System.out.println("No hay clientes registrados.");
		}

		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fich_personas));
			while (!finarchivo) {
				try {
					Object obj = ois.readObject();
					if (obj instanceof Cliente) {
						clientes.add((Cliente) obj);
					}
				} catch (EOFException e) {
					finarchivo = true;
				}
			}
			ois.close();
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("Error leyendo clientes.");
		}

		for (int i = 0; i < clientes.size() - 1; i++) {
			for (int j = i + 1; j < clientes.size(); j++) {
				if (clientes.get(i).getTotalCompras() < clientes.get(j).getTotalCompras()) {
					Cliente aux = clientes.get(i);
					clientes.set(i, clientes.get(j));
					clientes.set(j, aux);
				}
			}
		}

		System.out.println("=== RANKING DE COMPRADORES ===");
		for (int i = 0; i < clientes.size(); i++) {
			Cliente c = clientes.get(i);
			System.out.println(
					(i + 1) + ". " +
							c.getNombre() +
							" | Código: " + c.getCodigoCliente() +
							" | Compras: " + c.getTotalCompras()
					);
		}
	}

	public static void borrarCliente(File fich_personas) {
		boolean borrado = false;
		boolean finarchivo = false;
		if (!fich_personas.exists()) {
			System.out.println("No existe el fichero de personas.");
			return;
		}

		System.out.println("Introduce el código del cliente a borrar:");
		int codCliente = Utilidades.leerInt();

		File fichAux = new File("personas_aux.dat");


		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fich_personas));
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fichAux));

			while (!finarchivo) {
				try {
					Object obj = ois.readObject();

					if (obj instanceof Cliente) {
						Cliente c = (Cliente) obj;

						if (c.getCodigoCliente() == codCliente) {
							borrado = true;
						} else {
							oos.writeObject(c);
						}
					} else {
						oos.writeObject(obj);
					}

				} catch (EOFException e) {
					finarchivo = true;
				}
			}

			ois.close();
			oos.close();

		} catch (IOException | ClassNotFoundException e) {
			System.out.println("Error al borrar cliente.");
			return;
		}

		if (borrado) {
			fich_personas.delete();
			fichAux.renameTo(fich_personas);
			System.out.println("Cliente borrado correctamente.");
		} else {
			fichAux.delete();
			System.out.println("No se encontró ningún cliente con ese código.");
		}
	}

}