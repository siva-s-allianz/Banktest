public abstract class BankAccount implements Transaction {

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

    @Override
    public void deposit(double amount) {

        if (amount <= 0) {
            System.out.println("Deposit amount must be greater than 0.");
            return;
        }

        balance = balance + amount;

        System.out.println("Deposited: " + amount);
        System.out.println("New Balance: " + balance);
   }
    
    @Override
    public void withdraw(double amount) {

        if (amount <= 0) {
            System.out.println("Withdrawal amount must be greater than 0.");
            return;
        }

        if (amount > balance) {
            throw new InsufficientBalanceException("Insufficient balance for withdrawal. Current balance: " + balance);
         
        }

        balance = balance - amount;

        System.out.println("₹" + amount + " withdrawn successfully.");
        System.out.println("Current Balance: " + balance);
    }
    
    @Override
    public void checkbalance(){
        System.out.println("Current Balance: " + balance);
    }

    public abstract void showAccountType();
}