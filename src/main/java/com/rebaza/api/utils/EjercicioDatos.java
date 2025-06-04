package com.rebaza.api.utils;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import com.rebaza.api.models.Producto;
import com.rebaza.api.models.Usuario;

public class EjercicioDatos{

	public static List<Usuario> getUsuariosDeEjemplo(){
		return Arrays.asList(
				new Usuario(1L, "Alice", 30, true, Arrays.asList("USER", "EDITOR"), "IT"),
	            new Usuario(2L, "Bob", 24, false, Arrays.asList("USER"), "VENTAS"),
	            new Usuario(3L, "Carol", 35, true, Arrays.asList("ADMIN", "USER"), "IT"),
	            new Usuario(4L, "David", 28, true, Arrays.asList("USER"), "MARKETING")
		        );
	}
	
    public static List<Producto> getProductosDeEjemplo() {
        return Arrays.asList(
           new Producto("Laptop", "ELECTRÓNICA", new BigDecimal("1200.00"), 10, false),
           new Producto("Mouse", "ELECTRÓNICA", new BigDecimal("25.00"), 50, true),
           new Producto("Teclado", "ELECTRÓNICA", new BigDecimal("75.00"), 30, false)
       );
   }
}
/**
	// Record para representar un Producto de forma concisa.
	record Producto(String nombre, String categoria, BigDecimal precio, int stock, boolean  tieneDescuento) {
		
		// Metodo para obtener el precio final aplicando un descuento si existe.
		public BigDecimal getPrecioFinal() {
			return tieneDescuento ? precio.multiply(BigDecimal.valueOf(0.9))
					: precio;
		}
	}
	
	// Record para representar un usuario.
	record Usuario (Long id, String nombre, int edad, boolean activo, List<String> roles, String 	departamento) {
		
		//Metodo para verificar si un usuario tiene un rol especifico.
		public boolean tieneRol(String rol) {
			return roles != null && roles.contains(rol); // Verifica que la lista de roles no sea nula y contenga el rol especifico.
			
		}
}
	// Record para representar un pedido
	record Pedido(String id, long clienteId, List<LineaPedido> lineas, String estado) {}
	
	
	// Record para representar una linea de pedido
	record LineaPedido(String producto, String nombreProducto, int cantidad, BigDecimal  precioUnitario) {
		
		// Metodo para cacular el total de la linea de pedido.
		public BigDecimal getTotalLinea() {
			return precioUnitario.multiply(BigDecimal.valueOf(cantidad)); // Multiplica el precio unitario por la cantidad.
		}
	}
	
	// Enumeración para los departamentos de empleados
	enum Departamento{
		VENTAS, IT, HR, MARKETING
	}
	
	// Record para representar a los empleados.
	record Empleado(String nombre, Departamento departamento, BigDecimal salario) {}

**/

