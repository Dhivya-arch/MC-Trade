package com.customer.services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
 *  CustomerAccountRepositary which holds CustomerAccount entity
 */
@Repository
public interface CustomerAccountRepositary extends JpaRepository<CustomerAccount,Long> {

	/**
	 * find By UserName
	 * 
	 * @param userName
	 * @return
	 */
	public CustomerAccount findByUserName(String userName);
}