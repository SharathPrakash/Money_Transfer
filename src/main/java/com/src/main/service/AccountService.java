package com.src.main.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.src.dao.DAOFactory;
import com.src.main.model.Account;
import com.src.main.model.MoneyUtil;

@Path("/account")
@Produces(MediaType.APPLICATION_JSON)
public class AccountService {
	private final DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.H2);

	/**
	 * Find all accounts
	 * 
	 * */
	@GET
	@Path("/all")
	public List<Account> getAllAccounts() throws Exception {
		return daoFactory.getAccountDAO().getAllAccounts();
	}

	/**
	 * Find by account id
	 * 
	 * */
	@GET
	@Path("/{accountId}")
	public Account getAccount(@PathParam("accountId") long accountId) throws Exception {
		return daoFactory.getAccountDAO().getAccountById(accountId);
	}

	/**
	 * Find balance by account Id
	 * 
	 * */
	@GET
	@Path("/{accountId}/balance")
	public BigDecimal getBalance(@PathParam("accountId") long accountId) throws Exception {
		final Account account = daoFactory.getAccountDAO().getAccountById(accountId);

		if (account == null) {
			throw new WebApplicationException("Account not found", Response.Status.NOT_FOUND);
		}
		return account.getBalance();
	}

	/**
	 * Create Account
	 * 
	 * */
	@PUT
	@Path("/create")
	public Account createAccount(Account account) throws Exception {
		final long accountId = daoFactory.getAccountDAO().createAccount(account);
		return daoFactory.getAccountDAO().getAccountById(accountId);
	}

	/**
	 * Deposit amount by account Id
	 * 
	 * */
	@PUT
	@Path("/{accountId}/deposit/{amount}")
	public Account deposit(@PathParam("accountId") long accountId, @PathParam("amount") BigDecimal amount)
			throws Exception {

		if (amount.compareTo(MoneyUtil.zeroAmount) <= 0) {
			throw new WebApplicationException("Invalid Deposit amount", Response.Status.BAD_REQUEST);
		}

		daoFactory.getAccountDAO().updateAccountBalance(accountId, amount.setScale(4, RoundingMode.HALF_EVEN));
		return daoFactory.getAccountDAO().getAccountById(accountId);
	}
	
	/**
	 * Withdraw amount by account Id
	 * 
	 * */
	@Path("/{accountId}/withdraw/{amount}")
	public Account withdraw(@PathParam("accountId") long accountId, @PathParam("amount") BigDecimal amount)
			throws Exception {

		if (amount.compareTo(MoneyUtil.zeroAmount) <= 0) {
			throw new WebApplicationException("Invalid Deposit amount", Response.Status.BAD_REQUEST);
		}
		BigDecimal delta = amount.negate();

		System.out.println("Withdraw service: delta change to account  " + delta + " Account ID = " + accountId);
		daoFactory.getAccountDAO().updateAccountBalance(accountId, delta.setScale(4, RoundingMode.HALF_EVEN));
		return daoFactory.getAccountDAO().getAccountById(accountId);
	}
	
	/**
	 * Delete amount by account Id
	 * 
	 * */
	@DELETE
	@Path("/{accountId}")
	public Response deleteAccount(@PathParam("accountId") long accountId) throws Exception {
		int deleteCount = daoFactory.getAccountDAO().deleteAccountById(accountId);
		if (deleteCount == 1) {
			return Response.status(Response.Status.OK).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}

}
