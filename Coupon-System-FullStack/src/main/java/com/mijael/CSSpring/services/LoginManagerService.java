package com.mijael.CSSpring.services;

import com.mijael.CSSpring.exceptions.IllegalActionException;
import com.mijael.CSSpring.repos.CompanyRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.mijael.CSSpring.enums.ClientType;
import com.mijael.CSSpring.exceptions.LoginException;
import com.mijael.CSSpring.exceptions.SecurityException;
import com.mijael.CSSpring.impl.AdminServiceImpl;
import com.mijael.CSSpring.impl.CompanyServiceImpl;
import com.mijael.CSSpring.impl.CustomerServiceImpl;
import com.mijael.CSSpring.security.TokenManager;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginManagerService {

    private final ApplicationContext ctx;
    private final TokenManager tokenManager;

    public String logIn(String email, String password, ClientType clientType) throws SecurityException, LoginException, IllegalActionException {

        switch (clientType) {

            case ADMINISTRATOR:
                AdminService adminService = ctx.getBean(AdminService.class);

                if (((AdminServiceImpl) adminService).logIn(email, password)) {
                    int adminId = 0;
                    String name = "ADMIN";
                    String token = tokenManager.addToken((ClientService) adminService, ClientType.ADMINISTRATOR, adminId,name);
                    return token;
                }
            case COMPANY:
                CompanyService companyService = ctx.getBean(CompanyService.class);
                if (((CompanyServiceImpl) companyService).logIn(email, password)) {
                    int companyId = ((CompanyServiceImpl) companyService).getCompanyId();
                    String name = ((CompanyServiceImpl)companyService).getCompanyName();
                    String token = tokenManager.addToken((ClientService) companyService, ClientType.COMPANY, companyId,name);
                    return token;
                }
            case CUSTOMER:
                CustomerService customerService = ctx.getBean(CustomerService.class);
                if (((CustomerServiceImpl) customerService).logIn(email, password)) {
                    int customerId = ((CustomerServiceImpl) customerService).getCustomerId();
                    String name = ((CustomerServiceImpl)customerService).getCustomerName();
                    String token = tokenManager.addToken((ClientService) customerService, ClientType.CUSTOMER, customerId ,name);
                    return token;
                }
        }
        throw new SecurityException("Error,Incorrect client type");
    }

}
