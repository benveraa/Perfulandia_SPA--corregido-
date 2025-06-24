package com.Perfulandia.controller;

import com.Perfulandia.model.Cliente;
import com.Perfulandia.model.CredencialesLogin;
import com.Perfulandia.model.Usuario;
import com.Perfulandia.repository.ClienteRepository;
import com.Perfulandia.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody CredencialesLogin credenciales) {

        // Primero buscamos en tabla usuarios (admin)
        Optional<Usuario> usuarioOpt = usuarioRepository.findByCorreo(credenciales.getCorreo());

        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            if (!usuario.getContrasena().equals(credenciales.getContrasena())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Contraseña incorrecta para usuario");
            }
            return ResponseEntity.ok("Login exitoso usuario admin");
        }

        // Si no está en usuarios, buscamos en clientes
        Optional<Cliente> clienteOpt = clienteRepository.findByCorreo(credenciales.getCorreo());

        if (!clienteOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Correo no registrado");
        }

        Cliente cliente = clienteOpt.get();

        if (!cliente.getContrasena().equals(credenciales.getContrasena())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Contraseña incorrecta para cliente");
        }

        return ResponseEntity.ok("Login exitoso cliente");
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Cliente nuevoCliente) {
        Optional<Cliente> clienteExistente = clienteRepository.findByCorreo(nuevoCliente.getCorreo());

        if (clienteExistente.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("El correo ya está registrado");
        }

        clienteRepository.save(nuevoCliente);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuario registrado correctamente");
    }
}
