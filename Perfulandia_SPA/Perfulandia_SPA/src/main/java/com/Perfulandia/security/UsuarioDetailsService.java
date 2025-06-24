package com.Perfulandia.security;

import com.Perfulandia.model.Usuario;
import com.Perfulandia.model.Cliente;
import com.Perfulandia.repository.UsuarioRepository;
import com.Perfulandia.repository.ClienteRepository;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UsuarioDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private final ClienteRepository clienteRepository;

    public UsuarioDetailsService(UsuarioRepository usuarioRepository, ClienteRepository clienteRepository) {
        this.usuarioRepository = usuarioRepository;
        this.clienteRepository = clienteRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        System.out.println("🔍 Buscando usuario: " + correo);  // <- Debug visible en consola

        // Buscar en tabla usuario (admins)
        Usuario usuario = usuarioRepository.findByCorreo(correo).orElse(null);
        if (usuario != null) {
            System.out.println("✅ Usuario encontrado en tabla 'usuario': " + usuario.getCorreo());
            return new User(
                usuario.getCorreo(),
                "{noop}" + usuario.getContrasena(),
                Collections.singleton(new SimpleGrantedAuthority("ROLE_" + usuario.getRol()))
            );
        }

        // Buscar en tabla cliente (clientes normales)
        Cliente cliente = clienteRepository.findByCorreo(correo)
            .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        System.out.println("✅ Usuario encontrado en tabla 'cliente': " + cliente.getCorreo());
        return new User(
            cliente.getCorreo(),
            "{noop}" + cliente.getContrasena(),
            Collections.singleton(new SimpleGrantedAuthority("ROLE_CLIENTE"))
        );
    }
}
