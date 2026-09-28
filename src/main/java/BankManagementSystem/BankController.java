package BankManagementSystem;

public class BankController {

    public double checkBalance(BankAccount account) {
        return account.getBalance();
    }

    public void deposit(BankAccount account, double amount) {
        account.deposit(amount);
    }

    public void withdraw(BankAccount account, double amount) {
        account.withdraw(amount);
    }
}
