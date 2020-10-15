package com.customer;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import com.customer.services.CustomerAccount;
import com.customer.services.CustomerAccountRepositary;
import com.customer.services.CustomerAccountService;


@RunWith(SpringRunner.class)
@SpringBootTest
@Component
public class CustomerServiceApplicationTests {

	@Autowired
	private CustomerAccountService service;
	
	
	private CustomerAccount customerAccount;
	
	@MockBean
	@Autowired
	private CustomerAccountRepositary repositary;
	
	@Test
	public void createTradeAccountTest() throws Exception{
		
		CustomerAccount customer = new CustomerAccount(100045,"Raj","pass","Rajgoyal","raj@gmail.com","Chennai","TN","India", 46347,"default");
		when(repositary.save(customer)).thenReturn(customer);
		assertEquals(customer,service.saveUserDetails(customer));
	}
	
	/*
	 * @Test public void getCustomerDetailsByUsernameTest() {
	 * 
	 * String username = "Raj"; when(repositary.findByUserName(username)).
	 * thenReturn(Stream.of( new
	 * CustomerAccount(1,"Raj","pass","Rajgoyal","raj@gmail.com","Chennai","TN",
	 * "India", 546347,"default")). collect(Collectors.toList()));
	 * 
	 * 
	 * }
	 */
	
	
	
}
