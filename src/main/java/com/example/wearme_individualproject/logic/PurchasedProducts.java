package com.example.wearme_individualproject.logic;

import lombok.*;

import javax.persistence.*;

@Data
@Entity(name = "PurchasedProducts")
@AllArgsConstructor
@NoArgsConstructor
public class PurchasedProducts {

    @Id
    @SequenceGenerator(
            name = "purchasedItems_sequence",
            sequenceName = "purchasedItems_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "purchasedItems_sequence")
    @Column(name="id", updatable = false)
    @Getter @Setter private int id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", nullable = false)
    @Getter @Setter private Product product;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    @Getter @Setter private User user;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "orderInfo_id", nullable = false)
    @Getter @Setter private OrderInformation orderInformation;


    public PurchasedProducts(User user, Product product, OrderInformation orderInformation){
        this.user = user;
        this.product = product;
        this.orderInformation = orderInformation;
    }
}
