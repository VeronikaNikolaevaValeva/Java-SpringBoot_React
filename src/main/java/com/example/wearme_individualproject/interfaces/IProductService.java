package com.example.wearme_individualproject.interfaces;


import com.example.wearme_individualproject.enumeration.ProductCategory;
import com.example.wearme_individualproject.enumeration.ProductGender;
import com.example.wearme_individualproject.enumeration.ProductStatus;
import com.example.wearme_individualproject.logic.Product;

import java.util.List;

public interface IProductService {

     Product getProductById(int id);

     void addNewProduct(Product product) ;

     boolean existingProduct(int id) ;

     void deleteProduct(int id) ;

     List<Product> getListOfAllProducts();

     List<Product> getListOfNineProducts(int num, ProductCategory category);

     List<Product> getNineProducts(int num, ProductCategory category);

     List<Product> getListOfNineGenderProducts(int num, ProductGender productGneder, ProductCategory category);

     List<Product> getNineGenderProducts(int num, ProductGender productGender, ProductCategory category);

     List<Product> getListOfAllProductsByCategory(ProductCategory productCategory) ;

     List<Product> getListOfAllProductsByGender(ProductGender productGender);

     List<Product> getListOfAllProductsByGenderAndCategory(ProductGender productGender, ProductCategory productCategory);

     List<Product> getListOfAllProductsByProductStatus(ProductStatus productStatus);

}
