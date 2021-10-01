package com.mijael.CSSpring.controller;

import java.util.List;

import com.mijael.CSSpring.security.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.mijael.CSSpring.beans.Company;
import com.mijael.CSSpring.beans.Customer;
import com.mijael.CSSpring.exceptions.IllegalActionException;
import com.mijael.CSSpring.exceptions.SaveException;
import com.mijael.CSSpring.services.AdminService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("admin")
@RequiredArgsConstructor
@CrossOrigin(origins = "*",allowedHeaders = "*")

public class AdminController {

    @Autowired
    private  AdminService adminService;

    @PostMapping("company")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Company addCompany(@RequestHeader(value = "Authorization") String token,@ModelAttribute Company company)
            throws IllegalActionException, SaveException{
        return adminService.addCompany(company);
    }

    @PutMapping("company")
    @ResponseStatus(code = HttpStatus.OK)
    public Company updateCompany(@RequestHeader(value = "Authorization") String token,@RequestBody  Company company)
        throws IllegalActionException, SaveException{
    return adminService.updateCompany(company);
    }

    @DeleteMapping("company/{companyId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteCompany(@RequestHeader(value = "Authorization") String token, @PathVariable int companyId)
            throws IllegalActionException{
        adminService.DeleteCompany(companyId);
    }

    @GetMapping("companies")
    @ResponseStatus(code = HttpStatus.OK)
    public List<Company> getAllCompanies(@RequestHeader(value = "Authorization") String token) {
        return adminService.getAllCompanies();
    }

    @GetMapping("company/{companyId}")
    @ResponseStatus(code = HttpStatus.OK)
    public Company getOneCompany(@RequestHeader(value = "Authorization") String token, @PathVariable int companyId)throws IllegalActionException{
        return adminService.getOneCompany(companyId);
    }

    @PostMapping("customer")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Customer addCustomer(@RequestHeader(value = "Authorization") String token, @ModelAttribute Customer customer)
            throws IllegalActionException, SaveException{
        return adminService.addCustomer(customer);
    }

    @PutMapping("customer")
    @ResponseStatus(code = HttpStatus.OK)
    public Customer updateCustomer(@RequestHeader(value = "Authorization") String token, @RequestBody Customer customer)
            throws IllegalActionException, SaveException{
        return  adminService.updateCustomer(customer);
    }

    @DeleteMapping("customer/{customerId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteCustomer(@RequestHeader(value = "Authorization") String token, @PathVariable int customerId)
            throws IllegalActionException{
        adminService.deleteCustomer(customerId);
    }

    @GetMapping("customers")
    @ResponseStatus(code = HttpStatus.OK)
    public List<Customer> getAllCustomers(@RequestHeader(value = "Authorization") String token) {
        return adminService.getAllCustomers();
    }

    @GetMapping("customer/{customerId}")
    @ResponseStatus(code = HttpStatus.OK)
    public Customer getOneCustomer(@RequestHeader(value = "Authorization") String token, @PathVariable int customerId)
            throws IllegalActionException{
        return adminService.getOneCustomer(customerId);
    }
}
