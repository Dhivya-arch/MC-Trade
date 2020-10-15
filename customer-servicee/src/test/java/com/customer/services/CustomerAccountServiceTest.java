package com.customer.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class CustomerAccountServiceTest {

	private CustomerAccount customerAccount;

	@Mock
	private CustomerAccountRepositary customerAccountRepositary;

	@InjectMocks
	private CustomerAccountService customerAccountService;

	@Before
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);
		customerAccount = new CustomerAccount();

		customerAccount.setAccountId(100045);
		customerAccount.setUserName("Raj");
		customerAccount.setPassword("pass");
		customerAccount.setFullName("Rajgoyal");
		customerAccount.setAddress("Chennai");
		customerAccount.setEmail("raj@gmail.com");
		customerAccount.setState("TN");
		customerAccount.setCountry("India");
		customerAccount.setPhoneNumber(78656);
		customerAccount.setAccountType("Default");

	}

	@Test
	public void saveUserDetailsTestSucess() throws Exception {
		when(customerAccountRepositary.save((CustomerAccount) any())).thenReturn(customerAccount);
		CustomerAccount savedDetails = customerAccountService.saveUserDetails(customerAccount);
		Assert.assertEquals(customerAccount, savedDetails);
	}

	@Test(expected = RuntimeException.class)
	public void saveUserDetailsTestFailure() throws Exception {

		when(customerAccountRepositary.save((CustomerAccount) any())).thenThrow(RuntimeException.class);
		CustomerAccount savedDetails = customerAccountService.saveUserDetails(customerAccount);
		Assert.assertEquals(customerAccount, savedDetails);

	}

	@Test
	public void getCustomerDetailsByUsernameTest() throws Exception {

		when(customerAccountRepositary.findByUserName(customerAccount.getUserName())).thenReturn(customerAccount);

		assertEquals(customerAccount,
				customerAccountService.getCustomerDetailsByUsername(customerAccount.getUserName()));

	}

	@Test
	public void loginUserTestSucess() throws Exception {
		when(customerAccountRepositary.findByUserName(customerAccount.getUserName())).thenReturn(customerAccount);

		boolean afterverification = customerAccountService.loginUser(customerAccount.getUserName(),
				customerAccount.getPassword());
		assertEquals(true, afterverification);
	}

	@Test
	public void loginUserTestFailure() throws Exception {
		when(customerAccountRepositary.findByUserName(customerAccount.getUserName())).thenReturn(customerAccount);

		boolean afterverification = customerAccountService.loginUser(customerAccount.getUserName(), "345");
		assertEquals(false, afterverification);
	}
}
