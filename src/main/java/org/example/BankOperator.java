package org.example;

import com.mongodb.client.FindIterable;
import org.bson.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class BankOperator {
        private static MongoCRUD dbHandler;
        private ArrayList<BankAccount> listOfAccounts = new ArrayList<BankAccount>();
        private int numOfAccounts = 0;

        public BankOperator() throws IOException {
                dbHandler = new MongoCRUD();
                getDataFromDB();
        }

        public void getDataFromDB() {
                FindIterable<Document> allDocs =  dbHandler.collection.find();
                for(Document d: allDocs){
                        listOfAccounts.add(new SavingsAccount((String) d.get("Account Holder"),(double) d.get("Balance")));
                        numOfAccounts++;
                }
        }

        public boolean accountExists(String accountHolderName) {
                for(BankAccount acc: listOfAccounts) {
                        if (acc.getBankAccountHolderName().equals(accountHolderName)) {
                                return true;
                        }
                }
                return false;
        }
        public void addAccount(String accountHolderName, double balance, String accountType) {
                if (!accountExists(accountHolderName)) {
                        SavingsAccount newAccount = new SavingsAccount(accountHolderName, balance);
                        listOfAccounts.add(newAccount);
                        numOfAccounts++;
                        System.out.println("Successfully created an account for " + accountHolderName + " with balance of Rs. " + balance + "/- [Account Type: " + accountType + "]");
                }
                else {
                        System.out.println("Already an account exists with username: " + accountHolderName);
                }
        }

        public void deleteAnAccount(String accHolderName) {
                System.out.println("Are you sure that you want to delete the account of " + accHolderName + " ?(yes/no)");
                Scanner sc = new Scanner(System.in);
                if (sc.nextLine().equals("no")) {
                        return;
                }
                else {
                        dbHandler.deleteDocument(accHolderName);
                }

        }

        public void displayAccounts() {
                dbHandler.displayAllDocuments();
        }

        public void saveToDatabase() {
                for(BankAccount acc: listOfAccounts) {
                        dbHandler.updateDocument(acc.getBankAccountHolderName(),acc.getBankAccountCurrentBalance(), acc.getBankAccountType());
                }
        }

        public void depositToAccount(String accountHolderName, double depositAmount) {
                for(BankAccount acc: listOfAccounts) {
                        if (acc.getBankAccountHolderName().equals(accountHolderName)) {
                                acc.deposit(depositAmount);
                                return;
                        }
                }
                System.out.println("Account with the holder name as " + accountHolderName + " does not exist!");
        }

        public void withdrawFromAccount(String accountHolderName, double withdrawalAmount) {
                for(BankAccount acc: listOfAccounts) {
                        if (acc.getBankAccountHolderName().equals(accountHolderName)) {
                                acc.withdrawal(withdrawalAmount);
                                return;
                        }
                }
                System.out.println("Account with the holder name as " + accountHolderName + " does not exist!");
        }
}
