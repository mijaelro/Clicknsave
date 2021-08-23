package com.mijael.CSSpring.security;

import java.time.LocalDateTime;

import com.mijael.CSSpring.enums.ClientType;
import com.mijael.CSSpring.services.ClientService;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Information {

	private int clientId;
	private ClientService clientService;
	private LocalDateTime time;
	private ClientType clientType;
	private String clientName;

}
