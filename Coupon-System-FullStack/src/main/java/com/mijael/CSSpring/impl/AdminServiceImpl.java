package com.mijael.CSSpring.impl;

import java.util.List;

import lombok.Getter;
import org.springframework.stereotype.Service;

import com.mijael.CSSpring.beans.Company;
import com.mijael.CSSpring.beans.Customer;
import com.mijael.CSSpring.exceptions.IllegalActionException;
import com.mijael.CSSpring.exceptions.LoginException;
import com.mijael.CSSpring.exceptions.SaveException;
import com.mijael.CSSpring.services.AdminService;
import com.mijael.CSSpring.services.ClientService;

@Service
@Getter
public class AdminServiceImpl extends ClientService implements AdminService {


	@Override
	public boolean logIn(String email, String password) throws LoginException {
		boolean isLoggedIn = (email.equals("admin@admin.com") && password.equals("admin"));
		if (!isLoggedIn) {
			throw new LoginException("Error , unable to logg in .. try again");
		}
		return true;
	}

	@Override
	public Company addCompany(Company company) throws IllegalActionException, SaveException {
		if (companyRepository.existsByName(company.getName())) {
			throw new IllegalActionException("The name for the company: " + company.getId()
					+ " already exists... try with different attributes");
		}
		if (companyRepository.existsByEmail(company.getEmail())) {
			throw new IllegalActionException("The email for the company: " + company.getId()
					+ " already exists... try with different attributes ");
		}
		try {
			companyRepository.saveAndFlush(company);
		} catch (Exception e) {
			throw new SaveException(e.getMessage());
		}
		return company;
	}

	@Override
	public Company getOneCompany(int id) throws IllegalActionException {
		return companyRepository.findById(id)
				.orElseThrow(() -> new IllegalActionException("there is no company with the id: " + id));
	}

	@Override
	public Company updateCompany(Company company) throws IllegalActionException, SaveException {
		Company toUpdate = companyRepository.findById(company.getId())
				.orElseThrow(() -> new IllegalActionException("there is no company by the id: " + company.getId()));
		try {
			company.setId(toUpdate.getId());
			companyRepository.saveAndFlush(company);
		} catch (Exception e) {
			throw new SaveException("name or email (which are unique) already exist,try with different attributes");
		}
        return company;
    }

	@Override
	public void DeleteCompany(int id) throws IllegalActionException {
		Company company = companyRepository.findById(id)
				.orElseThrow(() -> new IllegalActionException("there is no company by the id : " + id));
		companyRepository.delete(company);
		System.out.println("company: " + company.getName() + " was deleted succesfully");
	}

	@Override
	public List<Company> getAllCompanies() {
		return companyRepository.findAll();
	}

	@Override
	public Customer addCustomer(Customer customer) throws IllegalActionException, SaveException {
		if (customerRepository.existsByEmail(customer.getEmail())) {
			throw new IllegalActionException("Error , email: " + customer.getEmail()
					+ "already exists , try adding a customer with a different email");
		}
		try {
			customerRepository.saveAndFlush(customer);
		} catch (Exception e) {
			throw new SaveException(e.getMessage());
		}
        return customer;
    }

	@Override
	public Customer updateCustomer(Customer customer) throws IllegalActionException, SaveException {
		Customer toUpdate = customerRepository.findById(customer.getId()).orElseThrow(
				() -> new IllegalActionException("customer by the id: " + customer.getId() + "doesnt exist"));
		try {
			customer.setId(toUpdate.getId());
			customerRepository.saveAndFlush(customer);
		} catch (Exception e) {
			throw new SaveException(
					"The email you want is already taken (email is unique) , try with different attributes..");
		}
		return customer;
	}

	@Override
	public void deleteCustomer(int id) throws IllegalActionException {
		Customer customer = customerRepository.findById(id)
				.orElseThrow(() -> new IllegalActionException("the customer by the id: " + id + "doesnt exist"));
		customerRepository.delete(customer);

		System.out.println("customer " + customer.getFirstName() + " was deleted succesfully");
	}

	@Override
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	@Override
	public Customer getOneCustomer(int id) throws IllegalActionException {

		return customerRepository.findById(id).orElseThrow(
				() -> new IllegalActionException("No customer by the id: " + id + ", try a diferent one "));
	}


}
