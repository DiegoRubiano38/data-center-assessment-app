package com.api.datacenterapi.repository;

import com.api.datacenterapi.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller, Long> {
}
