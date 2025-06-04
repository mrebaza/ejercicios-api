package com.rebaza.api.ejercicios;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;

public class EjerciciosBinaryOperator {

	public static void ejercicio1() {
		System.out.println("\n--- BinaryOperator Ejercicio 1: Sumar Números ---");
		List<Integer> numeros = Arrays.asList(10, 20, 30, 40, 50);

		BinaryOperator<Integer> suma = (a, b) -> a + b;
		int resultado = numeros.stream().reduce(0, suma);
		System.out.println("Resultado: " + resultado);
	}
	public static void ejercicio2() {
		System.out.println("\n--- BinaryOperator Ejercicio 2: Encontrar el String Más Largo ---");
		List<String> palabras = Arrays.asList("Java", "Python", "JavaScript", "C++", "Ruby");

		BinaryOperator<String> elMasLargo = (s1, s2) -> s1.length() > s2.length() ? s1 : s2;

		Optional<String> palabraMasLargaOpt = palabras.stream()
				.reduce(elMasLargo);

		if(palabraMasLargaOpt.isEmpty()) {
			System.out.println("La Lista de palabras está vacía.");
		} else {
			System.out.println("La palabra más larga es: " + palabraMasLargaOpt.get());
		}
	}
}
