package com.capgemini.Test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.when;

import com.capgemini.Exceptions.InsufficientBalanceException;
import com.capgemini.Exceptions.InsufficientInitialAmountException;
import com.capgemini.Exceptions.InvalidAccountNumberException;
import com.capgemini.model.Account;
import com.capgemini.repository.AccountRepository;
import com.capgemini.service.AccountService;
import com.capgemini.service.AccountServiceImpl;

public class AccountServiceImplTest {

	AccountService accountService;

	@Mock
	AccountRepository accountRepository;

	@Before
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);
		accountService = new AccountServiceImpl(accountRepository);
	}

	/*
	 * CreateAccount() 
	 * 1. when amount is less than 500 it should throw  insufficientInitialAmountexception 
	 * 2. when valid in[ut is provided it should return correct return object
	 */
	@Test(expected = com.capgemini.Exceptions.InsufficientInitialAmountException.class)
	public void whenamountIsLessItShouldinsufficientInitialAmountexception() throws InsufficientInitialAmountException {
		Account account = new Account();
		account.setAccountNumber(101);
		account.setAmonut(50);

		when(accountRepository.save(account)).thenReturn(true);
		accountService.createAccount(101, 50);
	}

	@Test
	public void whenValidInputIsProvidedItShouldReturnCorrectObject() throws InsufficientInitialAmountException {
		Account account = new Account();
		account.setAccountNumber(101);
		account.setAmonut(600);

		when(accountRepository.save(account)).thenReturn(true);
		assertEquals(account, accountService.createAccount(101, 600));
	}

	/*
	 * Deposit() - 
	 * 1. When Account number is not valid it should throw invalidAccountNumberException. 
	 * 2. When valid data is provided it should  deposit the amount and return true
	 * 
	 */

	@Test(expected = com.capgemini.Exceptions.InvalidAccountNumberException.class)
	public void whenAccountNumberIsNotValidItShouldThrowInvalidAccountNumberException()
			throws InvalidAccountNumberException {

		int accountNumber = 101;
		when(accountRepository.search(accountNumber)).thenReturn(null);
		accountService.deposit(accountNumber, 700);
	}

	@Test
	public void whenValidDataProvidedItShouldReturnTrue() throws InvalidAccountNumberException {

		int accountNumber = 101;

		Account account = new Account();
		account.setAccountNumber(101);
		account.setAmonut(600);

		when(accountRepository.search(accountNumber)).thenReturn(account);
		assertEquals(1300, accountService.deposit(accountNumber, 700));
	}

	/*
	 * Withdraw() - 
	 * 1. When Account number is not valid it should throw invalidAccountNumberException. 
	 * 2. When amount is greater that balance then it should throw InsufficientBalanceException
	 * 3. When valid data is provided it should deposit the amount and return true
	 * 
	 */

	@Test(expected = com.capgemini.Exceptions.InvalidAccountNumberException.class)
	public void whenAccountNumberIsNotValidItShouldThrowInvalidAccountNumberExceptionForWithdraw()
			throws InvalidAccountNumberException, InsufficientBalanceException {

		int accountNumber = 101;
		int amount = 400;
		when(accountRepository.search(accountNumber)).thenReturn(null);
		accountService.withdraw(accountNumber, amount);
	}

	@Test(expected = com.capgemini.Exceptions.InsufficientBalanceException.class)
	public void whenAmountIsMoreThanBalaceItShouldThrowInsufficientBalanceException()
			throws InvalidAccountNumberException, InsufficientBalanceException {

		int accountNumber = 101;
		int amount = 400;

		Account account = new Account();
		account.setAccountNumber(accountNumber);
		account.setAmonut(amount);

		when(accountRepository.search(accountNumber)).thenReturn(account);
		accountService.withdraw(accountNumber, 800);
	}

	@Test
	public void whenValidDataProvidedItShouldReturnTrueForWithdraw()
			throws InvalidAccountNumberException, InsufficientBalanceException {

		int accountNumber = 101;
		int amount = 700;

		Account account = new Account();
		account.setAccountNumber(accountNumber);
		account.setAmonut(amount);

		when(accountRepository.search(accountNumber)).thenReturn(account);
		assertEquals(500, accountService.withdraw(accountNumber, 200));
	}
}
