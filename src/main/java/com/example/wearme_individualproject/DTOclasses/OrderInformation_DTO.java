package com.example.wearme_individualproject.DTOclasses;

import com.example.wearme_individualproject.logic.PaymentInformation;
import com.example.wearme_individualproject.logic.User;
import lombok.Getter;

public class OrderInformation_DTO {

    @Getter private User user;
    @Getter private String billingAddress;
    @Getter private PaymentInformation paymentInformation;
    @Getter private double totalPrice;

}
