package com.example.wearme_individualproject.repository;

import com.example.wearme_individualproject.logic.PurchasedProducts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPurchasedProductsRepository extends JpaRepository<PurchasedProducts, Integer> {
    PurchasedProducts findById(int purchasedProductsId);
    List<PurchasedProducts> findAll();
}
