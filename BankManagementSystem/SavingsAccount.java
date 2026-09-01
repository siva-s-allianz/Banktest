public class SavingsAccount extends BankAccount{
    public SavingsAccount(String customerName, int accountNumber, double balance){
        super(customerName, accountNumber, balance);
    }
    @Override
    public void showAccountType(){
        System.out.println("Account Type : Savings Account");
    }
    
}
