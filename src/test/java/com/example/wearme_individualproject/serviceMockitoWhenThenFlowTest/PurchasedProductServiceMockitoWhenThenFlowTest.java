package com.example.wearme_individualproject.serviceMockitoWhenThenFlowTest;
import com.example.wearme_individualproject.enumeration.AccountStatus;
import com.example.wearme_individualproject.enumeration.ProductCategory;
import com.example.wearme_individualproject.enumeration.ProductGender;
import com.example.wearme_individualproject.enumeration.ProductStatus;
import com.example.wearme_individualproject.interfaces.*;
import com.example.wearme_individualproject.logic.*;
import com.example.wearme_individualproject.repository.*;
import com.example.wearme_individualproject.service.PurchasedProductService;
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
class PurchasedProductServiceMockitoWhenThenFlowTest {

    @Mock
    private IPurchasedProductsRepository purchasedProductRepo;
    private IPurchasedProductService underTest;
    @Mock
    private IPaymentInformationRepository paymentRepo;
    @Mock
    private IUserRepository userRepo;
    @Mock
    private IProductRepository productRepo;
    @Mock
    private IOrderInformationRepository orderRepo;

    @BeforeEach
    void setUp(){
        underTest = new PurchasedProductService(purchasedProductRepo);
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
        List<PurchasedProducts> orderInfo = List.of(
                new PurchasedProducts(user, product, order)
        );
        when(underTest.getListOfAllPurchasedProducts()).thenReturn(orderInfo);
        when(underTest.getListOfAllPurchasedProductsByUsername("test")).thenReturn(orderInfo);
    }

    @Test
    void getAllPurchasedProductInfoTest(){
        List<PurchasedProducts> purchasedProductsList = underTest.getListOfAllPurchasedProducts();
        Assertions.assertEquals("test", purchasedProductsList.get(0).getProduct().getBrand());
    }
    @Test
    void getListOfAllPurchasedProductsByUsername(){
        List<PurchasedProducts> purchasedProductsList = underTest.getListOfAllPurchasedProductsByUsername("test");
        Assertions.assertEquals("test", purchasedProductsList.get(0).getProduct().getBrand());
    }

}
