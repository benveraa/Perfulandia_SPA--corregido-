package com.Perfulandia.controller;

import com.Perfulandia.model.Soporte;
import com.Perfulandia.service.SoporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/soporte")
public class SoporteController {

    @Autowired
    private SoporteService soporteService;

    // Enviar nuevo mensaje de soporte
    @PostMapping
    public Soporte crearMensaje(@RequestBody Soporte soporte) {
        return soporteService.guardarMensaje(soporte);
    }

    // Ver todos los mensajes de soporte (uso interno)
    @GetMapping
    public List<Soporte> obtenerMensajes() {
        return soporteService.obtenerTodos();
    }
}
