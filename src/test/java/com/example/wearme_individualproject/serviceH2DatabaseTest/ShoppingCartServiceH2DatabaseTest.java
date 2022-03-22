package com.example.wearme_individualproject.serviceH2DatabaseTest;

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
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ActiveProfiles("test")
@SpringBootTest
class ShoppingCartServiceH2DatabaseTest {

    @Autowired
    private IShoppingCartRepository iShoppingCartRepository;
    @Autowired
    private IUserRepository iUserRepository;
    @Autowired
    private IProductRepository iProductRepository;
    private ShoppingCartService underTest;
    private IUserService userService;
    private IProductService productService;
    private final PasswordEncoder passwordEncoder;

    ShoppingCartServiceH2DatabaseTest(){
        passwordEncoder = new BCryptPasswordEncoder();
    }

    @BeforeEach
    void setUp(){
        userService =new UserService(iUserRepository, passwordEncoder);
        productService = new ProductService(iProductRepository);
        underTest = new ShoppingCartService(iShoppingCartRepository,productService);}


    @Test
    void addAndGetListOfShoppingCartItemsByUsername() {
        User user = new User("test", "test", "testtesttest", "test",
                "test.test@test.test", LocalDate.parse("2001-02-07"), "+test(test)test", "test",
                "test", "test", "test", "test", AccountStatus.ACTIVE, Role.CUSTOMER);
        Product product = new Product("test", ProductCategory.SNEAKERS, "test", "test",
                "test",
                ProductGender.UNISEX, 165, 125, ProductStatus.AVAILABLE, "some url");
        userService.addNewUser(user);
        productService.addNewProduct(product);
        ShoppingCartItem shoppingCartItem = new ShoppingCartItem(user, product);
        underTest.addItemToUsersShoppingCart(shoppingCartItem);
        List<ShoppingCartItem> capturedShoppingCart = underTest.getListOfShoppingCartItemsByUsername(user.getUsername());
        assertThat(capturedShoppingCart.get(0).getProduct().getId()).isEqualTo(product.getId());
        underTest.deleteItemFromUsersShoppingCart(shoppingCartItem.getCartId());
        userService.deleteUser(user.getId());
        productService.deleteProduct(product.getId());
    }

    @Test
    void deleteItemFromShoppingCart() {
        User user = new User("test", "test", "testtesttest", "test",
                "test.test@test.test", LocalDate.parse("2001-02-07"), "+test(test)test", "test",
                "test", "test", "test", "test", AccountStatus.ACTIVE, Role.CUSTOMER);
        Product product = new Product("test", ProductCategory.SNEAKERS, "test", "test",
                "test",
                ProductGender.UNISEX, 165, 125, ProductStatus.AVAILABLE, "some url");
        userService.addNewUser(user);
        productService.addNewProduct(product);
        ShoppingCartItem shoppingCartItem = new ShoppingCartItem(user, product);
        underTest.addItemToUsersShoppingCart(shoppingCartItem);

        underTest.deleteItemFromUsersShoppingCart(shoppingCartItem.getCartId());
        List<ShoppingCartItem> capturedShoppingCart = underTest.getListOfShoppingCartItemsByUsername(user.getUsername());
        assertThat(capturedShoppingCart.size()).isZero();
        userService.deleteUser(user.getId());
        productService.deleteProduct(product.getId());
    }

}
