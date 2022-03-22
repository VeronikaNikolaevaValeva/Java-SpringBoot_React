package com.example.wearme_individualproject.controller;

import com.example.wearme_individualproject.exceptions.NotExistingUserException;
import com.example.wearme_individualproject.interfaces.IPaymentInformationService;
import com.example.wearme_individualproject.interfaces.IUserService;
import com.example.wearme_individualproject.logic.PaymentInformation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/paymentInfo")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
public class PaymentInformationController {

    private final IPaymentInformationService paymentInformationService;
    private final IUserService userService;
    private static final  String ADDEDINFO = "Payment details added";
    private static final  String INFOUPDATED = "Payment details updated";
    private static final  String REMOVEDINFO = "Payment details deleted";

    @GetMapping("/AllPaymentInfo/{username}")
    public ResponseEntity<PaymentInformation> getListOfAllPaymentInformation(@PathVariable(value = "username")String username) {
        PaymentInformation info = paymentInformationService.getPaymentInformationByUsername(username);
        if(info != null){
            return ResponseEntity.ok().body(info);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<PaymentInformation> getPaymentInfoById(@PathVariable(value = "id")int id) {
        try{
            PaymentInformation info = paymentInformationService.findPaymentInformationById(id);
            return ResponseEntity.ok().body(info);
        }
        catch(NotExistingUserException e)
        {
            return new ResponseEntity("Payment information not found", HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/addPaymentInfo/{username}/{cardType}/{cardNumber}/{cardName}/{cardHolderName}/{expirationDate}")
    public ResponseEntity addPaymentInfo(@PathVariable(value = "username")String username, @PathVariable(value = "cardType")String cardType,
                                      @PathVariable(value="cardNumber")String cardNumber,@PathVariable(value = "cardName")String cardName,
                                      @PathVariable(value="cardHolderName")String cardHolderName, @PathVariable(value="expirationDate")String expirationDate){
        paymentInformationService.addPaymentInformation(new PaymentInformation(userService.getUserByUsername(username), cardType, cardNumber, cardName, cardHolderName, expirationDate));
        return new ResponseEntity(ADDEDINFO, HttpStatus.CREATED);
    }

    @PutMapping("/updatePaymentInfo/{username}/{cardType}/{cardNumber}/{cardName}/{cardHolderName}/{expirationDate}")
    public ResponseEntity updatePaymentInfo(@PathVariable(value = "username")String username, @PathVariable(value = "cardType")String cardType,
                                      @PathVariable(value="cardNumber")String cardNumber,@PathVariable(value = "cardName")String cardName,
                                      @PathVariable(value="cardHolderName")String cardHolderName, @PathVariable(value="expirationDate")String expirationDate){
        paymentInformationService.updatePaymentInfoInformation(username, cardType, cardName, cardNumber, cardHolderName, expirationDate);
        return new ResponseEntity(INFOUPDATED, HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
    @DeleteMapping("/deletePaymentInfo/{paymentInfoId}")
    public ResponseEntity<PaymentInformation> deletePaymentInfo(@PathVariable(value = "paymentInfoId")int paymentInfoId){
        try {
            paymentInformationService.deletePaymentInformationById(paymentInfoId);
            return new ResponseEntity(REMOVEDINFO, HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.CONFLICT);
        }

    }

}
