import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.function.Predicate;
import java.util.function.Consumer;
import java.util.function.Function;

public class Main {

    public static void main(String[] args) {

        System.out.println("================================");
        System.out.println("     BANK MANAGEMENT SYSTEM");
        System.out.println("================================");

        Scanner scanner = new Scanner(System.in);

        // Store bank accounts using ArrayList
        ArrayList<BankAccount> accounts = new ArrayList<>();

        int choice = 0;

        while (choice != 4) {

            System.out.println();
            System.out.println("========== MAIN MENU ==========");
            System.out.println("1. Create Account");
            System.out.println("2. Login");
            System.out.println("3. Account Reports");
            System.out.println("4. Exit");

            System.out.print("Enter your choice: ");

            try{
                choice = scanner.nextInt();
                scanner.nextLine();
            }
            catch(InputMismatchException e){
                System.out.println("Please enter a valid number.");
                scanner.nextLine();
            }
            switch (choice) {

                // =========================
                // CREATE ACCOUNT
                // =========================
                case 1:

                    System.out.print("Enter customer name: ");
                    String customerName = scanner.nextLine();

                    System.out.print("Enter account number: ");
                    int accountNumber = scanner.nextInt();

                    // Check duplicate account number
                    if (findAccount(accounts, accountNumber) != null) {

                        System.out.println(
                                "Account number already exists."
                        );

                        break;
                    }

                    System.out.print("Enter initial deposit: ");
                    double balance = scanner.nextDouble();

                    // Validate initial deposit
                    if (balance <= 0) {

                        System.out.println(
                                "Initial deposit must be greater than 0."
                        );

                        break;
                    }

                    // Create new account
                    System.out.println();
                    System.out.println("Select Account Type:");
                    System.out.println("1. Savings Account");
                    System.out.println("2. Current Account");
                    System.out.print("Enter your choice: ");

                    int accountType = scanner.nextInt();

                    BankAccount newAccount;

                        if (accountType == 1) {

                        newAccount = new SavingsAccount(
                                customerName,
                                accountNumber,
                                balance
                        );

                        } else if (accountType == 2) {

                        newAccount = new CurrentAccount(
                                customerName,
                                accountNumber,
                                balance
                        );

                        } else {

                        System.out.println("Invalid account type.");
                        break;
                        }

                        accounts.add(newAccount);

                System.out.println("Account created successfully!");

                    break;

                // =========================
                // LOGIN
                // =========================
                case 2:

                    System.out.print(
                            "Enter account number to login: "
                    );

                    int loginAccountNumber =
                            scanner.nextInt();

                    BankAccount currentAccount =
                            findAccount(
                                    accounts,
                                    loginAccountNumber
                            );

                    if (currentAccount == null) {

                        System.out.println(
                                "Account not found."
                        );

                    } else {

                        System.out.println(
                                "Welcome, "
                                + currentAccount.getCustomerName()
                                + "!"
                        );

                        int bankingChoice = 0;

                        // =========================
                        // BANKING MENU
                        // =========================
                        while (bankingChoice != 5) {

                            System.out.println();
                            System.out.println(
                                    "========== BANKING MENU =========="
                            );

                            System.out.println(
                                    "1. Check Balance"
                            );

                            System.out.println(
                                    "2. Deposit Money"
                            );

                            System.out.println(
                                    "3. Withdraw Money"
                            );

                            System.out.println(
                                    "4. Account Details"
                            );

                            System.out.println(
                                    "5. Logout"
                            );

                            System.out.print(
                                    "Enter your choice: "
                            );

                            bankingChoice =
                                    scanner.nextInt();

                            switch (bankingChoice) {

                                case 1:

                                    performOperation(
                                        currentAccount,
                                        Main::printAccountBalance
                                );

                                    break;

                                case 2:

                                    System.out.print(
                                            "Enter the amount to deposit: "
                                    );

                                    double depositAmount =
                                            scanner.nextDouble();

                                    currentAccount.deposit(
                                            depositAmount
                                    );

                                    break;

                                case 3:

                                    System.out.print(
                                            "Enter the amount to withdraw: "
                                    );

                                    double withdrawalAmount =
                                            scanner.nextDouble();
                                    try{
                                    currentAccount.withdraw(
                                            withdrawalAmount
                                    );}
                                    catch(InsufficientBalanceException e){
                                        System.out.println(e.getMessage());
                                    }

                                    break;

                                case 4:

                                    System.out.println(
                                            "-----Account Details-----"
                                    );
                                    performOperation(currentAccount,account -> {

                                    System.out.println(
                                            "Customer Name: "
                                            + account
                                                    .getCustomerName()
                                    );

                                    System.out.println(
                                            "Account Number: "
                                            + account
                                                    .getAccountNumber()
                                    );

                                    System.out.println(
                                            "Balance: "
                                            + account
                                                    .getBalance()
                                    );
                                   
                                });
                                 currentAccount.showAccountType();
                                    

                                    break;

                                case 5:

                                    System.out.println(
                                            "Logged out successfully."
                                    );

                                    break;

                                default:

                                    System.out.println(
                                            "Invalid choice. Please try again."
                                    );
                            }
                        }
                    }

                    break;

                // =========================
                // ACCOUNT REPORTS
                // =========================

                case 3:
                        System.out.println();
                        System.out.println("========== ACCOUNT REPORTS ==========");
                        System.out.println("1. Show All Accounts");
                        System.out.println("2. Show Accounts with balance above 5000");
                        System.out.println("3. Sort Accounts by Balance");
                        System.out.println("4. Count Total Accounts");
                        System.out.println("5. Group Accounts By Type");

                        System.out.print("Enter your choice: ");
                        int reportChoice = scanner.nextInt();

                        switch (reportChoice) {
                                case 1:
                                        System.out.println("-----All Accounts-----");
                                        

                                        performOperationOnAll(accounts, account -> System.out.println(account.getCustomerName()+
                                        '-'+account.getBalance()));

                                        if (!accounts.isEmpty()) {
                                                showAccountName(
                                                        accounts.get(0),
                                                        account -> account.getCustomerName()
                                                );
                                        }
                                        break;
                                
                                case 2:
                                        System.out.println("-----Accounts with balance above 5000-----");
                                        filterAccounts(accounts,account -> account.getBalance()>5000);

                                        break;

                                case 3:
                                        System.out.println("-----Accounts sorted by balance-----");

                                        List<BankAccount> sortedAccounts =
                                                accounts.stream()
                                                        .sorted(
                                                                Comparator.comparingDouble(
                                                                        BankAccount::getBalance
                                                                )
                                                        )
                                                        .collect(Collectors.toList());
                                        
                                        sortedAccounts.forEach(account ->
                                                System.out.println(
                                                        account.getCustomerName()+
                                                        '-'+
                                                        account.getBalance()
                                                ));

                                        
                                        break;

                                case 4:

                                        long totalAccounts = accounts.stream().count();

                                        System.out.println("Total Accounts: "+ totalAccounts);

                                        break;

                                case 5 :
                                        Map<String,List<BankAccount>> accountsByType = 
                                                accounts.stream()
                                                        .collect(Collectors.groupingBy(
                                                                account -> account.getClass().getSimpleName()
                                                        ));
                                        System.out.println();
                                        System.out.println("-----Accounts grouped by type-----");

                                        accountsByType.forEach((type,accountList)->{
                                                System.out.println();
                                                System.out.println("Account Type: "+type + ":");

                                                accountList.forEach(account ->
                                                        System.out.println(
                                                                account.getCustomerName()+
                                                                '-'+
                                                                account.getBalance()
                                                        )
                                                );
                                        });
                                        break;
                                                

                                default:
                                        System.out.println("Invalid report choice. Please try again.");
                                        break;
                        }


                // =========================
                // EXIT
                // =========================
                case 4:

                    System.out.println(
                            "Thank you for using the banking system."
                    );

                    break;

                default:

                    System.out.println(
                            "Invalid choice. Please try again."
                    );
            }
        }

        scanner.close();
    }

