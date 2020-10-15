package com.trade.info;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class TradeAccountServiceTest {

	private Optional<TradeAccount> tradeAccountOp;
	
	private TradeAccount tradeAccount;
	
	@Mock
	private TradeAccountRepositary tradeAccountRepositary;
	
	@InjectMocks
	private TradeAccountService tradeAccountService;
	
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
	public void saveTradeDetailsTestSucess() {
		 when(tradeAccountRepositary.save((TradeAccount) any())).thenReturn(tradeAccount);
		  TradeAccount savedDetails = tradeAccountService.saveTradeDetails(tradeAccount);
	        Assert.assertEquals(tradeAccount, savedDetails);
	}
	
	@Test(expected =RuntimeException.class)
	public void saveTradeDetailsTestFailure() {
		 when(tradeAccountRepositary.save((TradeAccount) any())).thenThrow(RuntimeException.class);
		  TradeAccount savedDetails = tradeAccountService.saveTradeDetails(tradeAccount);
	        Assert.assertEquals(tradeAccount, savedDetails);
	}
	
	//@Test
    public void updateTradeAccountTest() {
        when(tradeAccountRepositary.findByUserName(tradeAccount.getUserName())).thenReturn(tradeAccountOp);
        tradeAccount.setDuration(100);
        TradeAccount fetchedTrade = tradeAccountService.updateTradeAccount(tradeAccount, tradeAccount.getUserName());
       
        Assert.assertEquals(tradeAccount, fetchedTrade);

    }
}
