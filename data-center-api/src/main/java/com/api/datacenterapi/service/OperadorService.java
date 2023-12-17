package com.api.datacenterapi.service;

import com.api.datacenterapi.entity.Operador;

import java.util.List;

public interface OperadorService {

    public List<Operador> obtenerTodosLosOperadores();

    public Operador guardarOperador(Operador operador);

}
