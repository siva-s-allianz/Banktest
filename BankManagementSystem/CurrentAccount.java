public class CurrentAccount extends BankAccount {
    public CurrentAccount(String customerName, int accountNumber, double balance){
        super(customerName, accountNumber, balance);
    }

    @Override
    void showAccountType(){
        System.out.println("Account Type : Current Account");
    }
    
}
