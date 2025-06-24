package com.Perfulandia.service;

import com.Perfulandia.model.Cupon;
import com.Perfulandia.repository.CuponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CuponService {

    @Autowired
    private CuponRepository cuponRepository;

    // Buscar cupón por código y verificar si está activo
    public Optional<Cupon> validarCupon(String codigo) {
        Cupon cupon = cuponRepository.findByCodigo(codigo);
        if (cupon != null && cupon.isActivo()) {
            return Optional.of(cupon);
        } else {
            return Optional.empty();
        }
    }

    // Crear o actualizar un cupón
    public Cupon guardarCupon(Cupon cupon) {
        return cuponRepository.save(cupon);
    }

    // Obtener todos los cupones
    public List<Cupon> obtenerTodos() {
        return cuponRepository.findAll();
    }
}
