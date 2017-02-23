package com.capgemini.service;

import com.capgemini.Exceptions.InsufficientBalanceException;
import com.capgemini.Exceptions.InsufficientInitialAmountException;
import com.capgemini.Exceptions.InvalidAccountNumberException;
import com.capgemini.model.Account;
import com.capgemini.repository.AccountRepository;

public class AccountServiceImpl implements AccountService {

	AccountRepository accountRepository;

	public AccountServiceImpl(AccountRepository accountRepository) {
		super();
		this.accountRepository = accountRepository;
	}


	/* (non-Javadoc)
	 * @see com.capgemini.service.AccountService#createAccount(int, int)
	 */
	@Override
	public Account createAccount(int accountNumber, int amount) throws InsufficientInitialAmountException {
		System.out.println("Amount:"+amount);
		if (amount < 500)
		{
			System.out.println("Amount:"+amount);
			throw new InsufficientInitialAmountException();
		}
		Account account = new Account();
		account.setAccountNumber(accountNumber);
		account.setAmonut(amount);

		if (accountRepository.save(account))
		{	
			System.out.println("Account created");
			return account;
		}
		else
			return null;
	}
	
	
	/* (non-Javadoc)
	 * @see com.capgemini.service.AccountService#withdraw(int, int)
	 */
	@Override
	public int withdraw(int accountNumber, int amount) throws InsufficientBalanceException, InvalidAccountNumberException
	{
		Account account=accountRepository.search(accountNumber);
		if(account==null)
			throw new InvalidAccountNumberException();
		
		if(account.getAmonut()-amount<0)
			throw new InsufficientBalanceException();
		
		account.setAmonut(account.getAmonut()-amount);
		System.out.println("Amnount retured:"+account.getAmonut());
		return account.getAmonut();
	}
	
	/* (non-Javadoc)
	 * @see com.capgemini.service.AccountService#deposit(int, int)
	 */
	@Override
	public int deposit(int accountNumber, int amount) throws InvalidAccountNumberException 
	{
		Account account=accountRepository.search(accountNumber);
		if(account==null)
			throw new InvalidAccountNumberException();
		
		account.setAmonut(account.getAmonut()+amount);
		System.out.println("Amnount retured:"+account.getAmonut());
		return account.getAmonut();
	}
}
