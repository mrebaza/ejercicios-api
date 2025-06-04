package com.rebaza.api.models;

import java.math.BigDecimal;


public record Empleado(String nombre, Departamento departamento, BigDecimal salario) {}
