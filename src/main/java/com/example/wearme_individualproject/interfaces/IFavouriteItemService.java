package com.example.wearme_individualproject.interfaces;

import com.example.wearme_individualproject.logic.FavouriteItem;
import com.example.wearme_individualproject.logic.Product;
import com.example.wearme_individualproject.logic.User;

import java.util.List;

public interface IFavouriteItemService {

    FavouriteItem getFavouriteItemById(int favouriteItemId);

    Product getFavouriteItemProductById(int favouriteItemId);

    List<FavouriteItem> getListOfAllFavouriteItemsItems();

    List<FavouriteItem> getListOfFavouriteItemsByUsername(String username);

    void addItemToUsersFavouriteItems(User user, int productId);

    void addItemToUsersFavouriteItems(FavouriteItem favouriteItem);

    void deleteItemFromUsersFsavouriteItems(int favouriteItemId);

}
