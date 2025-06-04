package com.rebaza.api.models;

import java.math.BigDecimal;

public record LineaPedido(String producto, String nombreProducto, int cantidad, BigDecimal  precioUnitario) {
	
	// Metodo para cacular el total de la linea de pedido.
	public BigDecimal getTotalLinea() {
		return precioUnitario.multiply(BigDecimal.valueOf(cantidad)); // Multiplica el precio unitario por la cantidad.
	}
}
