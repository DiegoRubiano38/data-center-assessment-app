package com.api.datacenterapi.repository;

import com.api.datacenterapi.entity.Operador;
import com.api.datacenterapi.entity.Recarga;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecargaRepository extends JpaRepository<Recarga, Long> {
    List<Recarga> findByOperador(Operador operador);
}
