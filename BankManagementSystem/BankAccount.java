public abstract class BankAccount implements Transaction {

    private String customerName;
    private int accountNumber;
    private double balance;

    /** Returns the name of the customer who owns this account. */
    public String getCustomerName(){
        return customerName;
    }

    /** Returns the unique number assigned to this account. */
    public int getAccountNumber(){
        return accountNumber;
    }

    /** Returns the account's current balance. */
    public double getBalance(){
        return balance;
    }

    /** Creates a bank account with the supplied customer and opening details. */
    public BankAccount(String customerName, int accountNumber, double balance){
        this.customerName = customerName;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    @Override
    /** Adds a positive amount to the account balance. */
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
    /** Removes a positive amount when sufficient funds are available. */
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
    /** Prints the account's current balance. */
    public void checkbalance(){
        System.out.println("Current Balance: " + balance);
    }

    /** Prints the concrete type of this account. */
    public abstract void showAccountType();
}