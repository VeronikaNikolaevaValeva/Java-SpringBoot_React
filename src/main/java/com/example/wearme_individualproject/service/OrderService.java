package com.example.wearme_individualproject.service;

import com.example.wearme_individualproject.interfaces.*;
import com.example.wearme_individualproject.logic.*;
import com.example.wearme_individualproject.repository.IOrderInformationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OrderService implements IOrderService {

    private final IOrderInformationRepository iOrderInformationRepository;
    private final IShoppingCartService shoppingCartService;
    private final IPurchasedProductService purchasedProductService;
    private final IDiscountService discountService;

    @Override
    public OrderInformation getOrderInformationById(int orderInfoId){
        return iOrderInformationRepository.findById(orderInfoId);
    }

    @Override
    public List<OrderInformation> getListOfALlOrderInformation(){
        return iOrderInformationRepository.findAll();
    }

    @Override
    public OrderInformation getOrderInformationByOrderNumber(String orderNumber){
        for(OrderInformation orderInfo : this.getListOfALlOrderInformation()){
            if(orderInfo.getOrderNumber().toString().equals(orderNumber)){
                return orderInfo;
            }
        }
        return null;
    }

    @Override
    public List<OrderInformation> getOrderInformationByUsername(String username){
        List<OrderInformation> filteredList = new ArrayList<>();
        for(OrderInformation orderInfo : this.getListOfALlOrderInformation()){
            if(orderInfo.getUser().getUsername().equals(username)){
                filteredList.add(orderInfo);
            }
        }
        return  filteredList;
    }

    @Override
    public List<ShoppingCartItem> getProductsInOrder(String username){
        return shoppingCartService.getListOfShoppingCartItemsByUsername(username);

    }

    @Override
    public void createOrder(OrderInformation order, String couponCode){
        if(getProductsInOrder(order.getUser().getUsername()).size()>0){
            double totalPrice = getTotalPriceFromProductsInOrder(order.getUser().getUsername());
            if(!couponCode.equals("none")) {
                order.setTotalPrice(applyCouponCodeOnTotalPriceFromProductsInOrder(totalPrice, couponCode));
            }
            else{
                order.setTotalPrice(totalPrice);
            }
            this.addOrder(order);
            finishOrder(order.getUser().getUsername(), order);
        }
    }

    @Override
    public void addOrder(OrderInformation order){
        iOrderInformationRepository.save(order);
    }

    @Override
    public double getTotalPriceFromProductsInOrder(String username){
        double price = 0;
        for(ShoppingCartItem item : this.getProductsInOrder(username)){
            price += item.getProduct().getSalesPrice();
        }
        return price;
    }

    @Override
    public double applyCouponCodeOnTotalPriceFromProductsInOrder(double totalPrice, String couponCode){
        totalPrice = totalPrice - (totalPrice*discountService.getDiscountByDiscountCode(couponCode).getDiscount_percentage())/100;
        return totalPrice ;
    }

    @Override
    public void finishOrder(String username, OrderInformation orderInformation){
        for(ShoppingCartItem item: this.getProductsInOrder(username)){
            purchasedProductService.addPurchasedProduct(new PurchasedProducts(item.getUser(), item.getProduct(), orderInformation));
        }
        for(ShoppingCartItem item: this.getProductsInOrder(username)){
            shoppingCartService.deleteItemFromUsersShoppingCart(item.getCartId());
        }
    }


}
