package account;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import account.AccountException;
import account.AccountImpl;

public class AccountImplTest {

	@Test
	public void testInitialBalance() {
		AccountImpl account = new AccountImpl(1, "testOwner");
        int expectedBalance = 0;
        int actualBalance = account.getBalance();
        assertEquals("Initial balance should be zero", expectedBalance, actualBalance);
    }
	
    @Test
    public void testWithdraw() throws AccountException {
        int amount = 100;
 
		AccountImpl account = new AccountImpl(1, "testOwner");
        account.deposit(2 * amount);
        account.withdraw(amount);

        int expectedBalance = amount;
        int actualBalance = account.getBalance();
        assertEquals("Balance after withdraw should be " + expectedBalance, expectedBalance, actualBalance);
    }
    
}
