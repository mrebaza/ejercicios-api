package com.rebaza.api.ejercicios;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import com.rebaza.api.models.Producto;
import com.rebaza.api.models.Usuario;
import com.rebaza.api.utils.EjercicioDatos;

public class EjerciciosMatch {
	
    public static void ejercicioAnyMatch1() {
        System.out.println("\n--- anyMatch Ejercicio 1: Verificar si Hay Algún Número Negativo ---");
        List<Integer> numeros1 = Arrays.asList(1, 5, -3, 8, 2); // Lista con un negativo.
        List<Integer> numeros2 = Arrays.asList(10, 20, 30, 40); // Lista sin negativos.

        // Predicado para verificar si un número es negativo.
        Predicate<Integer> esNegativo = n -> n < 0;

        boolean hayNegativo1 = numeros1.stream() // Convierte la primera lista a Stream<Integer>.
                                      .anyMatch(esNegativo); // Verifica si algún elemento cumple 'esNegativo'.
        System.out.println("¿La lista " + numeros1 + " tiene algún negativo? " + hayNegativo1); // Debería ser true.

        boolean hayNegativo2 = numeros2.stream() // Convierte la segunda lista a Stream<Integer>.
                                      .anyMatch(esNegativo); // Verifica si algún elemento cumple 'esNegativo'.
        System.out.println("¿La lista " + numeros2 + " tiene algún negativo? " + hayNegativo2); // Debería ser false.
    }

    public static void ejercicioAnyMatch2() {
        System.out.println("\n--- anyMatch Ejercicio 2: Verificar si Algún Usuario es Administrador ---");
        List<Usuario> usuarios = EjercicioDatos.getUsuariosDeEjemplo(); // Reutiliza lista de usuarios.

        // Predicado para verificar si un usuario tiene el rol "ADMIN".
        Predicate<Usuario> esAdmin = usuario -> usuario.tieneRol("ADMIN");

        boolean algunAdmin = usuarios.stream() // Convierte la lista a Stream<Usuario>.
                                    .anyMatch(esAdmin); // Verifica si algún usuario es administrador.

        System.out.println("¿Hay algún administrador en la lista? " + algunAdmin); // Imprime el resultado.
    }


// --- allMatch ---
    public static void ejercicioAllMatch1() {
        System.out.println("\n--- allMatch Ejercicio 1: Verificar si Todos los Números son Positivos ---");
        List<Integer> numeros1 = Arrays.asList(1, 5, 3, 8, 2); // Todos positivos.
        List<Integer> numeros2 = Arrays.asList(10, -2, 30, 40); // Contiene un negativo.

        // Predicado para verificar si un número es positivo.
        Predicate<Integer> esPositivo = n -> n > 0;

        boolean todosPositivos1 = numeros1.stream() // Convierte la primera lista a Stream.
                                         .allMatch(esPositivo); // Verifica si TODOS los elementos cumplen 'esPositivo'.
        System.out.println("¿Todos los números en " + numeros1 + " son positivos? " + todosPositivos1); // Debería ser true.

        boolean todosPositivos2 = numeros2.stream() // Convierte la segunda lista a Stream.
                                         .allMatch(esPositivo); // Verifica si TODOS los elementos cumplen 'esPositivo'.
        System.out.println("¿Todos los números en " + numeros2 + " son positivos? " + todosPositivos2); // Debería ser false.
    }

    public static void ejercicioAllMatch2() {
        System.out.println("\n--- allMatch Ejercicio 2: Verificar si Todos los Productos Tienen Stock ---");
        List<Producto> productosConStock = Arrays.asList( // Todos tienen stock.
            new Producto("A", "C1", BigDecimal.ONE, 10, false),
            new Producto("B", "C2", BigDecimal.ONE, 5, false)
        );
        List<Producto> productosAlgunosSinStock = Arrays.asList( // Uno no tiene stock.
            new Producto("C", "C1", BigDecimal.ONE, 10, false),
            new Producto("D", "C2", BigDecimal.ONE, 0, false)
        );

        // Predicado para verificar si un producto tiene stock.
        Predicate<Producto> tieneStock = p -> p.stock() > 0;

        boolean todosConStock1 = productosConStock.stream() // Convierte a Stream.
                                                .allMatch(tieneStock); // Verifica si todos tienen stock.
        System.out.println("¿Todos los productos en la primera lista tienen stock? " + todosConStock1); // Debería ser true.

        boolean todosConStock2 = productosAlgunosSinStock.stream() // Convierte a Stream.
                                                        .allMatch(tieneStock); // Verifica si todos tienen stock.
        System.out.println("¿Todos los productos en la segunda lista tienen stock? " + todosConStock2); // Debería ser false.
    }

// --- noneMatch ---
    public static void ejercicioNoneMatch1() {
        System.out.println("\n--- noneMatch Ejercicio 1: Verificar si Ningún Número es Cero ---");
        List<Integer> numeros1 = Arrays.asList(1, 5, 3, 8, 2); // Ninguno es cero.
        List<Integer> numeros2 = Arrays.asList(10, 0, 30, 40); // Contiene un cero.

        // Predicado para verificar si un número es cero.
        Predicate<Integer> esCero = n -> n == 0;

        boolean ningunoEsCero1 = numeros1.stream() // Convierte a Stream.
                                        .noneMatch(esCero); // Verifica si NINGÚN elemento cumple 'esCero'.
        System.out.println("¿Ningún número en " + numeros1 + " es cero? " + ningunoEsCero1); // Debería ser true.

        boolean ningunoEsCero2 = numeros2.stream() // Convierte a Stream.
                                        .noneMatch(esCero); // Verifica si NINGÚN elemento cumple 'esCero'.
        System.out.println("¿Ningún número en " + numeros2 + " es cero? " + ningunoEsCero2); // Debería ser false.
    }

     public static void ejercicioNoneMatch2() {
        System.out.println("\n--- noneMatch Ejercicio 2: Verificar si Ningún Usuario está Inactivo ---");
        List<Usuario> usuariosTodosActivos = Arrays.asList( // Todos activos
             new Usuario(1L, "Alice", 30, true, Collections.emptyList(), "IT"),
             new Usuario(2L, "Bob", 24, true, Collections.emptyList(), "VENTAS")
        );
        List<Usuario> usuariosAlgunosInactivos = EjercicioDatos.getUsuariosDeEjemplo(); // Contiene usuarios inactivos.


        // Predicado para verificar si un usuario está inactivo.
        Predicate<Usuario> estaInactivo = usuario -> !usuario.activo();

        boolean ningunoInactivo1 = usuariosTodosActivos.stream() // Convierte a Stream.
                                                     .noneMatch(estaInactivo); // Verifica si ninguno está inactivo.
        System.out.println("¿Ningún usuario en la primera lista está inactivo? " + ningunoInactivo1); // Debería ser true.

        boolean ningunoInactivo2 = usuariosAlgunosInactivos.stream() // Convierte a Stream.
                                                         .noneMatch(estaInactivo); // Verifica si ninguno está inactivo.
        System.out.println("¿Ningún usuario en la segunda lista está inactivo? " + ningunoInactivo2); // Debería ser false.
    }

}
