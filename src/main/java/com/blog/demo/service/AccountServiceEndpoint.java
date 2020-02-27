package com.blog.demo.service;

import com.blog.demo.webservices.accountservice.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Service
public class AccountServiceEndpoint implements com.briansdevblog.accounts.AccountService {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Override
	public AccountDetailsResponse getAccountDetails(AccountDetailsRequest parameters) {

		ObjectFactory factory = new ObjectFactory();
		AccountDetailsResponse response = factory.createAccountDetailsResponse();
		
		Account account = factory.createAccount();
		account.setAccountNumber("12345");
		account.setAccountStatus(EnumAccountStatus.ACTIVE);
		account.setAccountName("Joe Bloggs");
		account.setAccountBalance(3400);
		
		response.setAccountDetails(account);		
		return response;
	}

	@Override
	public SaveAccountDetailsResponse saveAccountDetails(SaveAccountDetailsRequest parameters) {
		ObjectFactory objectFactory = new ObjectFactory();
		ListenableFuture<SendResult<String, String>> result =
				kafkaTemplate.send("accounts", "1", parameters.getAccountNumber());
		try {
			result.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return objectFactory.createSaveAccountDetailsResponse();
	}


}