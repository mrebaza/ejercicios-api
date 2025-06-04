package com.rebaza.api.ejercicios;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

import com.rebaza.api.models.Producto;

public class EjerciciosBiFunction {

	public static void ejercicio1() {
		System.out.println("\n--- BiFunction Ejercicio 1: Combinar Nombre y Edad ---");

		BiFunction<String, Integer, String> formatearPersona = (nombre, edad) -> 
														"Nombre: " + nombre + ", Edad: " + edad;
		
		String personaFormateada = formatearPersona.apply("Juan", 30);
		System.out.println("Persona Formateada: " + personaFormateada);
		
		String otraPersonaFormateada = formatearPersona.apply("Ana", 25);
		System.out.println("Otra Persona Formateada: " + otraPersonaFormateada);
	}

	public static void ejercicio2() {
		System.out.println("\n--- BiFunction Ejercicio 2: Crear Producto desde Nombre y Precio (simplificado) ---");

		BiFunction<String , BigDecimal, Producto> crearProductoSimple = 
		(nombre, precio) -> new Producto(nombre, "GENERICO", precio, 0, false);

		Producto laptop = crearProductoSimple.apply("Laptop", new BigDecimal("1200.00"));
		Producto monitor = crearProductoSimple.apply("Monitor", new BigDecimal("810.50"));
		
		System.out.println("Producto 1: " + laptop);
		System.out.println("Producto 2: " + monitor);
		
		// Ejemplo de uso en un Stream (aunque BiFunction se usa más directamente o en collectors)
        // Supongamos que tenemos dos listas paralelas que queremos combinar.
        List<String> nombresProducto = Arrays.asList("Café", "Té");
        List<BigDecimal> preciosProducto = Arrays.asList(new BigDecimal("5.00"), new BigDecimal("3.50"));

        if (nombresProducto.size() == preciosProducto.size()) { // Asegura que las listas tengan el mismo tamaño.
            System.out.println("\nProductos combinados desde listas:");
            for (int i = 0; i < nombresProducto.size(); i++) { // Itera sobre los índices.
                Producto p = crearProductoSimple.apply(nombresProducto.get(i), preciosProducto.get(i)); // Aplica la BiFunction con elementos de ambas listas.
                System.out.println(p); // Imprime el producto combinado.
            }
        }
	}
}
