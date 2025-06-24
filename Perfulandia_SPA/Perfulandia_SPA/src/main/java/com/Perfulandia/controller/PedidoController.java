package com.Perfulandia.controller;

import com.Perfulandia.model.Pedido;
import com.Perfulandia.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    // Endpoint bonito para Postman:
    @PostMapping("/realizar/{clienteId}")
    public Pedido realizarPedidoBonito(
            @PathVariable Long clienteId,
            @RequestParam(required = false) String cupon
    ) {
        return pedidoService.realizarPedido(clienteId, cupon);
    }

    // Otro endpoint si prefieres sin texto bonito:
    @PostMapping("/{clienteId}")
    public Pedido realizarPedido(
            @PathVariable Long clienteId,
            @RequestParam(required = false) String cupon
    ) {
        return pedidoService.realizarPedido(clienteId, cupon);
    }

    // Obtener todos los pedidos
    @GetMapping
    public List<Pedido> obtenerPedidos() {
        return pedidoService.obtenerPedidos();
    }

    // Obtener pedidos por cliente
    @GetMapping("/cliente/{clienteId}")
    public List<Pedido> obtenerPedidosPorCliente(@PathVariable Long clienteId) {
        return pedidoService.obtenerPedidosPorCliente(clienteId);
    }

    // Eliminar un pedido
    @DeleteMapping("/{id}")
    public void eliminarPedido(@PathVariable Long id) {
        pedidoService.eliminarPedido(id);
    }

    // 🧾 Ver PDF de boleta por ID de pedido
    @GetMapping("/boleta/{pedidoId}")
    public ResponseEntity<Resource> descargarBoleta(@PathVariable Long pedidoId) throws IOException {
        String nombreArchivo = "boleta_pedido_" + pedidoId + ".pdf";
        Path path = Paths.get(nombreArchivo);

        if (!Files.exists(path)) {
            return ResponseEntity.notFound().build();
        }

        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=" + nombreArchivo)
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }
}
