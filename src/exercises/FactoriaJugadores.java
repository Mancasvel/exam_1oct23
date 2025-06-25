package exercises;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import utiles.Checkers;

public class FactoriaJugadores {
	
	Jugador parseaJugador(String lineaCsv) {
		String[] partes = lineaCsv.split(";");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		Checkers.check("Formato no valido", partes.length==9);
		String nombre = partes[0].trim();
		LocalDate fechaNacimiento = LocalDate.parse(partes[1].trim(), formatter);
		Posicion posicion = Posicion.valueOf(partes[2].trim());
		Integer goles = Integer.parseInt(partes[3].trim());
		Integer asistencias = Integer.parseInt(partes[4].trim());
		List<String> lesiones = parseaLista(partes[5].trim());
		String equipo = partes[6].trim();
		Integer clasificacion = Integer.valueOf(partes[7].trim());
		String estadio = partes[8].trim();
		
		
		return new Jugador(nombre, fechaNacimiento, posicion, goles, asistencias, lesiones, new Equipo(equipo, clasificacion, estadio));
		
		
	}

	private List<String> parseaLista(String lesiones) {
		String limpio = lesiones.replace("[", "").replace("]", "");
		String[] partes = limpio.split(",");
		List<String> res = new ArrayList<>();
		for (String p: partes) {
			res.add(p);
		}
		
		return res;
	}

}
