package com.mijael.CSSpring.impl;

import java.io.IOException;
import java.util.List;

import com.mijael.CSSpring.controller.model.UploadCoupon;
import com.mijael.CSSpring.security.TokenManager;
import com.mijael.CSSpring.services.ImageService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.mijael.CSSpring.beans.Company;
import com.mijael.CSSpring.beans.Coupon;
import com.mijael.CSSpring.enums.CategoryType;
import com.mijael.CSSpring.exceptions.IllegalActionException;
import com.mijael.CSSpring.exceptions.LoginException;
import com.mijael.CSSpring.exceptions.SaveException;
import com.mijael.CSSpring.services.ClientService;
import com.mijael.CSSpring.services.CompanyService;

@Service
@Scope("prototype")
@Getter
@RequiredArgsConstructor
public class CompanyServiceImpl extends ClientService implements CompanyService {

	private final TokenManager tokenManager;
	private int companyId;
	private String companyName;

	@Autowired
	private  ImageService imageService;

	@Override
	public boolean logIn(String email, String password) throws LoginException {
		boolean isLoggedIn = companyRepository.existsByEmailAndPassword(email, password);
		if (!isLoggedIn) {
			throw new LoginException("Error, Unable to logg in.. try again ");
		}
		companyId = companyRepository.findByEmailAndPassword(email, password).getId();
		companyName = companyRepository.findByEmailAndPassword(email,password).getName();
		System.out.println("the company id is : " + companyId);
		return isLoggedIn;
	}

	@Override
	public UploadCoupon addCouponPayLoad(UploadCoupon coupon) throws IllegalActionException, SaveException, IOException {
		Coupon toAdd = Coupon.builder()
				.amount(coupon.getAmount())
				.companyId(coupon.getCompanyId())
				.categoryType(coupon.getCategoryType())
				.description(coupon.getDescription())
				.endDate(coupon.getEndDate())
				.price(coupon.getPrice())
				.startDate(coupon.getStartDate())
				.title(coupon.getTitle())
				.image(imageService.addImage(coupon.getImage().getBytes()))
				.build();
		if (couponRepository.existsByTitle(toAdd.getTitle())) {
			throw new IllegalActionException(
					String.format("coupon with the title : %s already exists", coupon.getTitle()));
		}
		couponRepository.saveAndFlush(toAdd);
		Coupon c = couponRepository.findCouponByTitle(toAdd.getTitle());
		coupon.setId(c.getId());
        return coupon;
    }

	@Override
	public Coupon updateCoupon(Coupon coupon) throws IllegalActionException, SaveException {
		Coupon toUpdate = couponRepository.findById(coupon.getId()).orElseThrow(
				() -> new IllegalActionException("Error, the coupon by the id: " + coupon.getId() + "doesnt exist"));
		try {
			coupon.setId(toUpdate.getId());
			coupon.setImage(toUpdate.getImage());
			couponRepository.saveAndFlush(coupon);
		} catch (Exception e) {
			throw new SaveException(
					"the title you requested for the coupon already exists (title is unique),try with different attributes...");
		}
		return coupon;
	}


	@Override
	public Coupon addCoupon(Coupon coupon) throws IllegalActionException, SaveException {
		if (couponRepository.existsByTitle(coupon.getTitle())) {
			throw new IllegalActionException(
					String.format("coupon with the title : %s already exists", coupon.getTitle()));
		}
		try {
			couponRepository.saveAndFlush(coupon);
		} catch (Exception e) {
			throw new SaveException(e.getMessage());
		}

		return coupon;
	}


	@Override
	public void deleteCoupon(int id) throws IllegalActionException {
		Coupon coupon = couponRepository.findById(id)
				.orElseThrow(() -> new IllegalActionException("Error, there is no coupon by the id: " + id));
		couponRepository.delete(coupon);

		System.out.println("coupon: " + coupon.getTitle() + "was deleted succesfully");
	}

	@Override
	public Coupon getOneCoupon(int id) throws IllegalActionException {
		return couponRepository.findById(id).orElseThrow(() -> new IllegalActionException(
				"The coupon by the id: " + id + "doesnt exists, try a diferent id..."));
	}





	@Override
	public Company getCompanyDetails() throws IllegalActionException {
		Company company = companyRepository.findById(companyId)
				.orElseThrow(() -> new IllegalActionException("company by the id : " + companyId + " doesnt exists"));
		return company;
	}

	@Override
	public List<Coupon> getCompanyCoupons() {
		 couponRepository.findCouponsByCompanyId(companyId).forEach(System.out::println);
		return couponRepository.findCouponsByCompanyId(companyId);
	}

	@Override
	public List<Coupon> getCompanyCoupons(CategoryType category) {
		return couponRepository.findByCategoryTypeAndCompanyId(category, companyId);
	}

	@Override
	public List<Coupon> getCompanyCoupons(double maxPrice) {
		return couponRepository.findByCompanyIdAndPriceLessThan(companyId, maxPrice);
	}

}
