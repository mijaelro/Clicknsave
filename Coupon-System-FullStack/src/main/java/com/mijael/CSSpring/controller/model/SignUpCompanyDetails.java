package com.mijael.CSSpring.controller.model;

import com.mijael.CSSpring.enums.ClientType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpCompanyDetails {
    private String name;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private ClientType clientType = ClientType.COMPANY;

}
