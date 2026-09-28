package BankManagementSystem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BankAccountTest {

    BankAccount account;

    @BeforeEach
    void setUp() {
        account = new SavingsAccount("Siva", 1001, 5000);
    }

    @Test
    void testDeposit() {

        account.deposit(2000);

        assertEquals(7000, account.getBalance());
    }

    @Test
    void testInitialBalanceIsPositive() {

        assertTrue(account.getBalance() > 0);
    }

    @Test
    void testBalanceIsNotNegative() {

        assertFalse(account.getBalance() < 0);
    }

    @Test
    void testWithdrawWithInsufficientBalance() {

        InsufficientBalanceException exception =
                assertThrows(
                        InsufficientBalanceException.class,
                        () -> account.withdraw(6000)
                );

        assertEquals(
            "Insufficient balance for withdrawal. Current balance: 5000.0",
            exception.getMessage()
        );
    }
    @Test
    void testWithdraw() {

    account.withdraw(1000);

    assertEquals(4000, account.getBalance());
    }

    @Test
    void testInvalidDeposit() {

    account.deposit(-1000);

    assertEquals(5000, account.getBalance());
    }


}