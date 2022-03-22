package com.example.wearme_individualproject.serviceMockitoVerifyFlowTest;

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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

@ActiveProfiles("test")
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ShoppingCartServiceMockitoVerifyFlowTest {

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

    ShoppingCartServiceMockitoVerifyFlowTest(){
        passwordEncoder = new BCryptPasswordEncoder();
    }

    @BeforeEach
    void setUp(){
        userService = new UserService(iUserRepository, passwordEncoder);
        productService = new ProductService(iProductRepository);
        underTest = new ShoppingCartService(iShoppingCartRepository,productService);}


    @Test
    void getListOfShoppingCartItemsByUsernameTest(){
        User user = new User("test", "test", "test", "test",
                "test.test@test.test", LocalDate.parse("2001-02-07"), "+test(test)test", "test",
                "test", "test", "test", "test", AccountStatus.ACTIVE, Role.CUSTOMER);
        Product product = new Product("test", ProductCategory.SNEAKERS, "test", "test",
                "test",
                ProductGender.UNISEX, 165, 125, ProductStatus.AVAILABLE, "some url");
        userService.addNewUser(user);
        productService.addNewProduct(product);
        ShoppingCartItem shoppingCartItem = new ShoppingCartItem(user, product);
        underTest.addItemToUsersShoppingCart(shoppingCartItem);
        //when
        underTest.getListOfShoppingCartItemsByUsername("test");
        //then verify that the method was invoked
        verify(iShoppingCartRepository).findAll();
        underTest.deleteItemFromUsersShoppingCart(shoppingCartItem.getCartId());
    }

    @Test
    void findShoppingCartItemByUserIdTest(){
        User user = new User("test", "test", "test", "test",
                "test.test@test.test", LocalDate.parse("2001-02-07"), "+test(test)test", "test",
                "test", "test", "test", "test", AccountStatus.ACTIVE, Role.CUSTOMER);
        Product product = new Product("test", ProductCategory.SNEAKERS, "test", "test",
                "test",
                ProductGender.UNISEX, 165, 125, ProductStatus.AVAILABLE, "some url");
        userService.addNewUser(user);
        productService.addNewProduct(product);
        ShoppingCartItem shoppingCartItem = new ShoppingCartItem(user, product);
        underTest.addItemToUsersShoppingCart(shoppingCartItem);
        //when
        underTest.getSHoppingCartItemById(shoppingCartItem.getCartId());
        //then verify that the method was invoked
        verify(iShoppingCartRepository).findById(shoppingCartItem.getCartId());
    }
    @Test
    void addItemToUsersShoppingCartTest(){
        User user = new User("test", "test", "test", "test",
                "test.test@test.test", LocalDate.parse("2001-02-07"), "+test(test)test", "test",
                "test", "test", "test", "test", AccountStatus.ACTIVE, Role.CUSTOMER);
        Product product = new Product("test", ProductCategory.SNEAKERS, "test", "test",
                "test",
                ProductGender.UNISEX, 165, 125, ProductStatus.AVAILABLE, "some url");
        userService.addNewUser(user);
        productService.addNewProduct(product);
        ShoppingCartItem shoppingCartItem = new ShoppingCartItem(user, product);
        //when
        underTest.addItemToUsersShoppingCart(shoppingCartItem);
        //then verify that the method was invoked
        ArgumentCaptor<ShoppingCartItem> shoppingCartArgumentCaptor = ArgumentCaptor.forClass(ShoppingCartItem.class);
        verify(iShoppingCartRepository).save(shoppingCartArgumentCaptor.capture());
        //Check if the captured value from the userRepo method id the same as the one we have passed to the userService
        ShoppingCartItem capturedShoppingCartItem = shoppingCartArgumentCaptor.getValue();
        assertThat(capturedShoppingCartItem).isEqualTo(shoppingCartItem);
    }

}
