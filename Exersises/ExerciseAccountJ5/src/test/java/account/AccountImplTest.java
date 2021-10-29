package account;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.verification.Times;

public class AccountImplTest {

// ----------- Start ----------	
	@Test
	public void testInitialBalance() {
		int testId = 123456;
		AccountImpl account = new AccountImpl(testId , "testOwner");
        int expectedBalance = 0;
        int actualBalance = account.getBalance();
        assertEquals(expectedBalance, actualBalance, "Initial balance should be zero");
    }
	
    @Test
    public void testWithdraw() throws AccountException {
        int amount = 100;
 
		int testId = 123456;
		AccountImpl account = new AccountImpl(testId , "testOwner");
        account.deposit(2 * amount);
        account.withdraw(amount);

        int expectedBalance = amount;
        int actualBalance = account.getBalance();
        assertEquals(expectedBalance, actualBalance, "Balance after withdraw should be " + expectedBalance);
    }
     
/*
// ------------- After Refactoring -------
	private AccountImpl account;

    @BeforeEach
    public void setUp() {
        account = new AccountImpl("testId", "testOwner");
    }

	@Test
	public void testInitialBalance() {
//        account = new AccountImpl("testId", "testOwner");
        int expectedBalance = 0;
        int actualBalance = account.getBalance();
        Assertions.assertEquals(expectedBalance, actualBalance);
    }
	
    @Test
    public void testWithdraw() throws AccountException {
        int amount = 100;
 
        account.deposit(2 * amount);
        account.withdraw(amount);

        int expectedBalance = amount;
        int actualBalance = account.getBalance();
        Assertions.assertEquals(expectedBalance, actualBalance);
    }
*/
//------------------
	private AccountImpl account;

    @BeforeEach
    public void setUp() {
        int testId = 123456;
		account = new AccountImpl(testId , "testOwner");
    }
/*
    @Test
    public void testDeposit() {
        int amount = 100;
        account.deposit(amount);

        int expectedBalance = amount;
        int actualBalance = account.getBalance();
        Assertions.assertEquals(expectedBalance, actualBalance);
    }

    @Test
    public void testDepositTwice() {
        int amount = 100;
        account.deposit(amount);
        account.deposit(amount);

        int expectedBalance = 2 * amount;
        int actualBalance = account.getBalance();
        Assertions.assertEquals(expectedBalance, actualBalance);
    }

 
    @Test
    public void testWithdrawAll() throws AccountException {
        int amount = 100;
        account.deposit(amount);

        account.withdraw(amount);

        int expectedBalance = 0;
        int actualBalance = account.getBalance();
        Assertions.assertEquals(expectedBalance, actualBalance);
    }

    @Test
    public void testWithdrawTooMuch() throws AccountException {
        int amount = 100;
        account.deposit(amount);

        try {
            account.withdraw(amount + 1);
            Assertions.fail("AccountException expected");
        } catch (AccountException e) {
            // Expected
        }
        int expectedBalance = amount;
        int actualBalance = account.getBalance();
        Assertions.assertEquals(expectedBalance, actualBalance);
    }
*/
    // ----- Mocking examples in Mockito

    public static final int SUPERVISION_TRESHOLD = 100000;

    @Test
    public void testLargeDepositShouldTriggerNotification() {
        int amount = AccountImpl.SUPERVISION_TRESHOLD;
        ISupervisor mockSupervisor = Mockito.mock(ISupervisor.class);
        Mockito.when(
            mockSupervisor.notify(Mockito.anyInt(), Mockito.anyString(), (Transaction) Mockito.any()))
            .thenReturn(true);
        account.setSupervisor(mockSupervisor);

        account.deposit(amount);

        Mockito.verify(mockSupervisor).notify(account.getAccountID(), account.getOwnerName(),
            new Transaction(Transaction.DEPOSIT, amount));
        Mockito.verifyNoMoreInteractions(mockSupervisor);
    }

    @SuppressWarnings("deprecation")
	@Test
    public void testSmallDepositShouldNotTriggerNotification() {
        int amount = AccountImpl.SUPERVISION_TRESHOLD - 1;
        ISupervisor mockSupervisor = Mockito.mock(ISupervisor.class);
        account.setSupervisor(mockSupervisor);

        account.deposit(amount);

        // Alternative 1, deprecated in later versions of Mockito.
        Mockito.verifyZeroInteractions(mockSupervisor);
        // Alternative 2, easier to read
        verify(mockSupervisor, never()).notify(Mockito.anyInt(), Mockito.anyString(), Mockito.any(Transaction.class));
        // Alternative 3, more flexible
        verify(mockSupervisor, new Times(0)).notify(Mockito.anyInt(), Mockito.anyString(), Mockito.any(Transaction.class));
    }

