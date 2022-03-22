package com.example.wearme_individualproject.interfaces;

import com.example.wearme_individualproject.logic.PaymentInformation;
import com.example.wearme_individualproject.logic.User;

import java.util.List;

public interface IPaymentInformationService {

    PaymentInformation findPaymentInformationById(int paymentInfoId);

    List<PaymentInformation> getListOfAllPaymentInformation();

    PaymentInformation getPaymentInformationByUsername(String username);

    void addPaymentInformation(PaymentInformation paymentInformation);

    void updatePaymentInfoInformation(String username, String cardType, String cardName, String cardNumber, String cardHolderName, String expirationDate);

    void deletePaymentInformationById(int paymentInfoId);

}
