package com.Perfulandia.service;

import com.Perfulandia.model.Resena;
import com.Perfulandia.repository.ResenaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResenaService {

    @Autowired
    private ResenaRepository resenaRepository;

    // Guardar una nueva reseña
    public Resena guardarResena(Resena resena) {
        return resenaRepository.save(resena);
    }

    // Obtener todas las reseñas
    public List<Resena> obtenerTodas() {
        return resenaRepository.findAll();
    }

    // Obtener reseñas por producto
    public List<Resena> obtenerPorProducto(Long idProducto) {
        return resenaRepository.findByIdProducto(idProducto);
    }
}
