package com.example.wearme_individualproject.controller;

import com.example.wearme_individualproject.DTOclasses.CustomCast_DTO;
import com.example.wearme_individualproject.DTOclasses.User_DTO;
import com.example.wearme_individualproject.emailSender.EmailSender;
import com.example.wearme_individualproject.exceptions.ExistingUserException;
import com.example.wearme_individualproject.exceptions.NotExistingUserException;
import com.example.wearme_individualproject.interfaces.IUserService;
import com.example.wearme_individualproject.logic.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.IOException;
import java.net.URI;

@RestController
@Slf4j
@RequestMapping("/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
public class SignUpController{

    private final IUserService userService;
    private final CustomCast_DTO castObject;
    private final EmailSender emailSender;
    private final AuthenticationManager authenticationManager;
    private static final String USERNOTFOUND = "User not found";
    private static final String USEREXISTS = "This customer already exists.";

    @GetMapping("/username/{username}")
    public ResponseEntity<User> getCustomerByUsername(@PathVariable(value = "username")String username) {
        try{
            User user = userService.getUserByUsername(username);
            return ResponseEntity.ok().body(user);
        }
        catch(NotExistingUserException e)
        {
            return new ResponseEntity(USERNOTFOUND,HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/signUp")
    public ResponseEntity<User> addCustomer(@RequestBody User_DTO userDTO)throws MessagingException, IOException{
        try {
            User user = castObject.castToUser(userDTO);
            userService.addNewUser(user);
            emailSender.sendConfirmationRegistrationEmail(user.getEmail());
            return new ResponseEntity(URI.create("customer" + "/" + user.getId()), HttpStatus.CREATED);
        }
        catch(ExistingUserException e){
            return new ResponseEntity(USEREXISTS, HttpStatus.CONFLICT);
        }
    }


}
