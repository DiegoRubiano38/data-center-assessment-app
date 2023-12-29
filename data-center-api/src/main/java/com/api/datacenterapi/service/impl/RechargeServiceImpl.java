package com.api.datacenterapi.service.impl;

import com.api.datacenterapi.entity.Operator;
import com.api.datacenterapi.entity.Recharge;
import com.api.datacenterapi.repository.OperatorRepository;
import com.api.datacenterapi.repository.RechargeRepository;
import com.api.datacenterapi.service.RechargeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RechargeServiceImpl implements RechargeService {

    private final RechargeRepository rechargeRepository;

    private final OperatorRepository operatorRepository;

    public RechargeServiceImpl(RechargeRepository rechargeRepository, OperatorRepository operatorRepository) {
        this.rechargeRepository = rechargeRepository;
        this.operatorRepository = operatorRepository;
    }
    @Override
    public List<Recharge> getAllRecharges() {
        return rechargeRepository.findAll();
    }

    @Override
    public Recharge doRecharge(Recharge recharge) {
        return rechargeRepository.save(recharge);
    }

    @Override
    public List<Recharge> getRechargeByOperator(String operator) {
        Long id = findOperatorIdByName(operator);
        Operator tempOperator = new Operator(id, operator);
        return rechargeRepository.findByOperator(tempOperator);
    }

    private Long findOperatorIdByName(String operatorName) {
        Optional<Operator> optionalOperator = operatorRepository.findByName(operatorName);
        return optionalOperator.map(Operator::getId).orElse(null);
    }
}
