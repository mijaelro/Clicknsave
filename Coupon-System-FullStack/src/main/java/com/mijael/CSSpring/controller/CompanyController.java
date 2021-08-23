package com.mijael.CSSpring.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.List;

import com.mijael.CSSpring.beans.Image;
import com.mijael.CSSpring.controller.model.UploadCoupon;
import com.mijael.CSSpring.security.TokenManager;
import com.mijael.CSSpring.services.ImageService;
import com.mijael.CSSpring.services.helper.IOService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.mijael.CSSpring.beans.Coupon;
import com.mijael.CSSpring.enums.CategoryType;
import com.mijael.CSSpring.exceptions.IllegalActionException;
import com.mijael.CSSpring.exceptions.SaveException;
import com.mijael.CSSpring.services.CompanyService;

import lombok.RequiredArgsConstructor;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("company")
@RequiredArgsConstructor
@CrossOrigin(origins="*",allowedHeaders="*")

   public class CompanyController  {

    private final TokenManager tokenManager;
    private final IOService IOService;
    private final ImageService imageService;

    @Autowired
    private  CompanyService companyService;

 @RequestMapping(value = "/images/{id}", method = RequestMethod.GET)
 public String getImage(@PathVariable int id , HttpServletResponse response) throws IOException, IllegalActionException {
        Coupon coupon = companyService.getOneCoupon(id);
        Image image =  coupon.getImage();
        System.out.println("test imagee1234213"+image);
    response.setHeader("Content-Disposition", "inline;filename=\"" + image.getImage().toString() + "\"");
    OutputStream out = response.getOutputStream();
    response.setContentType(MediaType.IMAGE_PNG_VALUE);
    IOUtils.copy(new ByteArrayInputStream(image.getImage()), out);
    out.close();
  return null;
 }


    @PostMapping( value = "coupon"
            ,
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @ResponseStatus(code = HttpStatus.CREATED)
    public @ResponseBody
    UploadCoupon addCoupon(@RequestHeader ("Authorization") String token ,@ModelAttribute UploadCoupon coupon)
            throws IllegalActionException, SaveException, SQLException, IOException {

     companyService= (CompanyService) tokenManager.getService(token);
     return companyService.addCouponPayLoad(coupon);
    }

    @PutMapping("coupon")
    @ResponseStatus(code = HttpStatus.OK)
    public Coupon updateCoupon(@RequestHeader ("Authorization") String token ,@RequestBody Coupon coupon)
            throws IllegalActionException, SaveException, IOException {
      companyService= (CompanyService) tokenManager.getService(token);
    return companyService.updateCoupon(coupon);
    }

    @DeleteMapping("coupon/{couponId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteCoupon(@RequestHeader ("Authorization") String token ,@PathVariable int couponId)
            throws IllegalActionException{
     companyService= (CompanyService) tokenManager.getService(token);
        companyService.deleteCoupon(couponId);
    }

    @GetMapping("coupon/{couponId}")
    @ResponseStatus(code = HttpStatus.OK)
    public Coupon getCoupon(@RequestHeader ("Authorization") String token ,@PathVariable int couponId)
            throws IllegalActionException{
     companyService= (CompanyService) tokenManager.getService(token);
        return companyService.getOneCoupon(couponId);
    }

    @GetMapping("coupons")
    @ResponseStatus(code = HttpStatus.OK)
    public List<Coupon> getCompanyCoupons(@RequestHeader ("Authorization") String token ) {
     System.out.println(token);
     System.out.println(companyService);
     companyService= (CompanyService) tokenManager.getService(token);
     return companyService.getCompanyCoupons();
    }

    @GetMapping("coupons/{category}")
    @ResponseStatus(code = HttpStatus.OK)
    public List<Coupon> getCompanyCoupons(@RequestHeader ("Authorization") String token ,@PathVariable CategoryType category) {
     companyService= (CompanyService) tokenManager.getService(token);
        return companyService.getCompanyCoupons(category);
    }

    @GetMapping("coupons/{maxPrice}")
    @ResponseStatus(code = HttpStatus.OK)
    public List<Coupon> getCompanyCoupons(@RequestHeader ("Authorization") String token ,@PathVariable double maxPrice){
     companyService= (CompanyService) tokenManager.getService(token);
        return companyService.getCompanyCoupons(maxPrice);
    }

    @GetMapping("details")
    @ResponseStatus(code = HttpStatus.OK)
    public void getCompanyDetails(@RequestHeader ("Authorization") String token )
            throws IllegalActionException{
     companyService= (CompanyService) tokenManager.getService(token);
        companyService.getCompanyDetails();
    }
}
