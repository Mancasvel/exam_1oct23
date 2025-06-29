package tests;

import java.util.List;
import java.util.SortedMap;

import exercises.Equipo;
import exercises.EstadisticaJugadores;
import exercises.FactoriaJugadores;
import exercises.Posicion;

public class Tests {
	
	public static void main(String[] args) {
		
		
		EstadisticaJugadores jugadores = FactoriaJugadores.leeJugadores("C:\\Users\\manue\\eclipse-workspace\\exam_1oct23\\src\\data\\jugadores.csv");
		
		
		System.out.println("Ejercicio 4.1================================================================");
		testJugadoresLesiones(jugadores, 6);
		testJugadoresLesiones(jugadores, 2);
		
		System.out.println("Ejericio 4.2==================================================================");
		testJugadoresMasJovenes(jugadores, Posicion.DELANTERO, 3);
		testJugadoresMasJovenes(jugadores, Posicion.CENTROCAMPISTA, 3);
		
		System.out.println("Ejercicio 4.3==================================================================");
		testEquipoMasJugadoresConGolesSuperior(jugadores);
		
		System.out.println("Ejercicio 4.4=================================================================");
		testTodosEquiposTienenJugadorLesionado(jugadores);
		
		System.out.println("Ejercicio 4.5==================================================================");
		testNombreJugadorConMayorSuma(jugadores);
	}

	





	private static void testJugadoresLesiones(EstadisticaJugadores jugadores, int umbralLesiones) {
		try {
			
			Long res1 = jugadores.getNumeroLesionesDistintas(umbralLesiones);
			String msg = "El número de lesiones distintas de los "+ umbralLesiones + " primeros es:";
			System.out.println(msg);
			System.out.println(res1);
			
		}
		catch (Exception e) {
			
			System.out.println("Excepción inesperada");
			System.out.println(e);
		}
		
	}
	
	private static void testJugadoresMasJovenes(EstadisticaJugadores jugadores, Posicion posicion, int i) {
		try {
			List<String> res = jugadores.getNJugadoresMasJovenesPosicionSinLesion(posicion, i);
			String msg = "Los " +  i + " jugadores más jovenes sin lesión de la posición " + posicion + " son:";
			
			System.out.println(msg);
			System.out.println(res);
			
	
		}
		catch(Exception e) {

			System.out.println("Excepción producida");
			System.out.println(e);
		}
	}
	
	private static void testEquipoMasJugadoresConGolesSuperior(EstadisticaJugadores jugadores) {
		try {
			
			Equipo res = jugadores.getEquipoMasJugadoresConGolesSuperiorMedia();
			String msg = "El equipo con mayor numero de jugadores con goles por encima de la media es:";
			
			System.out.println(msg + "\n" + res);
			
			
		}
		catch(Exception e) {
			
			System.out.println("Excepcion inesperada:");
			System.out.println(e);
		}
		
	}
	

	private static void testTodosEquiposTienenJugadorLesionado(EstadisticaJugadores jugadores) {
		try {
			
			Boolean res = jugadores.todosEquiposTienenJugadorLesionado();
			String msg = "¿Todos los equipos tienen al menos un jugador que ha sufrido una lesion?";
			
			System.out.println(msg + "\n" + res);
			
		}
		catch(Exception e) {
			System.out.println("Excepcion inesperada: ");
			System.out.println(e);
			
		}
	}
	
	
	
	private static void testNombreJugadorConMayorSuma(EstadisticaJugadores jugadores) {
		try {
			SortedMap<Equipo, String> res = jugadores.getNombreJugadorConMayorSumaGAPorEquipo();
			String msg = "Los nombres de jugadores con mayor ratio goles/asistencias por equipo son:";
			
			System.out.println(msg + "\n" + res);
			
		}
		catch(Exception e) {
			
			System.out.println("Excepcion inesperada: ");
			System.out.println(e);
			
		}
	}


}
