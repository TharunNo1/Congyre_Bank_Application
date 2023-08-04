package org.example;

public abstract class BankAccount {

    private String bankAccountHolderName;
    private double bankAccountCurrentBalance;
    private String bankAccountType;

    public BankAccount(String accountHolderName, double balance, String accountType){
        this.bankAccountHolderName = accountHolderName;
        this.bankAccountCurrentBalance = balance;
        this.bankAccountType = accountType;
    }

    public String getBankAccountHolderName() {
        return bankAccountHolderName;
    }

    public String getBankAccountType() {
        return bankAccountType;
    }

    public double getBankAccountCurrentBalance() {

        return bankAccountCurrentBalance;
    }

    public void setBankAccountHolderName(String bankAccountHolderName) {
        this.bankAccountHolderName = bankAccountHolderName;
    }

    public void setBankAccountCurrentBalance(double bankAccountCurrentBalance) {
        this.bankAccountCurrentBalance = bankAccountCurrentBalance;
    }

    public void setBankAccountType(String bankAccountType) {

        this.bankAccountType = bankAccountType;
    }

    public abstract void deposit(double depositAmount);
    public abstract void withdrawal(double withdrawlAmount);

}
