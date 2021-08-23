package com.mijael.CSSpring.services;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.mijael.CSSpring.beans.Company;
import com.mijael.CSSpring.beans.Coupon;
import com.mijael.CSSpring.controller.model.UploadCoupon;
import com.mijael.CSSpring.enums.CategoryType;
import com.mijael.CSSpring.exceptions.IllegalActionException;
import com.mijael.CSSpring.exceptions.SaveException;

public interface CompanyService {


	Coupon addCoupon(Coupon coupon) throws IllegalActionException, SaveException;

	UploadCoupon addCouponPayLoad(UploadCoupon coupon) throws IllegalActionException, SaveException, SQLException, IOException;

	void deleteCoupon(int id) throws IllegalActionException;

	Coupon getOneCoupon(int id) throws IllegalActionException;

	Coupon updateCoupon(Coupon coupon) throws IllegalActionException, SaveException;

	Company getCompanyDetails() throws IllegalActionException;

	List<Coupon> getCompanyCoupons();

	List<Coupon> getCompanyCoupons(CategoryType category);

	List<Coupon> getCompanyCoupons(double maxPrice);

}
