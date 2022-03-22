package com.example.wearme_individualproject.serviceH2DatabaseTest;
import com.example.wearme_individualproject.enumeration.AccountStatus;
import com.example.wearme_individualproject.enumeration.Role;
import com.example.wearme_individualproject.interfaces.IUserService;
import com.example.wearme_individualproject.logic.User;
import com.example.wearme_individualproject.repository.IUserRepository;
import com.example.wearme_individualproject.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import javax.mail.MessagingException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@ActiveProfiles("test")
@SpringBootTest
class UserServiceH2DatabaseTest {

    @Autowired
    private IUserRepository iUserRepository;
    private final PasswordEncoder passwordEncoder;
    private IUserService underTest;

    UserServiceH2DatabaseTest(){
        passwordEncoder = new BCryptPasswordEncoder();
    }

    @BeforeEach
    void setUp(){
        underTest = new UserService(iUserRepository, passwordEncoder);
    }

//    @Test
//     void getUserByUsername() throws MessagingException, IOException{
//        User user = new User("test", "test", "test", "test",
//                "test.test@test.test", LocalDate.parse("2001-02-07"), "+test(test)test", "test",
//                "test", "test", "test", "test", AccountStatus.ACTIVE, Role.CUSTOMER);
//        underTest.addNewUser(user);
//        User capturedUser = underTest.getUserByUsername("test");
//        Assertions.assertEquals(capturedUser.getUsername(), "test");
//        underTest.deleteUser(user.getId());
//    }

    @Test
     void getListOfAllUsers()throws MessagingException, IOException{
        User userTestOne = new User("test", "test", "testOne", "test",
                "test.test@test.test", LocalDate.parse("2001-02-07"), "+test(test)test", "test",
                "test", "test", "test", "test", AccountStatus.ACTIVE, Role.CUSTOMER);
        User userTestTwo = new User("test", "test", "testTwo", "test",
                "test.test@test.test", LocalDate.parse("2001-02-07"), "+test(test)test", "test",
                "test", "test", "test", "test", AccountStatus.ACTIVE, Role.CUSTOMER);
        User communityManagerTest = new User("test", "test", "communityManager", "test",
                "test.test@test.test", LocalDate.parse("2001-02-07"), "+test(test)test", "test",
                "test", "test", "test", "test", AccountStatus.ACTIVE, Role.COMMUNITYMANAGER);
        User salesManagerTest = new User("test", "test", "salesManager", "test",
                "test.test@test.test", LocalDate.parse("2001-02-07"), "+test(test)test", "test",
                "test", "test", "test", "test", AccountStatus.ACTIVE, Role.SALESMANAGER);
        underTest.addNewUser(userTestOne);
        underTest.addNewUser(userTestTwo);
        underTest.addNewUser(communityManagerTest);
        underTest.addNewUser(salesManagerTest);
        List<User> communityManagersListTest = underTest.getListOfAllCommunityManagers();
        List<User> salesManagersListTest = underTest.getListOfAllSalesManagers();
        Assertions.assertEquals(Role.COMMUNITYMANAGER, communityManagersListTest.get(0).getRole());
        Assertions.assertEquals(Role.SALESMANAGER, salesManagersListTest.get(0).getRole());
        underTest.deleteUser(userTestOne.getId());
        underTest.deleteUser(userTestTwo.getId());
        underTest.deleteUser(communityManagerTest.getId());
        underTest.deleteUser(salesManagerTest.getId());

    }

    @Test
     void deleteUser()throws MessagingException, IOException {
        User salesManagerTest = new User("test", "test", "salesManagerr", "test",
                "test.test@test.test", LocalDate.parse("2001-02-07"), "+test(test)test", "test",
                "test", "test", "test", "test", AccountStatus.ACTIVE, Role.SALESMANAGER);
        underTest.addNewUser(salesManagerTest);
        List<User> salesManagersListTest = underTest.getListOfAllSalesManagers();
        Assertions.assertEquals(1, salesManagersListTest.size());
        underTest.deleteUser(salesManagerTest.getId());
        salesManagersListTest = underTest.getListOfAllSalesManagers();
        Assertions.assertEquals(0, salesManagersListTest.size());
    }


}
