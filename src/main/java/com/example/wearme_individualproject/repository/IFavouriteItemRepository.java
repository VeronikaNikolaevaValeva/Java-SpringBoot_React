package com.example.wearme_individualproject.repository;

import com.example.wearme_individualproject.logic.FavouriteItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IFavouriteItemRepository extends JpaRepository<FavouriteItem, Integer> {
    List<FavouriteItem> findAll();
    FavouriteItem findById(int id);
}
