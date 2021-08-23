package com.mijael.CSSpring.controller.model;

import com.mijael.CSSpring.enums.ClientType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginDetails {

    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private ClientType clientType;

}
