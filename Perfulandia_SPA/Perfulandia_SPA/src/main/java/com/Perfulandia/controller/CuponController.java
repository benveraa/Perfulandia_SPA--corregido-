package com.Perfulandia.controller;

import com.Perfulandia.model.Cupon;
import com.Perfulandia.service.CuponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cupones")
public class CuponController {

    @Autowired
    private CuponService cuponService;

    // Crear o actualizar un cupón
    @PostMapping
    public Cupon crearCupon(@RequestBody Cupon cupon) {
        return cuponService.guardarCupon(cupon);
    }

    // Ver todos los cupones (uso interno)
    @GetMapping
    public List<Cupon> obtenerTodos() {
        return cuponService.obtenerTodos();
    }

    // Validar un cupón por su código
    @GetMapping("/validar/{codigo}")
    public Optional<Cupon> validar(@PathVariable String codigo) {
        return cuponService.validarCupon(codigo);
    }
}
