package org.iesalandalus.programacion.citasclinica.modelo;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.naming.OperationNotSupportedException;

public class Citas {
	
	private int capacidad;
	private int tamano;
	private Cita[] coleccionCitas;
	
	public Citas(int capacidad) {
		
		if(capacidad <= 0)
			throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
		this.capacidad = getCapacidad();
		coleccionCitas = new Cita[capacidad];
		this.tamano = 0;
	}
	
	public Cita[] getCitas() {
		
		return coleccionCitas;
	}
	
	public Cita[] getCitas(LocalDate fechaHora) {
		
		Cita[] citasFechaHora = new Cita[tamano];
				
		if (fechaHora.equals(null)) {
			throw new NullPointerException("ERROR: No se puede copiar una fecha vacía o nula.");
		}
		int j = 0;
		LocalDateTime comienzoDia = LocalDateTime.of(fechaHora.getYear(), fechaHora.getMonth(), fechaHora.getDayOfMonth(), 0, 0);
		LocalDateTime finDia = LocalDateTime.of(fechaHora.getYear(), fechaHora.getMonth(), fechaHora.getYear(), 23, 59);
		
		for(int i = 0; i < tamano; i++) {
			
			if (coleccionCitas[i].getFechaHora().isAfter(comienzoDia) && (coleccionCitas[i].getFechaHora().isBefore(finDia))) {
				
				citasFechaHora[j] = new Cita(coleccionCitas[i]);
				j++;
			}
			
		}
		return citasFechaHora;
	}
	
	public int getTamano() {
		
		return tamano;
	}
	
	public int getCapacidad() {
		
		return capacidad;
	}
	
	public void insertar(Cita cita) throws OperationNotSupportedException {
		
		if (cita == null) {
			throw new NullPointerException("ERROR: No se puede insertar una cita nula.");
		}
		if (capacidadSuperada(tamano)) {
			throw new OperationNotSupportedException("ERROR: No se pueden crear mas citas");
		}
		
		int indice = buscarIndice(cita);
		
		if (tamanoSuperado(indice)) {
			coleccionCitas[tamano] = new Cita(cita);
		} else {
			throw new OperationNotSupportedException("ERROR: No se aceptan más citas.");
		}
		tamano++;
	}
	private int buscarIndice(Cita cita) {
		
		boolean buscar = false;
		int indice = tamano + 1;
		
		for (int i = 0; i < tamano&&!buscar; i++) {
			if (coleccionCitas[i] == cita) {
				buscar = true;
				indice = i;
			}
		}
		return indice;
	}
	
	private boolean tamanoSuperado(int superaTamano) {
		boolean tamanoSuperado = false;;
		
		if (tamano > superaTamano) {
			 tamanoSuperado = true;
		} else if (tamano <= superaTamano) {
			 tamanoSuperado = false;
		}
		return tamanoSuperado;
	}
	
	private boolean capacidadSuperada(int superaCapacidad) {
		boolean capacidadSuperada = false;;
		
		if (capacidad > superaCapacidad) {
			capacidadSuperada = true;
		} else if (capacidad <= superaCapacidad) {
			capacidadSuperada = false;
		}
		return capacidadSuperada;
	}
	
	public Cita buscar(Cita cita) {
		
		if(cita == null) {
			throw new NullPointerException("ERROR: No se puede copiar una cita nula.");
		}
		int indice = buscarIndice(cita);
		
		if (tamanoSuperado(indice)) {
			return null;
		} else {
			return new Cita(coleccionCitas[indice]);
		}
	}
	public void borrar(Cita cita) throws OperationNotSupportedException {
		int indice;
		
		if (cita == null) {
			throw new NullPointerException("ERROR: No se puede copiar una cita nula.");
		}
		indice = buscarIndice(cita);
		
		if (tamanoSuperado(indice)) {
			throw new OperationNotSupportedException("ERROR: No existe ninguna cita para esa fecha y hora.");
		}
		desplazarUnaPosicionHaciaIzquierda(indice);
		tamano--;
	}
	private void desplazarUnaPosicionHaciaIzquierda(int indice) {
		
		int i; 
		
		for (i = indice; i < coleccionCitas.length - 1; i++) {
			coleccionCitas[i] = coleccionCitas[i+1];
		}
		coleccionCitas[i] = null;
	}
}