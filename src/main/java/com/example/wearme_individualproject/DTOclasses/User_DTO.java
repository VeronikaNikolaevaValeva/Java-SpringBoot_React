package com.example.wearme_individualproject.DTOclasses;

import com.example.wearme_individualproject.enumeration.AccountStatus;
import com.example.wearme_individualproject.enumeration.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User_DTO {

    @Getter private String firstName;
    @Getter private String familyName;
    @Getter private String username;
    @Getter private String password;
    @Getter private String email;
    @Getter private LocalDate dateOfBirth;
    @Getter private String telephoneNumber;
    @Getter private String streetAddress;
    @Getter private String streetNumber;
    @Getter private String zipCode;
    @Getter private String town;
    @Getter private String country;
    @Getter private AccountStatus accountStatus;
    @Getter private Role role;
}
