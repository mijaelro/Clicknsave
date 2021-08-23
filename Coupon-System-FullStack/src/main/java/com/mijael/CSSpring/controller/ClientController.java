package com.mijael.CSSpring.controller;

import com.mijael.CSSpring.beans.Company;
import com.mijael.CSSpring.beans.Customer;
import com.mijael.CSSpring.controller.model.*;
import com.mijael.CSSpring.exceptions.*;
import com.mijael.CSSpring.exceptions.SecurityException;
import com.mijael.CSSpring.security.Information;
import com.mijael.CSSpring.security.TokenManager;
import com.mijael.CSSpring.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("client")
@CrossOrigin(origins="*",allowedHeaders="*")

public  class ClientController {

	@Autowired
	private  TokenManager tokenManager;

	private final LoginManagerService loginManager;
	private final SignUpService signUpService;


	@PostMapping("login")
	public  ResponseEntity<?> login(@RequestBody LoginDetails loginDetails) throws SecurityException, LoginException, IllegalActionException {
		String token =loginManager.logIn(loginDetails.getEmail(),loginDetails.getPassword(),loginDetails.getClientType());
		Information information = tokenManager.getMap().get(token);
		Response response = new Response();
		response.setToken(token);
		response.setClientType(information.getClientType());
		response.setClientId(information.getClientId());
		response.setClientName(information.getClientName());

		return new ResponseEntity(response,HttpStatus.CREATED);
	};
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@DeleteMapping("logout")
	public void logout(@RequestHeader("Authorization") String token) throws TokenErrorException {

		tokenManager.deleteToken(token);
		System.out.println(token);

	}

	@PostMapping("/signup")
	public ResponseEntity<?> companySignUp(@RequestBody SignUpCompanyDetails signUpCompanyDetails) throws IllegalActionException, SaveException, LoginException, SecurityException {
		Company company = signUpService.companySignUp(signUpCompanyDetails.getName(),signUpCompanyDetails.getEmail(),signUpCompanyDetails.getPassword(),signUpCompanyDetails.getClientType());
		String token = loginManager.logIn(signUpCompanyDetails.getEmail(),signUpCompanyDetails.getPassword(),signUpCompanyDetails.getClientType());
		Response response = new Response();
		response.setToken(token);
		response.setClientName(company.getName());
		response.setClientEmail(company.getEmail());
		response.setClientType(signUpCompanyDetails.getClientType());
		response.setClientId(company.getId());
		return new ResponseEntity(response,HttpStatus.CREATED);
	}
	@PostMapping("/signup1")
	public ResponseEntity<?> customerSignUp(@RequestBody SignUpCustomerDetails signUpCustomerDetails) throws IllegalActionException, SaveException, LoginException, SecurityException {
		Customer customer = signUpService.customerSignUp(signUpCustomerDetails.getFirstName(),signUpCustomerDetails.getLastName(),signUpCustomerDetails.getEmail(),signUpCustomerDetails.getPassword());
		String token = loginManager.logIn(signUpCustomerDetails.getEmail(),signUpCustomerDetails.getPassword(),signUpCustomerDetails.getClientType());
		Response response = new Response();
		response.setToken(token);
		response.setClientId(customer.getId());
		response.setClientName(signUpCustomerDetails.getFirstName());
		response.setClientType(signUpCustomerDetails.getClientType());
		response.setClientEmail(signUpCustomerDetails.getEmail());
		return new ResponseEntity(response,HttpStatus.CREATED);
	}

}

