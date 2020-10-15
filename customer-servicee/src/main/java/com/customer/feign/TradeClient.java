package com.customer.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.customer.services.TradeAccount;

/*
 * Trade client interface to connect trade-service via Feign
 */

@FeignClient(name = "trade-service", url = "http://localhost:8080/trade-service", fallback = TradeClientFallback.class) 
public interface TradeClient {

	/**
	 * updateTradeAccount
	 * 
	 * @param updateDetails
	 * @param userName
	 * @return
	 */
	@PutMapping("/update-trade/{userName}")
	Object updateTradeAccount(@RequestBody TradeAccount updateDetails, @PathVariable String userName);

}
