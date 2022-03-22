package com.example.wearme_individualproject.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.wearme_individualproject.DTOclasses.User_DTO;
import com.example.wearme_individualproject.enumeration.Role;
import com.example.wearme_individualproject.exceptions.NotExistingUserException;
import com.example.wearme_individualproject.interfaces.IUserService;
import com.example.wearme_individualproject.logic.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import com.example.wearme_individualproject.enumeration.AccountStatus;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@Slf4j
@RequestMapping("/customer")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
public class CustomerServiceController {

    private final IUserService userService;
    private static final String REFRESHTOKNE = "Refresh token is missing.";
    private static final String USERNOTFOUND = "User not found.";

    @GetMapping("{id}")
    public ResponseEntity<User> getCustomerPath(@PathVariable(value = "id")int id) {
        try{
            User user = userService.getUserById(id);
            return ResponseEntity.ok().body(user);
        }
        catch(NotExistingUserException e)
        {
            return new ResponseEntity(USERNOTFOUND,HttpStatus.CONFLICT);
        }
    }

    @CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
    @GetMapping("/getLoggedUser")
    public ResponseEntity<User> getCustomerByUsername(Authentication authentication) {
        try{
            String username = authentication.getName();
            User user = userService.getUserByUsername(username);
            return ResponseEntity.ok().body(user);
        }
        catch(NotExistingUserException e)
        {
            return new ResponseEntity(USERNOTFOUND,HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/AllCustomers")
    public ResponseEntity<List<User>> getAllCustomers(){
        List<User> users = userService.getListOfAllCustomers();
        if(users != null){
            return ResponseEntity.ok().body(users);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/AccountStatus/{accountStatus}")
    public ResponseEntity<List<User>> getAllCustomersWithAccountStatus(@PathVariable(value = "accountStatus")String accountStatus) {
       List<User> users = null;
        if(accountStatus != null) {
            users = userService.getListOfAllUserWithAccountStatus(AccountStatus.valueOf(accountStatus));
        }
        if(users != null) {
            return ResponseEntity.ok().body(users);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
    @PostMapping("/UpdateUser")
    public ResponseEntity<User> updateUser(@RequestBody User_DTO user) {
        try{
            User currentUser = userService.getUserByUsername(user.getUsername());
            currentUser.setUsername(user.getUsername());
            currentUser.setFirstName(user.getFirstName());
            currentUser.setFamilyName(user.getFamilyName());
            currentUser.setCountry(user.getCountry());
            currentUser.setDateOfBirth(user.getDateOfBirth());
            currentUser.setEmail(user.getEmail());
            currentUser.setStreetAddress(user.getStreetAddress());
            currentUser.setStreetNumber(user.getStreetNumber());
            currentUser.setTown(user.getTown());
            currentUser.setZipCode(user.getZipCode());
            currentUser.setTelephoneNumber(user.getTelephoneNumber());
            userService.updateUser(currentUser);
            return ResponseEntity.noContent().build();
        }
        catch(NotExistingUserException e){
            return new ResponseEntity(USERNOTFOUND,HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteCustomer(@PathVariable int id){
        try{
            userService.deleteUser(id);
            return ResponseEntity.ok().build();
        }
        catch(NotExistingUserException e)
        {
            return new ResponseEntity(USERNOTFOUND,HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorisationHeader = request.getHeader(AUTHORIZATION);
        if(authorisationHeader!=null && authorisationHeader.startsWith("Bearer ")){
            try{
                String refreshToken = authorisationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refreshToken);
                String username = decodedJWT.getSubject();
                User user = userService.getUserByUsername(username);

                Collection<Role> userRoles = new ArrayList<>();
                userRoles.add(user.getRole());
                String accessToken = JWT.create().withSubject(user.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() +1000*60*1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", userRoles.stream().map(Role::name).collect(Collectors.toList()))
                        .sign(algorithm);

                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", accessToken);
                tokens.put("refresh_token", refreshToken);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
             }
            catch(Exception e){
                response.setHeader("error", e.getMessage());
                response.setStatus(FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put("error_message", e.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        }
        else{
            throw new RuntimeException(REFRESHTOKNE);
        }
    }





}
