package com.api.datacenterapi.service;

import com.api.datacenterapi.entity.Seller;

import java.util.List;

public interface SellerService {

    List<Seller> getAllSellers();

    Seller saveSeller(Seller seller);

}
