package com.customer.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 * Business implementations for customer account creation
 */
@Service
public class CustomerAccountService {

	static Logger logger = LogManager.getLogger(CustomerAccountService.class);
	@Autowired
	private CustomerAccountRepositary customerAccountRepositary;

	// @Autowired
	// private CustomerAccount savedEntity;

	/**
	 * save Customer/Trader Details
	 * 
	 * @param userDetails
	 * @return
	 * @throws Exception
	 */
	public CustomerAccount saveUserDetails(CustomerAccount userDetails) throws Exception {

		logger.debug("Inside save customer details method");
		CustomerAccount savedEntity = null;
		try {
			if (userDetails != null) {
				savedEntity = customerAccountRepositary.save(userDetails);
				return savedEntity;
			}
		} catch (Exception e) {
			throw new RuntimeException("User Account Not Created" + e);
		}
		return savedEntity;

	}

	/**
	 * get Customer Details By Username
	 * 
	 * @param userName
	 * @return
	 */
	public CustomerAccount getCustomerDetailsByUsername(String userName) {

		logger.debug("Inside getCustomerDetailsByUsername");
		return customerAccountRepositary.findByUserName(userName);
	}

	/**
	 * login User
	 * 
	 * @param userName
	 * @param password
	 * @return
	 */
	public boolean loginUser(String userName, String password) {

		logger.debug("Inside loginUser");
		CustomerAccount logindetails = customerAccountRepositary.findByUserName(userName);

		if (logindetails.getUserName().equals(userName) && logindetails.getPassword().equals(password)) {
			return true;
		} else
			return false;
	}

}
