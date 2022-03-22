package com.example.wearme_individualproject.serviceMockitoWhenThenFlowTest;
import com.example.wearme_individualproject.enumeration.*;
import com.example.wearme_individualproject.interfaces.IUserService;
import com.example.wearme_individualproject.logic.User;
import com.example.wearme_individualproject.repository.IUserRepository;
import com.example.wearme_individualproject.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@ActiveProfiles("test")
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UserServiceMockitoWhenThenFlowTest {

    @Mock
    private IUserRepository iUserRepository;
    private final PasswordEncoder passwordEncoder;
    private IUserService underTest;

    UserServiceMockitoWhenThenFlowTest(){
        passwordEncoder = new BCryptPasswordEncoder();
    }

    @BeforeEach
    void setUp(){
        underTest = new UserService(iUserRepository, passwordEncoder);
        List<User> testUsers= List.of(
                new User("test", "test", "testOne", "test",
                        "test.test@test.test", LocalDate.parse("2001-02-07"), "+test(test)test", "test",
                        "test", "test", "test", "test", AccountStatus.ACTIVE, Role.CUSTOMER),
                new User("test", "test", "testTwo", "test",
                        "test.test@test.test", LocalDate.parse("2001-02-07"), "+test(test)test", "test",
                        "test", "test", "test", "test", AccountStatus.ACTIVE, Role.CUSTOMER)
        );
        when(underTest.getListOfAllCustomers()).thenReturn(testUsers);
        when(underTest.getListOfAllUserWithAccountStatus(AccountStatus.ACTIVE)).thenReturn(testUsers);

    }

    @Test
    void getListOfALlCustomersTest(){
        List<User> userTestList = underTest.getListOfAllCustomers();
        Assertions.assertEquals("testOne", userTestList.get(0).getUsername());
        Assertions.assertEquals("testTwo", userTestList.get(1).getUsername());
    }

    @Test
    void getListOfAllUsersWithAccountStatusTest(){
        List<User> userTestList = underTest.getListOfAllUserWithAccountStatus(AccountStatus.ACTIVE);
        Assertions.assertEquals("testOne", userTestList.get(0).getUsername());
        Assertions.assertEquals("testTwo", userTestList.get(1).getUsername());
    }

}
