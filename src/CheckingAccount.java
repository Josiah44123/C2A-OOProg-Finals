public class CheckingAccount extends Account{
    private static final double INTEREST_RATE = 0.01;
    private static final double SERVICE_FEE = 10.0;
    private static final double OVERDRAFT_FEE = 35.0;
    private static final double OVERDRAFT_LIMIT = 100.0;

    public CheckingAccount(String clientId, double initialDeposit) {
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
}