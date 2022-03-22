package com.example.wearme_individualproject.logic;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity(name = "OrderInformation")
@AllArgsConstructor
@NoArgsConstructor
public class OrderInformation {

    @Id
    @SequenceGenerator(
            name = "orderInformation_sequence",
            sequenceName = "orderInformation_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "orderInformation_sequence")
    @Column(name="id", updatable = false)
    private int id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    @Getter @Setter private User user;
    @Column(name="billing_address", updatable = false)
    @Getter @Setter private String billingAddress;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "paymentInfo_id", nullable = false)
    @Getter @Setter private PaymentInformation paymentInformation;
    @Column(name="total_price", updatable = false)
    @Getter @Setter private double totalPrice;
    @Column(name="order_number", nullable = false)
    @Getter @Setter UUID orderNumber;

    public OrderInformation(User user, String billingAddress, PaymentInformation paymentInformation, double totalPrice){
        this.user = user;
        this.billingAddress = billingAddress;
        this.paymentInformation = paymentInformation;
        this.totalPrice = totalPrice;
        this.orderNumber = UUID.randomUUID();
    }

}
