package com.example.wearme_individualproject.service;

import com.example.wearme_individualproject.enumeration.ProductCategory;
import com.example.wearme_individualproject.interfaces.IDiscountService;
import com.example.wearme_individualproject.logic.Discount;
import com.example.wearme_individualproject.repository.IDiscountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class DiscountService implements IDiscountService {

    @Autowired
    private final IDiscountRepository discountRepository;

    @Override
    public Discount getDiscountById(int discountId){
       return discountRepository.findById(discountId);
    }

    @Override
    public List<Discount> getListOfAllDiscounts(){
        return discountRepository.findAll();
    }

    @Override
    public Discount getDiscountByDiscountCode(String discountCode){
        for (Discount discount : discountRepository.findAll()) {
            if(discount.getDiscount_code().equals(discountCode)){
                return discount;
            }
        }
        return null;
    }

    @Override
    public Discount getDiscountByProductCategory(ProductCategory productCategory){
        for (Discount discount : discountRepository.findAll()) {
            if(discount.getProductCategory().equals(productCategory)){
                return discount;
            }
        }
        return null;
    }

    @Override
    public List<Discount> getListOfAllDiscountByProductCategory(ProductCategory category){
        List<Discount> filteredList = new ArrayList<> ();
        for (Discount discount : discountRepository.findAll()) {
            if(discount.getProductCategory().equals(category)){
                filteredList.add(discount);
            }
        }
        return filteredList;
    }

    @Override
    public void addNewDiscount(Discount discount){
        discountRepository.save(discount);
    }

    @Override
    public void deleteDiscount(Discount discount){
        discountRepository.delete(discount);
    }

    @Override
    public void deleteDiscount(int discountId){
        discountRepository.delete(discountRepository.findById(discountId));
    }

}
