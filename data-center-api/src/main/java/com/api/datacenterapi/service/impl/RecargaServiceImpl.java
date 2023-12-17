package com.api.datacenterapi.service.impl;

import com.api.datacenterapi.entity.Operador;
import com.api.datacenterapi.entity.Recarga;
import com.api.datacenterapi.repository.OperadorRepository;
import com.api.datacenterapi.repository.RecargaRepository;
import com.api.datacenterapi.service.RecargaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecargaServiceImpl implements RecargaService {

    private final RecargaRepository recargaRepository;

    private final OperadorRepository operadorRepository;

    public RecargaServiceImpl(RecargaRepository recargaRepository, OperadorRepository operadorRepository) {
        this.recargaRepository = recargaRepository;
        this.operadorRepository = operadorRepository;
    }
    @Override
    public List<Recarga> obtenerTodasLasRecargas() {
        return recargaRepository.findAll();
    }

    @Override
    public Recarga realizarRecarga(Recarga recarga) {
        return recargaRepository.save(recarga);
    }

    @Override
    public List<Recarga> obtenerRecargasPorOperador(String operador) {
        Long id = encontrarIdOperadorPorNombre(operador);
        Operador tempOperador = new Operador(id, operador);
        return recargaRepository.findByOperador(tempOperador);
    }

    private Long encontrarIdOperadorPorNombre(String nombreOperador) {
        Optional<Operador> operadorOptional = operadorRepository.findByNombre(nombreOperador);
        return operadorOptional.map(Operador::getId).orElse(null);
    }
}
