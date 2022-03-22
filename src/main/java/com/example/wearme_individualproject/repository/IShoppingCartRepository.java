package com.example.wearme_individualproject.repository;

import com.example.wearme_individualproject.logic.Product;
import com.example.wearme_individualproject.logic.ShoppingCartItem;
import com.example.wearme_individualproject.logic.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IShoppingCartRepository extends JpaRepository<ShoppingCartItem, Integer> {
    List<ShoppingCartItem> findAll();
    ShoppingCartItem findById(int id);
}
