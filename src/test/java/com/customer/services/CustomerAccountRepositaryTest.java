package com.customer.services;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerAccountRepositaryTest {

	@Autowired
	private CustomerAccountRepositary customerAccountRepositary;
	private CustomerAccount customerAccount;

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
	public void saveUserDetailsTest() {

		customerAccountRepositary.save(customerAccount);
		CustomerAccount fetchedCustomerDetail = customerAccountRepositary.findByUserName(customerAccount.getUserName());
		Assert.assertEquals("Raj", fetchedCustomerDetail.getUserName());

	}

}
