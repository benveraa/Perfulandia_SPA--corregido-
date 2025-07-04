package com.Perfulandia.repository;

import com.Perfulandia.model.Soporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoporteRepository extends JpaRepository<Soporte, Long> {
}
