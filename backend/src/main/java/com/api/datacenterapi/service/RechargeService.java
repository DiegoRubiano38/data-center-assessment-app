package com.api.datacenterapi.service;

import com.api.datacenterapi.entity.Recharge;

import java.util.List;

public interface RechargeService {

    List<Recharge> getAllRecharges();

    Recharge doRecharge(Recharge recharge);

    List<Recharge> getRechargeByOperator(String operator);
}
