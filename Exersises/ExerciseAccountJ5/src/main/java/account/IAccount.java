package account;

import java.util.List;

public interface IAccount {

    public int getBalance();

    public String getOwnerName();

    public void setOwnerName(String ownerName);

    public int getAccountID();

    /**
     * Deposit a specified amount to the account. Updates the balance
     * correspondingly, and adds a corresponding Transaction to the Account.
     */
    public void deposit(int amount);

    /**
     * Withdraw a specified amount from the account. Updates the balance
     * correspondingly, and adds a corresponding Transaction to the Account.
     */
    public void withdraw(int amount) throws AccountException;

    /**
     * Get transaction record for this account.
     */
    public List<Transaction> getTransctions();

    public void setSupervisor(ISupervisor superVisor);
}
