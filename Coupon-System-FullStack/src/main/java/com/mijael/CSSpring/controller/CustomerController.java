package com.mijael.CSSpring.controller;

import java.util.List;

import com.mijael.CSSpring.beans.Customer;
import com.mijael.CSSpring.security.TokenManager;
import com.mijael.CSSpring.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.mijael.CSSpring.beans.Coupon;
import com.mijael.CSSpring.enums.CategoryType;
import com.mijael.CSSpring.exceptions.IllegalActionException;
import com.mijael.CSSpring.exceptions.PurchaseException;
import com.mijael.CSSpring.services.CustomerService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("customer")
@RequiredArgsConstructor
@CrossOrigin(origins="*",allowedHeaders="*")

    public class CustomerController  {
    private final TokenManager tokenManager;
    @Autowired
    private  CustomerService customerService;


    @PostMapping("purchase")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Coupon purchaseCoupon(@RequestHeader ("Authorization") String token, @RequestBody Coupon coupon)
            throws IllegalActionException, PurchaseException {
        customerService= (CustomerService) tokenManager.getService(token);
      return customerService.purchaseCoupon(coupon);
    }

    @GetMapping("coupons")
    @ResponseStatus(code = HttpStatus.OK)
    public List<Coupon> getCustomerCoupons(@RequestHeader ("Authorization") String token) {
        customerService= (CustomerService) tokenManager.getService(token);
        return customerService.getCustomerCoupons();
    }

    @GetMapping("coupons/{category}")
    @ResponseStatus(code = HttpStatus.OK)
    public List<Coupon> getCustomerCoupons(@RequestHeader ("Authorization") String token,@PathVariable CategoryType category)
            throws IllegalActionException{
        customerService= (CustomerService) tokenManager.getService(token);
        return customerService.getCustomerCoupons(category);
    }

    @GetMapping("coupons/{maxprice}")
    @ResponseStatus(code = HttpStatus.OK)
    public List<Coupon> getCustomerCoupons(@RequestHeader ("Authorization") String token,@PathVariable double maxPrice) {
        customerService= (CustomerService) tokenManager.getService(token);
        return customerService.getCustomerCoupons(maxPrice);
    }

    @GetMapping("coupon/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Coupon getCustomerCoupon(@RequestHeader ("Authorization") String token,@PathVariable int id) throws IllegalActionException {
        customerService= (CustomerService) tokenManager.getService(token);
        return  customerService.getSingleCoupon(id);
    }

    @GetMapping("details")
    @ResponseStatus(code = HttpStatus.OK)
    public Customer getCustomerDetails(@RequestHeader ("Authorization") String token)
            throws IllegalActionException {
        customerService= (CustomerService) tokenManager.getService(token);
       return  customerService.getCustomersDetails();
    }

}
