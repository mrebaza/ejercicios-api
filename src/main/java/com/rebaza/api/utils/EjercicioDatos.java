package com.rebaza.api.utils;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import com.rebaza.api.models.LineaPedido;
import com.rebaza.api.models.Pedido;
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
            new Producto("Teclado", "ELECTRÓNICA", new BigDecimal("75.00"), 30, false),
			new Producto("USB", "ELECTRÓNICA", BigDecimal.ZERO, 0, false),
            new Producto("Libro Java", "LIBROS", BigDecimal.ZERO, 0, false),
            new Producto("Mouse", "ELECTRÓNICA", BigDecimal.ZERO, 0, false),
            new Producto("Auriculares", "ELECTRÓNICA", BigDecimal.ZERO, 0, false),
            new Producto("Novela", "LIBROS", BigDecimal.ZERO, 0, false)
       );
   }

       // Datos de ejemplo para ejercicio 2 (usa las clases Pedido y LineaPedido definidas al inicio)
    public static List<Pedido> getPedidosDeEjemplo() {
        Producto p1 = new Producto("P1", "C1", BigDecimal.ONE, 1, false);
        Producto p2 = new Producto("P2", "C2", BigDecimal.TEN, 1, false);
        Producto p3 = new Producto("P3", "C1", BigDecimal.valueOf(5), 1, false);

        LineaPedido lp1 = new LineaPedido(p1.nombre(), p1.nombre(), 2, p1.precio());
        LineaPedido lp2 = new LineaPedido(p2.nombre(), p2.nombre(), 1, p2.precio());
        Pedido pedido1 = new Pedido("PED001", 1L, Arrays.asList(lp1, lp2), "PROCESANDO");

        LineaPedido lp3 = new LineaPedido(p3.nombre(), p3.nombre(), 5, p3.precio());
        LineaPedido lp4 = new LineaPedido(p1.nombre(), p1.nombre(), 3, p1.precio());
        Pedido pedido2 = new Pedido("PED002", 2L, Arrays.asList(lp3, lp4), "ENVIADO");

        return Arrays.asList(pedido1, pedido2);
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

