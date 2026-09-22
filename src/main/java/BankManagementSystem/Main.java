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
import java.util.TreeSet;

public class Main {

        /** Starts the interactive bank management application. */
    public static void main(String[] args) {

        System.out.println("================================");
        System.out.println("     BANK MANAGEMENT SYSTEM");
        System.out.println("================================");

        System.out.println("Sum = " + calculateSum(5));

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
                        while (bankingChoice != 9) {

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
                                "5. Transaction History"
                            );
                            System.out.println(
                                    "6. Undo Last Transaction"
                            );
                            System.out.println("7. Pending Transactions");
                            System.out.println("8. Process Next Transaction");

                            System.out.println(
                                    "9. Logout"
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
                                        System.out.println("-----Transaction History-----");
                                        currentAccount.showTransactionHistory();
                                        break;
                                
                                case 6:
                                        currentAccount.undoLastTransaction();
                                        break;        

                                case 7:
                                        currentAccount.showPendingTransactions();
                                        break;

                                case 8:
                                        currentAccount.processNextTransaction();
                                        break;

                                case 9:

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
                        System.out.println("8. Show sorted Customer Names");
                        System.out.println("9. Show Total Balance of All Accounts");
                        System.out.println("10. Show Highest Balance");
                        System.out.println("11. Selection Sort Accounts");
                        System.out.println("12. Sort Accounts by Account Number");
                        System.out.println("13. Insertion Sorting");
                        System.out.println("14. Binary Search Account");
                        System.out.println("15. Recursive Account Search");
                        System.out.println("16. Calculate Total Balance Using Recursion");

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
                                case 8:
                                        showSortedCustomerNames(accounts);
                                        break;
                                
                                case 9:
                                        showTotalBalance(accounts);
                                        break;

                                case 10:
                                        showHighestBalance(accounts);
                                        break;
                                
                                case 11:
                                        System.out.println("-----Selection Sort by Account Number-----");
                                        selectionSortAccounts(accounts);

                                        for (BankAccount account : accounts) {

                                                System.out.println(
                                                        account.getAccountNumber()
                                                        + " - "
                                                        + account.getCustomerName()
                                                );
                                        }
                                
                                break;

                                case 12:

                                        bubbleSortAccounts(accounts);

                                        System.out.println("Accounts sorted by account number.");

                                        for (BankAccount account : accounts) {
                                                System.out.println(
                                                        account.getAccountNumber()
                                                        + " - "
                                                        + account.getCustomerName()
                                                );
                                        }
                                        break;
                                
                                case 13:
                                        System.out.println("-----Insertion Sort by Account Number-----");

                                        insertionSortAccounts(accounts);

                                        for (BankAccount account : accounts) {

                                                System.out.println(
                                                        account.getAccountNumber()
                                                        + " - "
                                                        + account.getCustomerName()
                                                );
                                        }

                                        break;
                                        

                                case 14:
                                        bubbleSortAccounts(accounts);

                                        System.out.print("Enter account number to search: ");
                                        int searchNumber = scanner.nextInt();

                                        BankAccount foundAccount =
                                                binarySearch(accounts, searchNumber);

                                        if (foundAccount != null) {

                                                System.out.println("Account found!");
                                                System.out.println(
                                                        "Customer Name: "
                                                        + foundAccount.getCustomerName()
                                                );

                                                System.out.println(
                                                        "Account Number: "
                                                        + foundAccount.getAccountNumber()
                                                );

                                                System.out.println(
                                                        "Balance: "
                                                        + foundAccount.getBalance()
                                                );
                                        } else {
                                                System.out.println("Account not found.");
                                                }
                                        break;  
                                
                                case 15:
                                        System.out.print("Enter account number to search: ");
                                        int researchNumber = scanner.nextInt();

                                        BankAccount refoundAccount =
                                                recursiveSearch(accounts, researchNumber, 0);

                                        if (refoundAccount != null) {

                                                System.out.println("Account found!");

                                                System.out.println(
                                                        "Customer Name: "
                                                        + refoundAccount.getCustomerName()
                                                );

                                                System.out.println(
                                                        "Account Number: "
                                                        + refoundAccount.getAccountNumber()
                                                );

                                                System.out.println(
                                                        "Balance: "
                                                        + refoundAccount.getBalance()
                                                );

                                        } else {

                                                System.out.println("Account not found.");
                                        }

                                        break;

                                case 16 :
                                        double totalBalance = calculateTotalBalance(accounts, 0);

                                        System.out.println("Total Balance of All Accounts" + totalBalance);
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

        static void showSortedCustomerNames(ArrayList<BankAccount> accounts) {

                TreeSet<String> sortedNames = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);

                for (BankAccount account : accounts) {
                        sortedNames.add(account.getCustomerName());
                }

                System.out.println("-----Unique Customer Names (Sorted)-----");

                for (String name : sortedNames) {
                        System.out.println(name);
                }
        }

        static void showTotalBalance(ArrayList<BankAccount> accounts) {

        double totalBalance = accounts.stream()
                .map(BankAccount::getBalance)
                .reduce(0.0, (sum, balance) -> sum + balance);

        System.out.println("Total balance of all accounts: " + totalBalance);
        }

        static void showHighestBalance(ArrayList<BankAccount> accounts) {

                if (accounts.isEmpty()) {
                        System.out.println("No accounts available.");
                        return;
                }

                double highestBalance = accounts.stream()
                        .map(BankAccount::getBalance)
                        .reduce(0.0, (highest, balance) ->
                                Math.max(highest, balance));

                System.out.println("Highest balance: " + highestBalance);
        }

        public static void bubbleSortAccounts(
                ArrayList<BankAccount> accounts) {

                for (int i = 0; i < accounts.size() - 1; i++) {

                        for (int j = 0; j < accounts.size() - 1 - i; j++) {

                        if (accounts.get(j).getAccountNumber()
                                > accounts.get(j + 1).getAccountNumber()) {

                                BankAccount temp = accounts.get(j);

                                accounts.set(j, accounts.get(j + 1));

                                accounts.set(j + 1, temp);
                        }
                        }
                }
        }

        public static BankAccount binarySearch(
                ArrayList<BankAccount> accounts,
                int accountNumber) {

        int left = 0;
        int right = accounts.size() - 1;

        while (left <= right) {

                int middle = (left + right) / 2;

                int middleAccountNumber =
                        accounts.get(middle).getAccountNumber();

                if (middleAccountNumber == accountNumber) {
                return accounts.get(middle);
                }

                if (middleAccountNumber < accountNumber) {
                left = middle + 1;
                } else {
                right = middle - 1;
                }
        }

        return null;
        }

        public static void selectionSortAccounts(
                ArrayList<BankAccount> accounts){
                        
                for (int i=0;i<accounts.size()-1;i++){
                        int smallestIndex =i;

                        for(int j =i+1;j < accounts.size();j++){
                                if(accounts.get(j).getAccountNumber() < accounts.get(smallestIndex).getAccountNumber()){
                                        smallestIndex =j;
                                }
                        }
                        BankAccount temp = accounts.get(i);

                        accounts.set(i,accounts.get(smallestIndex));
                        accounts.set(smallestIndex, temp);
                }
        }

        public static int calculateSum(int n){
                if (n==0){
                        return 0;
                }

                return n +calculateSum(n-1);
        }

        public static BankAccount recursiveSearch(
                ArrayList<BankAccount> accounts, int accountNumber, int index){
                if(index >= accounts.size()){
                        return null;
                }

                if (accounts.get(index).getAccountNumber()==accountNumber) {
                        return accounts.get(index);          
                }

                return recursiveSearch(accounts, accountNumber, index+1);

        }

        public  static void  insertionSortAccounts( ArrayList<BankAccount> accounts){
                for (int i = 1; i < accounts.size(); i++) {

                        BankAccount currentAccount = accounts.get(i);

                        int j = i - 1;

                        while (j >= 0
                                && accounts.get(j).getAccountNumber()
                                > currentAccount.getAccountNumber()) {

                        accounts.set(j + 1, accounts.get(j));

                        j--;
                        }

                        accounts.set(j + 1, currentAccount);
                }
        }

        public static double calculateTotalBalance(
                ArrayList<BankAccount> accounts, int index){
                        if (index >=accounts.size()) {
                                return 0;
                        }
                        return accounts.get(index).getBalance() +
                                calculateTotalBalance(accounts, index+1);
                }

}
