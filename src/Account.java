//Abstract Account part of the Banking System

// Abstract Account class

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public abstract class Account implements Serializable {
    private static final long serialVersionUID = 1L;
    private static int accountCounter = 1000;
    
    private String accountId;
    private String clientId;
    private double balance;
    private LocalDateTime createdDate;
    private List<Transaction> transactionHistory;
    
    public Account(String clientId, double initialDeposit) {
        this.accountId = generateAccountId();
        this.clientId = clientId;
        this.balance = initialDeposit;
        this.createdDate = LocalDateTime.now();
        this.transactionHistory = new ArrayList<>();
        addTransaction("OPENING", initialDeposit, "Account opened");
    }
    
    private static synchronized String generateAccountId() {
        return "ACC" + (accountCounter++);
    }
    
    // Abstract methods 
    public abstract void applyInterest();
    public abstract void applyServiceFee();
    public abstract String getAccountType();
    public abstract double getInterestRate();
    public abstract double getServiceFee();
    
    // Encapsulated method
    public boolean deposit(double amount) {
        if (amount <= 0) {
            return false;
        }
        balance += amount;
        addTransaction("DEPOSIT", amount, "Deposit successful");
        return true;
    }
    
    public boolean withdraw(double amount) {
        if (amount <= 0 || amount > balance) {
            return false;
        }
        balance -= amount;
        addTransaction("WITHDRAWAL", amount, "Withdrawal successful");
        return true;
    }
    
    public boolean transfer(Account targetAccount, double amount) {
        if (amount <= 0 || amount > balance) {
            return false;
        }
        this.balance -= amount;
        targetAccount.balance += amount;
        
        addTransaction("TRANSFER_OUT", amount, "Transfer to " + targetAccount.getAccountId());
        targetAccount.addTransaction("TRANSFER_IN", amount, "Transfer from " + this.accountId);
        return true;
    }
    
    protected void addTransaction(String type, double amount, String description) {
        transactionHistory.add(new Transaction(type, amount, description));
    }
    
    // Getters
    public String getAccountId() { return accountId; }
    public String getClientId() { return clientId; }
    public double getBalance() { return balance; }
    public LocalDateTime getCreatedDate() { return createdDate; }
    public List<Transaction> getTransactionHistory() { return new ArrayList<>(transactionHistory); }
    
    // Setter for balance (protected - only for subclasses)
    protected void setBalance(double balance) { this.balance = balance; }
    
    @Override
    public String toString() {
        return String.format("%s [%s] - Balance: $%.2f", accountId, getAccountType(), balance);
    }
}
