package com.mijael.CSSpring.filter;

import com.mijael.CSSpring.enums.ClientType;
import com.mijael.CSSpring.security.TokenManager;
import com.mijael.CSSpring.services.AdminService;
import com.mijael.CSSpring.services.CompanyService;
import com.mijael.CSSpring.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Order(2)
@Component
@RequiredArgsConstructor
public class TokenFilter implements Filter {

    private final TokenManager tokenManager;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private AdminService adminService;

    private final static String ADMIN = "admin";
    private final static String CUSTOMER = "customer";
    private final static String COMPANY = "company";


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {


        String url = ((HttpServletRequest) servletRequest).getRequestURI();
        System.out.println("url : " + url);


        if (    url.endsWith("login")
                || url.endsWith("signup")
                || url.endsWith("signup1")
                ||url.endsWith("coupons")
                ||url.endsWith("start")
                ||url.endsWith("coupon")
                ||url.endsWith("1")
                ||url.endsWith("2")
                ||url.endsWith("3")
                ||url.endsWith("4")
                ||url.endsWith("5")
                ||url.endsWith("6")
                ||url.endsWith("7")
                ||url.endsWith("8")
                ||url.endsWith("9")
                ||url.endsWith("0")
                ||url.endsWith("home")
                ||url.endsWith("/")
                ||url.endsWith("*")
        )
                {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        try {
             String token;
             token = ((HttpServletRequest) servletRequest).getHeader("Authorization");
            String type = FilterHelper.getType(url);
            System.out.println("type : " + type);

            switch (type) {
                case ADMIN:
                    adminService = (AdminService) tokenManager.getService(token);
                    tokenManager.isControllerAllowed(ClientType.ADMINISTRATOR,token);
                    break;
                case CUSTOMER:
                    customerService = (CustomerService) tokenManager.getService(token);
                    tokenManager.isControllerAllowed( ClientType.CUSTOMER,token);
                    break;
                case COMPANY:
                    tokenManager.isControllerAllowed(ClientType.COMPANY,token);
                    break;
            }
            filterChain.doFilter(servletRequest,servletResponse);

        } catch (Exception e) {
            ((HttpServletResponse) servletResponse).sendError(401, e.getMessage());

        }
    }
}


