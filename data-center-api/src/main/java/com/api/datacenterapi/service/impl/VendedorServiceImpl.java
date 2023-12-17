package com.api.datacenterapi.service.impl;

import com.api.datacenterapi.entity.Vendedor;
import com.api.datacenterapi.repository.VendedorRepository;
import com.api.datacenterapi.service.VendedorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendedorServiceImpl implements VendedorService {

    private final VendedorRepository vendedorRepository;

    public VendedorServiceImpl(VendedorRepository vendedorRepository) {
        this.vendedorRepository = vendedorRepository;
    }

    @Override
    public List<Vendedor> obtenerTodosLosVendedores() {
        return vendedorRepository.findAll();
    }

    @Override
    public Vendedor guardarVendedor(Vendedor vendedor) {
        return vendedorRepository.save(vendedor);
    }
}
