package com.example.wearme_individualproject.controller;

import com.example.wearme_individualproject.DTOclasses.CustomCast_DTO;
import com.example.wearme_individualproject.DTOclasses.Discount_DTO;
import com.example.wearme_individualproject.enumeration.ProductCategory;
import com.example.wearme_individualproject.interfaces.IDiscountService;
import com.example.wearme_individualproject.logic.Discount;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/discount")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
public class DiscountController {

    private final IDiscountService discountService;
    private final CustomCast_DTO castObject;
    private final String DISCOUNTADDED = "Discount was added";
    private final String DISCOUNTREMOVED = "Discount was removed";

    @PostMapping("/addDiscount")
    public ResponseEntity addShoppingCartItem(@RequestBody Discount_DTO discountDTO){
        Discount discount = castObject.castToDiscount(discountDTO);
        discountService.addNewDiscount(discount);
        return new ResponseEntity(DISCOUNTADDED, HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteDiscount/{discountId}")
    public ResponseEntity addShoppingCartItem(@PathVariable(value = "discountId")String discountId){
        discountService.deleteDiscount(Integer.valueOf(discountId));
        return new ResponseEntity(DISCOUNTREMOVED, HttpStatus.CREATED);
    }

    @GetMapping("/AllDiscounts")
    public ResponseEntity<List<Discount>> getListOfALlDiscount(){
        List<Discount> discountList = discountService.getListOfAllDiscounts();
        if(discountList != null){
            return ResponseEntity.ok().body(discountList);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/AllDiscountsByCategory")
    public ResponseEntity<List<Discount>> getListOfALlDiscountByProductCategory(ProductCategory category){
        List<Discount> discountList = discountService.getListOfAllDiscountByProductCategory(category);
        if(discountList != null){
            return ResponseEntity.ok().body(discountList);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

}
