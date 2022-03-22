package com.example.wearme_individualproject.service;

import com.example.wearme_individualproject.interfaces.IFavouriteItemService;
import com.example.wearme_individualproject.interfaces.IProductService;
import com.example.wearme_individualproject.logic.FavouriteItem;
import com.example.wearme_individualproject.logic.Product;
import com.example.wearme_individualproject.logic.User;
import com.example.wearme_individualproject.repository.IFavouriteItemRepository;
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
public class FavouriteItemService implements IFavouriteItemService {

    private final IFavouriteItemRepository favouriteItemRepository;
    private final IProductService productService;

    @Override
    public FavouriteItem getFavouriteItemById(int favouriteItemId){
        return favouriteItemRepository.findById(favouriteItemId);
    }

    @Override
    public Product getFavouriteItemProductById(int favouriteItemId){
        return favouriteItemRepository.findById(favouriteItemId).getProduct();
    }
    @Override
    public List<FavouriteItem> getListOfAllFavouriteItemsItems(){
        return favouriteItemRepository.findAll();
    }
    @Override
    public List<FavouriteItem> getListOfFavouriteItemsByUsername(String username){
        List<FavouriteItem> filteredList = new ArrayList<>();
        for(FavouriteItem item : favouriteItemRepository.findAll()){
            if(Objects.equals(item.getUser().getUsername(), username)){
                filteredList.add(item);
            }
        }
        return filteredList;
    }
    @Override
    public void addItemToUsersFavouriteItems(User user, int productId){
        FavouriteItem item = new FavouriteItem(user, productService.getProductById(productId));
        favouriteItemRepository.save(item);
    }
    @Override
    public void addItemToUsersFavouriteItems(FavouriteItem favouriteItem){
        favouriteItemRepository.save(favouriteItem);
    }
    @Override
    public void deleteItemFromUsersFsavouriteItems(int favouriteItemId){
        favouriteItemRepository.delete(favouriteItemRepository.findById(favouriteItemId));
    }

}
