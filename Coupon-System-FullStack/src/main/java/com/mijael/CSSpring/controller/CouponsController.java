package com.mijael.CSSpring.controller;

import com.mijael.CSSpring.beans.Coupon;
import com.mijael.CSSpring.exceptions.IllegalActionException;
import com.mijael.CSSpring.services.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("coupon")
@RequiredArgsConstructor
@CrossOrigin(origins="*",allowedHeaders="*")
public class CouponsController {
    
    private final CouponService couponService;

    @GetMapping("coupons")
    @ResponseStatus(code= HttpStatus.OK)
    public List<Coupon> getCoupons () throws IllegalActionException {
        return couponService.getAllCoupons();
    }

}
