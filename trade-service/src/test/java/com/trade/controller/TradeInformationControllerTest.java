package com.trade.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.ArgumentMatchers.eq;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trade.info.TradeAccount;
import com.trade.info.TradeAccountService;

@RunWith(SpringRunner.class)
@WebMvcTest
public class TradeInformationControllerTest {

	
	
	@Autowired
	private MockMvc mockMvc;

	private TradeAccount tradeAccount;

	@MockBean
	private TradeAccountService tradeAccountService;
	
	@InjectMocks
	private TradeInformationController tradeInformationController;
	
	@Before
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(tradeInformationController).build();
		tradeAccount = new TradeAccount();

		tradeAccount.setUserName("Raj");
		tradeAccount.setDuration(10);
		tradeAccount.setTradeAmount(1200);
		tradeAccount.setTradeDate(new Date());
	}
	
	@Test
	 public void createTradeAccountTestSuccess() throws Exception {
		  
		  when(tradeAccountService.saveTradeDetails(any())).thenReturn(
		  tradeAccount);
		  mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/create-trade")
		  .contentType(MediaType.APPLICATION_JSON).content(asJsonString(tradeAccount)))
		  .andExpect((MockMvcResultMatchers.status().isOk()))
		  .andDo(MockMvcResultHandlers.print());
		  
		  }
	
	@Test
	 public void createTradeAccountTestFailure() throws Exception {
		  
		  when(tradeAccountService.saveTradeDetails(any())).thenThrow(RuntimeException.class);
		  mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/create-trade")
		  .contentType(MediaType.APPLICATION_JSON).content(asJsonString(tradeAccount)))
		  .andExpect((MockMvcResultMatchers.status().isNotAcceptable()))
		  .andDo(MockMvcResultHandlers.print());
		  
		  }
	
	@Test
	public void updateTradeAccountTestSucess() throws Exception {
	 when(tradeAccountService.updateTradeAccount(any(), eq(tradeAccount.getUserName()))).thenReturn(tradeAccount);
     mockMvc.perform(MockMvcRequestBuilders.put("/api/v1//update-trade/Raj")
             .contentType(MediaType.APPLICATION_JSON).content(asJsonString(tradeAccount)))
             .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
	}
	
	//@Test(expected= RuntimeException.class)
	public void updateTradeAccountTestFailure() throws Exception {
	 when(tradeAccountService.updateTradeAccount(any(), eq(tradeAccount.getUserName()))).thenThrow(RuntimeException.class);
     mockMvc.perform(MockMvcRequestBuilders.put("/api/v1//update-trade/Raj")
             .contentType(MediaType.APPLICATION_JSON).content(asJsonString(tradeAccount)))
             .andExpect(MockMvcResultMatchers.status().isInternalServerError()).andDo(MockMvcResultHandlers.print());
	}
	
	 private static String asJsonString(final Object obj) {
			try {
				return new ObjectMapper().writeValueAsString(obj);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
}
