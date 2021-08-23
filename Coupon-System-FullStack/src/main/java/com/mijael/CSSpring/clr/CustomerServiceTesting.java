package com.mijael.CSSpring.clr;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.mijael.CSSpring.beans.Coupon;
import com.mijael.CSSpring.enums.CategoryType;
import com.mijael.CSSpring.enums.ClientType;
import com.mijael.CSSpring.security.Information;
import com.mijael.CSSpring.security.TokenManager;
import com.mijael.CSSpring.services.CustomerService;
import com.mijael.CSSpring.services.LoginManagerService;
import com.mijael.CSSpring.utils.ArtUtils;
import com.mijael.CSSpring.utils.TestUtil;

import lombok.RequiredArgsConstructor;

//@Component
//@Order(4)
@RequiredArgsConstructor
public class CustomerServiceTesting implements CommandLineRunner {

    private final LoginManagerService loginManagerService;
    private final TokenManager tokenManager;

    @Override
    public void run(String... args) throws Exception {

        System.out.println(ArtUtils.CUSTOMER_SERVICE_TESTING);

        TestUtil.testInfo("Logg-In [GOOD]");

//        System.out.println(loginManagerService.logIn("moishe@gmail.com", "1234", ClientType.CUSTOMER));

        String token = loginManagerService.logIn("moishe@gmail.com", "1234", ClientType.CUSTOMER);
        System.out.println(token);
        Information information = tokenManager.getMap().get(token);
        CustomerService customerService = (CustomerService) information.getClientService();

        // TestUtil.testInfo("Logg-In [BAD]");

        // System.out.println(loginManagerService.logIn("mijarofe@.com", "008899m",
        // ClientType.CUSTOMER));

        System.out.println("--------------------------------------------------------------------------");

        TestUtil.testInfo("purchaseCoupon [GOOD]");

        Coupon c = customerService.getSingleCoupon(1);
        customerService.purchaseCoupon(c);

        // TestUtil.testInfo("purchaseCoupon [BAD -Expired]");

        // Coupon c1 = customerServiceImpl.getSingleCoupon(2);
        // customerServiceImpl.purchaseCoupon(c1);

        // TestUtil.testInfo("purchaseCoupon [BAD -Sold out]");

        // Coupon c1 =customerServiceImpl.getSingleCoupon(3);
        // customerServiceImpl.purchaseCoupon(c1);

        // TestUtil.testInfo("purchaseCoupon [BAD - Already purchased]");

        // Coupon c1 = customerServiceImpl.getSingleCoupon(1);
        // customerServiceImpl.purchaseCoupon(c1);

        System.out.println("--------------------------------------------------------------------------");

        TestUtil.testInfo("getCustomersDetails");
        System.out.println(customerService.getCustomersDetails());

        System.out.println("--------------------------------------------------------------------------");

        TestUtil.testInfo("getCustomersCoupons");
        System.out.println(customerService.getCustomerCoupons());

        System.out.println("--------------------------------------------------------------------------");

        TestUtil.testInfo("getCustomerCoupons(Category) [GOOD]");
        System.out.println(customerService.getCustomerCoupons(CategoryType.ELECTRONICS));

        System.out.println("--------------------------------------------------------------------------");

        TestUtil.testInfo("getCustomerCoupons(MaxPrice");
        System.out.println(customerService.getCustomerCoupons(260));
    }

}
