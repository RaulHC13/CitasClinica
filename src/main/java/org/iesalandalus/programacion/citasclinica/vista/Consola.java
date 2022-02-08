package org.iesalandalus.programacion.citasclinica.vista;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.iesalandalus.programacion.citasclinica.modelo.Cita;
import org.iesalandalus.programacion.citasclinica.modelo.Paciente;
import org.iesalandalus.programacion.utilidades.Entrada;

public class Consola {
	
	private Consola() {
			
	}
	
	public static void mostrarMenu() { //Muestra este menu.
		System.out.println("               Menu Inicial");
		System.out.println("==================================");
		System.out.println("1. Insertar una cita.");
		System.out.println("2. Buscar una cita.");
		System.out.println("3. Borrar una cita.");
		System.out.println("4. Mostrar todas las citas.");
		System.out.println("5. Mostrar las citas de una fecha.");
		System.out.println("\n0.- Salir del programa.\n");
	}
	
	public static Opciones elegirOpcion() {
		int codigoOpcion;
		Opciones opcion = null;
		
		do {
			
			System.out.println("Elige una opción: ");
			codigoOpcion = Entrada.entero();
			
		} while (codigoOpcion < 0 || codigoOpcion > 5);
		
		switch(codigoOpcion) {//Dependiendo de la opcion elegida devuelve un valor u otro.
		
		case 0:
			opcion = Opciones.SALIR;
			break;
		
		case 1:
			opcion = Opciones.INSERTAR_CITA;
			break;
		
		case 2:
			opcion = Opciones.BUSCAR_CITA;
			break;
			
		case 3:
			opcion = Opciones.BORRAR_CITA;
			break;
			
		case 4:
			opcion = Opciones.MOSTRAR_CITAS;
			break;
			
		case 5: 
			opcion = Opciones.MOSTRAR_CITAS_DIA;
			break;
		}
		return opcion;
		//return Opciones.values()[codigoOpcion]; Se puede utilizar .values para crear un array y devolver la posicion.
	}
	public static Cita leerCita() { //Utiliza dos métodos para crear una Cita
		
		Paciente pacienteCita = leerPaciente();
		LocalDateTime fechaHoraCita = leerFechaHora();
		
		return new Cita(pacienteCita, fechaHoraCita);
	}
	public static Paciente leerPaciente() {
		
		 Paciente pacienteLeer = null;
		 
		 String nombre, dni, telefono;
		 boolean bucle = true;
		 
		 while(bucle) {//Bucle para que en caso de error, el try catch maneje la excepción y pueda volver a pedir valores, es un bucle infinito.
		 
		 try {//Maneja la excepción que da en caso de que los valores no sean válidos.
		 
		 System.out.println("Introduce el nombre del paciente: ");
		 nombre = Entrada.cadena();
		 
		 System.out.println("Introduce el DNI del paciente: ");
		 dni = Entrada.cadena();
		 
		 System.out.println("Introduce el telefono del paciente: ");
		 telefono = Entrada.cadena();
		 
		 bucle = false;
		 
		pacienteLeer = new Paciente(nombre,dni,telefono);
		
		 } catch (Exception e) {
			 System.out.println("ERROR: Algunos de los datos no son válidos.");
			 bucle = true;
		 }
		 }
		return pacienteLeer;
	}
	
	public static LocalDateTime leerFechaHora() {
		
		LocalDateTime fechaHora = null;
		
		do {
			System.out.println("Introduce una fecha (Formato: dd/MM/yyyy)");
			try {
				fechaHora = LocalDateTime.parse(Entrada.cadena(), DateTimeFormatter.ofPattern(Cita.FORMATO_FECHA_HORA));
			}
			catch (DateTimeParseException e) {
				fechaHora = null;
			}
		}while (fechaHora == null);
			return fechaHora;
	}
	public static LocalDate leerFecha() {
		
		LocalDate fecha = null;
	
		do {
			System.out.println("Introduce una fecha (Formato: dd/MM/yyyy)");
			try {
				fecha = LocalDate.parse(Entrada.cadena(), DateTimeFormatter.ofPattern(Cita.FORMATO_FECHA_HORA));
			}
			catch (DateTimeParseException e) {
				fecha = null;
			}
		}while (fecha == null);
		
		return fecha;
	}	
}