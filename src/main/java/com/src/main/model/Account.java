package com.src.main.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Account {

	@JsonIgnore
	private long accountId;

	@JsonProperty(required = true)
	private String userName;

	@JsonProperty(required = true)
	private BigDecimal balance;

	@JsonProperty(required = true)
	private String currencyCode;

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

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public Account() {
	}

	public Account(String userName, BigDecimal balance, String currencyCode) {
		super();
		this.userName = userName;
		this.balance = balance;
		this.currencyCode = currencyCode;
	}

	public Account(long accountId, String userName, BigDecimal balance, String currencyCode) {
		super();
		this.accountId = accountId;
		this.userName = userName;
		this.balance = balance;
		this.currencyCode = currencyCode;
	}

	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", userName=" + userName + ", balance=" + balance + ", currencyCode="
				+ currencyCode + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (accountId ^ (accountId >>> 32));
		result = prime * result + ((balance == null) ? 0 : balance.hashCode());
		result = prime * result + ((currencyCode == null) ? 0 : currencyCode.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
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
		Account account = (Account) obj;
		if (accountId != account.accountId)
			return false;
		if (balance == null) {
			if (account.balance != null)
				return false;
		} else if (!balance.equals(account.balance))
			return false;
		if (userName == null) {
			if (account.userName != null)
				return false;
		} else if (!userName.equals(account.userName))
			return false;
		return currencyCode.equals(account.currencyCode);
	}

}
