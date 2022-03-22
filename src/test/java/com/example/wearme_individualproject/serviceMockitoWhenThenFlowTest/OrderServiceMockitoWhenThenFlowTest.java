package com.example.wearme_individualproject.serviceMockitoWhenThenFlowTest;
import com.example.wearme_individualproject.enumeration.AccountStatus;
import com.example.wearme_individualproject.enumeration.ProductCategory;
import com.example.wearme_individualproject.enumeration.ProductGender;
import com.example.wearme_individualproject.enumeration.ProductStatus;
import com.example.wearme_individualproject.interfaces.*;
import com.example.wearme_individualproject.logic.*;
import com.example.wearme_individualproject.repository.*;
import com.example.wearme_individualproject.service.OrderService;
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
class OrderServiceMockitoWhenThenFlowTest {

    @Mock
    private IOrderInformationRepository orderRepo;
    private IOrderService underTest;
    @Mock
    private IPaymentInformationRepository paymentRepo;
    @Mock
    private IUserRepository userRepo;
    @Mock
    private IProductRepository productRepo;
    @Mock
    private IShoppingCartRepository shoppingCartRepo;
    private IShoppingCartService shoppingCartService;
    private IPurchasedProductService purchasedProductService;
    private IDiscountService discountService;

    @BeforeEach
    void setUp(){
        underTest = new OrderService(orderRepo, shoppingCartService, purchasedProductService, discountService);
        User user = new User("test", "test", "test", "test",
                "test.test@test.test", LocalDate.parse("2001-02-07"), "+test(test)test", "test",
                "test", "test", "test", "test", AccountStatus.ACTIVE, com.example.wearme_individualproject.enumeration.Role.CUSTOMER);
        userRepo.save(user);
        PaymentInformation paymentInfo = new PaymentInformation(user, "test", "test", "test", "test", "test");
        paymentRepo.save(paymentInfo);
        Product product1 = new Product("product1", ProductCategory.SNEAKERS, "test", "test",
                "test",
                ProductGender.UNISEX, 100, 100, ProductStatus.AVAILABLE, "some url");
        productRepo.save(product1);
        ShoppingCartItem item1 = new ShoppingCartItem(user, product1);
        shoppingCartRepo.save(item1);
        List<OrderInformation> orderInfo = List.of(
                new OrderInformation(user, "test", paymentInfo, 0)
        );
        when(underTest.getListOfALlOrderInformation()).thenReturn(orderInfo);
        when(underTest.getOrderInformationByUsername("test")).thenReturn(orderInfo);
    }

    @Test
    void getAllOrderInfoTest(){
        List<OrderInformation> orderInfoListTest = underTest.getListOfALlOrderInformation();
        Assertions.assertEquals(0, orderInfoListTest.get(0).getTotalPrice());
    }
    @Test
    void getOrderInformationByUsername(){
        List<OrderInformation> orderInfoListTest = underTest.getOrderInformationByUsername("test");
        Assertions.assertEquals(0, orderInfoListTest.get(0).getTotalPrice());
    }

}
