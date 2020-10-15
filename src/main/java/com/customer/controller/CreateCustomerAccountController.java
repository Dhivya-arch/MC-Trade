package com.customer.controller;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.customer.feign.TradeClient;
import com.customer.services.CustomerAccount;
import com.customer.services.CustomerAccountService;
import com.customer.services.TradeAccount;

/*
 * Managing trader/customer basic information in to the database.
 * View/update the trade information as well as trader details
 */

@RestController
@RequestMapping("/api/v1")
public class CreateCustomerAccountController {

	@Autowired
	private CustomerAccountService customerAccountService;

	@Autowired
	private TradeClient tradeClient;

	static Logger logger = LogManager.getLogger(CreateCustomerAccountController.class);

	/**
	 * To create Customer/Trader Account
	 * 
	 * @param requestDetails
	 * @param erros
	 * @return
	 * @throws Exception
	 */
	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE }, value = "/create-account")
	public ResponseEntity<String> createCustomerAccount(@Valid @RequestBody CustomerAccount requestDetails,
			Errors erros) throws Exception {
		logger.debug("Inside createCustomerAccount");
		CustomerAccount customerAccount = null;

		try {
			customerAccount = customerAccountService.saveUserDetails(requestDetails);
		} catch (RuntimeException e) {
			return ResponseEntity.unprocessableEntity()
					.body("Error: User account not created . Your USERNAME should be unique");
		}
		// if(e.getClass() == (DataIntegrityViolationException))
		// throw new CustomException("User name " + requestDetails.getUserName()
		// +"already exisits in our database");

		return ResponseEntity
				.ok("Customer account created successfully for the Username: " + customerAccount.getUserName());

	}

	/**
	 * Customer logins into their account
	 * 
	 * @param userName
	 * @param password
	 * @return
	 */
	@GetMapping("/login-account")
	public String loginAccount(@RequestParam String userName, @RequestParam String password) {

		logger.debug("Inside login account method");
		boolean status = customerAccountService.loginUser(userName, password);

		if (status)
			return "Login Successfull !!! ";
		else
			return "Your Username or Password is incorrect.Please try again";
	}

	/**
	 * Get Customer Details By Username
	 * 
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/getCustomerbyUsername/{userName}")
	public ResponseEntity<CustomerAccount> getCustomerDetailsByUsername(@PathVariable String userName)
			throws Exception {

		logger.debug("Inside getcustomerdetailsby  username method");
		CustomerAccount entityexists = customerAccountService.getCustomerDetailsByUsername(userName);

		if (entityexists == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<CustomerAccount>(entityexists, HttpStatus.OK);

	}

	/**
	 * Update Trade Details By Username
	 * 
	 * @param updateDetails
	 * @param userName
	 * @return
	 */
	@PutMapping("/update-trade/{userName}")
	public Object updateTradeByUsername(@RequestBody TradeAccount updateDetails, @PathVariable String userName) {

		logger.debug("Inside getcustomerdetailsby  username method");

		return tradeClient.updateTradeAccount(updateDetails, userName);

	}
}
