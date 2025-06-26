package exercises;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
		
		
		return new Jugador(nombre, fechaNacimiento, posicion, goles, asistencias, new Equipo(equipo, clasificacion, estadio), lesiones);
		
		
	}

	private List<String> parseaLista(String lesiones) {
	    String limpio = lesiones.replace("[", "").replace("]", "").trim();
	    if (limpio.isEmpty()) return new ArrayList<>(); // Devuelve lista vacía si no hay lesiones
	    String[] partes = limpio.split(",");
	    List<String> res = new ArrayList<>();
	    for (String p : partes) {
	        res.add(p.trim());
	    }
	    return res;
	}


	public static EstadisticaJugadores leeJugadores(String rutaFichero) {
	    FactoriaJugadores factoria = new FactoriaJugadores();

	    List<String> lineas;
	    try {
	        lineas = Files.readAllLines(Paths.get(rutaFichero));
	    } catch (IOException e) {
	        throw new RuntimeException("Error leyendo el archivo CSV", e);
	    }

	    Set<Jugador> jugadores = lineas.stream()
	        .skip(1)
	        .map(String::trim)
	        .filter(linea -> !linea.isEmpty())
	        .map(factoria::parseaJugador)
	        .collect(Collectors.toSet());

	    return new EstadisticaJugadores(jugadores);
	}

}
