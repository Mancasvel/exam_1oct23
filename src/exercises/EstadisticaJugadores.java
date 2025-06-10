package exercises;

import java.util.Objects;
import java.util.Set;

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

}
