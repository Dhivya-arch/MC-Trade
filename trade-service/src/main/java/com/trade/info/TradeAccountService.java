package com.trade.info;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/*
 * TradeAccountService to handle business logic of trader information
 */
@Service
public class TradeAccountService {

	static Logger logger = LogManager.getLogger(TradeAccountService.class);

	private TradeAccount tradeAccount;

	@Autowired
	private TradeAccountRepositary tradeAccountRepositary;

	/**
	 * save Trade Details of a trader exists
	 * 
	 * @param tradeDetailS
	 * @return
	 */
	public TradeAccount saveTradeDetails(TradeAccount tradeDetailS) {

		logger.debug("Inside save trade details Service");
		TradeAccount savedEntity = null;
		try {

			if (tradeDetailS != null) {
				savedEntity = tradeAccountRepositary.save(tradeDetailS);
				return savedEntity;
			}
		} catch (Exception e) {
			throw new RuntimeException("User Account Not Created" + e);
		}
		return savedEntity;
	}

	/**
	 * update existing Trade Account
	 * 
	 * @param updateDetails
	 * @param tradeId
	 * @return
	 */
	public TradeAccount updateTradeAccount(TradeAccount updateDetails, String userName) {
		/*
		 * TradeAccount recordexists = tradeAccountRepositary.findByUserName(userName);
		 * if(recordexists!= null) return true; else return false;
		 */
		// TradeAccount accountToBeUpdated = null;

		// Optional<TradeAccount> accountToBeUpdated=
		// tradeAccountRepositary.getOne(userName);

		logger.debug("Inside update trade account service");

		Optional<TradeAccount> accountToBeUpdatedOp = tradeAccountRepositary.findByUserName(userName);

		TradeAccount accountUpdated = null;
		TradeAccount accountToBeUpdated = null;
		
		if (accountToBeUpdatedOp.isPresent()) {
			
			accountToBeUpdated = accountToBeUpdatedOp.get();
			
			if (updateDetails.getDuration() != 0)
				accountToBeUpdated.setDuration(updateDetails.getDuration());
			else if (updateDetails.getTradeAmount() != 0)
				accountToBeUpdated.setTradeAmount(updateDetails.getTradeAmount());
			else
				accountToBeUpdated.setTradeDate(updateDetails.getTradeDate());

			
			try {
				
				accountUpdated = tradeAccountRepositary.saveAndFlush(accountToBeUpdated);
				
			} catch (Exception e) {
				logger.error("Error in trade service" + e);
			}

		}

		return accountUpdated;

	}
}
