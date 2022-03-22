package com.example.wearme_individualproject.interfaces;

import com.example.wearme_individualproject.logic.PurchasedProducts;

import java.util.List;

public interface IPurchasedProductService {

    PurchasedProducts getPurchasedProductById(int purchasedProductId);

    List<PurchasedProducts> getListOfAllPurchasedProducts();

    List<PurchasedProducts> getListOfAllPurchasedProductsByUsername(String username);

    void addPurchasedProduct(PurchasedProducts purchasedProducts);

    void deletePurchasedProduct(int purchasedProductId);

    void deletePurchasedProduct(PurchasedProducts purchasedProduct);
}
