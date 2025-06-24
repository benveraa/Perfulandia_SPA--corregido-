package com.Perfulandia.config;

import com.Perfulandia.model.Usuario;
import com.Perfulandia.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;

    public DataInitializer(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void run(String... args) {
        if (usuarioRepository.findByCorreo("admin@perfulandia.com").isEmpty()) {
            Usuario admin = new Usuario();
            admin.setNombre("Administrador General");
            admin.setCorreo("admin@perfulandia.com");
            admin.setContrasena("1234"); // Si usas {noop}, esto está bien
            admin.setRol("ADMIN");
            admin.setActivo(true);
            usuarioRepository.save(admin);
            System.out.println("✅ Usuario admin creado automáticamente.");
        } else {
            System.out.println("ℹ️ Usuario admin ya existe.");
        }
    }
}
