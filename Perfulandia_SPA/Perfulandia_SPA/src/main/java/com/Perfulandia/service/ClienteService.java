package com.Perfulandia.service;

import com.Perfulandia.model.Cliente;
import com.Perfulandia.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente guardar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Optional<Cliente> obtenerPorId(Long id) {
        return clienteRepository.findById(id);
    }

    public Cliente login(String correo, String contrasena) {
        return clienteRepository.findByCorreoAndContrasena(correo, contrasena).orElse(null);
    }

    public Cliente obtenerPorNombre(String nombre) {
        return clienteRepository.findByNombre(nombre).orElse(null); //
    }

public Optional<Cliente> obtenerPorCorreo(String correo) {
    return clienteRepository.findByCorreo(correo);
}
}