package com.example.wearme_individualproject.service;

import com.example.wearme_individualproject.interfaces.IPurchasedProductService;
import com.example.wearme_individualproject.logic.PurchasedProducts;
import com.example.wearme_individualproject.repository.IPurchasedProductsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class PurchasedProductService implements IPurchasedProductService {

    private final IPurchasedProductsRepository iPurchasedProductsRepository;

    @Override
    public PurchasedProducts getPurchasedProductById(int purchasedProductId){
        return iPurchasedProductsRepository.findById(purchasedProductId);
    }

    @Override
    public List<PurchasedProducts> getListOfAllPurchasedProducts(){
        return iPurchasedProductsRepository.findAll();
    }

    @Override
    public List<PurchasedProducts> getListOfAllPurchasedProductsByUsername(String username){
        List<PurchasedProducts> filteredList = new ArrayList<>();
        for(PurchasedProducts purchasedProducts : this.getListOfAllPurchasedProducts()){
            if(purchasedProducts.getUser().getUsername().equals(username)){
                filteredList.add(purchasedProducts);
            }
        }
        return  filteredList;
    }

    @Override
    public void addPurchasedProduct(PurchasedProducts purchasedProducts){
        iPurchasedProductsRepository.save(purchasedProducts);
    }

    @Override
    public void deletePurchasedProduct(int purchasedProductId){
        iPurchasedProductsRepository.delete(this.getPurchasedProductById(purchasedProductId));
    }

    @Override
    public void deletePurchasedProduct(PurchasedProducts purchasedProduct){
        iPurchasedProductsRepository.delete(purchasedProduct);
    }


}
