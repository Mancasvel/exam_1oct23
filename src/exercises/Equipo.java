package exercises;

import utiles.Checkers;

public record Equipo(String nombre, Integer clasificacion, String estadio) {
	
	public Equipo{
		
		Checkers.check("Las posiciones de la clasificacion debe estar comprendidas entre 1 y 20", (clasificacion>=1 && clasificacion <=20));
		Checkers.check("La propiedad estadio debe comenzar por la cadena estadio", estadio.startsWith("Estadio"));
	}

	@Override
	public String toString() {
		return "Equipo [nombre=" + nombre + ", clasificacion=" + clasificacion + ", estadio=" + estadio + "]";
	}

}
