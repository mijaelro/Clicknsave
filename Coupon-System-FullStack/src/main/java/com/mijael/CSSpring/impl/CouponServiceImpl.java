package com.mijael.CSSpring.impl;

import com.mijael.CSSpring.beans.Coupon;
import com.mijael.CSSpring.exceptions.IllegalActionException;
import com.mijael.CSSpring.repos.CouponRepository;
import com.mijael.CSSpring.services.CouponService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@Getter
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {

    private final CouponRepository couponRepository;

    @Override
    public List<Coupon> getAllCoupons() throws IllegalActionException {
        return couponRepository.findAll();
    }

}
