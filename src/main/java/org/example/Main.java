package org.example;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Welcome to Conygre Banking Application!");
        BankOperator operator = new BankOperator();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("Choose your desired operation:\n" +
                        "\t(a) Add Account\n" +
                        "\t(i) Display all accounts\n" +
                        "\t(s) Save to the database\n" +
                        "\t(d) Deposit the fund/amount\n" +
                        "\t(w) Withdraw the fund/amount\n" +
                        "\t(x) Delete the account\n" +
                        "\t(q) Quit");
                String userOption = scanner.nextLine();
                System.out.printf("You have selected ");
                if (userOption.equals("q") || userOption.equals("Q")) {
                    operator.saveToDatabase();
                    System.out.printf("to quit the application! See you next time, Bye :)\n");
                    break;
                }
                String accountHolderName = null;
                double balance = 0.0, depositAmount = 0.0, withdrawalAmount = 0.0;
                switch (userOption) {
                    case "a":
                    case "A":
                        System.out.printf("to add an account.\n");
                        System.out.printf("Enter the name of account holder: ");
                        accountHolderName = scanner.nextLine();
                        System.out.printf("Enter the balance: ");
                        balance = Double.parseDouble(scanner.nextLine());
                        System.out.printf("Enter the account type: ");
                        String accountType = scanner.nextLine();
                        operator.addAccount(accountHolderName, balance, accountType);
                        break;
                    case "i":
                    case "I":
                        System.out.printf("to display the accounts.\n");
                        operator.displayAccounts();
                        break;
                    case "s":
                    case "S":
                        System.out.printf("to save the local data to database.\n");
                        operator.saveToDatabase();
                        break;
                    case "d":
                    case "D":
                        System.out.printf("to deposit fund in your account.\n");
                        System.out.printf("Enter the name of account holder: ");
                        accountHolderName = scanner.nextLine();
                        System.out.printf("Enter the amount to be deposited: ");
                        depositAmount = Double.parseDouble(scanner.nextLine());
                        operator.depositToAccount(accountHolderName, depositAmount);
                        break;
                    case "w":
                    case "W":
                        System.out.printf("to withdraw fund from your account.\n");
                        System.out.printf("Enter the name of account holder: ");
                        accountHolderName = scanner.nextLine();
                        System.out.printf("Enter the amount to be withdrawn: ");
                        withdrawalAmount = Double.parseDouble(scanner.nextLine());
                        operator.withdrawFromAccount(accountHolderName, withdrawalAmount);
                        break;
                    case "x":
                    case "X":
                        System.out.printf("to delete an account.\n");
                        System.out.printf("Enter the name of account holder: ");
                        accountHolderName = scanner.nextLine();
                        operator.deleteAnAccount(accountHolderName);
                        break;
                    default:
                        System.out.printf("an invalid option, Try again!\n");
                        break;
                }
            }
            catch (Exception excp) {
                System.out.println("An exception [" + excp.getMessage() + "] occurred, Try again!");
            }

        }
    }
}