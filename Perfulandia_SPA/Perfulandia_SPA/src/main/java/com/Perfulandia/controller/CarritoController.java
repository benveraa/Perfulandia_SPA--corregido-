package com.Perfulandia.controller;

import com.Perfulandia.model.Carrito;
import com.Perfulandia.model.CarritoDetalleDTO;
import com.Perfulandia.service.CarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carrito")
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    @PostMapping("/agregar")
    public Carrito agregarProducto(@RequestBody Carrito carrito) {
        return carritoService.agregarProducto(carrito);
    }

    @GetMapping("/{clienteId}")
    public List<Carrito> obtenerCarrito(@PathVariable Long clienteId) {
        return carritoService.obtenerCarritoPorCliente(clienteId);
    }

    @DeleteMapping("/{id}")
    public void eliminarProducto(@PathVariable Long id) {
        carritoService.eliminarProductoDelCarrito(id);
    }

    @GetMapping("/detalle/{clienteId}")
    public List<CarritoDetalleDTO> obtenerCarritoDetallado(@PathVariable Long clienteId) {
        return carritoService.obtenerCarritoConDetalle(clienteId);
    }
}
