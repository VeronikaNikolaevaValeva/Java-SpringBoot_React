package com.example.wearme_individualproject.controller;

import com.example.wearme_individualproject.enumeration.ProductCategory;
import com.example.wearme_individualproject.interfaces.IDiscountService;
import com.example.wearme_individualproject.logic.Discount;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
@Slf4j
public class WebSocketController {

    private final IDiscountService discountService;
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    @Getter @Setter private String userHeader;
    @MessageMapping("/header")
    @SendTo("/topic/discounts")
    public void getUsername (String userHeader) throws Exception {
        setUserHeader(userHeader);
    }

    @MessageMapping("/hey")
    @SendTo("/topic/discounts/")
    public String discountMessage(String productCategory) throws Exception {
        Discount discount = discountService.getDiscountByProductCategory(ProductCategory.valueOf(productCategory));
        if(discount!=null){
            simpMessagingTemplate.convertAndSend("/topic/discounts/" + getUserHeader(), discount.getDescription());
        }
        return null;
    }
}
