public abstract class BankAccount {

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

    public void deposit(double amount) {

        if (amount <= 0) {
            System.out.println("Deposit amount must be greater than 0.");
            return;
        }

        balance = balance + amount;

        System.out.println("Deposited: " + amount);
        System.out.println("New Balance: " + balance);
   }

    public void withdraw(double amount) {

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

    public void checkbalance(){
        System.out.println("Current Balance: " + balance);
    }

    public abstract void showAccountType();
}