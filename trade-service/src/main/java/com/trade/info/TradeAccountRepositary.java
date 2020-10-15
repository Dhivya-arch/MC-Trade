package com.trade.info;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
 * TradeAccountRepositary to save Trade account information
 */
@Repository
public interface TradeAccountRepositary extends JpaRepository<TradeAccount,String>{

	public Optional<TradeAccount> findByUserName(String userName);
}
