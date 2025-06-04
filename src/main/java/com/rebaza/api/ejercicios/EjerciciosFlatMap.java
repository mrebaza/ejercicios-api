package com.rebaza.api.ejercicios;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.rebaza.api.models.LineaPedido;
import com.rebaza.api.models.Pedido;
import com.rebaza.api.utils.EjercicioDatos;

public class EjerciciosFlatMap {

	public static void ejercicio1() {
		System.out.println("\n--- flatMap Ejercicio 1: Obtener Todas las Letras ---");
		List<String> palabras = List.of("Java", "Stream", "API", "Funcional", "Programacion", "CMS", "REST", "GraphQL");	

		// Function que convierte una palabra en un stream de letras
		Function<String, Stream<String>> obtenerLetras = 
			palabra -> palabra.chars()
			.mapToObj(c -> String.valueOf((char) c));

		List<String> todasLasLetras = palabras.stream()
		.flatMap(obtenerLetras)
		.collect(Collectors.toList());

		System.out.println("Palabras originales: " + palabras);
		System.out.println("Todas las letras: " + todasLasLetras);
			
	}
	public static void ejercicio2() {
		 System.out.println("\n--- flatMap Ejercicio 2: Obtener Todas las Líneas de Pedido ---");
		 List<Pedido> pedidos = EjercicioDatos.getPedidosDeEjemplo();

		 Function<Pedido, Stream<LineaPedido>> obtenerLineasPedido =
		 pedido -> pedido.lineas().stream();

		 List<LineaPedido> todasLasLineas = pedidos.stream()
		 .flatMap(obtenerLineasPedido)
		 .collect(Collectors.toList());

		 System.out.println("Total de líneas de pedido de todos los pedidos: " + todasLasLineas.size());

		 todasLasLineas.forEach(linea -> System.out.println(
			"Producto: " + linea.nombreProducto() + " Cantidad: "+ linea.cantidad()
		 ));
	}
}
