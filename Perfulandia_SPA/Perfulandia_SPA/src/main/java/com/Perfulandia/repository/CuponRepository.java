package com.Perfulandia.repository;

import com.Perfulandia.model.Cupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuponRepository extends JpaRepository<Cupon, Long> {
    Cupon findByCodigo(String codigo);
}
