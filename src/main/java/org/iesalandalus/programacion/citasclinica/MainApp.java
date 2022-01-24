package org.iesalandalus.programacion.citasclinica;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.citasclinica.modelo.Cita;
import org.iesalandalus.programacion.citasclinica.modelo.Citas;
import org.iesalandalus.programacion.citasclinica.vista.Consola;
import org.iesalandalus.programacion.citasclinica.vista.Opciones;

public class MainApp {
	
	final static private int NUM_MAX_CITAS = 10;
	
	public MainApp() {
		
	}
	
	public static void main(String[] args) throws OperationNotSupportedException {
		Opciones opcion = Opciones.SALIR;
		
		do {

		System.out.println("Programa para gestionar las citas de la clínica.\n");
		Consola.mostrarMenu();
		opcion = Consola.elegirOpcion();
		ejecutarOpcion(opcion);
		
		} while(opcion != Opciones.SALIR);
	}
	
	private static void ejecutarOpcion(Opciones opcion) throws OperationNotSupportedException {
		
		switch (opcion) {
		
		case SALIR:
			System.out.println("Saliendo del programa...");
			break;
		
		case INSERTAR_CITA:
			insertarCita();
			break;
			
		case BUSCAR_CITA:
			buscarCita();
			break;
		case BORRAR_CITA:
			borrarCita();
			break;
		case MOSTRAR_CITAS:
			mostrarCitas();
			break;
		case MOSTRAR_CITAS_DIA:
			mostrarCitasDia();
			break;
		}
	}
	
	private static void insertarCita() throws OperationNotSupportedException {
		
		Cita cita = new Cita(Consola.leerCita());
		
		try {
			Citas citas = new Citas(0);
			citas.insertar(cita);
		} catch (Exception e) {
		}
		System.out.println(cita);
	}
	
	private static void buscarCita() {
		
		Consola.leerFechaHora();
		
	}
	
	private static void borrarCita() {
		
		Consola.leerFechaHora();
		
	}
	
	private static void mostrarCitasDia() {
		
		Consola.leerFecha();	
	}

	private static void mostrarCitas() {
		
	}
}