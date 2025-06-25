package exercises;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

import utiles.Checkers;

public class Jugador implements Comparable<Jugador>{
	
	private String nombre;
	private LocalDate fechaNacimiento;
	private Integer edad;
	private Posicion posicion;
	private Integer goles;
	private Integer asistencias;
	private Equipo equipo;
	private List<String> lesiones;
	
	
	public Jugador(String nombre, LocalDate fechaNacimiento, Integer edad, Posicion posicion, Integer goles,
			Integer asistencias, Equipo equipo, List<String> lesiones) {
		super();
		this.nombre = nombre;
		Checkers.check("La fecha de nacimiento no puede ser anterior al 1970-01-01", !fechaNacimiento.isBefore(LocalDate.of(1970, 1, 1)));
		this.fechaNacimiento = fechaNacimiento;
		this.edad = edad;
		this.posicion = posicion;
		Checkers.check("Los goles y asistencias deben ser mayores o iguales a 0", (goles >= 0 && asistencias>= 0));
		this.goles = goles;
		this.asistencias = asistencias;
		this.equipo = equipo;
		this.lesiones = lesiones;

		
	}
	public Jugador(String nombre2, LocalDate fechaNacimiento2, Posicion posicion2, Integer goles2, Integer asistencias2,
			List<String> lesiones2, Equipo equipo2) {
	}
	public String getNombre() {
		return nombre;
	}
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}
	public Integer getEdad() {
		return edad;
	}
	public Posicion getPosicion() {
		return posicion;
	}
	public Integer getGoles() {
		return goles;
	}
	public Integer getAsistencias() {
		return asistencias;
	}
	public Equipo getEquipo() {
		return equipo;
	}
	public List<String> getLesiones() {
		return new ArrayList<>(lesiones);
	}
	public Boolean getCampeon() {
		Boolean res = false;
		if(this.getEquipo().clasificacion()==1) {
			res = true;
		}
		return res;
	}
	public Boolean getMismaLesionConsecutiva() {
		
		List<String> lesiones = this.getLesiones();
		for(String lesion: lesiones) {
			Integer positionAhora = lesiones.indexOf(lesion);
			Integer positionAnterior = lesiones.indexOf(lesion)-1;
			if(lesiones.get(positionAhora).equals(lesiones.get(positionAnterior))) {
				return true;
			}
			
		}
		
		return false;
	}
	
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Jugador other = (Jugador) obj;
		return Objects.equals(fechaNacimiento, other.fechaNacimiento) && Objects.equals(nombre, other.nombre);
	}
	@Override
	public int compareTo(Jugador o) {
		Integer res = this.fechaNacimiento.compareTo(o.fechaNacimiento);
		
		if(res!= 0) {
			res = this.nombre.compareTo(nombre);
		}
		return res;
	}
	
	
	

}
