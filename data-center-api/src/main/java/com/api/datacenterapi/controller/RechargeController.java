package com.api.datacenterapi.controller;

import com.api.datacenterapi.entity.Recharge;
import com.api.datacenterapi.service.RechargeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/recharge")
public class RechargeController {

    private final RechargeService rechargeService;

    public RechargeController(RechargeService rechargeService) {
        this.rechargeService = rechargeService;
    }

    @GetMapping
    public List<Recharge> getRecharges(@RequestParam(required = false) String operator) {
        if(operator != null) return rechargeService.getRechargeByOperator(operator);
        else return rechargeService.getAllRecharges();
    }

    @PostMapping
    public ResponseEntity<Recharge> doRecharge(@RequestBody Recharge recharge) {
        Recharge nuevaRecharge = rechargeService.doRecharge(recharge);
        return ResponseEntity.ok(nuevaRecharge);
    }

}
