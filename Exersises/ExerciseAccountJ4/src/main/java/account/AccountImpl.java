package account;

import java.util.ArrayList;
import java.util.List;

public class AccountImpl implements IAccount {

	public static final int SUPERVISION_TRESHOLD = 100000;
	
    private long id;
    private String ownerName;
    private int balance;
    private List<Transaction> transactions;
    private ISupervisor supervisor;

    public AccountImpl(long id, String owner) {
        this.id = id;
        this.setOwnerName(owner);
        this.balance = 0;
        this.transactions = new ArrayList<Transaction>();
    }

    /**
     * Deposit a specified amount to the account. Updates the balance
     * correspondingly, and adds a corresponding Transaction to the Account.
     */
    public void deposit(int amount) {
        Transaction tx = new Transaction(Transaction.DEPOSIT, amount);
        if (amount >= SUPERVISION_TRESHOLD && supervisor != null) {
            if (!supervisor.notify(this.id, this.ownerName, tx)) {
                throw new SupervisorException(
                        "Deposit transaction not approved by supervisor");
            } 
        }
        balance += amount;
        transactions.add(tx);
    }

    /**
     * Withdraw a specified amount from the account. Updates the balance
     * correspondingly, and adds a corresponding Transaction to the Account.
     * 
     * @param amount the amount to withdraw.
     * @throws AccountException if not enough funding exists.
     * @see Transaction
     * @see account.IAccount#withdraw(double)
     */
    public void withdraw(int amount) throws AccountException {
        if (amount <= balance) {
            Transaction tx = new Transaction(Transaction.WITHDRAW, amount);
            if (amount >= SUPERVISION_TRESHOLD && supervisor != null) {
                if (!supervisor.notify(this.id, this.ownerName, tx)) {
                    throw new SupervisorException(
                            "Withdraw transaction not approved by supervisor");
                }
            }
            balance -= amount;
            transactions.add(tx);
        } else {
            throw new AccountException("Balance exceeded!");
        }
    }

    public int getBalance() {
        return balance;
    }

    public String getOwnerName() {
        return this.ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public long getAccountID() {
        return this.id;
    }

    public List<Transaction> getTransctions() {
        return new ArrayList<Transaction>(transactions);
    }

    public void setSupervisor(ISupervisor supervisor) {
        this.supervisor = supervisor;
    }

}
