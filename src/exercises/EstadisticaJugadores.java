package exercises;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class EstadisticaJugadores {
	
	private Set<Jugador> jugadores;

	private Integer numJugadores;
	
	public EstadisticaJugadores(Set<Jugador> jugadores) {
		super();
		this.jugadores = jugadores;
	}

	
	public Set<Jugador> getJugadores() {
		return jugadores;
	}

	public Integer getNumJugadores() {
		return jugadores.size();
	}


	@Override
	public String toString() {
		return "EstadisticaJugadores [jugadores=" + jugadores + "]" + "\n";
	}


	@Override
	public int hashCode() {
		return Objects.hash(jugadores);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EstadisticaJugadores other = (EstadisticaJugadores) obj;
		return Objects.equals(jugadores, other.jugadores);
	}

	
	public Long getNumeroLesionesDistintas(Integer umbralClasificacion) {
		
		return jugadores.stream()
				.filter(jugador -> jugador.getEquipo().clasificacion() <= umbralClasificacion)
				.map(jugador -> jugador.getLesiones())
				.distinct()
				.count();
		
	}
	
	public List<String> getNJugadoresMasJovenesPosicionSinLesion(Posicion p, Integer n){
		
		return (List<String>) jugadores.stream()
				.filter(jugador -> jugador.getPosicion().equals(p) && jugador.getLesiones().isEmpty())
				.sorted(Comparator.comparing(jugador -> jugador.getEdad()))
				.limit(n);
		
	}
	public Equipo getEquipoMasJugadoresConGolesSuperiorMedia() {
		Double mediaGoles = jugadores.stream()
				.mapToInt(jugador -> jugador.getGoles())
				.average()
				.orElse(0.0);
		
		// mapa equipo numero de jugadores por encima de la media
		
		Map<Equipo, Long> equipoJugadores = jugadores.stream()
				.filter(jugador -> jugador.getGoles()> mediaGoles)
				.collect(Collectors.groupingBy(jugador -> ((Jugador) jugador).getEquipo(),
						Collectors.counting()
						));
		
		return equipoJugadores.entrySet().stream()
				.max(Comparator.comparing(j -> j.getValue()))
				.get()
				.getKey();
	}
	
	public Boolean todosEquiposTienenJugadorLesionado() {
		
		Map<Equipo, Long> equipoJugadores = jugadores.stream()
				.collect(Collectors.groupingBy(jugador -> ((Jugador) jugador).getEquipo(), 
						Collectors.summingLong(jugador -> jugador.getLesiones().size())
						));
		
		return equipoJugadores.entrySet().stream()
				.filter(equipo -> equipo.getValue().equals(0))
				.findAny()
				.isPresent();
				
	}
	
	public SortedMap<Equipo, String> getNombreJugadorConMayorSumaGAPorEquipo(){
		
		return jugadores.stream()
				.collect(Collectors.groupingBy(jugador -> jugador.getEquipo(),
						() -> new TreeMap<Equipo, String>(),
						Collectors.collectingAndThen(
								Collectors.maxBy(Comparator.comparing(j -> j.getGoles() + j.getAsistencias())), 
								opt -> opt.map(o -> o.getNombre()).orElse("No hay jugador con más asistencias y goles.")
						)
						));
	}
}
