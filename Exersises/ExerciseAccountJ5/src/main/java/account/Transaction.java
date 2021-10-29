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

    /**
     * Compare with other object.
     * 
     * @param otherObject
     *            the other object
     * @return true if equal to other object
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object otherObject) {
        // Quick test for identity
        if (this == otherObject) {
            return true;
        }
        // false if explicit parameter is null
        if (otherObject == null) {
            return false;
        }
        // false if instances of different classes
        if (getClass() != otherObject.getClass()) {
            return false;
        }
        // Now we know otherObject is a non-null AccountDTO
        Transaction other = (Transaction) otherObject;
        // Compare state for equality
        return amount == other.amount && type == other.type;
    }

    @Override
    public int hashCode() {
        return HASH_CONSTANT + (int) amount + type.hashCode();
    }

    @Override
    public String toString() {
        return this.type + ":" + this.amount;
    }

}
