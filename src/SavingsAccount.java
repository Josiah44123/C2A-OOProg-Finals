public class SavingsAccount extends Account {
    private static final double INTEREST_RATE = 0.03; // 3% interest kasi gusto ko bakit ba?
    private static final double SERVICE_FEE = 5.0;
    private static final int MIN_BALANCE_FOR_NO_FEE = 500;
    
    public SavingsAccount(String clientId, double initialDeposit) {
        super(clientId, initialDeposit);
    }
    
    @Override
    public void applyInterest() {
        double interest = getBalance() * INTEREST_RATE / 12; // Monthly interest
        setBalance(getBalance() + interest);
        addTransaction("INTEREST", interest, "Monthly interest applied");
    }
    
    @Override
    public void applyServiceFee() {
        if (getBalance() < MIN_BALANCE_FOR_NO_FEE) {
            setBalance(getBalance() - SERVICE_FEE);
            addTransaction("SERVICE_FEE", SERVICE_FEE, "Monthly service fee");
        }
    }
    
    @Override
    public String getAccountType() {
        return "SAVINGS";
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
