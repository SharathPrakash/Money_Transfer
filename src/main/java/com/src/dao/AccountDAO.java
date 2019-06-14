package com.src.dao;

import java.math.BigDecimal;
import java.util.List;

import com.src.main.model.Account;
import com.src.main.model.UserTransaction;

public interface AccountDAO {

	List<Account> getAllAccounts() throws Exception;

	Account getAccountById(long accountId) throws Exception;

	long createAccount(Account account) throws Exception;

	int deleteAccountById(long accountId) throws Exception;

	int updateAccountBalance(long accountId, BigDecimal deltaAmount) throws Exception;

	int transferAccountBalance(UserTransaction userTransaction) throws Exception;

}
