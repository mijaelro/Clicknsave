package com.mijael.CSSpring.services;

import java.util.List;

import com.mijael.CSSpring.beans.Company;
import com.mijael.CSSpring.beans.Customer;
import com.mijael.CSSpring.exceptions.IllegalActionException;
import com.mijael.CSSpring.exceptions.SaveException;

public interface AdminService {

	Company addCompany(Company company) throws IllegalActionException, SaveException;

	Company getOneCompany(int id) throws IllegalActionException;

	Company updateCompany(Company company) throws IllegalActionException, SaveException;

	void DeleteCompany(int id) throws IllegalActionException;

	List<Company> getAllCompanies();

	Customer addCustomer(Customer customer) throws IllegalActionException, SaveException;

	Customer updateCustomer(Customer customer) throws IllegalActionException, SaveException;

	void deleteCustomer(int id) throws IllegalActionException;

	List<Customer> getAllCustomers();

	Customer getOneCustomer(int id) throws IllegalActionException;

}
