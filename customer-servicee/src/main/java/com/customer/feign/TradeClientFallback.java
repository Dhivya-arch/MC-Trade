package com.customer.feign;

import java.util.Collections;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.customer.services.TradeAccount;

@Component
public class TradeClientFallback implements TradeClient{

	@Override
	public Object updateTradeAccount(@RequestBody TradeAccount updateDetails,@PathVariable String userName) {
		return Collections.emptyList();
	}
	
}
