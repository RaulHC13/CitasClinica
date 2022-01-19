package org.iesalandalus.programacion.citasclinica.modelo;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Paciente {

	private static final String ER_DNI = "([0-9]{8})([A-Za-z])";
	private static final String ER_TELEFONO = "([0-9]{9})";//???
	
	private String nombre;
	private String dni;
	private String telefono;
	
	public Paciente(String nombre, String dni, String telefono) {
		
		setNombre(nombre);
		setDni(dni);
		setTelefono(telefono);
	}
	
	public Paciente(Paciente paciente) {
		
		if (paciente == null) {
			throw new NullPointerException("No se puede copiar un paciente nulo.");
		}
		this.nombre = paciente.getNombre(); 
		this.dni = paciente.getDni();
		this.telefono = paciente.getTelefono();
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		if(nombre == null) {
			throw new NullPointerException("ERROR: El nombre de un paciente no puede ser nulo o vacío.");
		}
		this.nombre = formateaNombre(nombre);
	}
	
	private String formateaNombre(String nombre) {
		
		char[] caracter = nombre.toCharArray(); //Se crea un array a partir de la string nombre
		caracter[0] = Character.toUpperCase(caracter[0]); //Se convierte la primera letra a mayuscula
		
		for (int i = 0; i < caracter.length -1; i++) {
			
			if(caracter[i] == ' ' || caracter[i] == '.')//Si una iteración es espacio o punto, la siguiente iteración sera mayuscula
				caracter[i+1] = Character.toUpperCase(caracter[i+1]);
		}
		String salida = new String(caracter);//A partir del array caracter se crea una string salida
		
		return salida.trim();//Para eliminar espacios se utiliza trim()
	}
	
	public String getDni() {
		return dni;
	}

	private void setDni(String dni) {
		if(dni == null) {
			throw new NullPointerException("ERROR: El DNI de un paciente no puede ser nulo o vacío.");
		}
		if (comprobarLetraDni(dni) == false) {//Comprueba que es un DNI válido
			throw new IllegalArgumentException("No es un DNI válido");
		}
		this.dni = dni;
	}
	
	private String getIniciales() {
		
		String patron = "([a-z])";
		
		Pattern minusculas = Pattern.compile(patron);
		Matcher compararMinusculas = minusculas.matcher(getNombre());
		
		
		return (compararMinusculas.replaceAll("")).replaceAll("\\s+", "");
	}
	
	private Boolean comprobarLetraDni(String dni) {
		
		boolean valido;
		
		Pattern patron;
        Matcher comparador;
        patron = Pattern.compile(ER_DNI);//Se le da valor al patron, en este caso la ER de DNI
        comparador = patron.matcher(dni);//Se compara el patron con la string dni
        
        char dniLetra = comparador.group(2).charAt(0); //Se pasa la letra de string a char
        int dniNum;
        
        dniNum = Integer.parseInt(comparador.group(1));//Se pasan los numeros de string a int
        
        int resto = dniNum%23;
        
        char[] letras = {'T','R','W','A','G','M','Y','F','P','D','X','B','N','J','Z','S','Q','V','H','L','C','K','E'};
        //Se crea un array con el que se compara el resto, la letra correcta sera igual a la posicion a la que corresponda el resto
        if(letras[resto] == dniLetra) {
        	valido = true;
        } else valido = false;
		
		return valido;
	}
	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
	
		if(telefono == null) {
			throw new NullPointerException("No se puede copiar un telefono nulo.");
		}
		
		if ((Pattern.matches(ER_TELEFONO, telefono)) == false) {
			throw new IllegalArgumentException("No es un número de teléfono válido");
		}
		
		this.telefono = telefono;
	}
	@Override
	public int hashCode() {
		return Objects.hash(dni, nombre, telefono);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Paciente other = (Paciente) obj;
		return Objects.equals(dni, other.dni) && Objects.equals(nombre, other.nombre)
				&& Objects.equals(telefono, other.telefono);
	}
	@Override
	public String toString() {
		return String.format("nombre=%s (%s), dni=%s, teléfono=%s", nombre, getIniciales(), dni, telefono);
	}
}	