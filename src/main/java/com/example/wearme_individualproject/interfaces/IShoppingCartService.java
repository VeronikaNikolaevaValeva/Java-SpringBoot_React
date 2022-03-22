package com.example.wearme_individualproject.interfaces;

import com.example.wearme_individualproject.logic.Product;
import com.example.wearme_individualproject.logic.ShoppingCartItem;
import com.example.wearme_individualproject.logic.User;

import java.util.ArrayList;
import java.util.List;

public interface IShoppingCartService {

     ShoppingCartItem getSHoppingCartItemById(int shoppingCartId);

     List<ShoppingCartItem> getListOfAllShoppingCartItems();

     Product getSHoppingCartProductById(int shoppingCartId);

     List<ShoppingCartItem> getListOfShoppingCartItemsByUsername(String username);

     void addItemToUsersShoppingCart(User user, int productId);

     void addItemToUsersShoppingCart(ShoppingCartItem shoppingCartItem);

     void deleteItemFromUsersShoppingCart(int shoppingCartId);
}
