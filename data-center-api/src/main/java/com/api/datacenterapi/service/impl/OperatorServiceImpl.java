package com.api.datacenterapi.service.impl;

import com.api.datacenterapi.entity.Operator;
import com.api.datacenterapi.repository.OperatorRepository;
import com.api.datacenterapi.service.OperatorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperatorServiceImpl implements OperatorService {

    private final OperatorRepository operatorRepository;

    public OperatorServiceImpl(OperatorRepository operatorRepository) {
        this.operatorRepository = operatorRepository;
    }

    @Override
    public List<Operator> getAllOperators() {
        return operatorRepository.findAll();
    }

    @Override
    public Operator saveOperator(Operator operator) {
        return operatorRepository.save(operator);
    }
}
