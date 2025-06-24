package com.Perfulandia.repository;

import com.Perfulandia.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    
   
    List<Producto> findByCategoria(String categoria);

    
    List<Producto> findByNombreContainingIgnoreCase(String nombre);

    
    List<Producto> findByMarcaIgnoreCase(String marca);

    
    List<Producto> findByFraganciaIgnoreCase(String fragancia);
}
