package com.mijael.CSSpring.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.mijael.CSSpring.exceptions.LoginException;
import com.mijael.CSSpring.repos.CompanyRepository;
import com.mijael.CSSpring.repos.CouponRepository;
import com.mijael.CSSpring.repos.CustomerRepository;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public abstract class ClientService {

	@Autowired
	protected CompanyRepository companyRepository;
	@Autowired
	protected CustomerRepository customerRepository;
	@Autowired
	protected CouponRepository couponRepository;



	public abstract boolean logIn(String email, String password) throws LoginException;
}
