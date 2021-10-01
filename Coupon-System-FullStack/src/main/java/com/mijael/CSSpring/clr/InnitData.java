package com.mijael.CSSpring.clr;

import java.util.Arrays;

import com.mijael.CSSpring.beans.Image;
import com.mijael.CSSpring.enums.ClientType;
import com.mijael.CSSpring.repos.CouponRepository;
import com.mijael.CSSpring.services.SignUpService;
import com.mijael.CSSpring.services.helper.IOService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.mijael.CSSpring.beans.Company;
import com.mijael.CSSpring.beans.Coupon;
import com.mijael.CSSpring.beans.Customer;
import com.mijael.CSSpring.enums.CategoryType;
import com.mijael.CSSpring.repos.CompanyRepository;
import com.mijael.CSSpring.repos.CustomerRepository;
import com.mijael.CSSpring.utils.ArtUtils;

import lombok.RequiredArgsConstructor;

//@Component
@RequiredArgsConstructor
@Order(1)
public class InnitData implements CommandLineRunner {
    private final CompanyRepository companyRepository;
    private final CustomerRepository customerRepository;
    private final SignUpService signUpService;
    private final CouponRepository couponRepository;


    private IOService IOService = new IOService();


    @Override
    public void run(String... args) throws Exception {

        System.out.println(ArtUtils.INNIT_DATA);

        System.out.println(ArtUtils.COUPON);
        System.out.println("Adding coupons");

        Coupon c  = Coupon.createNewCoupon(1,  CategoryType.ELECTRONICS,  IOService.generateImage("Samsung.png"), "Samsung ","Up to 30% Off For Students, Educators & More");
        Coupon c1 = Coupon.createNewCoupon(2, CategoryType.ELECTRONICS, IOService.generateImage("BestBuy.png"),"BestBuy ","Over 50% Off Or More on Electronics");
        Coupon c2 = Coupon.createNewCoupon(3, CategoryType.ELECTRONICS, IOService.generateImage("Origin.png"),"Origin ","Extra 20% Off Sitewide Purchase");
        Coupon c3 = Coupon.createNewCoupon(4, CategoryType.ELECTRONICS, IOService.generateImage("Nintendo.png"),"Nintendo ","$20 Off Select Games");
        Coupon c4 = Coupon.createNewCoupon(5,  CategoryType.ELECTRONICS,  IOService.generateImage("Nvidia.png"),"Nvidia ","$30 Off 3D Wired Glasses");
        Coupon c5 = Coupon.createNewCoupon(0, CategoryType.ELECTRONICS, IOService.generateImage("Amazon.png"),"Amazon ","Up to 70% Off Select Electronics at Outlet");
        Coupon c6 = Coupon.createNewCoupon(0, CategoryType.ELECTRONICS, IOService.generateImage("PSN.png"),"PSN ","Up to 35% Off Playstation Deals");
        Coupon c7 = Coupon.createNewCoupon(0, CategoryType.ELECTRONICS, IOService.generateImage("Acer.png"),"Acer ","$300 Off The Swift 3 Laptop");

        Coupon c8  = Coupon.createNewCoupon(1, CategoryType.RESTAURANT, IOService.generateImage("Dominos.png"),"Dominos","30% Off Select Items");
        Coupon c9  = Coupon.createNewCoupon(2, CategoryType.RESTAURANT, IOService.generateImage("Subway.png"),"SubWay discount","2 Footlongs For the price of 1");
        Coupon c10 = Coupon.createNewCoupon(3, CategoryType.RESTAURANT, IOService.generateImage("PapaJohns.png"),"Papa Johns ","25% Off Your Full-priced Order");
        Coupon c11 = Coupon.createNewCoupon(4, CategoryType.RESTAURANT, IOService.generateImage("McDonalds.png"),"Mc Donald's ","Free fries With Your Online Purchase");
        Coupon c12 = Coupon.createNewCoupon(5, CategoryType.RESTAURANT, IOService.generateImage("BurgerKing.png"),"Burger King ","Buy 1, Get 1 For $1 on Select Entrees");
        Coupon c13 = Coupon.createNewCoupon(0, CategoryType.RESTAURANT, IOService.generateImage("TacoBell.png"),"Taco Bell ","2x1 in any meal");
        Coupon c14 = Coupon.createNewCoupon(0, CategoryType.RESTAURANT,  IOService.generateImage("KFC.png"),"KFC ","all you can eat ");

        Coupon c15 = Coupon.createNewCoupon(1, CategoryType.LIFE_STYLE,  IOService.generateImage("ShoePalace.png"),"Shoe Palace","Air Jordan Retro 9 Pantone Low Men's Lifestyle Shoe with 40%");
        Coupon c16 = Coupon.createNewCoupon(2, CategoryType.LIFE_STYLE,  IOService.generateImage("Fox.png"),"Fox ","40% off on summer collection");
        Coupon c17 = Coupon.createNewCoupon(3, CategoryType.LIFE_STYLE,  IOService.generateImage("AmericanEagle.png"),"American Eagle ","60% off on summer collection");
        Coupon c18 = Coupon.createNewCoupon(4, CategoryType.LIFE_STYLE, IOService.generateImage("H&M.png"),"H&M ","2x1 on summer collection");
        Coupon c19 = Coupon.createNewCoupon(5, CategoryType.LIFE_STYLE,  IOService.generateImage("Nautica.png"),"Nautica ","30% off on denim jeans");
        Coupon c20 = Coupon.createNewCoupon(0, CategoryType.LIFE_STYLE,  IOService.generateImage("Abercrombie.png"),"Abrecrombie ","35% off on v-neck shirts");

        Coupon c21 = Coupon.createNewCoupon(1, CategoryType.FOOD, IOService.generateImage("SamsClub.png"),"Sam's Club ","30% on final payment");
        Coupon c22 = Coupon.createNewCoupon(2, CategoryType.FOOD,  IOService.generateImage("Ampm.png"),"AM/PM ","40% on dairy products");
        Coupon c23 = Coupon.createNewCoupon(3, CategoryType.FOOD, IOService.generateImage("Costco.png"),"Costco ","30% on vegetables and fruits");
        Coupon c24 = Coupon.createNewCoupon(4, CategoryType.FOOD, IOService.generateImage("SuperYudah.png"),"Super Yudah ","30% on meat products");
        Coupon c25 = Coupon.createNewCoupon(5, CategoryType.FOOD,  IOService.generateImage("Markolit.png"),"Hamarkolit ","20% on cereals");
        Coupon c26 = Coupon.createNewCoupon(0, CategoryType.FOOD,  IOService.generateImage("Osherad.png"),"Osher Ad ","30% on candy");

        Coupon c27 = Coupon.createNewCoupon(1, CategoryType.VACATION,  IOService.generateImage("Kayak.png"),"Kayaak ","30% on flights to greece");
        Coupon c28 = Coupon.createNewCoupon(2, CategoryType.VACATION, IOService.generateImage("Airbnb.png"),"AirBnb ","30% on bookings all over the world");
        Coupon c29 = Coupon.createNewCoupon(3, CategoryType.VACATION,  IOService.generateImage("Hotels.png"),"Hotels ","40% on bookings in Prague for the weekend");
        Coupon c30 = Coupon.createNewCoupon(4, CategoryType.VACATION,  IOService.generateImage("Booking.png"),"Booking ","20% on any booking");
        Coupon c31 = Coupon.createNewCoupon(5, CategoryType.VACATION,  IOService.generateImage("Hotwire.png"),"HotWire ","20% on any hotel");
        Coupon c32 = Coupon.createNewCoupon(0, CategoryType.VACATION,  IOService.generateImage("Avis.png"),"Avis ","30% on flights to Portugal");
        Coupon c33 = Coupon.createNewCoupon(0, CategoryType.VACATION,  IOService.generateImage("Elal.png"),"ElAl ","30% on flights to Italy");
        Coupon c34 = Coupon.createNewCoupon(0, CategoryType.VACATION,  IOService.generateImage("OneTravel.png"),"OneTravel ","20% on flights to China");
        Coupon c35 = Coupon.createNewCoupon(0, CategoryType.VACATION,  IOService.generateImage("Xpedia.png"),"Expedia ","30% on flights worldwide");



//        c1.setEndDate(Date.valueOf(LocalDate.now().minusDays(1)));
//        c2.setAmount(0);

        couponRepository.saveAll(Arrays.asList(c, c1, c2, c3, c4, c5, c6, c7, c8,c9,c10,c11,c12,c13,c14,c15,c16,c17,c18,c19,c20,c21,c22,c23,c24,c25,c26,c27,c28,c29,c30,c31,c32,c33,c34,c35));
        System.out.println(Arrays.asList(c, c1, c2, c3, c4, c5, c6, c7, c8,c9,c10,c11,c12,c13,c14,c15,c16,c17,c18,c19,c20,c21,c22,c23,c24,c25,c26,c27,c28,c29,c30,c31,c32,c33,c34,c35));

        System.out.println(
                "------------------------------------------------------------------------------------------------------");
        Company linux = Company.builder().email("linux@linux.com").name("Linux").password("1234")
//                .coupons(Arrays.asList(c, c8, c15,c21,c27))
                .build();

        Company apple = Company.builder().email("apple@apple.com").name("Apple").password("1234")
//                .coupons(Arrays.asList(c1, c9, c16,c22,c28))
                .build();

        Company matrix = Company.builder().email("matrix@matrix.com").name("Matrix").password("1234")
//                .coupons(Arrays.asList(c2,c10,c17,c23,c29))
                .build();

        Company dell = Company.builder().email("dell@dell.com").name("Dell").password("1234")
//                .coupons(Arrays.asList(c3,c11,c18,c24,c30))
                .build();

        Company microsoft = Company.builder().email("micro@soft.com").name("Microsoft").password("1234")
//                .coupons(Arrays.asList(c4,c12,c19,c25,c31))
                .build();


        Company linuxx = Company.builder().email("linuxx@linuxx.com").name("linuxx").password("1234")
//                .coupons(Arrays.asList(c35))
                .build();

        companyRepository.saveAll(Arrays.asList(linux, apple, matrix, dell, linuxx,microsoft));

        System.out.println(ArtUtils.COMPANY);
        System.out.println("adding companies-->");
        System.out.println(Arrays.asList(linux, linuxx, apple, dell, matrix,microsoft));

        System.out.println(
                "------------------------------------------------------------------------------------------------------");

        System.out.println(ArtUtils.CUSTOMER);

        Customer cus1 = Customer.builder().email("moishe@gmail.com").firstName("moshe").lastName("moshiko")
               .password("1234").build();

        Customer cus2 = Customer.builder().email("yoni@gmail.com").firstName("yoni").lastName("eyov").password("1234")
                .build();

        Customer cus3 = Customer.builder().email("shira@hotmail.com").firstName("shira").lastName("cohen")
               .password("1234").build();

        Customer cus4 = Customer.builder().email("shisra@hotmail.com").firstName("shir").lastName("cohl")
                .password("1234").build();


        System.out.println("adding Customers--->");
        customerRepository.saveAll(Arrays.asList(cus1, cus2, cus3, cus4));
        System.out.println(Arrays.asList(cus1, cus2, cus3, cus4));


        System.out.println(
                "------------------------------------------------------------------------------------------------------");
        System.out.println("signUp");
        System.out.println(signUpService.companySignUp("someCompany","some@compp.comm","1234",ClientType.COMPANY));
    }


}
