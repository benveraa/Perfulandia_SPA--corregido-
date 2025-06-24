package com.Perfulandia.service;

import com.Perfulandia.model.Carrito;
import com.Perfulandia.model.CarritoDetalleDTO;
import com.Perfulandia.model.Producto;
import com.Perfulandia.repository.CarritoRepository;
import com.Perfulandia.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarritoService {

    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    public Carrito agregarProducto(Carrito carrito) {
        return carritoRepository.save(carrito);
    }

    public List<Carrito> obtenerCarritoPorCliente(Long clienteId) {
        return carritoRepository.findByClienteId(clienteId);
    }

    public void eliminarProductoDelCarrito(Long id) {
        carritoRepository.deleteById(id);
    }

    public List<CarritoDetalleDTO> obtenerCarritoConDetalle(Long clienteId) {
        List<Carrito> carrito = carritoRepository.findByClienteId(clienteId);
        List<CarritoDetalleDTO> detalle = new ArrayList<>();

        for (Carrito item : carrito) {
            Optional<Producto> productoOpt = productoRepository.findById(item.getProductoId());
            if (productoOpt.isPresent()) {
                Producto producto = productoOpt.get();
                detalle.add(new CarritoDetalleDTO(
                    producto.getNombre(),
                    producto.getPrecio(),
                    item.getCantidad()
                ));
            }
        }

        return detalle;
    }
}
