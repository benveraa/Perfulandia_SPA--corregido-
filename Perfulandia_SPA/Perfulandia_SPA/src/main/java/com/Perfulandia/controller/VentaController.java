package com.Perfulandia.controller;

import com.Perfulandia.model.Venta;
import com.Perfulandia.service.VentaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/venta")
public class VentaController {

    private final VentaService ventaService;

    @Autowired
    public VentaController(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    @PostMapping("/registrar")
    public ResponseEntity<Venta> registrarVenta(@RequestBody Venta venta) {
        Venta ventaRegistrada = ventaService.registrarVenta(venta);
        return ResponseEntity.ok(ventaRegistrada);
    }

    @PutMapping("/devolucion/{id}")
    public ResponseEntity<Venta> procesarDevolucion(@PathVariable Long id, @RequestParam String motivo) {
        Venta ventaDevuelta = ventaService.procesarDevolucion(id, motivo);
        return ResponseEntity.ok(ventaDevuelta);
    }
}
