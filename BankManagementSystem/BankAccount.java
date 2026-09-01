public class BankAccount {

    private String customerName;
    private int accountNumber;
    private double balance;

    public String getCustomerName(){
        return customerName;
    }

    public int getAccountNumber(){
        return accountNumber;
    }

    public double getBalance(){
        return balance;
    }

    public BankAccount(String customerName, int accountNumber, double balance){
        this.customerName = customerName;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    void deposit(double amount) {

        if (amount <= 0) {
            System.out.println("Deposit amount must be greater than 0.");
            return;
        }

        balance = balance + amount;

        System.out.println("Deposited: " + amount);
        System.out.println("New Balance: " + balance);
   }

    void withdraw(double amount) {

        if (amount <= 0) {
            System.out.println("Withdrawal amount must be greater than 0.");
            return;
        }

        if (amount > balance) {
            System.out.println("Insufficient balance.");
            System.out.println("Current Balance: " + balance);
            return;
        }

        balance = balance - amount;

        System.out.println("₹" + amount + " withdrawn successfully.");
        System.out.println("Current Balance: " + balance);
    }

    void checkbalance(){
        System.out.println("Current Balance: " + balance);
    }

    void showAccountType(){
        System.out.println("Account Type : Bank Account");
    }

}