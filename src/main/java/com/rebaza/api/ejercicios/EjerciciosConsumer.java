package com.rebaza.api.ejercicios;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import com.rebaza.api.models.Producto;
import com.rebaza.api.utils.EjercicioDatos;

public class EjerciciosConsumer {

	public static void ejercicio1() {
		System.out.println("\n--- Consumer Ejercicio 1: Imprimir Elementos de una Lista ---");
		List<String> mensajes = Arrays.asList("Hola","como","estas","el","dia","de","hoy");
		
		Consumer<String> imprimirMensajes = mensaje -> System.out.println("Mensaje: " + mensaje); 
		
		mensajes.stream()
		.forEach(imprimirMensajes);
	}
	
	public static void ejercicio2() {
		System.out.println("\n--- Consumer Ejercicio 2: Actualizar Stock (Simulado) ---");
		
		List<Producto> productos = EjercicioDatos.getProductosDeEjemplo();
		
		Consumer<Producto> simularActualizacionStock = producto -> {
			System.out.println("Simulando actualización de stock para: " + 
						producto.nombre() + ". Nuevo stock: " + (producto.stock() - 1));
		};
		
		productos.stream()
		.filter(p -> p.stock() > 0)
		.forEach(simularActualizacionStock);
		
		
	}
}
