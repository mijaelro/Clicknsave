package com.mijael.CSSpring.beans;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Data
@SuperBuilder
@Table(name="images")
@NoArgsConstructor
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;

    @OneToOne()
    private Coupon coupon;

    @Lob
    private byte[] image;

    public Image(byte[] image) {
        super();
        this.image = image;
    }
}
