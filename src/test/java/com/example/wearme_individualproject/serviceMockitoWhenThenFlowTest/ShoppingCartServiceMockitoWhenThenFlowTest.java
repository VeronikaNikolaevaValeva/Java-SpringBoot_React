package com.example.wearme_individualproject.serviceMockitoWhenThenFlowTest;

import com.example.wearme_individualproject.enumeration.*;
import com.example.wearme_individualproject.interfaces.IProductService;
import com.example.wearme_individualproject.interfaces.IUserService;
import com.example.wearme_individualproject.logic.Product;
import com.example.wearme_individualproject.logic.ShoppingCartItem;
import com.example.wearme_individualproject.logic.User;
import com.example.wearme_individualproject.repository.IProductRepository;
import com.example.wearme_individualproject.repository.IShoppingCartRepository;
import com.example.wearme_individualproject.repository.IUserRepository;
import com.example.wearme_individualproject.service.ProductService;
import com.example.wearme_individualproject.service.ShoppingCartService;
import com.example.wearme_individualproject.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.when;
@ActiveProfiles("test")
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ShoppingCartServiceMockitoWhenThenFlowTest {

    @Mock
    private IShoppingCartRepository iShoppingCartRepository;
    @Mock
    private IUserRepository iUserRepository;
    @Mock
    private IProductRepository iProductRepository;
    private ShoppingCartService underTest;
    private IUserService userService;
    private IProductService productService;
    private final PasswordEncoder passwordEncoder;

    ShoppingCartServiceMockitoWhenThenFlowTest(){passwordEncoder = new BCryptPasswordEncoder();}

    @BeforeEach
    void setUp(){
        userService = new UserService(iUserRepository, passwordEncoder);
        productService = new ProductService(iProductRepository);
        underTest = new ShoppingCartService(iShoppingCartRepository,productService);
        User user = new User("test", "test", "test", "test",
                "test.test@test.test", LocalDate.parse("2001-02-07"), "+test(test)test", "test",
                "test", "test", "test", "test", AccountStatus.ACTIVE, Role.CUSTOMER);
        Product productOne = new Product("TestOne", ProductCategory.SNEAKERS, "test", "test",
                "test",
                ProductGender.UNISEX, 165, 125, ProductStatus.AVAILABLE, "some url");
        userService.addNewUser(user);
        productService.addNewProduct(productOne);
        List<ShoppingCartItem> itemInfoList = List.of(
                new ShoppingCartItem(user, productOne),
                new ShoppingCartItem(user, productOne)
        );
        when(underTest.getSHoppingCartItemById(0)).thenReturn(itemInfoList.get(0));
    }

    @Test
    void getListOfShoppingCartItemsByUsernameTest(){
        ShoppingCartItem shoppingCartItemtest = underTest.getSHoppingCartItemById(0);
        Assertions.assertEquals(0, shoppingCartItemtest.getProduct().getId());
    }

}
