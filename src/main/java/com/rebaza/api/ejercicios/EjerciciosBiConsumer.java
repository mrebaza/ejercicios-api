package com.rebaza.api.ejercicios;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

import com.rebaza.api.models.Usuario;
import com.rebaza.api.utils.EjercicioDatos;

public class EjerciciosBiConsumer {

	public static void ejercicio1() {
		System.out.println("\n--- BiConsumer Ejercicio 1: Imprimir Clave-Valor de un Mapa ---");
		
		Map<String, Integer> puntuaciones = Map.of("Alice", 90, "Bob", 85, "Carol", 92);

		BiConsumer<String, Integer> imprimirClaveValor = 
		(clave, valor) -> System.out.println( "Clave: " + clave + ", Valor: " + valor);

		puntuaciones.forEach(imprimirClaveValor);

	}
	public static void ejercicio2() {
		System.out.println("\n--- BiConsumer Ejercicio 2: Concatenar Detalles de Usuario y Rol Asignado (Simulado) ---");
		List<Usuario> usuarios = EjercicioDatos.getUsuariosDeEjemplo();

		BiConsumer<Usuario, String> asignarRolSimulado =
		(usuario, rol) -> System.out.println("Asignando rol" + rol + " al usuario "+ usuario);

		for(Usuario usr: usuarios) {
			asignarRolSimulado.accept(usr, "EMPLEADO");
		}
	}
}
