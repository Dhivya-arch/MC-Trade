package com.customer.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.customer.feign.TradeClient;
import com.customer.services.CustomerAccount;
import com.customer.services.CustomerAccountService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest
public class CreateCustomerAccountControllerTest {

	@Autowired
	private MockMvc mockMvc;

	private CustomerAccount customerAccount;

	@MockBean
	private TradeClient tradeClient;

	@MockBean
	private CustomerAccountService customerAccountService;

	@InjectMocks
	private CreateCustomerAccountController createCustomerAccountController;

	@Before
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(createCustomerAccountController).build();
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
	public void createCustomerAccountSuccess() throws Exception {

		when(customerAccountService.saveUserDetails(any())).thenReturn(customerAccount);
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/create-account").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(customerAccount))).andExpect((MockMvcResultMatchers.status().isOk()))
				.andDo(MockMvcResultHandlers.print());

	}

	@Test
	public void createCustomerAccountFailure() throws Exception {

		when(customerAccountService.saveUserDetails(any())).thenThrow(RuntimeException.class);
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/create-account").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(customerAccount)))
				.andExpect((MockMvcResultMatchers.status().isUnprocessableEntity()))
				.andDo(MockMvcResultHandlers.print());

	}

	@Test
	public void getCustomerDetailsByUsernameTest() throws Exception {
		when(customerAccountService.getCustomerDetailsByUsername(customerAccount.getUserName()))
				.thenReturn(customerAccount);
		mockMvc.perform(
				MockMvcRequestBuilders.get("/api/v1/getCustomerbyUsername/Raj").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void loginAccountTest() throws Exception {

		when(customerAccountService.loginUser(customerAccount.getUserName(), customerAccount.getPassword()))
				.thenReturn(true);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/login-account?userName=Raj&password=pass")
				.contentType(MediaType.ALL_VALUE)).andExpect(MockMvcResultMatchers.status().isOk());
	}

	private static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
