package com.example.wearme_individualproject.serviceH2DatabaseTest;
import com.example.wearme_individualproject.enumeration.AccountStatus;
import com.example.wearme_individualproject.enumeration.ProductCategory;
import com.example.wearme_individualproject.enumeration.ProductGender;
import com.example.wearme_individualproject.enumeration.ProductStatus;
import com.example.wearme_individualproject.interfaces.*;
import com.example.wearme_individualproject.logic.*;
import com.example.wearme_individualproject.repository.*;
import com.example.wearme_individualproject.service.OrderService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;

@ActiveProfiles("test")
@SpringBootTest
class OrderServiceH2DatabaseTest {

    @Autowired
    private IOrderService underTest;
    @Autowired
    private IOrderInformationRepository orderRepo;
    @Autowired
    private IProductRepository productRepo;
    @Autowired
    private IUserRepository userRepo;
    @Autowired
    private IPaymentInformationRepository paymentRepo;
    @Autowired
    private IShoppingCartRepository shoppingCartRepo;

    @Autowired
    private IShoppingCartService shoppingCartService;
    @Autowired
    private IDiscountService discountService;
    @Autowired
    private IDiscountRepository discountRepo;
    @Autowired
    private IPurchasedProductService purchasedProductService;
    @Autowired
    private IPurchasedProductsRepository purchasedProductsRepo;

    @BeforeEach
    void setUp(){
        underTest = new OrderService(orderRepo, shoppingCartService, purchasedProductService, discountService);
    }

    @AfterEach
    void tearDown(){
        purchasedProductsRepo.deleteAll();
        orderRepo.deleteAll();
        shoppingCartRepo.deleteAll();
        discountRepo.deleteAll();
        paymentRepo.deleteAll();
        productRepo.deleteAll();
        userRepo.deleteAll();
    }

    @Test
    void addAndGetListOfAllOrders() {
        User user = new User("test", "test", "test", "test",
                "test.test@test.test", LocalDate.parse("2001-02-07"), "+test(test)test", "test",
                "test", "test", "test", "test", AccountStatus.ACTIVE, com.example.wearme_individualproject.enumeration.Role.CUSTOMER);
        userRepo.save(user);
        PaymentInformation paymentInfo = new PaymentInformation(user, "test", "test", "test", "test", "test");
        paymentRepo.save(paymentInfo);
        Product product = new Product("test", ProductCategory.SNEAKERS, "test", "test",
                "test",
                ProductGender.UNISEX, 100, 100, ProductStatus.AVAILABLE, "some url");
        productRepo.save(product);
        ShoppingCartItem item = new ShoppingCartItem(user, product);
        shoppingCartRepo.save(item);
        OrderInformation order = new OrderInformation(user, "test", paymentInfo, 0);
        underTest.addOrder(order);

        List<OrderInformation> listOfAllOrdersByUsernamePass = underTest.getOrderInformationByUsername("test");
        List<OrderInformation> listOfAllOrdersByUsernameFail = underTest.getOrderInformationByUsername("fail");
        List<OrderInformation> listOfAllOrders = underTest.getListOfALlOrderInformation();
        Assertions.assertEquals(1, listOfAllOrdersByUsernamePass.size());
        Assertions.assertEquals(0, listOfAllOrdersByUsernameFail.size());
        Assertions.assertEquals(1, listOfAllOrders.size());
    }


    @Test
    void addAndGetTotalPriceWithCouponCode(){
        User user = new User("test", "test", "test", "test",
                "test.test@test.test", LocalDate.parse("2001-02-07"), "+test(test)test", "test",
                "test", "test", "test", "test", AccountStatus.ACTIVE, com.example.wearme_individualproject.enumeration.Role.CUSTOMER);
        userRepo.save(user);
        PaymentInformation paymentInfo = new PaymentInformation(user, "test", "test", "test", "test", "test");
        paymentRepo.save(paymentInfo);
        Product product = new Product("test", ProductCategory.SNEAKERS, "test", "test",
                "test",
                ProductGender.UNISEX, 100, 100, ProductStatus.AVAILABLE, "some url");
        productRepo.save(product);
        ShoppingCartItem item = new ShoppingCartItem(user, product);
        shoppingCartRepo.save(item);
        Discount discount = new Discount("test", "test", ProductCategory.SNEAKERS, 10);
        discountService.addNewDiscount(discount);
        OrderInformation order = new OrderInformation(user, "test", paymentInfo, 0);
        underTest.createOrder(order, discount.getDiscount_code());
        List<OrderInformation> orderList = underTest.getOrderInformationByUsername("test");

        Assertions.assertEquals(90, orderList.get(0).getTotalPrice());
    }

}
