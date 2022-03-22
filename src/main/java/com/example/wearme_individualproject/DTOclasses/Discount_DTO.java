package com.example.wearme_individualproject.DTOclasses;

import com.example.wearme_individualproject.enumeration.ProductCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Discount_DTO {

    @Getter private String description;
    @Getter private ProductCategory productCategory;
    @Getter private String discount_code;
    @Getter private int discount_percentage;

}
