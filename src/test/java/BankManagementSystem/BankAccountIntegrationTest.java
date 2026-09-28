package BankManagementSystem;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class BankAccountIntegrationTest {

    @Test
    void testSavingsAccountDepositAndWithdrawal() {

        SavingsAccount account =
                new SavingsAccount("Siva", 1001, 5000);

        account.deposit(2000);
        account.withdraw(1000);

        assertEquals(6000, account.getBalance());
    }

    @Test
    void testCurrentAccountDepositAndWithdrawal() {

    CurrentAccount account =
            new CurrentAccount("Siva", 1002, 10000);

    account.deposit(5000);
    account.withdraw(3000);

    assertEquals(12000, account.getBalance());
    }

    @Test
    void testCurrentAccountInsufficientBalance() {

        CurrentAccount account =
                new CurrentAccount("Siva", 1002, 10000);

        InsufficientBalanceException exception =
                assertThrows(
                        InsufficientBalanceException.class,
                        () -> account.withdraw(15000)
                );

        assertEquals(
                "Insufficient balance for withdrawal. Current balance: 10000.0",
                exception.getMessage()
        );
    }

    @Test
    void testTransactionLinkedList() {

    TransactionLinkedList transactions = new TransactionLinkedList();

    BankTransaction deposit =
            new BankTransaction("Deposit", 2000);

    BankTransaction withdrawal =
            new BankTransaction("Withdrawal", 1000);

    transactions.add(deposit);
    transactions.add(withdrawal);

    assertTrue(transactions.search("Deposit"));
    assertTrue(transactions.search("Withdrawal"));
    assertFalse(transactions.search("Transfer"));
    }

    @Test
    void testDeleteNonExistingTransaction() {

        TransactionLinkedList transactions = new TransactionLinkedList();

        BankTransaction deposit =
                new BankTransaction("Deposit", 2000);

        transactions.add(deposit);

        boolean deleted = transactions.delete("Withdrawal");

        assertFalse(deleted);
        assertTrue(transactions.search("Deposit"));
    }

    @Test
    void testDeleteFromEmptyList() {        

    TransactionLinkedList transactions =
            new TransactionLinkedList();

    boolean deleted = transactions.delete("Deposit");

    assertFalse(deleted);
   }

   @Test
        void testDisplayTransactions() {
        TransactionLinkedList transactions =
                new TransactionLinkedList();

        BankTransaction deposit =
                new BankTransaction("Deposit", 2000);

        BankTransaction withdrawal =
                new BankTransaction("Withdrawal", 1000);

        transactions.add(deposit);
        transactions.add(withdrawal);

        transactions.display();
        }       

        @Test
        void testSearchEmptyList() {
        TransactionLinkedList transactions =
                new TransactionLinkedList();

        boolean found = transactions.search("Deposit");

        assertFalse(found);
        }
}