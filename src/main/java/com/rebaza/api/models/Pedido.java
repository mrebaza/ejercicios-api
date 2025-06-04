package com.rebaza.api.models;

import java.util.List;

public record Pedido(String id, long clienteId, List<LineaPedido> lineas, String estado) {}