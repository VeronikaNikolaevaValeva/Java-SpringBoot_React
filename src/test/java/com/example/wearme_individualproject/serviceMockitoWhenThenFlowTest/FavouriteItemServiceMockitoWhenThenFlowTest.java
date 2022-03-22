package com.example.wearme_individualproject.serviceMockitoWhenThenFlowTest;
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
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;
import static org.mockito.Mockito.when;
@ActiveProfiles("test")
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class FavouriteItemServiceMockitoWhenThenFlowTest {

    @Mock
    private IFavouriteItemRepository favItemRepository;
    private IFavouriteItemService underTest;
    @Mock
    private IProductRepository productRepo;
    @Mock
    private IUserRepository userRepo;
    private IProductService productService;


    @BeforeEach
    void setUp(){
        underTest = new FavouriteItemService(favItemRepository, productService);
        User user = new User("test", "test", "test", "test",
                "test.test@test.test", LocalDate.parse("2001-02-07"), "+test(test)test", "test",
                "test", "test", "test", "test", AccountStatus.ACTIVE, com.example.wearme_individualproject.enumeration.Role.CUSTOMER);
        userRepo.save(user);
        Product product = new Product("test", ProductCategory.SNEAKERS, "test", "test",
                "test",
                ProductGender.UNISEX, 165, 125, ProductStatus.AVAILABLE, "some url");
        productRepo.save(product);
        List<FavouriteItem> testFavItems = List.of(
                new FavouriteItem(user, product)
        );
        when(underTest.getListOfAllFavouriteItemsItems()).thenReturn(testFavItems);
        when(underTest.getListOfFavouriteItemsByUsername("test")).thenReturn(testFavItems);
    }

    @Test
    void getAllFavItemsTest(){
        List<FavouriteItem> favItemsTestList = underTest.getListOfAllFavouriteItemsItems();
        Assertions.assertEquals(ProductCategory.SNEAKERS, favItemsTestList.get(0).getProduct().getProductCategory());
    }

    @Test
    void getAllFavItemsByUsernameTest(){
        List<FavouriteItem> favItemsTestList = underTest.getListOfFavouriteItemsByUsername("test");
        Assertions.assertEquals(ProductCategory.SNEAKERS, favItemsTestList.get(0).getProduct().getProductCategory());
    }
}
