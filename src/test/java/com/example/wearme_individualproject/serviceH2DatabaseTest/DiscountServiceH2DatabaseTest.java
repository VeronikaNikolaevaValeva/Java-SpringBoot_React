package com.example.wearme_individualproject.serviceH2DatabaseTest;
import com.example.wearme_individualproject.enumeration.ProductCategory;
import com.example.wearme_individualproject.interfaces.IDiscountService;
import com.example.wearme_individualproject.logic.Discount;
import com.example.wearme_individualproject.repository.IDiscountRepository;
import com.example.wearme_individualproject.service.DiscountService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@ActiveProfiles("test")
@SpringBootTest
class DiscountServiceH2DatabaseTest {

    @Autowired
    private IDiscountService underTest;
    @Autowired
    private IDiscountRepository discountRepository;

    @BeforeEach
    void setUp(){
        underTest = new DiscountService(discountRepository);
    }

    @Test
    void getListOfAllDiscounts() {
        Discount discount = new Discount("test", "test", ProductCategory.BOOTS, 10);
        underTest.addNewDiscount(discount);
        List<Discount> listDiscountsTest = underTest.getListOfAllDiscounts();
        Assertions.assertEquals(1, listDiscountsTest.size());
        underTest.deleteDiscount(discount.getId());
    }

    @Test
    void deleteDiscountTest(){
        Discount discount = new Discount("test", "test", ProductCategory.BOOTS, 10);
        underTest.addNewDiscount(discount);
        List<Discount> listDiscountTest = underTest.getListOfAllDiscounts();
        Assertions.assertEquals(1, listDiscountTest.size());
        underTest.deleteDiscount(discount.getId());
        listDiscountTest = underTest.getListOfAllDiscounts();
        Assertions.assertEquals(0, listDiscountTest.size());
    }

    @Test
    void getDiscountByProductCategory(){
        Discount discount = new Discount("test", "test", ProductCategory.BOOTS, 10);
        underTest.addNewDiscount(discount);
        List<Discount> listOfDiscountForBoots = underTest.getListOfAllDiscountByProductCategory(ProductCategory.BOOTS);
        List<Discount> listOfDiscountForSneakers = underTest.getListOfAllDiscountByProductCategory(ProductCategory.SNEAKERS);
        Assertions.assertEquals(1, listOfDiscountForBoots.size());
        Assertions.assertEquals(0, listOfDiscountForSneakers.size());
        underTest.deleteDiscount(discount.getId());
    }
}
