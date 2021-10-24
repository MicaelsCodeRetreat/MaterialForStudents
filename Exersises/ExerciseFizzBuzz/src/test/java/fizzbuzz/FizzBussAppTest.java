package fizzbuzz;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class FizzBussAppTest {
    @Test
    public void testAppHasAGreeting() {
        FizzBussApp classUnderTest = new FizzBussApp();
        assertNotNull("FizzBussApp should have a greeting", classUnderTest.getGreeting());
    }
}
