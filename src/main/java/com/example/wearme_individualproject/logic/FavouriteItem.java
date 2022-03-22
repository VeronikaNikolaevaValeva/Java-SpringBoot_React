package com.example.wearme_individualproject.logic;

import lombok.*;

import javax.persistence.*;

@Data
@Entity(name = "FavouriteItems")
@AllArgsConstructor @NoArgsConstructor
public class FavouriteItem {

    @Id
    @SequenceGenerator(
            name = "favouriteItem_sequence",
            sequenceName = "favouriteItem_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "favouriteItem_sequence")
    @Column(name="id", updatable = false)
    private int id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", nullable = false)
    @Getter @Setter private Product product;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    @Getter @Setter private User user;

    public FavouriteItem(User user, Product product){
        this.user = user;
        this.product = product;
    }

}
