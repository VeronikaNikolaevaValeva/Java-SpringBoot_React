package com.example.wearme_individualproject.controller;

import com.example.wearme_individualproject.DTOclasses.CustomCast_DTO;
import com.example.wearme_individualproject.DTOclasses.User_DTO;
import com.example.wearme_individualproject.exceptions.ExistingUserException;
import com.example.wearme_individualproject.exceptions.NotExistingUserException;
import com.example.wearme_individualproject.interfaces.IUserService;
import com.example.wearme_individualproject.logic.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/communityManager")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
public class CommunityManagerServiceController {

    @Autowired
    private final IUserService userService;
    private final CustomCast_DTO castObject;
    private static final String USERNOTFOUND = "User not found.";
    private static final  String USEREXISTS = "This manager already exists";

    @GetMapping("{id}")
    public ResponseEntity<User> getMaintainerPath(@PathVariable(value = "id")int id) {
        User manager = userService.getUserById(id);
        if(manager != null) {
            return ResponseEntity.ok().body(manager);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<User> getManagerByUsername(@PathVariable(value = "username")String username) {
        try{
            User user = userService.getUserByUsername(username);
            return ResponseEntity.ok().body(user);
        }
        catch(NotExistingUserException e)
        {
            return new ResponseEntity(USERNOTFOUND,HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/AllManagers")
    public ResponseEntity<List<User>> getAllManagers(){
        List<User> managers = userService.getListOfAllCommunityManagers();
        if(managers != null){
            return ResponseEntity.ok().body(managers);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping()
    public ResponseEntity<User> createManager(@RequestBody User_DTO userDTO){
       try{
           User user = castObject.castToUser(userDTO);
           userService.addNewUser(user);
           return new ResponseEntity(URI.create("manager" + "/" + user.getId()),HttpStatus.CREATED);
       }
       catch(ExistingUserException e) {
           return new ResponseEntity(USEREXISTS,HttpStatus.CONFLICT);
       }
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteManager(@PathVariable int id){
        try{
            userService.deleteUser(id);
            return ResponseEntity.ok().build();
        }
        catch(NotExistingUserException e){
            return new ResponseEntity(USERNOTFOUND,HttpStatus.CONFLICT);
        }

    }



}
