package com.rebaza.api.ejercicios;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.rebaza.api.models.Producto;
import com.rebaza.api.models.Usuario;
import com.rebaza.api.utils.EjercicioDatos;

public class EjerciciosFindFirstAny {

    public static void ejercicioFindFirst1() {
        System.out.println("\n--- findFirst Ejercicio 1: Encontrar el Primer Número Par ---");
        List<Integer> numeros = Arrays.asList(1, 3, 5, 6, 8, 10); // Lista de números.
        List<Integer> numerosImpares = Arrays.asList(1, 3, 5, 7); // Lista solo con impares.

        Optional<Integer> primerPar = numeros.stream() // Convierte a Stream<Integer>.
                                            .filter(n -> n % 2 == 0) // Filtra para obtener solo los números pares.
                                            .findFirst(); // Obtiene el primer elemento que cumple el filtro.

        primerPar.ifPresentOrElse( // Si el Optional contiene un valor...
            n -> System.out.println("El primer número par encontrado es: " + n), // ...imprime el valor.
            () -> System.out.println("No se encontraron números pares.") // ...sino, imprime este mensaje.
        );

        Optional<Integer> primerParImpares = numerosImpares.stream() // Convierte la segunda lista a Stream.
                                                        .filter(n -> n % 2 == 0) // Filtra pares.
                                                        .findFirst(); // Intenta encontrar el primer par.
        primerParImpares.ifPresentOrElse(
            n -> System.out.println("El primer número par encontrado es: " + n),
            () -> System.out.println("No se encontraron números pares en la lista de impares.")
        );
    }

    public static void ejercicioFindFirst2() {
        System.out.println("\n--- findFirst Ejercicio 2: Encontrar el Primer Producto con Descuento ---");
        List<Producto> productos = Arrays.asList( // Lista de productos.
            new Producto("Laptop", "ELECTRÓNICA", BigDecimal.ZERO, 0, false),
            new Producto("Mouse", "ELECTRÓNICA", BigDecimal.ZERO, 0, true), // Con descuento
            new Producto("Teclado", "ELECTRÓNICA", BigDecimal.ZERO, 0, true) // También con descuento
        );

        Optional<Producto> primerProductoConDescuento = productos.stream() // Convierte a Stream<Producto>.
            .filter(Producto::tieneDescuento) // Filtra productos con descuento.
            .findFirst(); // Obtiene el primer producto que cumple.

        primerProductoConDescuento.ifPresentOrElse(
            p -> System.out.println("Primer producto con descuento: " + p.nombre()),
            () -> System.out.println("No hay productos con descuento.")
        );
    }

// --- findAny --- (generalmente más útil en streams paralelos, pero funciona en secuenciales)
    public static void ejercicioFindAny1() {
        System.out.println("\n--- findAny Ejercicio 1: Encontrar Cualquier Nombre que Empiece con 'A' ---");
        List<String> nombres = Arrays.asList("Eva", "Adam", "Ana", "Luis", "Alberto"); // Lista de nombres.

        // En un stream secuencial, findAny suele devolver el primero, pero no está garantizado.
        Optional<String> cualquierNombreConA = nombres.stream() // Convierte a Stream<String>.
                                                    .filter(nombre -> nombre.startsWith("A")) // Filtra nombres que empiezan con 'A'.
                                                    .findAny(); // Obtiene cualquier elemento que cumpla.

        cualquierNombreConA.ifPresentOrElse(
            nombre -> System.out.println("Un nombre que empieza con 'A': " + nombre),
            () -> System.out.println("No se encontró ningún nombre que empiece con 'A'.")
        );
    }

    public static void ejercicioFindAny2() {
        System.out.println("\n--- findAny Ejercicio 2: Encontrar Cualquier Usuario Activo del Departamento de IT ---");
        List<Usuario> usuarios = EjercicioDatos.getUsuariosDeEjemplo(); // Reutiliza usuarios.

        Optional<Usuario> cualquierUsuarioITActivo = usuarios.stream() // Convierte a Stream<Usuario>.
            // .parallel() // Descomentar para ver el comportamiento en paralelo (el resultado podría variar)
            .filter(u -> u.activo() && "IT".equals(u.departamento())) // Filtra usuarios activos del departamento de IT.
            .findAny(); // Obtiene cualquier usuario que cumpla.

        cualquierUsuarioITActivo.ifPresentOrElse(
            u -> System.out.println("Un usuario activo de IT: " + u.nombre()),
            () -> System.out.println("No se encontró ningún usuario activo de IT.")
        );
    }
}
