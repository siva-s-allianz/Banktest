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

    BankAccount(String customerName, int accountNumber, double balance){
        this.customerName = customerName;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    void deposit(double amount){
        balance = balance + amount;
        System.out.println("Deposited: " + amount);
        System.out.println("New Balance: " + balance);
    }

    void withdraw(double amount){
        if(amount<=balance){
        balance = balance - amount;
        System.out.println("₹" + amount + " withdrawn successfully.");
        System.out.println("Current Balance: " + balance);
        }
        else{
            System.out.println("Insufficient balance.");
            System.out.println("Current Balance: " + balance);
        }
    }

    void checkbalance(){
        System.out.println("Current Balance: " + balance);
    }

}

