package com.api.datacenterapi.service;

import com.api.datacenterapi.entity.Vendedor;

import java.util.List;

public interface VendedorService {

    public List<Vendedor> obtenerTodosLosVendedores();

    public Vendedor guardarVendedor(Vendedor vendedor);

}
