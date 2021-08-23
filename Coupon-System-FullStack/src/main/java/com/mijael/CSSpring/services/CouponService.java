package com.mijael.CSSpring.services;

import com.mijael.CSSpring.beans.Coupon;
import com.mijael.CSSpring.enums.CategoryType;
import com.mijael.CSSpring.exceptions.IllegalActionException;

import java.util.List;

public interface CouponService {
    List<Coupon> getAllCoupons() throws IllegalActionException;
}
