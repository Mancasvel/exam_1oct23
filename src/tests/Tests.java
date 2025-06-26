package tests;

import java.util.List;

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


}
