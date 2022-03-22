package com.example.wearme_individualproject.service;

import com.example.wearme_individualproject.interfaces.IPaymentInformationService;
import com.example.wearme_individualproject.logic.PaymentInformation;
import com.example.wearme_individualproject.repository.IPaymentInformationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class PaymentInformationService implements IPaymentInformationService {

    private final IPaymentInformationRepository iPaymentInformationRepository;

    @Override
    public PaymentInformation findPaymentInformationById(int paymentInfoId){
       return iPaymentInformationRepository.findById(paymentInfoId);
    }

    @Override
    public List<PaymentInformation> getListOfAllPaymentInformation(){
        return iPaymentInformationRepository.findAll();
    }

    @Override
    public PaymentInformation getPaymentInformationByUsername(String username){
        for(PaymentInformation paymentInformation : this.getListOfAllPaymentInformation()){
            if(paymentInformation.getUser().getUsername().equals(username)){
                return paymentInformation;
            }
        }
        return null;
    }

    @Override
    public void updatePaymentInfoInformation(String username, String cardType, String cardName, String cardNumber, String cardHolderName, String expirationDate){
        PaymentInformation paymentInformation = this.getPaymentInformationByUsername(username);
        if(paymentInformation!=null){
            paymentInformation.setCardType(cardType);
            paymentInformation.setCardName(cardName);
            paymentInformation.setCardNumber(cardNumber);
            paymentInformation.setCardHolderName(cardHolderName);
            paymentInformation.setExpirationDate(expirationDate);
            iPaymentInformationRepository.save(paymentInformation);
        }
    }

    @Override
    public void addPaymentInformation(PaymentInformation paymentInformation){
        iPaymentInformationRepository.save(paymentInformation);
    }

    @Override
    public void deletePaymentInformationById(int paymentInfoId){
        iPaymentInformationRepository.delete(this.findPaymentInformationById(paymentInfoId));
    }

}
