package com.example.wearme_individualproject.serviceMockitoWhenThenFlowTest;

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
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import static org.mockito.Mockito.when;
@ActiveProfiles("test")
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ProductServiceMockitoWhenThenFlowTest {

    @Mock
    private IProductRepository iProductRepository;
    private IProductService underTest;

    @BeforeEach
    void setUp(){
        underTest = new ProductService(iProductRepository);
        List<Product> testProducts = List.of(
                new Product("testOne", ProductCategory.SNEAKERS, "test", "test",
                        "test",
                        ProductGender.UNISEX, 165, 125, ProductStatus.AVAILABLE, "some url"),
                new Product("testTwo", ProductCategory.SNEAKERS, "test", "test",
                        "test",
                        ProductGender.UNISEX, 165, 125, ProductStatus.AVAILABLE, "some url")
        );
        when(underTest.getListOfAllProducts()).thenReturn(testProducts);
        when(underTest.getListOfAllProductsByProductStatus(ProductStatus.AVAILABLE)).thenReturn(testProducts);
        when(underTest.getListOfAllProductsByGenderAndCategory( ProductGender.UNISEX,ProductCategory.SNEAKERS)).thenReturn(testProducts);
    }

    @Test
    void getAllProductsTest(){
        List<Product> productsTestList = underTest.getListOfAllProducts();
        Assertions.assertEquals("testOne", productsTestList.get(0).getName());
        Assertions.assertEquals("testTwo", productsTestList.get(1).getName());
    }

    @Test
    void getProductsByProductStatus(){
        List<Product> productsTestList = underTest.getListOfAllProductsByProductStatus(ProductStatus.AVAILABLE);
        Assertions.assertEquals("testOne", productsTestList.get(0).getName());
        Assertions.assertEquals("testTwo", productsTestList.get(1).getName());
    }

    @Test
    void getListOfAllProductsByGenderAndCategory(){
        List<Product> productsTestList = underTest.getListOfAllProductsByGenderAndCategory(ProductGender.UNISEX,ProductCategory.SNEAKERS);
        Assertions.assertEquals("testOne", productsTestList.get(0).getName());
        Assertions.assertEquals("testTwo", productsTestList.get(1).getName());
    }

}
