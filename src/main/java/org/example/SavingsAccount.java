package org.example;

public class SavingsAccount extends BankAccount {

    public SavingsAccount(String accountHolderName, double balance) {
        super(accountHolderName, balance, "Savings Account");
    }

    @Override
    public void deposit(double depositAmount) {
        setBankAccountCurrentBalance( getBankAccountCurrentBalance() + depositAmount);
        System.out.println("Updated balance of " + this.getBankAccountHolderName() + " [Account holder] is " + getBankAccountCurrentBalance());
    }

    @Override
    public void withdrawal(double withdrawalAmount) {
        double updatedBalance = getBankAccountCurrentBalance() - withdrawalAmount;
        if (updatedBalance >= 100) {
            setBankAccountCurrentBalance(updatedBalance);
            System.out.println("Updated balance of " + this.getBankAccountHolderName() + " [Account holder] is " + getBankAccountCurrentBalance());
        }
        else
            System.out.println("Insufficient balance!");
    }
}
