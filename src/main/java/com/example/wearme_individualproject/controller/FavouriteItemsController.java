package com.example.wearme_individualproject.controller;

import com.example.wearme_individualproject.exceptions.NotExistingUserException;
import com.example.wearme_individualproject.interfaces.IFavouriteItemService;
import com.example.wearme_individualproject.interfaces.IUserService;
import com.example.wearme_individualproject.logic.FavouriteItem;
import com.example.wearme_individualproject.logic.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/favouriteItem")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
public class FavouriteItemsController {


    private final IFavouriteItemService favouriteItemService;
    private final IUserService userService;
    private static final  String ADDEDITEM = "item added to favourites";
    private static final  String REMOVEDITEM = "item deleted from favourites";

    @GetMapping("/AllFavouriteItems/{username}")
    public ResponseEntity<List<FavouriteItem>> getListOfAllUsersFavouriteItems(@PathVariable(value = "username")String username) {
        List<FavouriteItem> items = favouriteItemService.getListOfFavouriteItemsByUsername(username);
        if(items != null){
            return ResponseEntity.ok().body(items);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<FavouriteItem> getFavouriteItem(@PathVariable(value = "id")int id) {
        try{
            FavouriteItem favouriteItem = favouriteItemService.getFavouriteItemById(id);
            return ResponseEntity.ok().body(favouriteItem);
        }
        catch(NotExistingUserException e)
        {
            return new ResponseEntity("Favourite item not found", HttpStatus.CONFLICT);
        }
    }

    @CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
    @DeleteMapping("/deleteItem/{favouriteItemId}")
    public ResponseEntity<Product> deleteFavouriteItem(@PathVariable(value = "favouriteItemId")int favouriteItemId){
        try {
            favouriteItemService.deleteItemFromUsersFsavouriteItems(favouriteItemId);
            return new ResponseEntity(REMOVEDITEM, HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/addItem/{username}/{productID}")
    public ResponseEntity addFavouriteItem(@PathVariable(value = "username")String username, @PathVariable(value = "productID")String productId){
        favouriteItemService.addItemToUsersFavouriteItems(userService.getUserByUsername(username), Integer.parseInt(productId));
        return new ResponseEntity(ADDEDITEM, HttpStatus.CREATED);
    }
}
