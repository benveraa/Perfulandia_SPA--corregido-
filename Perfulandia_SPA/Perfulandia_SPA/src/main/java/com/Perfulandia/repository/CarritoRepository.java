package com.Perfulandia.repository;

import com.Perfulandia.model.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarritoRepository extends JpaRepository<Carrito, Long> {

    // Devuelve todos los productos del carrito de un cliente (entidad completa)
    List<Carrito> findByClienteId(Long clienteId);

    // ✅ Devuelve datos personalizados: productoId, cantidad, precio y nombre
    @Query("SELECT c.productoId, c.cantidad, p.precio, p.nombre " +
           "FROM Carrito c LEFT JOIN Producto p ON c.productoId = p.id " +
           "WHERE c.clienteId = :clienteId")
    List<Object[]> obtenerCarritoPorCliente(@Param("clienteId") Long clienteId);

    // Elimina el carrito de un cliente completo
    void deleteByClienteId(Long clienteId);
}
