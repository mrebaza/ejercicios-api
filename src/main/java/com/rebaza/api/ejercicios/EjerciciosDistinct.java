package com.rebaza.api.ejercicios;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.rebaza.api.models.Producto;
import com.rebaza.api.utils.EjercicioDatos;

public class EjerciciosDistinct {

	public static void ejercicio1() {
		System.out.println("\n--- distinct Ejercicio 1: Obtener Números Únicos ---");
        List<Integer> numerosConDuplicados = Arrays.asList(1, 2, 2, 3, 4, 4, 4, 5, 1, 6); // Lista con duplicados.

        List<Integer> numerosUnicos = numerosConDuplicados.stream() // Convierte la lista a Stream<Integer>.
                                                        .distinct() // Elimina los elementos duplicados del Stream.
                                                        .collect(Collectors.toList()); // Recolecta los elementos únicos en una List<Integer>.

        System.out.println("Lista original: " + numerosConDuplicados); // Imprime la lista original.
        System.out.println("Números únicos: " + numerosUnicos); // Imprime la lista de números únicos.
    
	}
	public static void ejercicio2() {
		System.out.println("\n--- distinct Ejercicio 2: Obtener Categorías de Productos Únicas ---");
        List<Producto> productos = EjercicioDatos.getProductosDeEjemplo();

        List<String> categoriasUnicas = productos.stream() // Convierte la lista a Stream<Producto>.
                                                .map(Producto::categoria) // Extrae la categoría de cada producto (Stream<String>).
                                                .distinct() // Obtiene solo las categorías únicas.
                                                .collect(Collectors.toList()); // Recolecta en una List<String>.

        System.out.println("Categorías de productos únicas: " + categoriasUnicas); // Imprime las categorías únicas.
    
	}
}
