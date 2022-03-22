package com.example.wearme_individualproject.logic;
import com.example.wearme_individualproject.enumeration.AccountStatus;
import com.example.wearme_individualproject.enumeration.Role;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;

@Data @Entity(name = "User")
@AllArgsConstructor @NoArgsConstructor
@Table(name="user", uniqueConstraints = {@UniqueConstraint(name = "user_username_unique", columnNames = "username")})
public class User {

    @Id @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "user_sequence")
    @Column(name="id", updatable = false)
    private int id;
    @Column(name = "first_name", nullable = false, columnDefinition = "TEXT")
    private String firstName;
    @Column(name = "family_name", nullable = false, columnDefinition = "TEXT")
    private String familyName;
    @Column(name = "username", nullable = false, columnDefinition = "TEXT")
    private String username;
    @Column(name = "password", nullable = false, columnDefinition = "TEXT")
    private String password;
    @Column(name = "email", nullable = false, columnDefinition = "TEXT")
    private String email;
    @Column(name = "date_of_birth", nullable = false, columnDefinition = "TEXT")
    private LocalDate dateOfBirth;
    @Column(name = "telephone_number", columnDefinition = "TEXT")
    private String telephoneNumber;
    @Column(name = "street_address", columnDefinition = "TEXT")
    private String streetAddress;
    @Column(name = "street_number", columnDefinition = "TEXT")
    private String streetNumber;
    @Column(name = "zip_code", columnDefinition = "TEXT")
    private String zipCode;
    @Column(name = "town", columnDefinition = "TEXT")
    private String town;
    @Column(name = "country", columnDefinition = "TEXT")
    private String country;
    @Column(name = "account_status", columnDefinition = "TEXT")
    private AccountStatus accountStatus;
    @Column(name = "user_type", columnDefinition = "TEXT")
    private Role role;


    public User(String firstName, String familyName, String username, String password,
    String email, LocalDate dateOfBirth, String telephoneNumber, String streetAddress,
                String streetNumber, String zipCode, String town, String country,
                AccountStatus accountStatus, Role role){
        this.firstName = firstName;
        this.familyName = familyName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.telephoneNumber = telephoneNumber;
        this.streetAddress = streetAddress;
        this.streetNumber = streetNumber;
        this.zipCode = zipCode;
        this.town = town;
        this.country = country;
        this.accountStatus = accountStatus;
        this.role = role;
    }



}
