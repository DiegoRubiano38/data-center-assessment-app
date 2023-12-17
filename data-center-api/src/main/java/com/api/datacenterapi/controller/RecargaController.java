package com.api.datacenterapi.controller;

import com.api.datacenterapi.entity.Recarga;
import com.api.datacenterapi.service.RecargaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/recargas")
public class RecargaController {

    private final RecargaService recargaService;

    public RecargaController(RecargaService recargaService) {
        this.recargaService = recargaService;
    }

    @GetMapping
    public List<Recarga> obtenerRecargas(@RequestParam(required = false) String operador) {
        if(operador != null) return recargaService.obtenerRecargasPorOperador(operador);
        else return recargaService.obtenerTodasLasRecargas();
    }

    @PostMapping
    public ResponseEntity<Recarga> realizarRecarga(@RequestBody Recarga recarga) {
        Recarga nuevaRecarga = recargaService.realizarRecarga(recarga);
        return ResponseEntity.ok(nuevaRecarga);
    }

}
