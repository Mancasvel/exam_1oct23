package exercises;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.NoSuchElementException;


public class EstadisticaJugadores {
	
	private Set<Jugador> jugadores;

	public EstadisticaJugadores(Set<Jugador> jugadores) {
		super();
		this.jugadores = jugadores;
	}

	public Set<Jugador> getJugadores() {
		return new HashSet<>(jugadores);
	}

	public Integer getNumJugadores() {
		return jugadores.size();
	}


	@Override
	public String toString() {
		return "EstadisticaJugadores [jugadores=" + jugadores + "]" + "\n";
	}

	
	public Long getNumeroLesionesDistintas(Integer umbralClasificacion) {
		
		return jugadores.stream()
				.filter(jugador -> jugador.getEquipo().clasificacion() < umbralClasificacion)
				.flatMap(jugador -> jugador.getLesiones().stream()) // asi se accede a la lista de dentro y se aplana teniendo todas las lesiones juntas
				.distinct()
				.count();
	}
	
	public List<String> getNJugadoresMasJovenesPosicionSinLesion(Posicion p, Integer n){
		
		return  jugadores.stream()
				.filter(jugador -> jugador.getPosicion().equals(p) &&
						(jugador.getLesiones().isEmpty()))
				.sorted(Comparator.comparing(jugador -> jugador.getEdad()))  // se deja en orden natural por que quiero los mas jovenes
				.limit(n)
				.map(jugador -> jugador.getNombre())
				.collect(Collectors.toList());
		
	}
	public Equipo getEquipoMasJugadoresConGolesSuperiorMedia() {
		Double golesMedia = jugadores.stream()
				.mapToInt(jugador -> jugador.getGoles())
				.average()
				.orElse(0.0);
		
		// mapa entrada equipo y valor cantidad de jugadores con mas goles que la media
		
		Map<Equipo, Long> equipoJugadores = jugadores.stream()
				.filter(jugador -> jugador.getGoles() > golesMedia)
				.collect(Collectors.groupingBy(jugador -> jugador.getEquipo(),
								Collectors.counting()));
		
		
		return equipoJugadores.entrySet().stream()
				.max(Comparator.comparing(par -> par.getValue()))
				.map(par -> par.getKey())
				.orElseThrow(() -> new NoSuchElementException("No se encontro equipo"));
	}
	
	public Boolean todosEquiposTienenJugadorLesionado() {
		
		Map<Equipo, Long> equipoJugadores = jugadores.stream()
				.collect(Collectors.groupingBy(jugador -> ((Jugador) jugador).getEquipo(), 
						Collectors.summingLong(jugador -> jugador.getLesiones().size())
						));
		
		return equipoJugadores.values().stream()
				.allMatch(lesiones -> lesiones>= 1);
				
	}
	
	public SortedMap<Equipo, String> getNombreJugadorConMayorSumaGAPorEquipo() {
		
		SortedMap<Equipo, String> resultado = new TreeMap<>(
			    Comparator.comparingInt(Equipo::clasificacion).reversed()
			);


	    Map<Equipo, Jugador> mejoresPorEquipo = new HashMap<>();

	    for (Jugador jugador : jugadores) {
	        Equipo equipo = jugador.getEquipo();
	        int sumaGA = jugador.getGoles() + jugador.getAsistencias();

	        if (!mejoresPorEquipo.containsKey(equipo)) {
	            mejoresPorEquipo.put(equipo, jugador);
	        } else {
	            Jugador actual = mejoresPorEquipo.get(equipo);
	            int actualGA = actual.getGoles() + actual.getAsistencias();

	            if (sumaGA > actualGA) {
	                mejoresPorEquipo.put(equipo, jugador);
	            }
	        }
	    }

	    for (Map.Entry<Equipo, Jugador> entry : mejoresPorEquipo.entrySet()) {
	        resultado.put(entry.getKey(), entry.getValue().getNombre());
	    }

	    return resultado;
	}

}
