package com.rebaza.api.ejercicios;

import java.util.function.BiPredicate;

public class EjerciciosBiPredicate {

	public static void ejercicio1() {
		System.out.println("\n--- BiPredicate Ejercicio 1: Verificar si un Número es Mayor que Otro ---");
        // Define un BiPredicate que toma dos Integer.
        // '(num1, num2)' son los dos parámetros de entrada.
        // 'num1 > num2' es la condición que devuelve true si num1 es mayor que num2.
        BiPredicate<Integer, Integer> esMayorQue = (num1, num2) -> num1 > num2;

        System.out.println("¿Es 10 mayor que 5? " + esMayorQue.test(10, 5)); // Prueba el BiPredicate, debería ser true.
        System.out.println("¿Es 3 mayor que 7? " + esMayorQue.test(3, 7));  // Prueba el BiPredicate, debería ser false.
        System.out.println("¿Es 5 mayor que 5? " + esMayorQue.test(5, 5));  // Prueba el BiPredicate, debería ser false.
    
	}

	public static void ejercicio2() {
		System.out.println("\n--- BiPredicate Ejercicio 2: Verificar si un String contiene a otro (ignorando mayúsculas) ---");
        // Define un BiPredicate que toma dos Strings.
        // '(texto, subcadena)' son los dos parámetros de entrada.
        // 'texto.toLowerCase().contains(subcadena.toLowerCase())' convierte ambos a minúsculas y verifica la contención.
        BiPredicate<String, String> contieneIgnorandoMayusculas =
            (texto, subcadena) -> texto.toLowerCase().contains(subcadena.toLowerCase());

        String frase = "Java Streams son Poderosos"; // Define la frase principal.
        System.out.println("Frase: \"" + frase + "\"");
        System.out.println("¿Contiene 'streams'? " + contieneIgnorandoMayusculas.test(frase, "streams")); // Debería ser true.
        System.out.println("¿Contiene 'PYTHON'? " + contieneIgnorandoMayusculas.test(frase, "PYTHON")); // Debería ser false.
        System.out.println("¿Contiene 'Poder'? " + contieneIgnorandoMayusculas.test(frase, "Poder"));   // Debería ser true.
    
	}
}
