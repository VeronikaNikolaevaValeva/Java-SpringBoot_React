package com.example.wearme_individualproject.logic;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Table(indexes = {
        @Index(name = "idx_shoppingcartitem", columnList = "product_id")
})
@Data
@Entity(name = "ShoppingCartItems")
@AllArgsConstructor @NoArgsConstructor
public class ShoppingCartItem {

    @Id
    @SequenceGenerator(
            name = "shoppingCart_sequence",
            sequenceName = "shoppingCart_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "shoppingCart_sequence")
    @Column(name="id", updatable = false)
    private int cartId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", nullable = false)
    @Getter @Setter private Product product;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    @Getter @Setter private User user;


    public ShoppingCartItem(User user, Product product){
        this.user = user;
        this.product = product;
    }

}
