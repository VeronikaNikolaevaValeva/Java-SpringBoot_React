package com.example.wearme_individualproject.logic;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity(name = "PaymentInformation")
@AllArgsConstructor @NoArgsConstructor
public class PaymentInformation {
    @Id
    @SequenceGenerator(
            name = "paymentInformation_sequence",
            sequenceName = "paymentInformation_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "paymentInformation_sequence")
    @Column(name="id", updatable = false)
    private int id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    @Getter @Setter private User user;
    @Column(name = "card_type", nullable = false, columnDefinition = "TEXT")
    @Getter @Setter private String cardType;                                                         //DEBIT, CREDIT
    @Column(name = "card_name", nullable = false, columnDefinition = "TEXT")
    @Getter @Setter private String cardName;                                                         //VISA, MASTERCARD, etc.
    @Column(name = "card_number", nullable = false, columnDefinition = "TEXT")
    @Getter @Setter private String cardNumber;
    @Column(name = "card_holder_name", nullable = false, columnDefinition = "TEXT")
    @Getter @Setter private String cardHolderName;
    @Column(name = "expiration_date", nullable = false, columnDefinition = "TEXT")
    @Getter @Setter private String expirationDate;

    public PaymentInformation(User user, String cardType, String cardName, String cardNumber, String cardHolderName, String expirationDate){
        this.user = user;
        this.cardType = cardType;
        this.cardName = cardName;
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.expirationDate = expirationDate;
    }

}
