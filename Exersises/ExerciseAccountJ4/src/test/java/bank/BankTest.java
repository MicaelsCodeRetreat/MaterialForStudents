package bank;

import static org.junit.Assert.fail;

import org.junit.Test;

import account.AccountException;

public class BankTest {

	@Test
	public void testAddAccount() {
		// TODO: Implement this test
		fail("Yet to be implemented!");
	}
	
	@Test(expected = AccountException.class)
	public void testGetNonExistingAccount() throws AccountException {
		// TODO: Implement this test
		fail("Yet to be implemented!");
	}

	@Test
	public void testTransferMonneyBetweenAccounts() {
		// TODO: Implement this test
		fail("Yet to be implemented!");
	}
	
	@Test
	public void testTransactionsSequence() throws AccountException {
	    // TODO: Implement a test that verifies that a sequence of transaction is maintained over time..
		fail("Yet to be implemented!");
	}
    // TODO: Add more test to cover different aspects of a Bank.
}
