package account;

public interface ISupervisor {

    /**
     * Notification of transaction.
     * 
     * @return true if agreed to the transaction, false if veto
     */
    public boolean notify(int accountId, String owner,
            Transaction transaction);

}
