package com.example.wearme_individualproject.interfaces;

import com.example.wearme_individualproject.enumeration.AccountStatus;
import com.example.wearme_individualproject.logic.User;
import org.springframework.security.core.userdetails.UserDetails;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

public interface IUserService {

     void addNewUser(User user);

     UserDetails loadUserByUsername(String username);

     boolean existingUser(int id);

     User getUserById(int id);

     User getUserByUsername(String username);

     void updateUser(User user);

     void deleteUser(int id);

     List<User> getListOfAllCustomers();

     List<User> getListOfAllCommunityManagers();

     List<User> getListOfAllSalesManagers();

     List<User> getListOfAllUserWithAccountStatus(AccountStatus accountStatus);

}
