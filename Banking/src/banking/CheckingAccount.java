package banking;

public class CheckingAccount extends Account {
    private static final double INTEREST_RATE = 0.01; // 1% annual
    private static final double SERVICE_FEE = 10.0;
    private static final double OVERDRAFT_FEE = 35.0;
    private static final double OVERDRAFT_LIMIT = 100.0;
    
    public CheckingAccount(String clientId, double initialDeposit) {
        super(clientId, initialDeposit);
    }
    
    @Override
    public boolean withdraw(double amount) {
        if (amount <= 0) {
            return false;
        }
        
        // Allow overdraft up to limit
        if (amount > getBalance() && amount <= getBalance() + OVERDRAFT_LIMIT) {
            setBalance(getBalance() - amount - OVERDRAFT_FEE);
            addTransaction("WITHDRAWAL", amount, "Withdrawal with overdraft fee");
            addTransaction("OVERDRAFT_FEE", OVERDRAFT_FEE, "Overdraft fee charged");
            return true;
        }
        
        return super.withdraw(amount);
    }
    
    @Override
    public void applyInterest() {
        if (getBalance() > 0) {
            double interest = getBalance() * INTEREST_RATE / 12;
            setBalance(getBalance() + interest);
            addTransaction("INTEREST", interest, "Monthly interest applied");
        }
    }
    
    @Override
    public void applyServiceFee() {
        setBalance(getBalance() - SERVICE_FEE);
        addTransaction("SERVICE_FEE", SERVICE_FEE, "Monthly service fee");
    }
    
    @Override
    public String getAccountType() {
        return "CHECKING";
    }
    
    @Override
    public double getInterestRate() {
        return INTEREST_RATE;
    }
    
    @Override
    public double getServiceFee() {
        return SERVICE_FEE;
    }
}