package com.example.wearme_individualproject.repository;

import com.example.wearme_individualproject.logic.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<Product, Integer> {
    Product findById(int id);

}
