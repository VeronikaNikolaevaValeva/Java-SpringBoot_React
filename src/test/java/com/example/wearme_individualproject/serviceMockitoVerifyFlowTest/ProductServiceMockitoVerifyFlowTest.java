package com.example.wearme_individualproject.serviceMockitoVerifyFlowTest;

import com.example.wearme_individualproject.enumeration.ProductCategory;
import com.example.wearme_individualproject.enumeration.ProductGender;
import com.example.wearme_individualproject.enumeration.ProductStatus;
import com.example.wearme_individualproject.exceptions.NotExistingUserException;
import com.example.wearme_individualproject.interfaces.IProductService;
import com.example.wearme_individualproject.logic.Product;
import com.example.wearme_individualproject.repository.IProductRepository;
import com.example.wearme_individualproject.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

@ActiveProfiles("test")
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ProductServiceMockitoVerifyFlowTest {

    @Mock
    private IProductRepository iProductRepository;
    private IProductService underTest;

    @BeforeEach
    void setUp(){
        underTest = new ProductService(iProductRepository);
    }

    @Test
    void findAllUProductsTestVerifyFlow(){
        //when
        underTest.getListOfAllProducts();
        //then verify that the method was invoked
        verify(iProductRepository).findAll();
    }


    @Test
    void addNewProductTest(){
        //given
        Product product = new Product("test", ProductCategory.SNEAKERS, "test", "test",
                "test",
                ProductGender.UNISEX, 165, 125, ProductStatus.AVAILABLE, "some url");
        //when
        underTest.addNewProduct(product);
        //then verify that the method was invoked. We want to 'capture' the actual user that was passed in this method
        ArgumentCaptor<Product> productArgumentCaptor = ArgumentCaptor.forClass(Product.class);
        verify(iProductRepository).save(productArgumentCaptor.capture());
        //Check if the captured value from the userRepo method id the same as the one we have passed to the userService
        Product capturedProduct = productArgumentCaptor.getValue();
        assertThat(capturedProduct).isEqualTo(product);
    }

    @Test
    void getNineProductsTest() throws NotExistingUserException {
        try {
            //when
            underTest.getNineProducts(1, ProductCategory.SNEAKERS);
            //then verify that the method was invoked
            verify(iProductRepository).findAll();
        }
        catch(NotExistingUserException e){}
    }





}
