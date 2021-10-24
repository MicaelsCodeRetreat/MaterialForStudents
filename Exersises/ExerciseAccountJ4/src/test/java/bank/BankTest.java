package bank;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import account.AccountException;
import account.AccountImpl;
import account.IAccount;
import account.Transaction;
import bank.Bank;

public class BankTest {

	@Test
	public void testAddAccount() {
	}
	
	@Test(expected = AccountException.class)
	public void testGetNonExistingAccount() throws AccountException {
	}

	@Test
	public void testTransferMonneyBetweenAccounts() {
	}
	
	@Test
	public void testTransactionsSequence() throws AccountException {
	}
}
