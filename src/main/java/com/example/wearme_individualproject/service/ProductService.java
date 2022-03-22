package com.example.wearme_individualproject.service;

import com.example.wearme_individualproject.enumeration.ProductCategory;
import com.example.wearme_individualproject.enumeration.ProductGender;
import com.example.wearme_individualproject.enumeration.ProductStatus;
import com.example.wearme_individualproject.exceptions.ExistingProductException;
import com.example.wearme_individualproject.exceptions.NotExistingProductException;
import com.example.wearme_individualproject.repository.IProductRepository;
import com.example.wearme_individualproject.interfaces.IProductService;
import com.example.wearme_individualproject.logic.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service @RequiredArgsConstructor
@Transactional
@Slf4j
public class ProductService implements IProductService {

    private final IProductRepository productRepo;

    @Override
    public Product getProductById(int id) {
        Product product = productRepo.findById(id);
        if(productRepo.findById(id) == null){
            throw new NotExistingProductException();
        }
        return product;
    }

    @Override
    public void addNewProduct(Product product) {
        if(this.existingProduct(product.getId())) {
            throw new ExistingProductException();
        }
        productRepo.save(product);
    }

    @Override
    public boolean existingProduct(int id) {
        if(productRepo.findById(id) == null){
            return false;
        }
        return true;
    }

    @Override
    public void deleteProduct(int id) {
        if(productRepo.findById(id)==null) {
            throw new NotExistingProductException();
        }
        productRepo.delete(productRepo.findById(id));
    }

    @Override
    public List<Product> getListOfAllProducts() {
        return productRepo.findAll();
    }

    @Override
    public List<Product> getListOfNineProducts(int number, ProductCategory category) {
        List<Product> products = new ArrayList<>();
        if(this.getListOfAllProducts().size()<=number*9){
            int scope = this.getListOfAllProducts().size()/9;
            products = this.getNineProducts(scope, category);
        }
        else {
            products = this.getNineProducts(number, category);
        }
        return products;
    }


    @Override
    public List<Product> getNineProducts(int number, ProductCategory category) {
        int num = 9*(number-1) + 1;
        List<Product> filteredList = new ArrayList<>();
        for(int i = num-1; i < num+8; i++){
            if(this.getListOfAllProducts().size() <= i){
                return filteredList;
            }
            if(this.getListOfAllProducts().get(i).getProductCategory()==category){
                filteredList.add(this.getListOfAllProducts().get(i));
            }
        }
          return filteredList;
    }

    @Override
    public List<Product> getListOfNineGenderProducts(int number, ProductGender productGender, ProductCategory category) {
        List<Product> products = new ArrayList<>();

        if(this.getListOfAllProductsByGender(productGender).size()<=number*9){
            int scope = this.getListOfAllProductsByGender(productGender).size()/9;

            products = this.getNineGenderProducts(scope, productGender, category);
        }
        else {
            products = this.getNineGenderProducts(number, productGender, category);
        }
        return products;
    }

    @Override
    public List<Product> getNineGenderProducts(int number, ProductGender gender, ProductCategory category) {
        int num = 9*(number-1) + 1;
        List<Product> filteredList = new ArrayList<>();
        for(int i = num-1; i < num+8; i++){
            if(this.getListOfAllProductsByGender(gender).size() <= i){
                return filteredList;
            }
            if(this.getListOfAllProductsByGender(gender).get(i).getProductCategory()==category){
                filteredList.add(this.getListOfAllProductsByGender(gender).get(i));
            }
        }
        return filteredList;
    }

    @Override
    public List<Product> getListOfAllProductsByCategory(ProductCategory productCategory) {
        List<Product> filteredList = new ArrayList<>();
        for(Product product : this.getListOfAllProducts()){
            if(product.getProductCategory() == productCategory){
                filteredList.add(product);
            }
        }
        return filteredList;
    }

    @Override
    public List<Product> getListOfAllProductsByGender(ProductGender productGender) {
        List<Product> filteredList = new ArrayList<>();
        for(Product product : this.getListOfAllProducts()){
            if(product.getProductGender() == productGender || product.getProductGender()==ProductGender.UNISEX){
                filteredList.add(product);
            }
        }
        return filteredList;
    }

    @Override
    public List<Product> getListOfAllProductsByGenderAndCategory(ProductGender productGender, ProductCategory productCategory) {
        List<Product> filteredList = new ArrayList<>();
        for(Product product : this.getListOfAllProducts()){
            if(product.getProductGender()==productGender && product.getProductCategory()==productCategory){
                filteredList.add(product);
            }
        }
        return filteredList;
    }

    @Override
    public List<Product> getListOfAllProductsByProductStatus(ProductStatus productStatus) {
        List<Product> filteredList = new ArrayList<>();
        for(Product product : this.getListOfAllProducts()){
            if(product.getProductStatus() == productStatus){
                filteredList.add(product);
            }
        }
        return filteredList;
    }


}
