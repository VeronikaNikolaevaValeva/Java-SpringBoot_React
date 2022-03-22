package com.example.wearme_individualproject.logic;

import com.example.wearme_individualproject.enumeration.ProductCategory;
import com.example.wearme_individualproject.enumeration.ProductGender;
import com.example.wearme_individualproject.enumeration.ProductStatus;
import lombok.*;

import javax.persistence.*;

@Data
@Entity(name = "Product")
@AllArgsConstructor @NoArgsConstructor
public class Product {

    @Id
    @SequenceGenerator(
            name = "product_sequence",
            sequenceName = "product_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_sequence"
    )
    @Column(name="id", updatable = false)
    private int id;
    @Column(name = "name", nullable = false, columnDefinition = "TEXT")
    private String name;
    @Column(name = "product_category", nullable = false, columnDefinition = "TEXT")
    private ProductCategory productCategory;
    @Column(name = "model", nullable = false, columnDefinition = "TEXT")
    private String model;
    @Column(name = "brand", nullable = false, columnDefinition = "TEXT")
    private String brand;
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
    @Column(name = "product_gender", nullable = false, columnDefinition = "TEXT")
    private ProductGender productGender;
    @Column(name = "cost_price", nullable = false, columnDefinition = "VARCHAR")
    private double costPrice;
    @Column(name = "sales_price", nullable = false, columnDefinition = "VARCHAR")
    private double salesPrice;
    @Column(name = "product_status", nullable = false, columnDefinition = "TEXT")
    private ProductStatus productStatus;
    @Column(name = "photo_url", nullable = false, columnDefinition = "VARCHAR")
    private String photoURL;

    public Product(String name, ProductCategory productsCategory, String model, String brand, String description,
                   ProductGender productGender, double costPrice, double salesPrice,
                   ProductStatus productStatus, String photoURL)
    {
        this.name = name;
        this.productCategory = productsCategory;
        this.model = model;
        this.brand = brand;
        this.description = description;
        this.productGender = productGender;
        this.costPrice = costPrice;
        this.salesPrice = salesPrice;
        this.productStatus = productStatus;
        this.photoURL = photoURL;
    }


}
