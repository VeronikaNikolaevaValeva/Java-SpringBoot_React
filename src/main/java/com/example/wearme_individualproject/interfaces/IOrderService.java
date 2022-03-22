package com.example.wearme_individualproject.interfaces;

import com.example.wearme_individualproject.logic.OrderInformation;
import com.example.wearme_individualproject.logic.PaymentInformation;
import com.example.wearme_individualproject.logic.ShoppingCartItem;
import com.example.wearme_individualproject.logic.User;

import java.util.List;

public interface IOrderService {

    OrderInformation getOrderInformationById(int orderInfoId);

    OrderInformation getOrderInformationByOrderNumber(String orderNumber);

    double getTotalPriceFromProductsInOrder(String username);

    double applyCouponCodeOnTotalPriceFromProductsInOrder(double totalPrice, String couponCode);

    List<OrderInformation> getListOfALlOrderInformation();

    List<OrderInformation> getOrderInformationByUsername(String username);

    List<ShoppingCartItem> getProductsInOrder(String username);

    void createOrder(OrderInformation order, String couponCode);

    void addOrder(OrderInformation order);

    void finishOrder(String username, OrderInformation orderInformation);
}
