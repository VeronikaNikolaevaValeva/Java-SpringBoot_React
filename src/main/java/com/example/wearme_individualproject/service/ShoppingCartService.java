package com.example.wearme_individualproject.service;

import com.example.wearme_individualproject.interfaces.IProductService;
import com.example.wearme_individualproject.interfaces.IShoppingCartService;
import com.example.wearme_individualproject.logic.Product;
import com.example.wearme_individualproject.logic.ShoppingCartItem;
import com.example.wearme_individualproject.logic.User;
import com.example.wearme_individualproject.repository.IShoppingCartRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ShoppingCartService implements IShoppingCartService{

    private final IShoppingCartRepository shoppingCartRepository;
    private final IProductService productService;

    @Override
    public ShoppingCartItem getSHoppingCartItemById(int shoppingCartId){
        return shoppingCartRepository.findById(shoppingCartId);
    }

    @Override
    public Product getSHoppingCartProductById(int shoppingCartId){
        return shoppingCartRepository.findById(shoppingCartId).getProduct();
    }
    @Override
    public List<ShoppingCartItem> getListOfAllShoppingCartItems(){
        return shoppingCartRepository.findAll();
    }
    @Override
    public List<ShoppingCartItem> getListOfShoppingCartItemsByUsername(String username){
        List<ShoppingCartItem> filteredList = new ArrayList<>();
        for(ShoppingCartItem item : shoppingCartRepository.findAll()){
            if(Objects.equals(item.getUser().getUsername(), username)){
                filteredList.add(item);
            }
        }
        return filteredList;
    }
    @Override
    public void addItemToUsersShoppingCart(User user, int productId){
        ShoppingCartItem item = new ShoppingCartItem(user, productService.getProductById(productId));
        shoppingCartRepository.save(item);
    }
    @Override
    public void addItemToUsersShoppingCart(ShoppingCartItem shoppingCartItem){
        shoppingCartRepository.save(shoppingCartItem);
    }
    @Override
    public void deleteItemFromUsersShoppingCart(int shoppingCartId){
        shoppingCartRepository.delete(shoppingCartRepository.findById(shoppingCartId));
    }
}
