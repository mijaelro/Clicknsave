package com.mijael.CSSpring.beans;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.context.annotation.Scope;

import com.mijael.CSSpring.enums.CategoryType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="coupons")
@Scope("prototype")
public class Coupon {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private int companyId;

    @Enumerated(EnumType.STRING)
    private CategoryType categoryType;

    @Column(nullable = false, unique = true, length = 25)
    private String title;

    @Column(nullable = false, length = 255)
    private String description;

    @Column(nullable = false)
    private Date startDate;

    @Column(nullable = false)
    private Date endDate;

    @Column(nullable = false)
    private int amount;

    @Column(nullable = false)
    private double price;

    @JsonProperty(value = "image")
    @OneToOne(cascade = CascadeType.ALL)
    private Image image ;

    @Singular
    @ManyToMany(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    private List<Customer> customers;

    public static Coupon createNewCoupon(int companyId, CategoryType category,Image image, String title, String description) {
        return Coupon.builder().companyId(companyId).categoryType(category).title(title)
                .description(description).startDate(Date.valueOf(LocalDate.now())).endDate(Date.valueOf(LocalDate.now().plusDays(65)))
                .amount(10).price (Math.round(Math.random() * 40 + 1)).image(image).build();
    }


}
