package com.rebaza.api.ejercicios;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// Definición simple de Persona para el ejemplo
record PersonaRecord(String nombre, String ciudad, int edad) {}


public class ErrorCollectors {

    public static void resolverDuplicadosEnToMap() {
        System.out.println("\n--- Escenario 4: Resolver Duplicados en Collectors.toMap ---");
        List<PersonaRecord> personas = Arrays.asList( // Lista de personas.
            new PersonaRecord("Ana", "Lima", 30),
            new PersonaRecord("Luis", "Arequipa", 25),
            new PersonaRecord("Ana", "Cusco", 32), // Nombre "Ana" duplicado.
            new PersonaRecord("Carlos", "Lima", 40),
            new PersonaRecord("Beatriz", "Lima", 28)
        );

        // Intenta crear un mapa donde la clave es el nombre y el valor es la ciudad.
        // (ciudad1, ciudad2) -> ciudad1 + "/" + ciudad2 es la función de merge.
        // Si se encuentra una clave duplicada (mismo nombre), esta función decide cómo combinar los valores.
        // Aquí, si "Ana" aparece dos veces, se concatenan sus ciudades (ej. "Lima/Cusco").
        Map<String, String> mapaCiudades = personas.stream()
            .collect(Collectors.toMap(
                PersonaRecord::nombre,     // Función para obtener la clave (el nombre de la persona).
                PersonaRecord::ciudad,     // Función para obtener el valor (la ciudad de la persona).
                (ciudadExistente, nuevaCiudad) -> ciudadExistente + " y " + nuevaCiudad // Función de merge para manejar claves duplicadas.
            ));
        System.out.println("Mapa nombre a ciudades (con merge): " + mapaCiudades); // Imprime el mapa resultante.

        // Otra estrategia: quedarse con la persona de mayor edad en caso de nombres duplicados.
        Map<String, PersonaRecord> mapaPersonasUnicasPorNombre = personas.stream()
            .collect(Collectors.toMap(
                PersonaRecord::nombre, // Clave: nombre de la persona.
                p -> p,                // Valor: el objeto PersonaRecord completo.
                (personaExistente, nuevaPersona) -> // Función de merge.
                    personaExistente.edad() > nuevaPersona.edad() ? personaExistente : nuevaPersona // Se queda con la persona de mayor edad.
            ));
        System.out.println("Mapa nombre a Persona (quedándose con la de mayor edad): " + mapaPersonasUnicasPorNombre); // Imprime el mapa.
    }    
}
