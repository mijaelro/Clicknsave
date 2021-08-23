package com.mijael.CSSpring.controller.model;

import com.mijael.CSSpring.security.TokenManager;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
public class LogoutDetails {
	private String token;
}
