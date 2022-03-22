package com.example.wearme_individualproject.controller;

import com.example.wearme_individualproject.exceptions.NotExistingUserException;
import com.example.wearme_individualproject.interfaces.IShoppingCartService;
import com.example.wearme_individualproject.interfaces.IUserService;
import com.example.wearme_individualproject.logic.Product;
import com.example.wearme_individualproject.logic.ShoppingCartItem;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/shoppingCart")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
public class ShoppingCartController {

    private final IShoppingCartService shoppingCartService;
    private final IUserService userService;
    private static final  String ADDEDITEM = "shopping cart item added";
    private static final  String REMOVEDITEM = "shopping cart item deleted";

    @GetMapping("/AllShoppingCartItems/{username}")
    public ResponseEntity<List<ShoppingCartItem>> getListOfAllUsersSHoppingCartItems(@PathVariable(value = "username")String username) {
        List<ShoppingCartItem> items = shoppingCartService.getListOfShoppingCartItemsByUsername(username);
        if(items != null){
            return ResponseEntity.ok().body(items);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<ShoppingCartItem> getShoppingCartItem(@PathVariable(value = "id")int id) {
        try{
            ShoppingCartItem shoppingCartItem = shoppingCartService.getSHoppingCartItemById(id);
            return ResponseEntity.ok().body(shoppingCartItem);
        }
        catch(NotExistingUserException e)
        {
            return new ResponseEntity("ShoppingCartItem not found",HttpStatus.CONFLICT);
        }
    }

    @CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
    @DeleteMapping("/deleteItem/{shoppingCartItemId}")
    public ResponseEntity<Product> deleteShoppingCartItem(@PathVariable(value = "shoppingCartItemId")int shoppingCartItemId){
        try {
            shoppingCartService.deleteItemFromUsersShoppingCart(shoppingCartItemId);
            return new ResponseEntity(REMOVEDITEM, HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.CONFLICT);
        }

    }

    @PostMapping("/addItem/{username}/{productID}")
    public ResponseEntity<Product> addShoppingCartItem(@PathVariable(value = "username")String username, @PathVariable(value = "productID")String productId){
         shoppingCartService.addItemToUsersShoppingCart(userService.getUserByUsername(username), Integer.parseInt(productId));
            return new ResponseEntity(ADDEDITEM, HttpStatus.CREATED);
    }
}
