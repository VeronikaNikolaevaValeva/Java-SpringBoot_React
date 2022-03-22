package com.example.wearme_individualproject.logic;

import com.example.wearme_individualproject.enumeration.ProductCategory;
import lombok.*;

import javax.persistence.*;

@Data
@Entity(name = "Discount")
@AllArgsConstructor
@NoArgsConstructor
public class Discount {
    @Id
    @SequenceGenerator(
            name = "discount_sequence",
            sequenceName = "discount_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "discount_sequence"
    )
    @Column(name="id", updatable = false)
    @Getter @Setter private int id;
    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    @Getter @Setter private String description;
    @Column(name = "product_category", nullable = false, columnDefinition = "TEXT")
    @Getter @Setter private ProductCategory productCategory;
    @Column(name = "discount_code", nullable = false, columnDefinition = "TEXT")
    @Getter @Setter private String discount_code;
    @Column(name = "discount_percentage", nullable = false, columnDefinition = "TEXT")
    @Getter @Setter private int discount_percentage;

    public Discount(String description, String discount_code, ProductCategory productsCategory, int percentage)
    {
        this.description = description;
        this.discount_code = discount_code;
        this.productCategory = productsCategory;
        this.discount_percentage =percentage;
    }

}