    @Test
    public void testNotificationVetoShouldBeHonoured() {
        int amount = AccountImpl.SUPERVISION_TRESHOLD;
        ISupervisor mockSupervisor = Mockito.mock(ISupervisor.class);
        // Default boolean stub return value is false, hence the following stub instruction is redundant
        Mockito.when(
            mockSupervisor.notify(Mockito.anyInt(), Mockito.anyString(), (Transaction) Mockito.any()))
            .thenReturn(false);
        account.setSupervisor(mockSupervisor);

        try {
            account.deposit(amount);
            Assertions.fail("SupervisorException expected");
        } catch (SupervisorException expected) {
            // expected
            System.err.println(expected);
        }
        Mockito.verify(mockSupervisor).notify(account.getAccountID(), account.getOwnerName(),
            new Transaction(Transaction.DEPOSIT, amount));
    }

}
	
/* Earlier version
    private AccountImpl account;

    @Before
    public void setUp() {
        account = new AccountImpl("testId", "testOwner");
    }

    @Test
    public void testInitialBalance() {
        account = new AccountImpl("testId", "testOwner");
        int expectedBalance = 0;
        int actualBalance = account.getBalance();
        Assert.assertEquals(expectedBalance, actualBalance);
    }

    @Test
    public void testDeposit() {
        int amount = 100;
        account.deposit(amount);

        int expectedBalance = amount;
        int actualBalance = account.getBalance();
        Assert.assertEquals(expectedBalance, actualBalance);
    }

    @Test
    public void testDepositTwice() {
        int amount = 100;
        account.deposit(amount);
        account.deposit(amount);

        int expectedBalance = 2 * amount;
        int actualBalance = account.getBalance();
        Assert.assertEquals(expectedBalance, actualBalance);
    }

    @Test
    public void testWithdraw() throws AccountException {
        int amount = 100;
        account.deposit(2 * amount);

        account.withdraw(amount);

        int expectedBalance = amount;
        int actualBalance = account.getBalance();
        Assert.assertEquals(expectedBalance, actualBalance);
    }

    @Test
    public void testWithdrawAll() throws AccountException {
        int amount = 100;
        account.deposit(amount);

        account.withdraw(amount);

        int expectedBalance = 0;
        int actualBalance = account.getBalance();
        Assert.assertEquals(expectedBalance, actualBalance);
    }

    @Test
    public void testWithdrawTooMuch() throws AccountException {
        int amount = 100;
        account.deposit(amount);

        try {
            account.withdraw(amount + 1);
            Assert.fail("AccountException expected");
        } catch (AccountException e) {
            // Expected
        }
        int expectedBalance = amount;
        int actualBalance = account.getBalance();
        Assert.assertEquals(expectedBalance, actualBalance);
    }

    @Test
    public void testLargeDepositShouldTriggerNotification() {
        int amount = AccountImpl.SUPERVISION_TRESHOLD;
        Supervisor mockSupervisor = Mockito.mock(Supervisor.class);
        Mockito.when(
            mockSupervisor.notify(Mockito.anyString(), Mockito.anyString(), (Transaction) Mockito.anyObject()))
            .thenReturn(true);
        account.setSupervisor(mockSupervisor);

        account.deposit(amount);

        Mockito.verify(mockSupervisor).notify(account.getAccountID(), account.getOwnerName(),
            new Transaction(Transaction.DEPOSIT, amount));
        Mockito.verifyNoMoreInteractions(mockSupervisor);
    }

    @Test
    public void testSmallDepositShouldNotTriggerNotification() {
        int amount = AccountImpl.SUPERVISION_TRESHOLD - 1;
        Supervisor mockSupervisor = Mockito.mock(Supervisor.class);
        account.setSupervisor(mockSupervisor);

        account.deposit(amount);

        // Alternative 1, deprecated in later versions of Mockito.
        Mockito.verifyZeroInteractions(mockSupervisor);
        // Alternative 2, easier to read
        verify(mockSupervisor, never()).notify(Mockito.anyInt(), Mockito.anyString(), Mockito.any(Transaction.class));
        // Alternative 3, more flexible
        verify(mockSupervisor, new Times(0)).notify(Mockito.anyInt(), Mockito.anyString(), Mockito.any(Transaction.class));
    }

    @Test
    public void testNotificationVetoShouldBeHonoured() {
        int amount = AccountImpl.SUPERVISION_TRESHOLD;
        Supervisor mockSupervisor = Mockito.mock(Supervisor.class);
        
        // Default boolean stub return value is false, hence the following stub instruction is redundant
        Mockito.when(
            mockSupervisor.notify(Mockito.anyString(), Mockito.anyString(), (Transaction) Mockito.anyObject()))
            .thenReturn(false);
        account.setSupervisor(mockSupervisor);
        
        try {
            account.deposit(amount);
            Assert.fail("SupervisorException expected");
        } catch (SupervisorException expected) {
            // expected
            System.err.println(expected);
        }
        Mockito.verify(mockSupervisor).notify(account.getAccountID(), account.getOwnerName(),
            new Transaction(Transaction.DEPOSIT, amount));
    }
*/


