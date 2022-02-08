package org.iesalandalus.programacion.citasclinica;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.citasclinica.modelo.Cita;
import org.iesalandalus.programacion.citasclinica.modelo.Citas;
import org.iesalandalus.programacion.citasclinica.modelo.Paciente;
import org.iesalandalus.programacion.citasclinica.vista.Consola;
import org.iesalandalus.programacion.citasclinica.vista.Opciones;

public class MainApp {
	
	final static private int NUM_MAX_CITAS = 10;
	private static Citas citasClinica = new Citas(NUM_MAX_CITAS);
	
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
	
	private static void insertarCita(){
		
		try {
			
			citasClinica.insertar(Consola.leerCita());
			System.out.println("Cita insertada");
		}
		catch(IllegalArgumentException | NullPointerException | OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		}
		
	
	}
	
	private static void buscarCita() {
		
		try {
			Paciente paciente = new Paciente("Paciente test", "91708653V","681345216");
			Cita cita = new Cita(paciente, Consola.leerFechaHora());
			cita = citasClinica.buscar(cita);
		
		if (cita != null) {
			System.out.printf("La cita es: %s",cita);
		} else {
			System.out.println("No se ha encontrado ninguna cita.");
		}
		}
		catch (IllegalArgumentException | NullPointerException e) {
			System.out.println(e.getMessage());
		}
			
	}
	
	private static void borrarCita() {
		
		try {
			Paciente paciente = new Paciente("Paciente test", "91708653V","681345216");
			Cita cita = new Cita(paciente, Consola.leerFechaHora());
			citasClinica.borrar(cita);
			System.out.println("Cita borrada");
		}
		catch (IllegalArgumentException | NullPointerException | OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	private static void mostrarCitasDia() {
		
		Cita[] citasDia	 = citasClinica.getCitas(Consola.leerFecha());
		int citasMonstradas = 0;
		
		for (int i = 0; i < citasDia.length; i++) {
			if(citasDia[i] != null) {
				System.out.println(citasDia[i]);
				citasMonstradas++;
			}
		}
		if (citasMonstradas == 0) {
			System.out.println("No existen citas para ese dia");
		} else  {
			System.out.println("");
		}
	}

	private static void mostrarCitas() {
		
		Cita[] citas = citasClinica.getCitas();
		int citasMonstradas = 0;
		
		for (int i = 0; i < citas.length; i++) {
			if(citas[i] != null) {
				System.out.println(citas[i]);
				citasMonstradas++;
			}
		}
		if (citasMonstradas == 0) {
			System.out.println("No existen citas para ese dia");
		} else  {
			System.out.println("");
		
	}
}
}