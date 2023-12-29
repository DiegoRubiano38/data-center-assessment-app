package com.api.datacenterapi.repository;

import com.api.datacenterapi.entity.Operator;
import com.api.datacenterapi.entity.Recharge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RechargeRepository extends JpaRepository<Recharge, Long> {
    List<Recharge> findByOperator(Operator operator);
}
