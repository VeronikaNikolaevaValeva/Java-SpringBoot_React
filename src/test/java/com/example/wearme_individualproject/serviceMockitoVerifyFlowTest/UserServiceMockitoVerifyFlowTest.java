package com.example.wearme_individualproject.serviceMockitoVerifyFlowTest;

import com.example.wearme_individualproject.enumeration.AccountStatus;
import com.example.wearme_individualproject.enumeration.Role;
import com.example.wearme_individualproject.exceptions.NotExistingUserException;
import com.example.wearme_individualproject.interfaces.IUserService;
import com.example.wearme_individualproject.repository.IUserRepository;
import com.example.wearme_individualproject.logic.User;
import com.example.wearme_individualproject.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
@ActiveProfiles("test")
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UserServiceMockitoVerifyFlowTest {

    @Mock
    private IUserRepository iUserRepository;
    private final PasswordEncoder passwordEncoder;
    private IUserService underTest;

    UserServiceMockitoVerifyFlowTest(){
        passwordEncoder = new BCryptPasswordEncoder();
    }

    @BeforeEach
    void setUp(){
       underTest = new UserService(iUserRepository, passwordEncoder);
    }


    @Test
    void findAllUsersTest(){
        //when
        underTest.getListOfAllCustomers();
        //then verify that the method was invoked
        verify(iUserRepository).findAll();
    }

    @Test
    void addNewUserTest(){
        //given
        User user = new User("test", "test", "test", "test",
                "test.test@test.test", LocalDate.parse("2001-02-07"), "+test(test)test", "test",
                "test", "test", "test", "test", AccountStatus.ACTIVE, Role.CUSTOMER);
        //when
        underTest.addNewUser(user);
        //then verify that the method was invoked. We want to 'capture' the actual user that was passed in this method
        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);
        verify(iUserRepository).save(userArgumentCaptor.capture());
        //Check if the captured value from the userRepo method id the same as the one we have passed to the userService
        User capturedUser = userArgumentCaptor.getValue();
        assertThat(capturedUser).isEqualTo(user);
    }

    @Test
    void getUserByUsernameTest() throws NotExistingUserException {
       try {
           //given
           User user = new User("test", "test", "test", "test",
                   "test.test@test.test", LocalDate.parse("2001-02-07"), "+test(test)test", "test",
                   "test", "test", "test", "test", AccountStatus.ACTIVE, Role.CUSTOMER);
           //when
           underTest.getUserByUsername(user.getUsername());
           //then verify that the method was invoked
           ArgumentCaptor<String> usernameArgumentCaptor = ArgumentCaptor.forClass(String.class);
           verify(iUserRepository).findByUsername(usernameArgumentCaptor.capture());

           String capturedUsername = usernameArgumentCaptor.getValue();
           assertThat(capturedUsername).isEqualTo(user.getUsername());
       }
       catch(NotExistingUserException e){}
    }
    @Test
    void getUserByIdTest() throws NotExistingUserException{
       try {
           //given
           User user = new User("test", "test", "test", "test",
                   "test.test@test.test", LocalDate.parse("2001-02-07"), "+test(test)test", "test",
                   "test", "test", "test", "test", AccountStatus.ACTIVE, Role.CUSTOMER);
           //when
           underTest.getUserById(user.getId());
           //then verify that the method was invoked
           ArgumentCaptor<Integer> idArgumentCaptor = ArgumentCaptor.forClass(Integer.class);
           verify(iUserRepository).findById(idArgumentCaptor.capture());

           int capturedId = idArgumentCaptor.getValue();
           assertThat(capturedId).isEqualTo(user.getId());
       }
       catch(NotExistingUserException e){}
    }
    @Test
    void deleteUserTest() throws NotExistingUserException{
       try {
           //given
           User user = new User("test", "test", "test", "test",
                   "test.test@test.test", LocalDate.parse("2001-02-07"), "+test(test)test", "test",
                   "test", "test", "test", "test", AccountStatus.ACTIVE, Role.CUSTOMER);
           //when
           underTest.deleteUser(user.getId());
           //then verify that the method was invoked
           ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);
           verify(iUserRepository).delete(userArgumentCaptor.capture());

           User capturedUser = userArgumentCaptor.getValue();
           assertThat(capturedUser).isEqualTo(user);
       }
       catch(NotExistingUserException e){}
    }
}
