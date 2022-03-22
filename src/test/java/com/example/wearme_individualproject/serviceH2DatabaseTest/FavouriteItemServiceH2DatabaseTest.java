package com.example.wearme_individualproject.serviceH2DatabaseTest;
import com.example.wearme_individualproject.enumeration.AccountStatus;
import com.example.wearme_individualproject.enumeration.ProductCategory;
import com.example.wearme_individualproject.enumeration.ProductGender;
import com.example.wearme_individualproject.enumeration.ProductStatus;
import com.example.wearme_individualproject.interfaces.IFavouriteItemService;
import com.example.wearme_individualproject.interfaces.IProductService;
import com.example.wearme_individualproject.logic.FavouriteItem;
import com.example.wearme_individualproject.logic.Product;
import com.example.wearme_individualproject.logic.User;
import com.example.wearme_individualproject.repository.IFavouriteItemRepository;
import com.example.wearme_individualproject.repository.IProductRepository;
import com.example.wearme_individualproject.repository.IUserRepository;
import com.example.wearme_individualproject.service.FavouriteItemService;
import com.example.wearme_individualproject.service.ProductService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;

@ActiveProfiles("test")
@SpringBootTest
class FavouriteItemServiceH2DatabaseTest {

    @Autowired
    private IFavouriteItemService underTest;
    @Autowired
    private IFavouriteItemRepository favouriteItemRepository;
    @Autowired
    private IProductService productService;
    @Autowired
    private IProductRepository productRepo;
    @Autowired
    private IUserRepository userRepo;

    @BeforeEach
    void setUp(){
        productService = new ProductService(productRepo);
        underTest = new FavouriteItemService(favouriteItemRepository, productService);
    }

    @AfterEach
    void tearDown(){
        favouriteItemRepository.deleteAll();
        productRepo.deleteAll();
        userRepo.deleteAll();
    }

    @Test
    void addAndGetListOfAllFavItems() {
        User user = new User("test", "test", "test", "test",
                "test.test@test.test", LocalDate.parse("2001-02-07"), "+test(test)test", "test",
                "test", "test", "test", "test", AccountStatus.ACTIVE, com.example.wearme_individualproject.enumeration.Role.CUSTOMER);
        userRepo.save(user);
        Product product = new Product("test", ProductCategory.SNEAKERS, "test", "test",
                "test",
                ProductGender.UNISEX, 165, 125, ProductStatus.AVAILABLE, "some url");
        productRepo.save(product);
        FavouriteItem favouriteItem = new FavouriteItem(user, product);
        underTest.addItemToUsersFavouriteItems(favouriteItem);

        List<FavouriteItem> listOfFavItems = underTest.getListOfAllFavouriteItemsItems();
        Assertions.assertEquals(1, listOfFavItems.size());
    }

    @Test
    void deleteFavItemTest(){
        User user = new User("test", "test", "test", "test",
                "test.test@test.test", LocalDate.parse("2001-02-07"), "+test(test)test", "test",
                "test", "test", "test", "test", AccountStatus.ACTIVE, com.example.wearme_individualproject.enumeration.Role.CUSTOMER);
        userRepo.save(user);
        Product product = new Product("test", ProductCategory.SNEAKERS, "test", "test",
                "test",
                ProductGender.UNISEX, 165, 125, ProductStatus.AVAILABLE, "some url");
        productRepo.save(product);
        FavouriteItem favouriteItem = new FavouriteItem(user, product);
        underTest.addItemToUsersFavouriteItems(favouriteItem);

        List<FavouriteItem> listOfFavItems = underTest.getListOfAllFavouriteItemsItems();
        Assertions.assertEquals(1, listOfFavItems.size());
        underTest.deleteItemFromUsersFsavouriteItems(favouriteItem.getId());
        listOfFavItems = underTest.getListOfAllFavouriteItemsItems();
        Assertions.assertEquals(0, listOfFavItems.size());
    }

    @Test
    void addAndGetFavItemsByUsername(){
        User user = new User("test", "test", "test", "test",
                "test.test@test.test", LocalDate.parse("2001-02-07"), "+test(test)test", "test",
                "test", "test", "test", "test", AccountStatus.ACTIVE, com.example.wearme_individualproject.enumeration.Role.CUSTOMER);
        userRepo.save(user);
        Product product = new Product("test", ProductCategory.SNEAKERS, "test", "test",
                "test",
                ProductGender.UNISEX, 165, 125, ProductStatus.AVAILABLE, "some url");
        productRepo.save(product);
        FavouriteItem favouriteItem = new FavouriteItem(user, product);
        underTest.addItemToUsersFavouriteItems(favouriteItem);

        List<FavouriteItem> listOfFavItemsByUsernamePass = underTest.getListOfFavouriteItemsByUsername("test");
        List<FavouriteItem> listOfFavItemsByUsernameFail = underTest.getListOfFavouriteItemsByUsername("fail");

        Assertions.assertEquals(1, listOfFavItemsByUsernamePass.size());
        Assertions.assertEquals(0, listOfFavItemsByUsernameFail.size());
    }
}
