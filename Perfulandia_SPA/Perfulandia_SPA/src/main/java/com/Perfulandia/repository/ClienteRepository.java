package com.Perfulandia.repository;

import com.Perfulandia.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    // Buscar cliente por correo para autenticación
    Optional<Cliente> findByCorreo(String correo);

    // Buscar cliente por correo y contraseña (aunque no recomendado guardar contraseña en texto plano)
    Optional<Cliente> findByCorreoAndContrasena(String correo, String contrasena);

    Optional<Cliente> findByNombre(String nombre);

    
}


    git config --global user.name "benveraa"

    git config --global user.email "ben.veraa@duocuc.cl"