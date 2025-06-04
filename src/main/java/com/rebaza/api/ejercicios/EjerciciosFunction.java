package com.rebaza.api.ejercicios;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.rebaza.api.models.Usuario;
import com.rebaza.api.utils.EjercicioDatos;

public class EjerciciosFunction {

	public static void ejercicio1() {
		System.out.println("\n--- Function Ejercicio 1: Obtener Longitudes de Strings ---");
		// Lista de palabras
		List<String> palabras = Arrays.asList("Java","Stream","API","Funcional","Programacion","CMS","REST","Graft");
		
		
		Function<String, Integer> obtenerLongitud = palabra -> palabra.length();
		
		List<Integer> longitudes = palabras.stream()
				.map(obtenerLongitud)
				.collect(Collectors.toList());
		
		System.out.println("Palabras originales: " + palabras);
		System.out.println("Longitudes: " + longitudes);
	}
	
	public static void ejercicio2() {
		System.out.println("\n--- Function Ejercicio 2: Extraer Nombres de Usuarios ---");
		List<Usuario> usuarios = EjercicioDatos.getUsuariosDeEjemplo();
		
		Function<Usuario, String> obtenerNombre = u -> u.nombre();
		
		List<String> nombresUsuarios = usuarios.stream()
				.map(obtenerNombre)
				.collect(Collectors.toList());
		
		System.out.println("Nombres de usuarios: " + nombresUsuarios);
		
	}
}
