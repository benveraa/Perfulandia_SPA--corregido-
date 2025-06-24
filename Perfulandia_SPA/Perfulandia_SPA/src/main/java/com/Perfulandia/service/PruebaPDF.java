package com.Perfulandia.service;

import com.Perfulandia.model.DetallePedido;
import com.Perfulandia.model.Pedido;
import com.Perfulandia.model.Cliente;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PruebaPDF {

    public static void main(String[] args) {
        // Crear cliente de prueba
        Cliente cliente = new Cliente();
        cliente.setNombre("Andrea Rojas");

        // Crear detalles de pedido de prueba
        DetallePedido detalle1 = new DetallePedido();
        detalle1.setProductoId(1L);
        detalle1.setCantidad(2);
        detalle1.setPrecioUnitario(14990);

        DetallePedido detalle2 = new DetallePedido();
        detalle2.setProductoId(2L);
        detalle2.setCantidad(1);
        detalle2.setPrecioUnitario(29990);

        List<DetallePedido> listaDetalles = new ArrayList<>();
        listaDetalles.add(detalle1);
        listaDetalles.add(detalle2);

        // Crear pedido de prueba
        Pedido pedido = new Pedido();
        pedido.setId(999L);
        pedido.setClienteId(1L);
        pedido.setFecha(new Date());
        pedido.setDetalles(listaDetalles);
        pedido.setTotal(14990 * 2 + 29990);
        pedido.setDescuento(5000.0); // Si deseas probar con descuento

        // Generar boleta PDF
        GeneradorBoletaPDF.generarBoleta(pedido, cliente);
    }
}
