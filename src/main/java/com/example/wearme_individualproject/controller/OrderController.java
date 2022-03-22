package com.example.wearme_individualproject.controller;

import com.example.wearme_individualproject.DTOclasses.CustomCast_DTO;
import com.example.wearme_individualproject.DTOclasses.OrderInformation_DTO;
import com.example.wearme_individualproject.emailSender.EmailSender;
import com.example.wearme_individualproject.exceptions.NotExistingUserException;
import com.example.wearme_individualproject.interfaces.IOrderService;
import com.example.wearme_individualproject.interfaces.IPaymentInformationService;
import com.example.wearme_individualproject.interfaces.IPurchasedProductService;
import com.example.wearme_individualproject.interfaces.IUserService;
import com.example.wearme_individualproject.logic.OrderInformation;
import com.example.wearme_individualproject.logic.PurchasedProducts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/order")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
public class OrderController {

    private final IOrderService orderService;
    private final IUserService userService;
    private final IPurchasedProductService purchasedProducts;
    private final EmailSender emailSender;
    private final CustomCast_DTO castObject;
    private final IPaymentInformationService paymentInformationService;
    private static final  String ADDEDORDER = "order made";

    @GetMapping("/AllOrders/{username}")
    public ResponseEntity<List<OrderInformation>> getListOfAllOrders(@PathVariable(value = "username")String username) {
        List<OrderInformation> orders = orderService.getOrderInformationByUsername(username);
        if(orders != null){
            return ResponseEntity.ok().body(orders);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/Products/{username}")
    public ResponseEntity<List<PurchasedProducts>> getListOfProductsInOrder(@PathVariable(value = "username")String username) {
        List<PurchasedProducts> orders = purchasedProducts.getListOfAllPurchasedProductsByUsername(username);
        if(orders != null){
            return ResponseEntity.ok().body(orders);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<OrderInformation> getOrderById(@PathVariable(value = "id")int id) {
        try{
            OrderInformation order = orderService.getOrderInformationById(id);
            return ResponseEntity.ok().body(order);
        }
        catch(NotExistingUserException e)
        {
            return new ResponseEntity("Order not found", HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/addOrder/{couponCode}")
    public ResponseEntity createOrder(@PathVariable(value = "couponCode")String couponCode, @RequestBody OrderInformation_DTO order_DTO){
        try{
            OrderInformation order = castObject.castToOrderInformation(order_DTO);
            orderService.createOrder(order, couponCode);
            emailSender.sendOrderConfirmationEmail(userService.getUserByUsername(order.getUser().getUsername()).getEmail(),
                    order.getUser().getUsername(), order.getBillingAddress());
            return new ResponseEntity(ADDEDORDER, HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity("Order not found", HttpStatus.CONFLICT);
        }

    }


}
