package com.rebaza.api.ejercicios;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.rebaza.api.models.Usuario;
import com.rebaza.api.utils.*;



public class EjerciciosPredicate {

	public static void ejercicio1() {
		System.out.println("\n--- Predicate Ejercicio 1: Filtrar Números Pares ---");
		List<Integer> numeros = Arrays.asList(1,2,3,4,5,6,7,8,9);
		
		
		// Crear un predicate para verificar si un número es par o no
		Predicate<Integer> esPar = numero -> numero % 2 == 0;
		
		List<Integer> numerosPares = numeros.stream()
				.filter(esPar)
				.collect(Collectors.toList());
		
		System.out.println("Lista original: " + numeros);
		System.out.println("Lista pares: " + numerosPares);
		
		
	}
	
	
	
	public static void ejercico2() {
		System.out.println("\n--- Predicate Ejercicio 2: Filtrar Usuarios Activos ---");
		List<Usuario> usuarios = EjercicioDatos.getUsuariosDeEjemplo();
		
		// Crear un predicate para filtar si un usuario es activo o no
		Predicate<Usuario> esActivo = usuario -> usuario.activo();
		
		List<Usuario> usuariosActivos = usuarios.stream()
				.filter(esActivo)
				.collect(Collectors.toList());
		
		System.out.println("Usuarios activos");
		usuariosActivos.forEach(u -> System.out.println(u.nombre()));
	}
	
}
