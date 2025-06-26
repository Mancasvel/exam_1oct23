package tests;

import exercises.EstadisticaJugadores;
import exercises.FactoriaJugadores;

public class Tests {
	
	public static void main(String[] args) {
		
		
		EstadisticaJugadores jugadores = FactoriaJugadores.leeJugadores("C:\\Users\\manue\\eclipse-workspace\\exam_1oct23\\src\\data\\jugadores.csv");
		
		
		System.out.println("Ejercicio 4.1================================================================");
		testJugadoresLesiones(jugadores, 6);
		testJugadoresLesiones(jugadores, 2);
		
		
		
		
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

}
