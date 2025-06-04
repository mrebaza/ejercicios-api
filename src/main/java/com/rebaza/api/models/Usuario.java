package com.rebaza.api.models;

import java.util.List;

public record Usuario (Long id, String nombre, int edad, boolean activo, List<String> roles, String 	departamento) {
	
	//Metodo para verificar si un usuario tiene un rol especifico.
	public boolean tieneRol(String rol) {
		return roles != null && roles.contains(rol); // Verifica que la lista de roles no sea nula y contenga el rol especifico.
		
	}
}
