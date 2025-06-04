package com.rebaza.api.ejercicios;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

public class ErrorRecursosIO {
    public static void manejarStreamDeArchivo() {
        System.out.println("\n--- Escenario 6: Manejo de Recursos con Streams de I/O (try-with-resources) ---");
        Path rutaArchivo = Paths.get("ejemplo.txt"); // Define la ruta al archivo.

        // Crea un archivo de ejemplo si no existe para que la prueba funcione.
        if (!Files.exists(rutaArchivo)) {
            try {
                Files.write(rutaArchivo, Arrays.asList("Línea de prueba 1", "Otra línea", "Fin del archivo de prueba"));
                System.out.println("Archivo 'ejemplo.txt' creado para la prueba.");
            } catch (IOException e) {
                System.err.println("No se pudo crear el archivo de prueba 'ejemplo.txt': " + e.getMessage());
                return; // No continuar si no se puede crear el archivo.
            }
        }


        // try-with-resources asegura que el Stream (que es AutoCloseable) se cierre automáticamente.
        try (Stream<String> lineas = Files.lines(rutaArchivo)) { // Abre un Stream de las líneas del archivo.
            lineas.filter(linea -> !linea.startsWith("#")) // Filtra líneas que no sean comentarios (ejemplo).
                  .map(String::trim) // Quita espacios al inicio y fin de cada línea.
                  .forEach(System.out::println); // Imprime cada línea procesada.
        } catch (IOException e) { // Captura posibles IOExceptions al abrir o leer el archivo.
            System.err.println("Error al leer el archivo '" + rutaArchivo + "': " + e.getMessage()); // Imprime un mensaje de error.
        }
        // El Stream 'lineas' se cierra automáticamente aquí, haya o no excepciones.
    }    
}
