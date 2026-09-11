import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.function.Predicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.HashSet;
import java.util.Set;
import java.util.HashMap;

public class Main {

        /** Starts the interactive bank management application. */
    public static void main(String[] args) {

        System.out.println("================================");
        System.out.println("     BANK MANAGEMENT SYSTEM");
        System.out.println("================================");

        testBankAccountMap();

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
                if (findAccount(accounts, accountNumber).isPresent()) {
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

                    Optional<BankAccount> optionalAccount =
                            findAccount(
                                    accounts,
                                    loginAccountNumber
                            );
                
                        try {

                                BankAccount currentAccount =
                                        optionalAccount.orElseThrow(
                                                () -> new RuntimeException("Account not found.")
                                        );

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
                catch(RuntimeException e){
                        
                        System.out.println(e.getMessage());
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
                        System.out.println("6. Show Customer Names");
                        System.out.println("7. High Balance Customer Names");

                        System.out.print("Enter your choice: ");
                        int reportChoice = scanner.nextInt();

                        switch (reportChoice) {
                                case 1:
                                        System.out.println("-----All Accounts-----");
                                        

                                        performOperationOnAll(accounts, account -> System.out.println(account.getCustomerName()+
                                        " - "+account.getBalance()));

                                        if (!accounts.isEmpty()) {
                                                showAccountName(
                                                        accounts.get(0),
                                                        account -> account.getCustomerName()
                                                );
                                        }
                                        showBankName(
                                                () -> "Siva Bank"
                                        );
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
                                                        " - "+
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
                                                                " - "+
                                                                account.getBalance()
                                                        )
                                                );
                                        });
                                        break;

                                case 6:
                                        System.out.println("-----Customer Names-----");

                                        List<String> customerNames = 
                                                 accounts.stream()
                                                        .map(account -> account.getCustomerName()+
                                                        "- Account :"
                                                        + account.getAccountNumber()+
                                                        "- Balance : "
                                                        + account.getBalance()
                                                )
                                                        .collect(Collectors.toList());
                                                        
                                                customerNames.forEach(name -> 
                                                        System.out.println(name)
                                                );

                                        break;
                                case 7:
                                        System.out.println("-----High Balance customer names-----");
                                        List<String> highBalanceCustomers =
                                                accounts.stream()
                                                        .filter(account -> account.getBalance() > 50000)
                                                        .map(account -> account.getCustomerName())
                                                        .collect(Collectors.toList());

                                        highBalanceCustomers.forEach(name ->
                                                System.out.println(name)
                                        );

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
        /** Finds an account by account number. */
    static Optional<BankAccount> findAccount(
            ArrayList<BankAccount> accounts,
            int accountNumber) {

        for (int i = 0; i < accounts.size(); i++) {

            if (accounts.get(i).getAccountNumber()
                    == accountNumber) {

                return Optional.of(accounts.get(i));
            }
        }

        return Optional.empty();
    }

        /** Applies one account operation to the supplied account. */
        static void  performOperation(BankAccount account , AccountOperation operation){
        operation.perform(account);
    }

        /** Applies one operation to every account in the collection. */
        static void performOperationOnAll(
        ArrayList<BankAccount> accounts,
        Consumer<BankAccount> operation
    ){
        for(BankAccount account : accounts){
                operation.accept(account);
        }
    }

        /** Prints accounts that satisfy the supplied filter. */
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

        /** Applies a name-producing function and prints its result. */
        static void showAccountName(
                BankAccount account,
                Function<BankAccount, String> function
        ) {
        String result = function.apply(account);
        System.out.println("Result: " + result);
        }
        
        /** Retrieves and prints a bank name from the supplied provider. */
        static void showBankName(Supplier<String> supplier){
                String bankName = supplier.get();

                System.out.println("Bank Name: " + bankName);
    }

        /** Prints a formatted report when an account meets a condition. */
        static void accountReport(
        BankAccount account,
        Predicate<BankAccount> condition,
        Function<BankAccount, String> formatter,
        Consumer<String> printer,
        Supplier<String> title
    ){
        System.out.println(title.get());

        if(condition.test(account)){
                String result = formatter.apply(account);

                printer.accept(result);

        }else{
                System.out.println("Account does not meet the condition.");
        }
    }


        /** Demonstrates optional handling for a possibly missing account. */
        static void checkOptionalAccount(BankAccount account) {

                Optional<BankAccount> optionalAccount =
                        Optional.ofNullable(account);

                BankAccount result =
                        optionalAccount.orElseGet(() -> {
                                System.out.println("No account found.");
                                return null;
                        });

                if (result != null) {
                        System.out.println(
                                "Account found: "
                                + result.getCustomerName()
                        );
                }
        }

        /** Prints each distinct customer name in the account collection. */
        static void showUniqueCustomerNames(
                        ArrayList<BankAccount> accounts) {

                Set<String> uniqueNames = new HashSet<>();

                for (BankAccount account : accounts) {
                        uniqueNames.add(account.getCustomerName());
                }

                System.out.println("-----Unique Customer Names-----");

                for (String name : uniqueNames) {
                        System.out.println(name);
                }
        }

        /** Prints the balance of the supplied account. */
        static void printAccountBalance(BankAccount account){
        System.out.println(
                "Balance : "+account.getBalance()
        );
    }
        /** Demonstrates basic key and value operations on a customer map. */
        static void testHashMap() {

        HashMap<Integer, String> customers = new HashMap<>();

        customers.put(1001, "Itachi");
        customers.put(1002, "Siva");
        customers.put(1003, "Ravi");

        System.out.println("-----Customer Map-----");

        System.out.println("1001: " + customers.get(1001));
        System.out.println("1002: " + customers.get(1002));
        System.out.println("1003: " + customers.get(1003));

        System.out.println(
                "Does account 1001 exist? "
                + customers.containsKey(1001)
        );

        System.out.println(
                "Does account 9999 exist? "
                + customers.containsKey(9999)
        );

        System.out.println(
                "Does customer Itachi exist? "
                + customers.containsValue("Itachi")
        );
     }

        /** Demonstrates storing and retrieving bank accounts in a map. */
        static void testBankAccountMap() {

                HashMap<Integer, BankAccount> accountMap =
                        new HashMap<>();

                BankAccount account1 =
                        new SavingsAccount("Itachi", 1001, 85200);

                BankAccount account2 =
                        new CurrentAccount("Siva", 1002, 50000);

                accountMap.put(
                        account1.getAccountNumber(),
                        account1
                );

                accountMap.put(
                        account2.getAccountNumber(),
                        account2
                );

                BankAccount account =
                        accountMap.get(1001);

                System.out.println("-----Bank Account Map-----");

                System.out.println(
                        "Customer: "
                        + account.getCustomerName()
                );

                System.out.println(
                        "Account Number: "
                        + account.getAccountNumber()
                );

                System.out.println(
                        "Balance: "
                        + account.getBalance()
                );

                System.out.println("-----All Accounts in Map-----");

                for (Map.Entry<Integer, BankAccount> entry :
                        accountMap.entrySet()) {

                        System.out.println(
                                "Account Number: "
                                + entry.getKey()
                        );

                        System.out.println(
                                "Customer Name: "
                                + entry.getValue().getCustomerName()
                        );

                        System.out.println(
                                "Balance: "
                                + entry.getValue().getBalance()
                        );

                        System.out.println();
                }
        }

}


