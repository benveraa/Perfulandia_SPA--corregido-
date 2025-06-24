package com.Perfulandia.controller;

import com.Perfulandia.model.Cliente;
import com.Perfulandia.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    // Crear nuevo cliente
    @PostMapping
    public Cliente guardar(@RequestBody Cliente cliente) {
        return clienteService.guardar(cliente);
    }

    // Obtener cliente por ID
    @GetMapping("/{id}")
    public Optional<Cliente> obtenerPorId(@PathVariable Long id) {
        return clienteService.obtenerPorId(id);
    }

    // Actualizar datos del perfil del cliente
    @PutMapping("/{id}")
    public Cliente actualizarPerfil(@PathVariable Long id, @RequestBody Cliente clienteActualizado) {
        clienteActualizado.setId(id);
        return clienteService.guardar(clienteActualizado);
    }

    // Iniciar sesión
    @PostMapping("/login")
    public Cliente login(@RequestBody Cliente credenciales) {
        return clienteService.login(credenciales.getCorreo(), credenciales.getContrasena());
    }

    // Buscar cliente por nombre (opcional)
    @GetMapping("/nombre/{nombre}")
    public Cliente obtenerPorNombre(@PathVariable String nombre) {
        return clienteService.obtenerPorNombre(nombre);
    }

    // Obtener perfil del cliente autenticado
    @GetMapping("/perfil")
    @PreAuthorize("hasRole('CLIENTE')")
    public ResponseEntity<Cliente> obtenerPerfil(Authentication auth) {
        String correo = auth.getName();
        Cliente cliente = clienteService.obtenerPorCorreo(correo)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado"));
        return ResponseEntity.ok(cliente);
    }
}
