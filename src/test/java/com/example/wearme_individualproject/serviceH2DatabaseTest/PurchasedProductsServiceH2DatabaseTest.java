package com.example.wearme_individualproject.serviceH2DatabaseTest;
import com.example.wearme_individualproject.enumeration.AccountStatus;
import com.example.wearme_individualproject.enumeration.ProductCategory;
import com.example.wearme_individualproject.enumeration.ProductGender;
import com.example.wearme_individualproject.enumeration.ProductStatus;
import com.example.wearme_individualproject.interfaces.*;
import com.example.wearme_individualproject.logic.*;
import com.example.wearme_individualproject.repository.*;
import com.example.wearme_individualproject.service.PurchasedProductService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;

@ActiveProfiles("test")
@SpringBootTest
class PurchasedProductsServiceH2DatabaseTest {

    @Autowired
    private IPurchasedProductsRepository purchasedProductsRepo;
    @Autowired
    private IPurchasedProductService underTest;
    @Autowired
    private IPaymentInformationRepository paymentRepo;
    @Autowired
    private IOrderInformationRepository orderRepo;
    @Autowired
    private IProductRepository productRepo;
    @Autowired
    private IUserRepository userRepo;

    @BeforeEach
    void setUp(){
        underTest = new PurchasedProductService(purchasedProductsRepo);
    }

    @Test
    void addAndGetListOfAllOrders() {
        User user = new User("test", "test", "test", "test",
                "test.test@test.test", LocalDate.parse("2001-02-07"), "+test(test)test", "test",
                "test", "test", "test", "test", AccountStatus.ACTIVE, com.example.wearme_individualproject.enumeration.Role.CUSTOMER);
        userRepo.save(user);
        Product product = new Product("test", ProductCategory.SNEAKERS, "test", "test",
                "test",
                ProductGender.UNISEX, 165, 125, ProductStatus.AVAILABLE, "some url");
        productRepo.save(product);
        PaymentInformation paymentInfo = new PaymentInformation(user, "test", "test", "test", "test", "test");
        paymentRepo.save(paymentInfo);
        OrderInformation order = new OrderInformation(user, "test", paymentInfo, 0);
        orderRepo.save(order);
        PurchasedProducts purchasedProducts = new PurchasedProducts(user, product, order);
        underTest.addPurchasedProduct(purchasedProducts);

        List<PurchasedProducts> listOfAllPurchasedProducts = underTest.getListOfAllPurchasedProducts();
        Assertions.assertEquals(1, listOfAllPurchasedProducts.size());
        underTest.deletePurchasedProduct(purchasedProducts);
    }

    @Test
    void addAndGetListOfAllOrdersByUsername() {
        User user = new User("test", "test", "test", "test",
                "test.test@test.test", LocalDate.parse("2001-02-07"), "+test(test)test", "test",
                "test", "test", "test", "test", AccountStatus.ACTIVE, com.example.wearme_individualproject.enumeration.Role.CUSTOMER);
        userRepo.save(user);
        Product product = new Product("test", ProductCategory.SNEAKERS, "test", "test",
                "test",
                ProductGender.UNISEX, 165, 125, ProductStatus.AVAILABLE, "some url");
        productRepo.save(product);
        PaymentInformation paymentInfo = new PaymentInformation(user, "test", "test", "test", "test", "test");
        paymentRepo.save(paymentInfo);
        OrderInformation order = new OrderInformation(user, "test", paymentInfo, 0);
        orderRepo.save(order);
        PurchasedProducts purchasedProducts = new PurchasedProducts(user, product, order);
        underTest.addPurchasedProduct(purchasedProducts);

        List<PurchasedProducts> findPaymentInfoByUsername = underTest.getListOfAllPurchasedProductsByUsername("test");
        Assertions.assertEquals(1, findPaymentInfoByUsername.size());
        underTest.deletePurchasedProduct(purchasedProducts);
    }

    @Test
    void deleteOrder() {
        User user = new User("test", "test", "test", "test",
                "test.test@test.test", LocalDate.parse("2001-02-07"), "+test(test)test", "test",
                "test", "test", "test", "test", AccountStatus.ACTIVE, com.example.wearme_individualproject.enumeration.Role.CUSTOMER);
        userRepo.save(user);
        Product product = new Product("test", ProductCategory.SNEAKERS, "test", "test",
                "test",
                ProductGender.UNISEX, 165, 125, ProductStatus.AVAILABLE, "some url");
        productRepo.save(product);
        PaymentInformation paymentInfo = new PaymentInformation(user, "test", "test", "test", "test", "test");
        paymentRepo.save(paymentInfo);
        OrderInformation order = new OrderInformation(user, "test", paymentInfo, 0);
        orderRepo.save(order);
        PurchasedProducts purchasedProducts = new PurchasedProducts(user, product, order);
        underTest.addPurchasedProduct(purchasedProducts);
        underTest.deletePurchasedProduct(purchasedProducts);
        List<PurchasedProducts> listOfAllPurchasedProducts = underTest.getListOfAllPurchasedProducts();
        Assertions.assertEquals(0, listOfAllPurchasedProducts.size());

    }

}
