package account;

/**
 * Class representing a Transaction, used by the Supervisor.
 */
public class Transaction {

    private static final int HASH_CONSTANT = 37;

    public static final String DEPOSIT = "deposit";
    public static final String WITHDRAW = "withdraw";

    private String type;
    private int amount;

    public Transaction(String type, int amount) {
        this.type = type;
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

}
