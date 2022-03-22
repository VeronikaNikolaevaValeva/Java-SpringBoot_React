package com.example.wearme_individualproject.repository;

import com.example.wearme_individualproject.logic.PaymentInformation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPaymentInformationRepository extends JpaRepository<PaymentInformation, Integer> {
    List<PaymentInformation> findAll();
    PaymentInformation findById(int paymentInformationId);
}
