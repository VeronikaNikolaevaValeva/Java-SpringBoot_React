package com.example.wearme_individualproject.serviceMockitoWhenThenFlowTest;
import com.example.wearme_individualproject.enumeration.ProductCategory;
import com.example.wearme_individualproject.interfaces.IDiscountService;
import com.example.wearme_individualproject.logic.Discount;
import com.example.wearme_individualproject.repository.IDiscountRepository;
import com.example.wearme_individualproject.service.DiscountService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import static org.mockito.Mockito.when;
@ActiveProfiles("test")
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class DiscountServiceMockitoWhenThenFlowTest {

    @Mock
    private IDiscountRepository iDiscountRepository;
    private IDiscountService underTest;

    @BeforeEach
    void setUp(){
        underTest = new DiscountService(iDiscountRepository);
        List<Discount> testDiscounts = List.of(
                new Discount("test", "test", ProductCategory.BOOTS, 10),
                new Discount("test", "test", ProductCategory.SNEAKERS, 10)
        );
        when(underTest.getListOfAllDiscounts()).thenReturn(testDiscounts);
        when(underTest.getListOfAllDiscountByProductCategory(ProductCategory.BOOTS)).thenReturn(testDiscounts);
    }

    @Test
    void getAllProductsTest(){
        List<Discount> discountTestList = underTest.getListOfAllDiscounts();
        Assertions.assertEquals(ProductCategory.BOOTS, discountTestList.get(0).getProductCategory());
        Assertions.assertEquals(ProductCategory.SNEAKERS, discountTestList.get(1).getProductCategory());
    }

    @Test
    void getListOfAllDiscountByProductCategoryTest(){
        List<Discount> discountTestList = underTest.getListOfAllDiscountByProductCategory(ProductCategory.BOOTS);
        Assertions.assertEquals(ProductCategory.BOOTS, discountTestList.get(0).getProductCategory());
    }
}
