//Abstract Account part of the Banking System

// Abstract Account class
abstract class Account {
    protected String accountId;
    protected String clientId;
    protected double balance;
    protected LocalDateTime createdDate;
    protected LocalDateTime lastTransaction;
    protected int accountCounter;

    public Account(String clientId, double initialBalance) {
        this.clientId = clientId;
        this.balance = initialBalance;
        this.createdDate = LocalDateTime.now();
        this.lastTransaction = LocalDateTime.now();
    }

    // Getters
    public String getAccountId() {
        return accountId;
    }

    public String getClientId() {
        return clientId;
    }

    public double getBalance() {
        return balance;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    // Abstract methods to be implemented by child classes
    public abstract void deposit(double amount);
    public abstract boolean withdraw(double amount);
    public abstract void applyInterest();
    public abstract String getAccountType();
}
