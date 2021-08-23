package com.mijael.CSSpring.impl;

import com.mijael.CSSpring.beans.Company;
import com.mijael.CSSpring.beans.Customer;
import com.mijael.CSSpring.enums.ClientType;
import com.mijael.CSSpring.exceptions.IllegalActionException;
import com.mijael.CSSpring.exceptions.SaveException;
import com.mijael.CSSpring.repos.CompanyRepository;
import com.mijael.CSSpring.repos.CustomerRepository;
import com.mijael.CSSpring.services.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SignUpServiceImpl implements SignUpService {
   private final CompanyRepository companyRepository;
   private final CustomerRepository customerRepository;

    @Override
    public Company companySignUp(String name, String email, String password,ClientType clientType) throws IllegalActionException, SaveException {
        if (companyRepository.existsByName(name)) {
            throw new IllegalActionException("The name for the company already exists... try with different attributes");
        }
        if (companyRepository.existsByEmail(email)) {
            throw new IllegalActionException("The email for the company already exists... try with different attributes ");
        }
        Company company = Company.builder()
                .name(name)
                .email(email)
                .password(password)
                .build();
        try {
            companyRepository.saveAndFlush(company);
        } catch (Exception e) {
            throw new SaveException(e.getMessage());
        }
        return company;
    }

    @Override
    public Customer customerSignUp(String firstName, String lastName, String email, String password ) throws IllegalActionException, SaveException {
        if (customerRepository.existsByEmail(email)) {
            throw new IllegalActionException("Error , email: " + email
                    + "already exists , try adding a customer with a different email");
        }
       Customer customer = Customer.builder()
               .firstName(firstName)
               .lastName(lastName)
               .email(email)
               .password(password)
               .build();
        try {
            customerRepository.saveAndFlush(customer);
        } catch (Exception e) {
            throw new SaveException(e.getMessage());
        }
        return customer;
    }

}
