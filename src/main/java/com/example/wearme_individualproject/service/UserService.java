package com.example.wearme_individualproject.service;

import com.example.wearme_individualproject.enumeration.AccountStatus;
import com.example.wearme_individualproject.exceptions.ExistingUserException;
import com.example.wearme_individualproject.exceptions.NotExistingUserException;
import com.example.wearme_individualproject.interfaces.IUserService;
import com.example.wearme_individualproject.repository.IUserRepository;
import com.example.wearme_individualproject.logic.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserService implements IUserService, UserDetailsService {

    private final IUserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if(user == null) {
            throw new NotExistingUserException();
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().toString()));
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(), authorities);
    }


    @Override
    public void updateUser(User user){
        userRepo.save(user);
    }

    @Override
    public void addNewUser(User user){
        if(userRepo.findByUsername(user.getUsername())!=null){
            throw new ExistingUserException();
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
    }

    @Override
    public boolean existingUser(int id) {
        User user =userRepo.findById(id);
        if(user == null){
            return false;
        }
        return true;
    }

    @Override
    public User getUserById(int id) {
        User user = userRepo.findById(id);
        if(userRepo.findById(id) == null){
            throw new NotExistingUserException();
        }
        return user;
    }

    @Override
    public User getUserByUsername(String username){
        User user = userRepo.findByUsername(username);
        if(user == null){
            throw new NotExistingUserException();
        }
        return user;
    }

    @Override
    public void deleteUser(int id) {
        User user = userRepo.findById(id);
        if(user==null) {
            throw new NotExistingUserException();
        }
        userRepo.delete(user);
    }

    @Override
    public List<User> getListOfAllCustomers() {
        List<User> filteredList = new ArrayList<>();
        for(User user : userRepo.findAll()){
            if(user.getRole()== com.example.wearme_individualproject.enumeration.Role.CUSTOMER){
                filteredList.add(user);
            }
        }
        return filteredList;
    }

    @Override
    public List<User> getListOfAllSalesManagers() {
        List<User> filteredList = new ArrayList<>();
        for(User user : userRepo.findAll()){
            if(user.getRole()== com.example.wearme_individualproject.enumeration.Role.SALESMANAGER){
                filteredList.add(user);
            }
        }
        return filteredList;
    }

    @Override
    public List<User> getListOfAllCommunityManagers() {
        List<User> filteredList = new ArrayList<>();
        for(User user : userRepo.findAll()){
            if(user.getRole()== com.example.wearme_individualproject.enumeration.Role.COMMUNITYMANAGER){
                filteredList.add(user);
            }
        }
        return filteredList;
    }

    @Override
    public List<User> getListOfAllUserWithAccountStatus(AccountStatus accountStatus) {
        List<User> filteredList = new ArrayList<>();
        for (User user : userRepo.findAll()) {
            if (user.getAccountStatus().equals(accountStatus))
                filteredList.add(user);
        }
        return filteredList;
    }

}
