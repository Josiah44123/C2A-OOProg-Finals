//Savings Account part of the Banking System

// Savings Account class
class SavingsAccount extends Account {
    private static final double INTEREST_RATE = 0.03; // 3% interest kasi gusto ko bakit ba?
    private static final double MIN_BALANCE = 500.0;

    public SavingsAccount(String clientId, double initialBalance) {
        super(clientId, initialBalance);
        this.accountId = "SAV-" + System.currentTimeMillis();
    }

    @Override
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            lastTransaction = LocalDateTime.now();
            System.out.println("Deposit successful! New balance: ₱" + balance);
        } else {
            System.out.println("Invalid amount!");
        }
    }

    @Override
    public boolean withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount!");
            return false;
        }

        if (balance - amount >= MIN_BALANCE) {
            balance -= amount;
            lastTransaction = LocalDateTime.now();
            System.out.println("Withdrawal successful! New balance: ₱" + balance);
            return true;
        } else {
            System.out.println("Insufficient balance! Minimum balance required: ₱" + MIN_BALANCE);
            return false;
        }
    }

    @Override
    public void applyInterest() {
        double interest = balance * INTEREST_RATE;
        balance += interest;
        System.out.println("Interest applied: ₱" + interest);
    }

    @Override
    public String getAccountType() {
        return "Savings";
    }
}
