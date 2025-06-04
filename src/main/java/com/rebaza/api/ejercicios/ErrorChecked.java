package com.rebaza.api.ejercicios;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ErrorChecked {
    
    // Método de ejemplo que simula una operación que puede lanzar una excepción comprobada.
    private static String operacionRiesgosa(String input) throws IOException {
        if ("error".equalsIgnoreCase(input)) { // Si el input es "error"...
            throw new IOException("Ocurrió un error de IO simulado para: " + input); // ...lanza una IOException.
        }
        return "Procesado: " + input; // Si no, devuelve un resultado.
    }

    public static void envolverCheckedException() {
        System.out.println("\n--- Escenario 3a: Envolver Checked Exception en Unchecked ---");
        List<String> datos = Arrays.asList("dato1", "error", "dato2"); // Lista de datos a procesar.

        List<String> resultados = datos.stream() // Convierte la lista a Stream<String>.
            .map(dato -> { // Inicia la operación map.
                try {
                    return operacionRiesgosa(dato); // Intenta ejecutar la operación riesgosa.
                } catch (IOException e) { // Si se captura una IOException...
                    // Envuelve la IOException en una RuntimeException.
                    // Esto permite que la excepción se propague a través del Stream sin violar la firma de la interfaz Function.
                    throw new RuntimeException("Error al procesar '" + dato + "': " + e.getMessage(), e);
                }
            })
            .collect(Collectors.toList()); // Intenta recolectar. Si se lanza RuntimeException, el stream se detiene.

        // Esta línea solo se alcanzará si ningún elemento lanza la RuntimeException.
        System.out.println("Resultados (si no hay errores fatales): " + resultados);
    }

    // Una función contenedora (wrapper function) para manejar la checked exception.
    // Esta función toma una "Function que puede lanzar una Checked Exception" y devuelve una "Function estándar".
    public static <T, R, E extends Exception> Function<T, R> wrapperChecked(FunctionWithException<T, R, E> fe) {
        return arg -> { // Devuelve una lambda que implementa Function<T,R>.
            try {
                return fe.apply(arg); // Llama al 'apply' de la función que puede lanzar la excepción.
            } catch (Exception e) { // Atrapa cualquier excepción (podría ser más específico).
                // Lanza una RuntimeException envolviendo la excepción original.
                // O podrías manejarla de otra forma (log, devolver Optional.empty(), etc.)
                throw new RuntimeException(e);
            }
        };
    }

    // Interfaz funcional personalizada que permite declarar una checked exception.
    @FunctionalInterface
    interface FunctionWithException<T, R, E extends Exception> {
        R apply(T t) throws E;
    }

    public static void usarWrapperFunction() {
        System.out.println("\n--- Escenario 3b: Usar Wrapper Function para Checked Exceptions ---");
        List<String> datos = Arrays.asList("ok1", "errorSimulado", "ok2"); // Datos de prueba.

        try {
            List<String> resultados = datos.stream() // Convierte la lista a Stream.
                // Usa el wrapper para adaptar 'operacionRiesgosaConWrapper' a la interfaz Function.
                .map(wrapperChecked(ErrorChecked::operacionRiesgosaConWrapper))
                .collect(Collectors.toList()); // Recolecta los resultados.
            System.out.println("Resultados con wrapper: " + resultados); // Imprime si todo va bien.
        } catch (RuntimeException e) { // Atrapa la RuntimeException que envuelve la IOException.
            System.err.println("Error capturado por el wrapper: " + e.getCause().getMessage()); // Imprime el mensaje de la causa original.
        }
    }

    // Método que simula una operación riesgosa, compatible con FunctionWithException.
    private static String operacionRiesgosaConWrapper(String input) throws IOException {
        if ("errorSimulado".equalsIgnoreCase(input)) { // Si el input es "errorSimulado"...
            throw new IOException("Fallo simulado en operación riesgosa con wrapper para: " + input); // ...lanza IOException.
        }
        return "Éxito con wrapper: " + input; // Si no, devuelve resultado exitoso.
    }    
}
