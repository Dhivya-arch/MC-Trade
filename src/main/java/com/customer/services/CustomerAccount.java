package com.customer.services;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

/*
 * Customer account entity which uses CUSTOMER_DETAILS table to store data
 */

@Entity
@Component
//@Data
@Table(name = "CUSTOMER_DETAILS")

public class CustomerAccount {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ACCOUNTID")
	private long accountId;

	/*
	 * @OneToOne(cascade = CascadeType.ALL)
	 * 
	 * @JoinColumn(name = "USERNAME") private TradeAccount tradeAccount;
	 */

	@Column(name = "USERNAME", unique = true)
	private String userName;

	@Column(name = "PASSWORD", nullable = false)
	@NotNull(message = "Password cannot be null")
	private String password;

	@Column(name = "FULL_NAME")
	private String fullName;

	@Column(name = "EMAIL")
	@Email
	private String email;

	@Column(name = "ADDRESS")
	private String address;

	@Column(name = "STATE")
	private String state;

	@Column(name = "COUNTRY")
	private String country;

	@Column(name = "MOBILE")
	private long phoneNumber;

	@Column(name = "ACCOUNT_TYPE")
	private String accountType;

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public CustomerAccount(long accountId, String userName,
			@NotNull(message = "Password cannot be null") String password, String fullName, @Email String email,
			String address, String state, String country, long phoneNumber, String accountType) {
		super();
		this.accountId = accountId;
		this.userName = userName;
		this.password = password;
		this.fullName = fullName;
		this.email = email;
		this.address = address;
		this.state = state;
		this.country = country;
		this.phoneNumber = phoneNumber;
		this.accountType = accountType;
	}

	@Override
	public String toString() {
		return "CustomerAccount [accountId=" + accountId + ", userName=" + userName + ", password=" + password
				+ ", fullName=" + fullName + ", email=" + email + ", address=" + address + ", state=" + state
				+ ", country=" + country + ", phoneNumber=" + phoneNumber + ", accountType=" + accountType + "]";
	}

	public CustomerAccount() {

	}
}
