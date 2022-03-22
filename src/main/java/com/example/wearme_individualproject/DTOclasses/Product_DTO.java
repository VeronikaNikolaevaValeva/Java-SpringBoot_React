package com.example.wearme_individualproject.DTOclasses;

import com.example.wearme_individualproject.enumeration.ProductCategory;
import com.example.wearme_individualproject.enumeration.ProductGender;
import com.example.wearme_individualproject.enumeration.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product_DTO {

    @Getter private int id;
    @Getter private String name;
    @Getter private ProductCategory productCategory;
    @Getter private String model;
    @Getter private String brand;
    @Getter private String description;
    @Getter private ProductGender productGender;
    @Getter private double costPrice;
    @Getter private double salesPrice;
    @Getter private ProductStatus productStatus;
    @Getter private String photoURL;
}
