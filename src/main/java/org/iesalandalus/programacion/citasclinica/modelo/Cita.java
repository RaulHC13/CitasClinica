package org.iesalandalus.programacion.citasclinica.modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Cita {
	
	public static final String FORMATO_FECHA_HORA = "dd/MM/yyyy HH:mm";
	private LocalDateTime fechaHora;
	private Paciente paciente;
	
	public Cita (Paciente paciente, LocalDateTime fechaHora) {//Constructor con parametros
		
		setPaciente(paciente);
		setFechaHora(fechaHora);
		
	}
	
	public Cita (Cita citaC) { //Constructor copia
		
		if (citaC == null) {
			throw new NullPointerException("ERROR: No se puede copiar una cita nula.");
		}
		
		setPaciente(citaC.getPaciente());
		setFechaHora(citaC.getFechaHora());
		
	}
	
	private void setPaciente(Paciente paciente) {
		
		if (paciente == null) {
			throw new NullPointerException("ERROR: El paciente de una cita no puede ser nulo.");
		}
		this.paciente = new Paciente(paciente); //this.paciente = paciente; Esta mal por que copia el objeto original, hay que crear uno nuevo.
	}
	
	public Paciente getPaciente() {
		return paciente;
	}
	
	public void setFechaHora(LocalDateTime fechaHora) {
		if (fechaHora == null) {	
			throw new NullPointerException("ERROR: La fecha y hora de una cita no puede ser nula.");
		}
		this.fechaHora = fechaHora;
	}
	
	public LocalDateTime getFechaHora() {
		return fechaHora;
	}

	@Override
	public int hashCode() {
		return Objects.hash(fechaHora);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cita other = (Cita) obj;
		return Objects.equals(fechaHora, other.fechaHora);
	}

	@Override
	public String toString() {
		return String.format("Cita:%n[%s]%n[Fecha y Hora = %s]",paciente, fechaHora.format(DateTimeFormatter.ofPattern(FORMATO_FECHA_HORA)));
		//return String.format("Cita:%n[%s]%n[Fecha y Hora = %s]",paciente, fechaHora); Hay que utilizar un format para darle formato a la fecha.
	}
	
	
	
	
	
	
	

}