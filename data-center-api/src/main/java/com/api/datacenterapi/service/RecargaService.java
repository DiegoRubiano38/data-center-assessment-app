package com.api.datacenterapi.service;

import com.api.datacenterapi.entity.Recarga;

import java.util.List;

public interface RecargaService {

    public List<Recarga> obtenerTodasLasRecargas();

    public Recarga realizarRecarga(Recarga recarga);

    List<Recarga> obtenerRecargasPorOperador(String operador);
}
