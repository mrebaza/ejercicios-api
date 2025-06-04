package com.rebaza.api.ejercicios;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.rebaza.api.models.Producto;
import com.rebaza.api.utils.EjercicioDatos;

public class EjerciciosPeek {

	public static void ejercicio1() {
		        System.out.println("\n--- peek Ejercicio 1: Depurar Stream de Números ---");
        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6); // Lista inicial.

        List<Integer> resultado = numeros.stream() // Convierte a Stream<Integer>.
            .peek(n -> System.out.println("Original: " + n)) // Acción de peek: imprime el número original.
            .filter(n -> n % 2 == 0) // Filtra para mantener solo los números pares.
            .peek(n -> System.out.println("Después de filtrar (par): " + n)) // Acción de peek: imprime el número si es par.
            .map(n -> n * 2) // Duplica cada número par.
            .peek(n -> System.out.println("Después de duplicar: " + n)) // Acción de peek: imprime el número duplicado.
            .collect(Collectors.toList()); // Recolecta los resultados en una List<Integer>.

        System.out.println("Resultado final: " + resultado); // Imprime la lista resultante.
    
	}
	public static void ejercicio2() {
		        System.out.println("\n--- peek Ejercicio 2: Observar Transformación de Nombres de Productos ---");
        List<Producto> productos = EjercicioDatos.getProductosDeEjemplo().subList(0,2); // Toma algunos productos.

        List<String> nombresTransformados = productos.stream() // Convierte a Stream<Producto>.
            .peek(p -> System.out.println("Procesando producto: " + p.nombre())) // Observa el producto original.
            .map(Producto::nombre) // Extrae el nombre del producto (Stream<String>).
            .peek(nombre -> System.out.println("Nombre extraído: " + nombre)) // Observa el nombre extraído.
            .map(String::toUpperCase) // Convierte el nombre a mayúsculas.
            .peek(nombreMayus -> System.out.println("Nombre en mayúsculas: " + nombreMayus)) // Observa el nombre en mayúsculas.
            .collect(Collectors.toList()); // Recolecta los nombres transformados.

        System.out.println("Nombres transformados finales: " + nombresTransformados); // Imprime la lista final.
   
	}
}
