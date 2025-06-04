package com.rebaza.api.ejercicios;

import java.util.Random;
import java.util.function.Supplier;

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
		
	}
}
