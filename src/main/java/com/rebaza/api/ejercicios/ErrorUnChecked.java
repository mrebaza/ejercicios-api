package com.rebaza.api.ejercicios;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional; // Necesario para la solución con Optional
import java.util.stream.Collectors;

class ErrorUnchecked {
    public static void capturarEnLambda() {
        System.out.println("\n--- Escenario 2a: Capturar Unchecked Exception en Lambda (y filtrar) ---");
        List<String> numerosStr = Arrays.asList("1", "2a", "3", "4b", "5"); // Lista con strings válidos e inválidos para parsear a Integer.

        List<Integer> numerosValidos = numerosStr.stream() // Convierte la lista a Stream<String>.
            .map(str -> { // Inicia la operación map.
                try {
                    return Integer.parseInt(str); // Intenta convertir el string a Integer.
                } catch (NumberFormatException e) { // Si ocurre una NumberFormatException...
                    System.err.println("Error al parsear '" + str + "': " + e.getMessage()); // Imprime un mensaje de error.
                    return null; // Devuelve null para indicar que este elemento no pudo ser procesado.
                }
            })
            .filter(Objects::nonNull) // Filtra los nulls (elementos que no se pudieron parsear).
            .collect(Collectors.toList()); // Recolecta los números válidos.

        System.out.println("Números válidos parseados: " + numerosValidos); // Imprime la lista de números válidos.
    }

    // Solución alternativa usando flatMap con Optional para un estilo más funcional
    public static void capturarConOptionalFlatMap() {
        System.out.println("\n--- Escenario 2b: Capturar Unchecked Exception con Optional y flatMap ---");
        List<String> numerosStr = Arrays.asList("10", "veinte", "30", "cuarenta", "50"); // Lista con strings válidos e inválidos.

        List<Integer> numeros = numerosStr.stream() // Convierte la lista a Stream<String>.
            .map(str -> { // Mapea cada string.
                try {
                    return Optional.of(Integer.parseInt(str)); // Si el parseo es exitoso, envuélvelo en un Optional.
                } catch (NumberFormatException e) {
                    System.err.println("Advertencia: No se pudo parsear '" + str + "' a entero."); // Mensaje de advertencia.
                    return Optional.<Integer>empty(); // Si falla el parseo, devuelve un Optional vacío.
                }
            }) // El Stream es ahora de tipo Stream<Optional<Integer>>.
            .flatMap(Optional::stream) // Convierte Stream<Optional<Integer>> a Stream<Integer>, descartando los Optional vacíos.
                                        // Optional::stream devuelve un Stream vacío para un Optional vacío, o un Stream de un elemento para un Optional con valor.
            .collect(Collectors.toList()); // Recolecta los enteros válidos.

        System.out.println("Números parseados exitosamente (con Optional): " + numeros); // Imprime la lista de números.
    }
}