package com.mijael.CSSpring.impl;

import java.util.ArrayList;
import java.util.List;

import com.mijael.CSSpring.security.TokenManager;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.mijael.CSSpring.beans.Coupon;
import com.mijael.CSSpring.beans.Customer;
import com.mijael.CSSpring.enums.CategoryType;
import com.mijael.CSSpring.exceptions.IllegalActionException;
import com.mijael.CSSpring.exceptions.LoginException;
import com.mijael.CSSpring.exceptions.PurchaseException;
import com.mijael.CSSpring.services.ClientService;
import com.mijael.CSSpring.services.CustomerService;

@Service
@Scope("prototype")
@Getter
@RequiredArgsConstructor
public class CustomerServiceImpl extends ClientService implements CustomerService {

	private final TokenManager tokenManager;
	private int customerId;
	private String customerName;
	private String customerLastName;


	@Override
	public boolean logIn(String email, String password) throws LoginException {
		boolean isLoggedIn = customerRepository.existsByEmailAndPassword(email, password);
		if (!isLoggedIn) {
			throw new LoginException("Error, Unable to logg in.. try again");
		}
		customerId = customerRepository.findByEmailAndPassword(email, password).getId();
		customerName = customerRepository.findByEmailAndPassword(email, password).getFirstName();
		customerLastName = customerRepository.findByEmailAndPassword(email, password).getLastName();
		System.out.println("the customer id is : " + customerId);
		return isLoggedIn;

	}

	@Override
	public Coupon getSingleCoupon(int couponId) throws IllegalActionException {
		return couponRepository.findById(couponId)
				.orElseThrow(() -> new IllegalActionException("coupon by the id: " + couponId + "doesnt exists"));
	}

	@Override
	public Coupon purchaseCoupon(Coupon coupon) throws IllegalActionException, PurchaseException {
		Customer customer = customerRepository.findById(customerId).orElseThrow(
				() -> new IllegalActionException("Error,customer by the id: " + customerId + " does not exists"));
		List<Coupon> customerCoupons = customer.getCoupons();

		int couponId = coupon.getId();
		coupon = couponRepository.findById(couponId)
				.orElseThrow(() -> new IllegalActionException("there is no coupon by the id: " + couponId));
		if (coupon.getEndDate().before(coupon.getStartDate())) {
			throw new PurchaseException("coupon by the id:" + couponId + " has already expired ");
		}
		if (coupon.getAmount() <= 0) {
			throw new PurchaseException("the coupon by the id: " + couponId + "is sold out");
		}
		for (Coupon c : customerCoupons) {
			if (couponId == c.getId()) {
				throw new PurchaseException("Coupon was previously purchased");
			}
		}
		coupon.setAmount(coupon.getAmount() - 1);
		customerCoupons.add(coupon);
		customer.setCoupons(customerCoupons);
		customerRepository.save(customer);
		couponRepository.save(coupon);
		System.out.println("the coupon : " + couponId + " was purchased succesfully");
		return coupon;
	}

	@Override
	public Customer getCustomersDetails() throws IllegalActionException {
		Customer customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new IllegalActionException("there is no customer by the id  " + customerId));
		return customer;
	}

	@Override
	public List<Coupon> getCustomerCoupons() {
		return customerRepository.getOne(customerId).getCoupons();
	}

	@Override
	public List<Coupon> getCustomerCoupons(CategoryType category) throws IllegalActionException {
		List<Coupon> customerCoupons = new ArrayList<Coupon>();
		for (Coupon c : customerRepository.getOne(customerId).getCoupons()) {
			if (c.getCategoryType().equals(category)) {
				customerCoupons.add(c);
			}
			if (category.equals(null)) {
				throw new IllegalActionException("there are no coupons by the category: " + category);
			}
		}
		return customerCoupons;
	}

	@Override
	public List<Coupon> getCustomerCoupons(double maxPrice) {
		List<Coupon> getAll = new ArrayList<>();

		for (Coupon coupon : getCustomerCoupons()) {
			if (coupon.getPrice() <= maxPrice) {
				getAll.add(coupon);

			}
		}
		return getAll;
	}


}
