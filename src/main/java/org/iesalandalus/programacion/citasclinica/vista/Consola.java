package org.iesalandalus.programacion.citasclinica.vista;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.iesalandalus.programacion.citasclinica.modelo.Cita;
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
	
	
	public static LocalDateTime leerFechaHora() {
		
		LocalDateTime fechaHora;
		
		DateTimeFormatter formato = DateTimeFormatter.ofPattern(Cita.FORMATO_FECHA_HORA);
		Pattern patronHora = Pattern.compile(Cita.FORMATO_FECHA_HORA);
		Matcher comparador;
		String entradaFecha;
		
		do {
			
			System.out.printf("Introduce una fecha (Formato: %s)%n", Cita.FORMATO_FECHA_HORA);
			entradaFecha = Entrada.cadena();
			
			comparador = patronHora.matcher(entradaFecha);
			
		} while(comparador.matches());
		
		fechaHora = LocalDateTime.parse(entradaFecha, formato);
		
		return fechaHora;
	}
	
	public static void main(String[] args) {
		/*mostrarMenu();
		System.out.println(elegirOpcion());*/
		System.out.println(leerFechaHora());
		
	}
	
}
