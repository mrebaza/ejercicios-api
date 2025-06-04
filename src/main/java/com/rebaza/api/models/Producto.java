package com.rebaza.api.models;

import java.math.BigDecimal;

public record Producto(String nombre, String categoria, BigDecimal precio, int stock, boolean  tieneDescuento) {
	
	// Metodo para obtener el precio final aplicando un descuento si existe.
	public BigDecimal getPrecioFinal() {
		return tieneDescuento ? precio.multiply(BigDecimal.valueOf(0.9))
				: precio;
	}
}