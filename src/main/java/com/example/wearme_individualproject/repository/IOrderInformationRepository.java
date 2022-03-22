package com.example.wearme_individualproject.repository;

import com.example.wearme_individualproject.logic.OrderInformation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IOrderInformationRepository extends JpaRepository<OrderInformation, Integer> {
    OrderInformation findById(int orderInfoId);
    List<OrderInformation> findAll();
}
