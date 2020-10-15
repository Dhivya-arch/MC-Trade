package com.trade.controller;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trade.info.TradeAccount;
import com.trade.info.TradeAccountService;

/*
 * TradeInformationController contains trade specific information of a customer/trader 
 */
@RestController
@RequestMapping("/api/v1")
public class TradeInformationController {

	@Autowired
	private TradeAccountService tradeAccountService;

	static Logger logger = LogManager.getLogger(TradeInformationController.class);

	/**
	 * createTradeAccount with unique username from customer service
	 * 
	 * @param requestDetails
	 * @param erros
	 * @return
	 * @throws Exception
	 */
	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE }, value = "/create-trade")
	public ResponseEntity<TradeAccount> createTradeAccount(@Valid @RequestBody TradeAccount requestDetails,
			Errors erros) throws Exception {

		logger.debug("Inside create trade account");
		TradeAccount tradeAccount = null;
		try {

			tradeAccount = tradeAccountService.saveTradeDetails(requestDetails);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}

		return new ResponseEntity<TradeAccount>(tradeAccount, HttpStatus.OK);

	}

	/**
	 * update TradeAccount using username
	 * 
	 * @param updateDetails
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	@PutMapping("/update-trade/{userName}")
	public TradeAccount updateTradeAccount(@RequestBody TradeAccount updateDetails, @PathVariable String userName)
			throws Exception {

		logger.debug("Inside update trade account controller");
		// TradeAccount updatedAccount =
		return tradeAccountService.updateTradeAccount(updateDetails, userName);

	}

}
