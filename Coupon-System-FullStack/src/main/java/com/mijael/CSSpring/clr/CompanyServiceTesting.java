package com.mijael.CSSpring.clr;

import java.sql.Date;
import java.time.LocalDate;

import com.mijael.CSSpring.services.helper.IOService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.mijael.CSSpring.beans.Coupon;
import com.mijael.CSSpring.enums.CategoryType;
import com.mijael.CSSpring.enums.ClientType;
import com.mijael.CSSpring.security.Information;
import com.mijael.CSSpring.security.TokenManager;
import com.mijael.CSSpring.services.CompanyService;
import com.mijael.CSSpring.services.LoginManagerService;
import com.mijael.CSSpring.utils.ArtUtils;
import com.mijael.CSSpring.utils.TestUtil;

import lombok.RequiredArgsConstructor;

//@Component
//@Order(3)
@RequiredArgsConstructor
public class CompanyServiceTesting implements CommandLineRunner {

    private final LoginManagerService loginManagerService;
    private final TokenManager tokenManager;
    private IOService IOService = new IOService();

    @Override
    public void run(String... args) throws Exception {

        System.out.println(ArtUtils.COMPANY_SERVICE_TESTING);

        

        TestUtil.testInfo("Logg in [GOOD]");
        String token = loginManagerService.logIn("apple@apple.com", "1234", ClientType.COMPANY);
        System.out.println(token);
        Information information = tokenManager.getMap().get(token);
        System.out.println(information);
        CompanyService companyService = (CompanyService) information.getClientService();
        // TestUtil.testInfo("Logg in [BAD]");

        // System.out.println(loginManagerService.logIn("apple@apple.comm", "1234m",
        // ClientType.COMPANY));

        System.out.println("--------------------------------------------------------------------------------");





        TestUtil.testInfo("addCoupon [GOOD]");
        Coupon c1 = Coupon.builder().amount(2).categoryType(CategoryType.ELECTRONICS).companyId(2).description("desc")
                .endDate(Date.valueOf(LocalDate.now().plusYears(1))).startDate(Date.valueOf(LocalDate.now()))
                .image( IOService.generateImage("Apple.png")).price(10).title("newC")
                .build();
        Coupon c2 = Coupon.builder().amount(2).categoryType(CategoryType.ELECTRONICS).companyId(2).description("descrip")
                .endDate(Date.valueOf(LocalDate.now().plusYears(1))).startDate(Date.valueOf(LocalDate.now())).image( IOService.generateImage("Apple.png")).price(102)
                .title("newC").build();

        companyService.addCoupon(c1);
        System.out.println(c1);

        // TestUtil.testInfo("addCoupon [BAD->title already exist]");

        // companyServiceImpl.addCoupon(c2);
        // System.out.println(c2);

        System.out.println("--------------------------------------------------------------------------------");

        TestUtil.testInfo("deleteCoupon [GOOD]");
//        companyService.deleteCoupon(7);

        // TestUtil.testInfo("deleteCoupon [BAD]");

        // companyServiceImpl.deleteCoupon(0);

        System.out.println("--------------------------------------------------------------------------------");

        TestUtil.testInfo("getOneCoupon [GOOD]");
		System.out.println(companyService.getOneCoupon(3));

        // TestUtil.testInfo("getOneCoupon [BAD-> coupon doesnt exist]");

        // System.out.println(companyServiceImpl.getOneCoupon(0));

        System.out.println("--------------------------------------------------------------------------------");

        TestUtil.testInfo("updateCoupon [GOOD]");

//        c1.setAmount(3);
//        c1.setDescription("oh what a description");
//        c1.setEndDate(Date.valueOf(LocalDate.now().minusDays(1)));
//        c1.setStartDate(Date.valueOf(LocalDate.now()));
//        c1.setImage( IOService.generateImage("Apple.png"));
//        c1.setPrice(200.3);
//        c1.setTitle("NewTitle111");
//        companyService.updateCoupon(c1);
//        System.out.println("updated coupon---->");
//        System.out.println(c1);
        // TestUtil.testInfo("updateCoupon [BAD-coupon doesnt exist]");

        // c2.setTitle("lets try it out");
        // companyServiceImpl.updateCoupon(c2);

        // TestUtil.testInfo("updateCoupon [BAD-coupon's title already exist]");

        // c1.setTitle("NewTitle1");
        // companyServiceImpl.updateCoupon(c1);
        // System.out.println(c1);

        System.out.println("--------------------------------------------------------------------------------");

        TestUtil.testInfo("getCompanyDetails");
        System.out.println(companyService.getCompanyDetails());

        System.out.println("--------------------------------------------------------------------------------");

        TestUtil.testInfo("getCompanyCoupons");
//        companyService.getCompanyCoupons().forEach(System.out::println);

        System.out.println("--------------------------------------------------------------------------------");

        TestUtil.testInfo("getCompanyCouponsByCategory[GOOD]");
//        companyService.getCompanyCoupons(CategoryType.RESTAURANT).forEach(System.out::println);

        System.out.println("--------------------------------------------------------------------------------");

        TestUtil.testInfo("getCompanyCouponsByMaxPrice");
//        companyService.getCompanyCoupons(38.00).forEach(System.out::println);

    }

}
