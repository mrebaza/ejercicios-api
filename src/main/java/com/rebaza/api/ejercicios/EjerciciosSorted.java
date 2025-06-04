package com.rebaza.api.ejercicios;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.rebaza.api.models.Usuario;
import com.rebaza.api.utils.EjercicioDatos;

public class EjerciciosSorted {

	public static void ejercicio1() {
		System.out.println("\n--- sorted Ejercicio 1: Ordenar Números Naturalmente ---");
		List<Integer> numerosDesordenados = Arrays.asList(5,1,8,2,10,3,7,4);

		List<Integer> numerosOrdenados = numerosDesordenados.stream()
		.sorted()
		.collect(Collectors.toList());

		System.out.println("Lista original: " + numerosDesordenados); // Imprime lista original.
        System.out.println("Números ordenados: " + numerosOrdenados); // Imprime lista ordenada.
    
	}
	public static void ejercicio2() {
		System.out.println("\n--- sorted Ejercicio 2: Ordenar Usuarios por Edad ---");
		List<Usuario> usuarios = EjercicioDatos.getUsuariosDeEjemplo();

		List<Usuario> usuariosOrdenadosPorEdad = usuarios.stream()
		.sorted(Comparator.comparingInt(Usuario::edad))
		.collect(Collectors.toList());

		System.out.println("Usuarios ordenados por edad: ");
		usuariosOrdenadosPorEdad.forEach(u -> 
		System.out.println(u.nombre() + " (Edad: " + u.edad() + ")"));

		
		System.out.println("\n--- sorted Ejercicio 2.1: Ordenar Usuarios por Edad (Descendente) ---");
         List<Usuario> usuariosOrdenadosPorEdadDesc = usuarios.stream() // Convierte a Stream<Usuario>.
                                                       .sorted(Comparator.comparingInt(Usuario::edad).reversed()) // Ordena por edad y luego invierte el orden.
                                                       .collect(Collectors.toList()); // Recolecta en una List<Usuario>.
        System.out.println("Usuarios ordenados por edad (descendente):");
        usuariosOrdenadosPorEdadDesc.forEach(u -> // Itera sobre los usuarios ordenados.
            System.out.println(u.nombre() + " (Edad: " + u.edad() + ")") // Imprime nombre y edad.
        );

	}
}