    // =========================
    // FIND ACCOUNT
    // =========================
    static BankAccount findAccount(
            ArrayList<BankAccount> accounts,
            int accountNumber) {

        for (int i = 0; i < accounts.size(); i++) {

            if (accounts.get(i).getAccountNumber()
                    == accountNumber) {

                return accounts.get(i);
            }
        }

        return null;
    }

    static void  performOperation(BankAccount account , AccountOperation operation){
        operation.perform(account);
    }

    static void performOperationOnAll(
        ArrayList<BankAccount> accounts,
        Consumer<BankAccount> operation
    ){
        for(BankAccount account : accounts){
                operation.accept(account);
        }
    }

    static void filterAccounts(
        ArrayList<BankAccount> accounts,
        Predicate<BankAccount> filter){
                for(BankAccount account : accounts){
                        if(filter.test(account)){
                                System.out.println(
                                        account.getCustomerName()+
                                        '-'+
                                        account.getBalance()
                                );
                        }
                }
        }

    static void showAccountName(
                BankAccount account,
                Function<BankAccount, String> function
        ) {
        String result = function.apply(account);
        System.out.println("Result: " + result);
        }

    static void printAccountBalance(BankAccount account){
        System.out.println(
                "Balance : "+account.getBalance()
        );
    }


}


