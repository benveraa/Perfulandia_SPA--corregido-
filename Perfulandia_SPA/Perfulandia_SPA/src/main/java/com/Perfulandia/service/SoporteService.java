package com.Perfulandia.service;

import com.Perfulandia.model.Soporte;
import com.Perfulandia.repository.SoporteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SoporteService {

    @Autowired
    private SoporteRepository soporteRepository;

    public Soporte guardarMensaje(Soporte soporte) {
        soporte.setFecha(new Date()); // Asignar fecha actual automáticamente
        return soporteRepository.save(soporte);
    }

    public List<Soporte> obtenerTodos() {
        return soporteRepository.findAll();
    }
}
