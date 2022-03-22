package com.example.wearme_individualproject.serviceH2DatabaseTest;

import com.example.wearme_individualproject.enumeration.ProductCategory;
import com.example.wearme_individualproject.enumeration.ProductGender;
import com.example.wearme_individualproject.enumeration.ProductStatus;
import com.example.wearme_individualproject.interfaces.IProductService;
import com.example.wearme_individualproject.logic.Product;
import com.example.wearme_individualproject.repository.IProductRepository;
import com.example.wearme_individualproject.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@ActiveProfiles("test")
@SpringBootTest
class ProductServiceH2DatabaseTest {

    @Autowired
    private IProductRepository iProductRepository;
    private IProductService underTest;

    @BeforeEach
    void setUp(){
        underTest = new ProductService(iProductRepository);
    }

    @Test
    void getListOfAllProducts() {
        Product productTest = new Product("test", ProductCategory.SNEAKERS, "test", "test",
                "test",
                ProductGender.UNISEX, 165, 125, ProductStatus.AVAILABLE, "some url");
        underTest.addNewProduct(productTest);
        List<Product> listProductsTest = underTest.getListOfAllProducts();
        Assertions.assertEquals(1, listProductsTest.size());
        underTest.deleteProduct(productTest.getId());
    }

    @Test
     void deleteProductTest(){
        Product productTest = new Product("test", ProductCategory.SNEAKERS, "test", "test",
                "test",
                ProductGender.UNISEX, 165, 125, ProductStatus.AVAILABLE, "some url");
        underTest.addNewProduct(productTest);
        List<Product> listProductsTest = underTest.getListOfAllProducts();
        Assertions.assertEquals(1, listProductsTest.size());
        underTest.deleteProduct(productTest.getId());
        listProductsTest = underTest.getListOfAllProducts();
        Assertions.assertEquals(0, listProductsTest.size());
    }

    @Test
     void getAndDeleteNineProductsTest(){
        int i;
        for (i = 0; i < 10; i++) {
            underTest.addNewProduct(new Product("test", ProductCategory.SNEAKERS, "test", "test",
                    "test",
                    ProductGender.UNISEX, 165, 125, ProductStatus.AVAILABLE, "some url"));
        }
        List<Product> listProductsTest = underTest.getListOfNineProducts(1, ProductCategory.SNEAKERS);
        Assertions.assertEquals(9, listProductsTest.size());
        for (Product product : underTest.getListOfAllProducts()) {
            underTest.deleteProduct(product.getId());
        }
    }

}
