package com.Perfulandia.controller;

import com.Perfulandia.model.Resena;
import com.Perfulandia.service.ResenaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/resenas")
public class ResenaController {

    @Autowired
    private ResenaService resenaService;

    // Enviar una nueva reseña
    @PostMapping
    public Resena crearResena(@RequestBody Resena resena) {
        return resenaService.guardarResena(resena);
    }

    // Ver todas las reseñas
    @GetMapping
    public List<Resena> obtenerTodas() {
        return resenaService.obtenerTodas();
    }

    // Ver reseñas por producto
    @GetMapping("/producto/{idProducto}")
    public List<Resena> obtenerPorProducto(@PathVariable Long idProducto) {
        return resenaService.obtenerPorProducto(idProducto);
    }
}
