package com.example.wearme_individualproject.serviceMockitoWhenThenFlowTest;
import com.example.wearme_individualproject.enumeration.AccountStatus;
import com.example.wearme_individualproject.interfaces.IPaymentInformationService;
import com.example.wearme_individualproject.logic.PaymentInformation;
import com.example.wearme_individualproject.logic.User;
import com.example.wearme_individualproject.repository.IPaymentInformationRepository;
import com.example.wearme_individualproject.repository.IUserRepository;
import com.example.wearme_individualproject.service.PaymentInformationService;
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
class PaymentInformationServiceMockitoWhenThenFlowTest {

    @Mock
    private IPaymentInformationRepository paymentRepo;
    private IPaymentInformationService underTest;
    @Mock
    private IUserRepository userRepo;

    @BeforeEach
    void setUp(){
        underTest = new PaymentInformationService(paymentRepo);
        User user1 = new User("test", "test", "test1", "test",
                "test.test@test.test", LocalDate.parse("2001-02-07"), "+test(test)test", "test",
                "test", "test", "test", "test", AccountStatus.ACTIVE, com.example.wearme_individualproject.enumeration.Role.CUSTOMER);
        userRepo.save(user1);
        List<PaymentInformation> paymentInfo = List.of(
                new PaymentInformation(user1, "test1", "test", "test", "test", "test")
        );
        when(underTest.getListOfAllPaymentInformation()).thenReturn(paymentInfo);
    }

    @Test
    void getAllPaymentInfoTest(){
        List<PaymentInformation> paymentInfoTestList = underTest.getListOfAllPaymentInformation();
        Assertions.assertEquals("test1", paymentInfoTestList.get(0).getCardType());
    }
}
