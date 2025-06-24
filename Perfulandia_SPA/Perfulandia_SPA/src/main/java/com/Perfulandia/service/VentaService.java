package com.Perfulandia.service;

import com.Perfulandia.model.Venta;
import com.Perfulandia.repository.VentaRepository;
import java.util.Date;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class VentaService {

    private final VentaRepository ventaRepository;

    public VentaService(VentaRepository ventaRepository) {
        this.ventaRepository = ventaRepository;
    }

    public Venta registrarVenta(Venta venta) {
        if (venta.getTotal() != null && venta.getTotal() > 100) {
            venta.setDescuento(venta.getTotal() * 0.1);
            venta.setTotal(venta.getTotal() - venta.getDescuento());
        }
        venta.setFechaventa(new Date());
        return ventaRepository.save(venta);
    }

    public Venta procesarDevolucion(Long idVenta, String motivo) {
        Optional<Venta> ventaOpt = ventaRepository.findById(idVenta);
        return ventaOpt.orElse(null);
    }
}
