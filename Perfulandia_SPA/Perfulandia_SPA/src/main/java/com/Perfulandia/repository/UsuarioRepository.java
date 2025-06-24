package com.Perfulandia.repository;

import com.Perfulandia.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    List<Usuario> findByActivoTrue();

    // 🔽 Método para buscar un usuario por su correo
    Optional<Usuario> findByCorreo(String correo);
}
