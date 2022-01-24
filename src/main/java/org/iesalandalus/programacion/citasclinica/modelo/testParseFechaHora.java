package org.iesalandalus.programacion.citasclinica.modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.iesalandalus.programacion.utilidades.Entrada;

public class testParseFechaHora {
	
	public static void main(String[] args) {
		
		LocalDateTime prueba;
		
		DateTimeFormatter formato = DateTimeFormatter.ofPattern(Cita.FORMATO_FECHA_HORA);
		Pattern patronHora = Pattern.compile(Cita.FORMATO_FECHA_HORA);
		Matcher comparador;
		
		do {
			
			System.out.printf("Introduce una fecha (Formato:%s)%n", Cita.FORMATO_FECHA_HORA);
			String test = Entrada.cadena();
			
			prueba = LocalDateTime.parse(test, formato);
			comparador = patronHora.matcher(test);
			
		} while(comparador.matches());
		
		System.out.println("Fecha y hora desde cadena: " + prueba.format(formato));
		
		
	}

}
