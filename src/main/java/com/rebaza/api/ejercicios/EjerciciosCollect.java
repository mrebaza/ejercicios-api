package com.rebaza.api.ejercicios;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.rebaza.api.models.Producto;
import com.rebaza.api.models.Usuario;
import com.rebaza.api.utils.EjercicioDatos;

public class EjerciciosCollect {

	public static void ejercicio1() {
		System.out.println("\n--- collect Ejercicio 1: Nombres de Usuarios a Lista y Set ---");
        List<Usuario> usuarios = EjercicioDatos.getUsuariosDeEjemplo();

        // Recolectar nombres en una Lista
        List<String> nombresLista = usuarios.stream() // Convierte a Stream<Usuario>.
                                          .map(Usuario::nombre) // Extrae los nombres (Stream<String>).
                                          .collect(Collectors.toList()); // Recolecta los nombres en una nueva List<String>.
        System.out.println("Nombres en Lista: " + nombresLista); // Imprime la lista de nombres.

        // Recolectar nombres en un Set (elimina duplicados)
        Set<String> nombresSet = usuarios.stream() // Convierte a Stream<Usuario>.
                                        .map(Usuario::nombre) // Extrae los nombres (Stream<String>).
                                        .collect(Collectors.toSet()); // Recolecta los nombres en un nuevo Set<String>, eliminando duplicados.
        System.out.println("Nombres en Set (únicos): " + nombresSet); // Imprime el conjunto de nombres únicos.
    
	}
	public static void ejercicio2() {
		System.out.println("\n--- collect Ejercicio 2: Agrupar Productos por Categoría ---");
        List<Producto> productos = EjercicioDatos.getProductosDeEjemplo();

        // Agrupar productos por categoría. El resultado es un Map<String, List<Producto>>.
        // La clave del mapa es la categoría (String).
        // El valor del mapa es una lista de Productos pertenecientes a esa categoría.
        Map<String, List<Producto>> productosPorCategoria = productos.stream() // Convierte a Stream<Producto>.
            .collect(Collectors.groupingBy(Producto::categoria)); // Agrupa los productos usando el resultado de Producto::categoria como clave.

        System.out.println("Productos agrupados por categoría:");
        productosPorCategoria.forEach((categoria, listaProductos) -> { // Itera sobre el mapa resultante.
            System.out.println("Categoría: " + categoria); // Imprime la categoría.
            listaProductos.forEach(p -> System.out.println("  - " + p.nombre())); // Imprime los nombres de los productos en esa categoría.
        });

        System.out.println("\n--- collect Ejercicio 2.1: Unir nombres de productos en un String ---");
        String nombresConcatenados = productos.stream() // Convierte a Stream<Producto>.
                                            .map(Producto::nombre) // Extrae los nombres (Stream<String>).
                                            .collect(Collectors.joining(", ")); // Une los nombres en un solo String, separados por ", ".
        System.out.println("Nombres de productos concatenados: " + nombresConcatenados); // Imprime el string resultante.


        System.out.println("\n--- collect Ejercicio 2.2: Calcular estadísticas de precios ---");
        // Calcula estadísticas sobre los precios de los productos.
        IntSummaryStatistics estadisticasPrecio = productos.stream() // Convierte a Stream<Producto>.
            .map(Producto::precio) // Extrae los precios (Stream<BigDecimal>).
            .collect(Collectors.summarizingInt(precio -> precio.intValue())); // Calcula estadísticas (conteo, suma, min, max, promedio) convirtiendo BigDecimal a int.
                                                                               // Nota: Para BigDecimal, es mejor usar colectores personalizados o librerías para estadísticas precisas.
                                                                               // Esto es una simplificación.

        System.out.println("Estadísticas de precios (convertidos a int):");
        System.out.println("Cantidad: " + estadisticasPrecio.getCount()); // Imprime la cantidad de precios.
        System.out.println("Suma: " + estadisticasPrecio.getSum()); // Imprime la suma de los precios.
        System.out.println("Promedio: " + estadisticasPrecio.getAverage()); // Imprime el promedio de los precios.
        System.out.println("Mínimo: " + estadisticasPrecio.getMin()); // Imprime el precio mínimo.
        System.out.println("Máximo: " + estadisticasPrecio.getMax()); // Imprime el precio máximo.
    
	}
}
