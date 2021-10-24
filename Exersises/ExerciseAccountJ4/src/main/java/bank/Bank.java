package bank;

import java.util.ArrayList;
import java.util.List;

import account.AccountException;
import account.IAccount;

public class Bank {

	List<IAccount> accounts = new ArrayList<IAccount>();
	
	public void addAccount(IAccount account) {
		accounts.add(account);
	}

	public IAccount getAccount(long id) throws AccountException {
		for(IAccount account : accounts) {
			if (account.getAccountID() == id) {
				return account;
			}
		}
		throw new AccountException("There is no account with id: " + id);
	}

	public void transfer(IAccount account1, IAccount account2, int amount) throws AccountException {
		account1.withdraw(amount);
		account2.deposit(amount);
	}

	public static void main(String[] args) {
		System.out.println("This is just a dummy main method!");
	}
	
}
