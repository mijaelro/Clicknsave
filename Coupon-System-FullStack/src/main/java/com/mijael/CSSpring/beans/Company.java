package com.mijael.CSSpring.beans;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.springframework.context.annotation.Scope;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Scope("prototype")
@Table(name="companies")
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private int id;

	@Column(unique = true, nullable = false, length = 25)
	private String name;

	@Column(unique = true, nullable = false, length = 80)
	private String email;

	@Column(nullable = false, length = 25)
	private String password;

	@OneToMany(cascade = CascadeType.ALL ,fetch = FetchType.EAGER)
	private List<Coupon> coupons = new ArrayList<>();
}
