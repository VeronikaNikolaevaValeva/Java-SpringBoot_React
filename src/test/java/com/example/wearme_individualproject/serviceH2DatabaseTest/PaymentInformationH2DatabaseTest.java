package com.example.wearme_individualproject.serviceH2DatabaseTest;
import com.example.wearme_individualproject.enumeration.AccountStatus;
import com.example.wearme_individualproject.interfaces.*;
import com.example.wearme_individualproject.logic.*;
import com.example.wearme_individualproject.repository.*;
import com.example.wearme_individualproject.service.PaymentInformationService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;

@ActiveProfiles("test")
@SpringBootTest
class PaymentInformationH2DatabaseTest {

    @Autowired
    private IPaymentInformationRepository paymentRepo;
    @Autowired
    private IPaymentInformationService underTest;
    @Autowired
    private IUserRepository userRepo;

    @BeforeEach
    void setUp(){
        underTest = new PaymentInformationService(paymentRepo);
    }

    @AfterEach
    void tearDown(){
        paymentRepo.deleteAll();
        userRepo.deleteAll();
    }

    @Test
    void addAndGetListOfAllOrders() {
        User user = new User("test", "test", "test", "test",
                "test.test@test.test", LocalDate.parse("2001-02-07"), "+test(test)test", "test",
                "test", "test", "test", "test", AccountStatus.ACTIVE, com.example.wearme_individualproject.enumeration.Role.CUSTOMER);
        userRepo.save(user);
        PaymentInformation paymentInfo = new PaymentInformation(user, "test", "test", "test", "test", "test");
        underTest.addPaymentInformation(paymentInfo);

        List<PaymentInformation> listOfAllPaymentInformation = underTest.getListOfAllPaymentInformation();
        PaymentInformation findPaymentInfoByUsername = underTest.getPaymentInformationByUsername("test");
        Assertions.assertEquals("test", findPaymentInfoByUsername.getCardType());
        Assertions.assertEquals(1, listOfAllPaymentInformation.size());
        underTest.deletePaymentInformationById(paymentInfo.getId());
        listOfAllPaymentInformation = underTest.getListOfAllPaymentInformation();
        Assertions.assertEquals(0, listOfAllPaymentInformation.size());

    }


    @Test
    void updatePaymentInfoInformation(){
        User user = new User("test", "test", "test", "test",
                "test.test@test.test", LocalDate.parse("2001-02-07"), "+test(test)test", "test",
                "test", "test", "test", "test", AccountStatus.ACTIVE, com.example.wearme_individualproject.enumeration.Role.CUSTOMER);
        userRepo.save(user);
        PaymentInformation paymentInfo = new PaymentInformation(user, "test1", "test", "test", "test", "test");
        underTest.addPaymentInformation(paymentInfo);
        Assertions.assertEquals("test1", paymentInfo.getCardType());
        underTest.updatePaymentInfoInformation("test", "test2", "test2", "test2", "test", "test");
        PaymentInformation paymentInformation = underTest.getPaymentInformationByUsername("test");
        Assertions.assertEquals("test2",paymentInformation.getCardType());
    }

}
