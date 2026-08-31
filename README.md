# Bank Management System

A basic console-based bank management system written in Java.

## Features

- Create a bank account
- Log in using an account number
- Check account balance
- Deposit money
- Withdraw money when sufficient funds are available
- View account details

## Requirements

- Java Development Kit (JDK) 8 or later

## Run the Application

Open a terminal in this directory and compile the source files:

```bash
javac BankAccount.java Main.java
```

Run the application:

```bash
java Main
```

Follow the on-screen menu to create an account or log in.

## Project Files

- `Main.java` - Contains the application entry point and console menus.
- `BankAccount.java` - Defines the bank account data and banking operations.

## Note

Accounts are stored in memory using an `ArrayList`. All account data is lost when the application closes.