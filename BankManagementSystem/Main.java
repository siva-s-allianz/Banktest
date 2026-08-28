import java.util.Scanner;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        System.out.println("================================");
        System.out.println("     BANK MANAGEMENT SYSTEM");
        System.out.println("================================");

        Scanner scanner = new Scanner(System.in);

        // Store bank accounts using ArrayList
        ArrayList<BankAccount> accounts = new ArrayList<>();

        int choice = 0;

        while (choice != 3) {

            System.out.println();
            System.out.println("========== MAIN MENU ==========");
            System.out.println("1. Create Account");
            System.out.println("2. Login");
            System.out.println("3. Exit");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

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
                    BankAccount newAccount =
                            new BankAccount(
                                    customerName,
                                    accountNumber,
                                    balance
                            );

                    // Add account to ArrayList
                    accounts.add(newAccount);

                    System.out.println(
                            "Account created successfully!"
                    );

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

                                    currentAccount.checkbalance();

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

                                    currentAccount.withdraw(
                                            withdrawalAmount
                                    );

                                    break;

                                case 4:

                                    System.out.println(
                                            "Customer Name: "
                                            + currentAccount
                                                    .getCustomerName()
                                    );

                                    System.out.println(
                                            "Account Number: "
                                            + currentAccount
                                                    .getAccountNumber()
                                    );

                                    System.out.println(
                                            "Balance: "
                                            + currentAccount
                                                    .getBalance()
                                    );

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
                // EXIT
                // =========================
                case 3:

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
}