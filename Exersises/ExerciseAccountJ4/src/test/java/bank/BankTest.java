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
		Bank bank = new Bank();
		String owner1 = "Donald Duck";
		long id1 = 1;
		IAccount account = new AccountImpl(id1 , owner1 );
		bank.addAccount(account);
		try {
			IAccount accountRetreived = bank.getAccount(id1);
			assertEquals("Could not retreive the newly created account: " + account, accountRetreived, account);
		} catch (AccountException e) {
			e.printStackTrace();
		}
	}
	
	@Test(expected = AccountException.class)
	public void testGetNonExistingAccount() throws AccountException {
		Bank bank = new Bank();
		long id1 = -1;
		@SuppressWarnings("unused")
		IAccount accountRetreived = bank.getAccount(id1);
	}

	@Test
	public void testTransferMonneyBetweenAccounts() {
		Bank bank = new Bank();
		String owner1 = "Scrooge McDuck";
		long id1 = 1;
		IAccount account1 = new AccountImpl(id1 , owner1);
		account1.deposit(1000000);
		String owner2 = "Donald Duck";
		long id2 = 2;
		IAccount account2 = new AccountImpl(id2 , owner2);
		bank.addAccount(account1);
		bank.addAccount(account2);
		try {
			bank.transfer(account1, account2, 10);
			assertEquals("", 1000000-10, account1.getBalance());
			assertEquals("", 10, account2.getBalance());
		} catch (AccountException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testTransactionsSequence() throws AccountException {
		String owner1 = "Scrooge McDuck";
		long id1 = 1;
		IAccount account1 = new AccountImpl(id1 , owner1);
		account1.deposit(1000000);
		account1.withdraw(10000);
		account1.withdraw(10000);
		account1.withdraw(10000);	
		List<Transaction> transactions = account1.getTransctions();
		for (Transaction transaction : transactions) {
			System.out.println("Transaction: " + transaction.getType() + " - " + transaction.getAmount());
		}
	}
}
