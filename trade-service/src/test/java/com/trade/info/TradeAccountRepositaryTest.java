package com.trade.info;

import java.util.Date;
import java.util.Optional;

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
public class TradeAccountRepositaryTest {

	private Optional<TradeAccount> tradeAccountOp;
	
	@Autowired
	private TradeAccountRepositary tradeAccountRepositary;
	
	
	private TradeAccount tradeAccount;
	
	@Before
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);
		tradeAccount = new TradeAccount();

		
		tradeAccount.setUserName("Raj");
		tradeAccount.setDuration(10);
		tradeAccount.setTradeAmount(1200);
		tradeAccount.setTradeDate(new Date());
		
		tradeAccountOp = Optional.of(tradeAccount);
		
	}
	
	 @Test
	    public void saveTradeDetailsTest() {

		 tradeAccountRepositary.save(tradeAccount);
	    	Optional<TradeAccount> fetchedCustomerDetail = tradeAccountRepositary.findByUserName(tradeAccount.getUserName());
	        Assert.assertEquals("Raj", fetchedCustomerDetail.get().getUserName());

	    }
}
