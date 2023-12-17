package com.api.datacenterapi.service.impl;

import com.api.datacenterapi.entity.Operador;
import com.api.datacenterapi.repository.OperadorRepository;
import com.api.datacenterapi.service.OperadorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperadorServiceImpl implements OperadorService {

    private final OperadorRepository operadorRepository;

    public OperadorServiceImpl(OperadorRepository operadorRepository) {
        this.operadorRepository = operadorRepository;
    }

    @Override
    public List<Operador> obtenerTodosLosOperadores() {
        return operadorRepository.findAll();
    }

    @Override
    public Operador guardarOperador(Operador operador) {
        return operadorRepository.save(operador);
    }
}
