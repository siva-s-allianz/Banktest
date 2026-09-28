package BankManagementSystem;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;

public class BankControllerTest {

    @Test
    void testCheckBalance() {

        BankAccount account =
                new SavingsAccount("Siva", 1001, 5000);

        BankController controller =
                new BankController();

        double balance =
                controller.checkBalance(account);

        assertEquals(5000, balance);
    }

    @Test
    void testDeposit() {

        BankAccount account =
                new SavingsAccount("Siva", 1001, 5000);

        BankController controller =
                new BankController();

        controller.deposit(account, 2000);

        assertEquals(7000, account.getBalance());
    }
    @Test
    void testWithdraw() {

    BankAccount account =
            new SavingsAccount("Siva", 1001, 5000);

    BankController controller =
            new BankController();

    controller.withdraw(account, 1000);

    assertEquals(4000, account.getBalance());
    }

    @Test
    void testWithdrawWithInsufficientBalance() {

        BankAccount account =
                new SavingsAccount("Siva", 1001, 5000);

        BankController controller =
                new BankController();

        assertThrows(
                InsufficientBalanceException.class,
                () -> controller.withdraw(account, 6000)
        );

        assertEquals(5000, account.getBalance());
    }   
   
    @Test
    void testDepositUsingMockito() {

        BankAccount account = mock(BankAccount.class);

        BankController controller =
                new BankController();

        controller.deposit(account, 2000);

        verify(account).deposit(2000);
    }

    @Test
    void testWithdrawUsingMockito() {   

        BankAccount account = mock(BankAccount.class);

        BankController controller =
                new BankController();

        controller.withdraw(account, 1000);

        verify(account).withdraw(1000);
    }

    @Test
    void testWithdrawUsingMockitoWithDifferentAmount() {

        BankAccount account = mock(BankAccount.class);

        BankController controller =
                new BankController();

        controller.withdraw(account, 1000);

        verify(account).withdraw(1000);
    }
    

    @Test
    void testDepositCalledOnce() {

        BankAccount account = mock(BankAccount.class);

        BankController controller =
                new BankController();

        controller.deposit(account, 2000);

        verify(account).deposit(2000);
    }

    @Test 
    void testCheckBalanceUsingMockito(){
        BankAccount account =mock(BankAccount.class);

        when(account.getBalance()).thenReturn(5000.0);

        BankController controller = new  BankController();
        double balance = controller.checkBalance(account);
        assertEquals(5000.0, balance);
    }

    @Test
void testDepositCalledOnce1() {

    BankAccount account = mock(BankAccount.class);

    BankController controller =
            new BankController();

    controller.deposit(account, 2000);

    verify(account, times(1)).deposit(2000);
}


}