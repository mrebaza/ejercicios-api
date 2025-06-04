package com.rebaza.api.ejercicios;

import java.util.Arrays;
import java.util.List;

public class ErrorOptional {
    public static void manejarOptionalCorrectamente() {
        System.out.println("\n--- Escenario 5: Manejar Optional Correctamente ---");
        List<String> nombres = Arrays.asList("Carlos", "Diana"); // Lista de nombres.
        List<String> listaVacia = Arrays.asList(); // Lista vacía.

        // Caso 1: Usando orElse para proveer un valor por defecto.
        String primerNombre = nombres.stream() // Convierte la lista a Stream.
                                  .filter(n -> n.startsWith("C")) // Filtra nombres que empiezan con "C".
                                  .findFirst() // Encuentra el primer nombre que cumple.
                                  .orElse("No encontrado"); // Si no se encuentra, devuelve "No encontrado".
        System.out.println("Primer nombre con 'C' (o defecto): " + primerNombre); // Imprime el resultado.

        String primerNombreVacia = listaVacia.stream() // Convierte la lista vacía a Stream.
                                        .findFirst() // Intenta encontrar el primer elemento.
                                        .orElse("Default para vacía"); // Provee un valor por defecto.
        System.out.println("Resultado de lista vacía con orElse: " + primerNombreVacia); // Imprime el resultado.

        // Caso 2: Usando ifPresent para ejecutar una acción solo si el valor existe.
        System.out.print("Usando ifPresent: ");
        nombres.stream() // Stream de nombres.
               .filter(n -> n.startsWith("D")) // Filtra nombres que empiezan con "D".
               .findFirst() // Encuentra el primero.
               .ifPresent(nombre -> System.out.println("Encontrado: " + nombre)); // Ejecuta esta acción si se encuentra un nombre.

        // Caso 3: Usando orElseThrow para lanzar una excepción personalizada si no se encuentra.
        try {
            String nombreRequerido = nombres.stream() // Stream de nombres.
                                       .filter(n -> n.startsWith("X")) // Filtra nombres que empiezan con "X" (probablemente no habrá).
                                       .findFirst() // Intenta encontrar el primero.
                                       .orElseThrow(() -> new IllegalArgumentException("Nombre con X es requerido y no se encontró.")); // Lanza excepción si no se encuentra.
            System.out.println("Nombre requerido: " + nombreRequerido); // Esta línea no se alcanzará si se lanza la excepción.
        } catch (IllegalArgumentException e) {
            System.err.println("Error al buscar nombre requerido: " + e.getMessage()); // Captura y muestra el mensaje de error.
        }
    }    
}
