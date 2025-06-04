package com.rebaza.api.ejercicios;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ErrorNPE {

    public static void filtrarNulls() {
    System.out.println("--- Escenario 1a: Filtrar NullPointerExceptions ---");
    List<String> nombres = Arrays.asList("Alice", null, "Bob", "  ", null, "Carol"); // Lista con nulls y un string vacío/blanco.

    List<String> nombresEnMayusculas = nombres.stream() // Convierte la lista a un Stream<String>.
        .filter(Objects::nonNull) // Filtra y descarta cualquier elemento que sea null. Objects::nonNull es un Predicate.
        .filter(nombre -> !nombre.trim().isEmpty()) // Opcional: Filtra strings que están vacíos o solo contienen espacios en blanco.
        .map(String::toUpperCase) // Convierte cada nombre (ya no null y no vacío) a mayúsculas.
        .collect(Collectors.toList()); // Recolecta los resultados en una nueva lista.

    System.out.println("Nombres en mayúsculas (sin nulls ni vacíos): " + nombresEnMayusculas); // Imprime la lista resultante.
    }

    public static void manejarNullEnLambda() {
    System.out.println("\n--- Escenario 1b: Manejar NullPointerExceptions en Lambda ---");
    List<String> nombres = Arrays.asList("Alice", null, "Bob"); // Lista con un null.

    List<String> nombresProcesados = nombres.stream() // Convierte la lista a un Stream<String>.
        .map(nombre -> { // Inicia la operación map.
            if (nombre == null) { // Comprueba si el nombre actual es null.
                return "DESCONOCIDO"; // Si es null, devuelve un valor por defecto.
            }
            return nombre.toUpperCase(); // Si no es null, lo convierte a mayúsculas.
        })
        .collect(Collectors.toList()); // Recolecta los resultados.

    System.out.println("Nombres procesados (con valor por defecto para null): " + nombresProcesados); // Imprime la lista.
    }
}
