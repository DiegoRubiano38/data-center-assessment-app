package com.api.datacenterapi.repository;

import com.api.datacenterapi.entity.Operador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OperadorRepository extends JpaRepository<Operador, Long> {
    Optional<Operador> findByNombre(String nombre);
}
