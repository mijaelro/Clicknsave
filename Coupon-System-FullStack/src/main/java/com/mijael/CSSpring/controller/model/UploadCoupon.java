package com.mijael.CSSpring.controller.model;

import com.mijael.CSSpring.enums.CategoryType;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UploadCoupon {
    private int id;

    private int companyId;

    @Enumerated(EnumType.STRING)
    private CategoryType categoryType;

    private String title;

    private String description;

    private Date startDate;

    private Date endDate;

    private int amount;

    private double price;

    private MultipartFile image;


}


