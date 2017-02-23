package com.capgemini.service;

import com.capgemini.Exceptions.InsufficientBalanceException;
import com.capgemini.Exceptions.InsufficientInitialAmountException;
import com.capgemini.Exceptions.InvalidAccountNumberException;
import com.capgemini.model.Account;

public interface AccountService {

	Account createAccount(int accountNumber, int amount) throws InsufficientInitialAmountException;

	int withdraw(int accountNumber, int amount) throws InsufficientBalanceException, InvalidAccountNumberException;

	int deposit(int accountNumber, int amount) throws InvalidAccountNumberException;

}