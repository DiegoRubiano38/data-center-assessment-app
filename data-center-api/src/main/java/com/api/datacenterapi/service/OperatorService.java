package com.api.datacenterapi.service;

import com.api.datacenterapi.entity.Operator;

import java.util.List;

public interface OperatorService {

    List<Operator> getAllOperators();

    Operator saveOperator(Operator operator);

}
