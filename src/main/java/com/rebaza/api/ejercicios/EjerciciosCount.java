package com.rebaza.api.ejercicios;

import java.util.Arrays;
import java.util.List;

import com.rebaza.api.models.Producto;
import com.rebaza.api.utils.EjercicioDatos;

public class EjerciciosCount {

	public static void ejercicio1() {
        System.out.println("\n--- count Ejercicio 1: Contar Elementos en una Lista ---");
        List<String> colores = Arrays.asList("Rojo", "Verde", "Azul", "Amarillo"); // Lista de colores.

        long numeroDeColores = colores.stream() // Convierte la lista a Stream<String>.
                                      .count(); // Cuenta el número de elementos en el Stream.

        System.out.println("La lista de colores tiene " + numeroDeColores + " elementos."); // Imprime el conteo.
   		
	}
	public static void ejercicio2() {
        System.out.println("\n--- count Ejercicio 2: Contar Productos con Descuento ---");
        List<Producto> productos = EjercicioDatos.getProductosDeEjemplo();
									
        long productosConDescuento = productos.stream() // Convierte la lista a Stream<Producto>.
                                            .filter(Producto::tieneDescuento) // Filtra los productos que tienen descuento.
                                            .count(); // Cuenta cuántos productos pasaron el filtro.

        System.out.println("Número de productos con descuento: " + productosConDescuento); // Imprime el conteo.
   		
	}
}
