package com.mijael.CSSpring.controller.model;

import com.mijael.CSSpring.enums.ClientType;
import com.mijael.CSSpring.security.Information;
import com.mijael.CSSpring.services.ClientService;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class Response {
    String token;
    @Enumerated(EnumType.STRING)
    ClientType clientType;
    String clientName;
    String clientLastName;
    int clientId;
    String clientEmail;
    String clientPassword;


}
