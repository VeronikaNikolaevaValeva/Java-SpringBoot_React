package com.example.wearme_individualproject.DTOclasses;

import com.example.wearme_individualproject.logic.*;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Transactional
public class CustomCast_DTO {

    public User castToUser(User_DTO user){
        return new User(user.getFirstName(), user.getFamilyName(), user.getUsername(), user.getPassword(),
                user.getEmail(), user.getDateOfBirth(), user.getTelephoneNumber(), user.getStreetAddress(),
                user.getStreetNumber(), user.getZipCode(), user.getTown(), user.getCountry(),
                user.getAccountStatus(), user.getRole());
    }

    public Product castToProduct(Product_DTO product){
        return new Product(product.getName(), product.getProductCategory(), product.getModel(),
                product.getBrand(), product.getDescription(), product.getProductGender(), product.getCostPrice(),
                product.getSalesPrice(), product.getProductStatus(), product.getPhotoURL());
    }

    public Discount castToDiscount(Discount_DTO discount){
        return new Discount(discount.getDescription(), discount.getDiscount_code(), discount.getProductCategory(),
                            discount.getDiscount_percentage());
    }

    public OrderInformation castToOrderInformation(OrderInformation_DTO order){
        return new OrderInformation(order.getUser(), order.getBillingAddress(), order.getPaymentInformation(),
                                    order.getTotalPrice());
    }

}
