package com.rebaza.api.ejercicios;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

import com.rebaza.api.models.Producto;
import com.rebaza.api.utils.EjercicioDatos;

public class EjerciciosUnaryOperator {

	public static void ejercicio1() {
		System.out.println("\n--- UnaryOperator Ejercicio 1: Convertir Strings a Mayúsculas ---");
		List<String> nombres = Arrays.asList( "Ana", "Juan", "Pedro", "Miguel", "Luis", "María");

		UnaryOperator<String> aMayusculas = s -> s.toUpperCase();

		List<String> nombresEnMayusculas = nombres.stream()
				.map(aMayusculas)
				.collect(Collectors.toList());

		System.out.println("Lista original: " + nombres);
		System.out.println("Lista en mayúsculas: " + nombresEnMayusculas);
	}
	
	public static void ejercicio2() {
		System.out.println("\n--- UnaryOperator Ejercicio 2: Incrementar Precios de Productos ---");
		List<Producto> productos = EjercicioDatos.getProductosDeEjemplo();
		UnaryOperator<Producto> incrementarPrecio = p -> 
			new Producto(
				p.nombre(), 
				p.categoria(), 
				p.precio().multiply(new BigDecimal("1.1")), 
				p.stock(), 
				p.tieneDescuento());
	
		List<Producto> productosIncrementados = productos.stream()
				.map(incrementarPrecio)
				.collect(Collectors.toList());

		System.out.println("Productos originales:");
		productos.forEach(p -> System.out.println(p.nombre() + ": " + p.precio())); // Muestra precios originales.
		System.out.println("Productos con precio incrementado:");
		productosIncrementados.forEach(p -> System.out.println(p.nombre() + ": " + p.precio())); // Muestra precios incrementados.
							
	}
}
