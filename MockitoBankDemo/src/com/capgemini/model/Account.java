package com.capgemini.model;

public class Account {
	int amonut;
	int accountNumber;

	public int getAmonut() {
		return amonut;
	}

	public void setAmonut(int amonut) {
		this.amonut = amonut;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accountNumber;
		result = prime * result + amonut;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (accountNumber != other.accountNumber)
			return false;
		if (amonut != other.amonut)
			return false;
		return true;
	}
  
}
