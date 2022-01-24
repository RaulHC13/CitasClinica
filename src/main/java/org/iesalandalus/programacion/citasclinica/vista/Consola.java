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
	public static void mostrarMenu() {
		System.out.println("\n\n           Menu Inicial");
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
		Opciones opcion = Opciones.SALIR;
		
		do {
			
			System.out.println("Elige una opción: ");
			codigoOpcion = Entrada.entero();
			
		} while (codigoOpcion < 0 || codigoOpcion > 5);
		
		switch(codigoOpcion) {
		
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
	}
	public static Cita leerCita() {
		
		Paciente pacienteCita = leerPaciente();
		LocalDateTime fechaHoraCita = leerFechaHora();
		
		return new Cita(pacienteCita, fechaHoraCita);
	}
	public static Paciente leerPaciente() {
		
		 Paciente pacienteLeer = null;
		 
		 String nombre, dni, telefono;
		 boolean bucle = true;
		 
		 while(bucle) {
		 
		 try {
		 
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
		
		DateTimeFormatter formato = DateTimeFormatter.ofPattern(Cita.FORMATO_FECHA_HORA);
		boolean bucle = true;
		
		while (bucle) {
		try {
		
		System.out.println("Introduce una fecha y hora (Formato: dd/MM/yyyy HH:mm)");
		String fechaHoraEntrada = Entrada.cadena();
		
		bucle = false;

		fechaHora = LocalDateTime.parse(fechaHoraEntrada, formato);
			
		} catch (DateTimeParseException e) {
			System.out.println("No es una fecha y hora válida o el formato no es correcto.");
			bucle = true;
		} 
		} 
			return fechaHora;
	}
	
	public static LocalDate leerFecha() {
		LocalDate fecha = null;
		
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		boolean bucle = true;
		
		while (bucle) {
		try {
		
		System.out.println("Introduce una fecha (Formato: dd/MM/yyyy)");
		String fechaEntrada = Entrada.cadena();
		
		bucle = false;

		fecha = LocalDate.parse(fechaEntrada, formato);
			
		} catch (DateTimeParseException e) {
			System.out.println("No es una fecha válida o el formato no es correcto.");
			bucle = true;
		} 
		}
		return fecha;
	}	
}