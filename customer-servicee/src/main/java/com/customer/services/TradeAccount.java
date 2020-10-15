package com.customer.services;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

/*
 * TradeAccount entity
 */
@Entity
@Component
//@Data
@Table(name = "TRADE_DETAILS")

public class TradeAccount {

	@Id
	@Column(unique = true)
	private String userName;

	@Column(name = "TRADE_DATE", nullable = false)
	private Date tradeDate;

	@Column(name = "TRADE_AMOUNT")
	private int tradeAmount;

	@Column(name = "DURATION")
	private int duration;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(Date tradeDate) {
		this.tradeDate = tradeDate;
	}

	public int getTradeAmount() {
		return tradeAmount;
	}

	public void setTradeAmount(int tradeAmount) {
		this.tradeAmount = tradeAmount;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}
}
