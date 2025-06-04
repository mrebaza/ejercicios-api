package com.rebaza.api.ejercicios;

import java.math.BigDecimal;
import java.util.Random;
import java.util.function.Supplier;

import com.rebaza.api.models.Producto;

public class EjerciciosSupplier {

	public static void ejercicio1() {
		System.out.println("\n--- Supplier Ejercicio 1: Generar Números Aleatorios ---");
		Supplier<Integer> generadorNumeroAleatorio = () -> new Random().nextInt(100) + 1;
		
		System.out.println("Numeros aleatorios: ");
		for(int i=0; i < 5; i++) {
			System.out.println(generadorNumeroAleatorio.get());
		}
	}
	
	public static void ejercicio2() {
		System.out.println("\n--- Supplier Ejercicio 2: Crear Nuevas Instancias de Producto ---");
		Supplier<Producto> generadorProducto = () ->
		new Producto("Producto nuevo", "GENERAL", BigDecimal.TEN,0,false);

		Producto p1 = generadorProducto.get();
		Producto p2 = generadorProducto.get();

		System.out.println("Producto 1: " + p1);
		System.out.println("Producto 2: " + p2);
		System.out.println("Son iguales? " + ( p1 == p2 )); // falso
	}
}
