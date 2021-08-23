package com.mijael.CSSpring.services;
import com.mijael.CSSpring.beans.Company;
import com.mijael.CSSpring.beans.Customer;
import com.mijael.CSSpring.enums.ClientType;
import com.mijael.CSSpring.exceptions.IllegalActionException;
import com.mijael.CSSpring.exceptions.SaveException;

public interface SignUpService {

  Company companySignUp(String name, String email, String password,ClientType clientType) throws IllegalActionException , SaveException;
  Customer customerSignUp(String firstName,String lastName, String email, String password) throws IllegalActionException, SaveException ;

}
