package com.example.wearme_individualproject.controller;

import com.example.wearme_individualproject.DTOclasses.CustomCast_DTO;
import com.example.wearme_individualproject.DTOclasses.Product_DTO;
import com.example.wearme_individualproject.enumeration.ProductCategory;
import com.example.wearme_individualproject.enumeration.ProductGender;
import com.example.wearme_individualproject.enumeration.ProductStatus;
import com.example.wearme_individualproject.exceptions.ExistingProductException;
import com.example.wearme_individualproject.exceptions.NotExistingProductException;
import com.example.wearme_individualproject.interfaces.IProductService;
import com.example.wearme_individualproject.logic.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/product")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
public class ProductServiceController {

    private final IProductService productService;
    private final CustomCast_DTO castObject;
    private static final  String PRODUCTNOTFOUND = "Product not found";
    private static final  String PRODUCTEXISTS = "This product already exists.";

    @GetMapping("{id}")
    public ResponseEntity<Product> getProductPath(@PathVariable(value = "id")int id) {
        try{
            Product product = productService.getProductById(id);
            return ResponseEntity.ok().body(product);
        }
        catch(NotExistingProductException e){
            return new ResponseEntity(PRODUCTNOTFOUND,HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/AllProducts")
    public ResponseEntity<List<Product>> getListOfAllProductsByCategory() {
        List<Product> products = productService.getListOfAllProducts();
        if(products != null){
            return ResponseEntity.ok().body(products);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
    @GetMapping("/NineProducts/{num}/{category}")
    public ResponseEntity<List<Product>> getListOfNineProducts(@PathVariable(value = "num")String num, @PathVariable(value = "category")String category) {
        List<Product> products = null;
        int number = Integer.parseInt(num);
        if(number >=0) {
            products = productService.getListOfNineProducts(number, ProductCategory.valueOf(category));
        }
        if(products != null){
            return ResponseEntity.ok().body(products);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
    @GetMapping("/NineProducts/{num}/{gender}/{category}")
    public ResponseEntity<List<Product>> getListOfNineProductsByGender(@PathVariable(value = "num")String num, @PathVariable(value = "gender")String gender
            , @PathVariable(value = "category")String category) {
        List<Product> products = null;
        int number = Integer.parseInt(num);

        if(number >=0) {
            products = productService.getListOfNineGenderProducts(number, ProductGender.valueOf(gender), ProductCategory.valueOf(category));
        }
        if(products != null){
            return ResponseEntity.ok().body(products);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/ProductCategory/{productCategory}")
    public ResponseEntity<List<Product>> getListOfAllProductsByCategory(@PathVariable(value = "productCategory")String productCategory) {
        List<Product> products = null;
        if(productCategory != null) {
            products = productService.getListOfAllProductsByCategory(ProductCategory.valueOf(productCategory));
        }
        if(products != null){
            return ResponseEntity.ok().body(products);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/ProductGender/{productGender}")
    public ResponseEntity<List<Product>> getListOfAllProductsByGender(@PathVariable(value = "productGender")String productGender) {
        List<Product> products = null;
        if(productGender != null) {
            products = productService.getListOfAllProductsByGender(ProductGender.valueOf(productGender));
        }
        if(products != null){
            return ResponseEntity.ok().body(products);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/ProductGenderAndCategory/{productGender}/{productCategory}")
    public ResponseEntity<List<Product>> getListOfAllProductsByGenderAndCategory (@PathVariable(value = "productGender")String productGender, @PathVariable(value = "productCategory")String productCategory) {
        List<Product> products = null;
        if(productGender != null && productCategory != null) {
            products = productService.getListOfAllProductsByGenderAndCategory(ProductGender.valueOf(productGender), ProductCategory.valueOf(productCategory));
        }
        if(products != null){
            return ResponseEntity.ok().body(products);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/ProductStatus/{productStatus}")
    public ResponseEntity<List<Product>> getListOfAllProductsByProductStatus(@PathVariable(value = "productStatus")String productStatus) {
        List<Product> products = null;
        if(productStatus != null) {
            products = productService.getListOfAllProductsByProductStatus(ProductStatus.valueOf(productStatus));
        }
        if(products != null){
            return ResponseEntity.ok().body(products);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping()
    public ResponseEntity<Product> createProduct(@RequestBody Product_DTO productDTO){
       try{
           Product product = castObject.castToProduct(productDTO);
           productService.addNewProduct(product);
           return new ResponseEntity(URI.create("product" + "/" + product.getId()), HttpStatus.CREATED);
       }
       catch(ExistingProductException e){
           return new ResponseEntity(PRODUCTEXISTS,HttpStatus.CONFLICT);
       }
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteProduct(@PathVariable int id){
        try{
            productService.deleteProduct(id);
            return ResponseEntity.ok().build();
        }
        catch(NotExistingProductException e){
            return new ResponseEntity(PRODUCTNOTFOUND,HttpStatus.CONFLICT);
        }

    }



}
