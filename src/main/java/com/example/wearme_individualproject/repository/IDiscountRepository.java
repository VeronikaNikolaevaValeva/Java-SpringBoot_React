package com.example.wearme_individualproject.repository;

import com.example.wearme_individualproject.logic.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IDiscountRepository  extends JpaRepository<Discount, Integer> {
    Discount findById(int Id);
    List<Discount> findAll();
}
