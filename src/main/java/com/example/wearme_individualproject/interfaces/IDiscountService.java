package com.example.wearme_individualproject.interfaces;

import com.example.wearme_individualproject.enumeration.ProductCategory;
import com.example.wearme_individualproject.logic.Discount;

import java.util.List;

public interface IDiscountService {

    Discount getDiscountById(int discountId);
    List<Discount> getListOfAllDiscounts();
    Discount getDiscountByDiscountCode(String discountCode);
    Discount getDiscountByProductCategory(ProductCategory productCategory);
    List<Discount> getListOfAllDiscountByProductCategory(ProductCategory category);
    void addNewDiscount(Discount discount);
    void deleteDiscount(Discount dicount);
    void deleteDiscount(int discountId);
}
